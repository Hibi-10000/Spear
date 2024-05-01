package com.github.hibi_10000.plugins.spear;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.HashMap;

public class Mech implements Listener {
    private final Mech mech = this;
    private final Main plugin;
    public final HashMap<Entity, SpearType> spearw = new HashMap<>();

    public Mech(Main instance) {
        this.plugin = instance;
    }

    public void shotSpear(Player p, PlayerInventory inv, ItemStack is, SpearType type) {
        if (p.hasPermission("spear.use." + type.getName())) {
            int amount = is.getAmount();
            if (amount >= 2) {
                is.setAmount(amount - 1);
            } else if (amount == 1) {
                inv.clear(inv.getHeldItemSlot());
            } else {
                return;
            }
            Arrow arrow = p.launchProjectile(Arrow.class);
            mech.spearw.put(arrow, type);
        } else {
            p.sendMessage("You do not have sufficient permissions");
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.LEFT_CLICK_AIR) return;
        Player p = e.getPlayer();
        PlayerInventory inv = p.getInventory();
        ItemStack is = inv.getItemInHand();
        ItemMeta im = is.getItemMeta();
        if (im == null) return;
        NamespacedKey key = new NamespacedKey(plugin, "spear_type");
        String tag = im.getCustomTagContainer().getCustomTag(key, ItemTagType.STRING);
        SpearType type = SpearType.fromName(tag);
        if (type != null) shotSpear(p, inv, is, type);
    }

    @EventHandler
    public void onHit(ProjectileHitEvent e) {
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if (e.getEntity() instanceof Arrow) {
                SpearType type = mech.spearw.get(e.getEntity());
                if (type != null) type.getSpear().onHit(e);
            }
        }, 1L);
    }

    @EventHandler
    public void onHit2(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Arrow) {
            SpearType type = mech.spearw.get(e.getDamager());
            if (type != null) type.getSpear().onHit(e);
        }
    }
}
