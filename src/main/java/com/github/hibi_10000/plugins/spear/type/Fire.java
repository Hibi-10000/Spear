package com.github.hibi_10000.plugins.spear.type;

import com.github.hibi_10000.plugins.spear.Main;
import com.github.hibi_10000.plugins.spear.SpearType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Fire extends Spear {
    public Fire(Main instance) {
        super(instance);
    }

    @Override
    public ItemMeta addRecipe() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + " Set your enemy on fire!");
        lore.add(ChatColor.RED + " Damage: 15 + fire damage");
        return addRecipe(SpearType.FIRE, lore, recipe -> {
            recipe.shape(
                "P  ",
                " B ",
                "  B"
            );
            recipe.setIngredient('B', Material.BLAZE_ROD);
            recipe.setIngredient('P', Material.BLAZE_POWDER);
        });
    }

    @Override
    public void onHit(ProjectileHitEvent e) {
        Block block = e.getEntity().getLocation().getBlock();
        block.setType(Material.FIRE);
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if (block.getType() == Material.FIRE)
                block.setType(Material.AIR);
        }, 200L);
        super.onHit(e);
    }

    @Override
    public void onHit(EntityDamageByEntityEvent e) {
        e.getEntity().setFireTicks(200);
        e.setDamage(15);
        super.onHit(e);
    }
}
