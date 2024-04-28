package com.github.hibi_10000.plugins.spear.type;

import com.github.hibi_10000.plugins.spear.Main;
import com.github.hibi_10000.plugins.spear.SpearType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Zeus extends Spear {
    public Zeus(Main instance) {
        super(instance);
    }

    @Override
    public ItemMeta addRecipe() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + " Harness the power of the skies!");
        lore.add(ChatColor.RED + " Damage: 30 + explosion damage");
        return addRecipe(SpearType.ZEUS, lore, recipe -> {
            recipe.shape(
                "DD ",
                "DB ",
                "  B"
            );
            recipe.setIngredient('B', Material.BLAZE_ROD);
            recipe.setIngredient('D', Material.DIAMOND);
        });
    }
}
