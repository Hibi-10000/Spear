package com.github.hibi_10000.plugins.spear.type;

import com.github.hibi_10000.plugins.spear.Main;
import com.github.hibi_10000.plugins.spear.SpearType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Regular extends Spear {
    public Regular(Main instance) {
        super(instance);
    }

    @Override
    public ItemMeta addRecipe() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + " Simple, but deadly.");
        lore.add(ChatColor.RED + " Damage: 15");
        return addRecipe(SpearType.REGULAR, lore, recipe -> {
            recipe.shape(
                "F  ",
                " S ",
                "  S"
            );
            recipe.setIngredient('S', Material.STICK);
            recipe.setIngredient('F', Material.FLINT);
        });
    }

    @Override
    public void onHit(EntityDamageByEntityEvent e) {
        e.setDamage(15);
        super.onHit(e);
    }
}
