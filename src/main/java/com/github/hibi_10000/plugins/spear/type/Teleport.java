package com.github.hibi_10000.plugins.spear.type;

import com.github.hibi_10000.plugins.spear.Main;
import com.github.hibi_10000.plugins.spear.SpearType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Teleport extends Spear {
    public Teleport(Main instance) {
        super(instance);
    }

    @Override
    public ItemMeta addRecipe() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + " Teleport yourself or others!");
        lore.add(ChatColor.RED + " Damage: 10");
        return addRecipe(SpearType.TELEPORT, lore, recipe -> {
            recipe.shape(
                "YE ",
                "ES ",
                "  S"
            );
            recipe.setIngredient('S', Material.STICK);
            recipe.setIngredient('E', Material.ENDER_PEARL);
            recipe.setIngredient('Y', Material.ENDER_EYE);
        });
    }

    @Override
    public void onHit(ProjectileHitEvent e) {
        Player p = (Player) e.getEntity().getShooter();
        if (p == null) return;
        Location loc = e.getEntity().getLocation();
        super.onHit(e);
        p.teleport(loc);
    }
}
