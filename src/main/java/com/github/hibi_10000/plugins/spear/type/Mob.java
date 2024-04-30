package com.github.hibi_10000.plugins.spear.type;

import com.github.hibi_10000.plugins.spear.Main;
import com.github.hibi_10000.plugins.spear.SpearType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class Mob extends Spear {
    public Mob(Main instance) {
        super(instance);
    }

    @Override
    public ItemMeta addRecipe() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + " Welcome to the dark side.");
        lore.add(ChatColor.RED + " Damage: 20");
        return addRecipe(SpearType.MOB, lore, recipe -> {
            recipe.shape(
                "EE ",
                "EB ",
                "  B"
            );
            recipe.setIngredient('B', Material.BONE);
            recipe.setIngredient('E', Material.EGG);
        });
    }

    @Override
    public void onHit(ProjectileHitEvent e) {
        World world = e.getEntity().getWorld();
        Location loc = e.getEntity().getLocation();
        Random r = new Random();
        int next = r.nextInt(5);
        if (next == 1) {
            world.spawnEntity(loc, EntityType.ZOMBIE);
        } else if (next == 2) {
            world.spawnEntity(loc, EntityType.CREEPER);
        } else if (next == 3) {
            world.spawnEntity(loc, EntityType.SKELETON);
        } else {
            world.spawnEntity(loc, EntityType.SPIDER);
        }
        super.onHit(e);
    }

    @Override
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setDamage(20);
            Player p = (Player) e.getEntity();
            if (p.getHealth() <= 5) {
                p.setHealth(0);
                e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.ZOMBIE);
                super.onHit(e);
            }
        }
    }
}
