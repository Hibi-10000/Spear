package com.github.hibi_10000.plugins.spear.type;

import com.github.hibi_10000.plugins.spear.Main;
import com.github.hibi_10000.plugins.spear.SpearType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

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
}
