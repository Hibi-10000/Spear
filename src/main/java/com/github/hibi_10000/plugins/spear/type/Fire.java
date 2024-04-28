package com.github.hibi_10000.plugins.spear.type;

import com.github.hibi_10000.plugins.spear.Main;
import com.github.hibi_10000.plugins.spear.SpearType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
}
