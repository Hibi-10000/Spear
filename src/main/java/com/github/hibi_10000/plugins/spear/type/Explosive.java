package com.github.hibi_10000.plugins.spear.type;

import com.github.hibi_10000.plugins.spear.Main;
import com.github.hibi_10000.plugins.spear.SpearType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Explosive extends Spear {
    public Explosive(Main instance) {
        super(instance);
    }

    @Override
    public ItemMeta addRecipe() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + " Create a deadly explosion!");
        lore.add(ChatColor.RED + " Damage: 15 + explosion damage");
        return addRecipe(SpearType.EXPLOSIVE, lore, recipe -> {
            recipe.shape(
                "U  ",
                " S ",
                "  S"
            );
            recipe.setIngredient('S', Material.STICK);
            recipe.setIngredient('U', Material.GUNPOWDER);
        });
    }

    @Override
    public void onHit(ProjectileHitEvent e) {
        e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 1.0F);
        super.onHit(e);
    }
}
