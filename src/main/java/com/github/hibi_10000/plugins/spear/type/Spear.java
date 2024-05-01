package com.github.hibi_10000.plugins.spear.type;

import com.github.hibi_10000.plugins.spear.Main;
import com.github.hibi_10000.plugins.spear.SpearType;
import org.bukkit.NamespacedKey;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class Spear {
    protected final Main plugin;
    protected Spear(Main instance) {
        this.plugin = instance;
    }

    protected final ItemMeta addRecipe(SpearType spear, ArrayList<String> lore, Consumer<ShapedRecipe> recipeConsumer) {
        ItemStack stack = new ItemStack(spear.getMaterial(), 1);
        ItemMeta im = stack.getItemMeta();
        im.setDisplayName(spear.getDisplayName());
        im.setLore(lore);
        im.getCustomTagContainer().setCustomTag(new NamespacedKey(this.plugin, "spear_type"), ItemTagType.STRING, spear.getName());
        stack.setItemMeta(im);
        NamespacedKey namespacedKey = new NamespacedKey(this.plugin, spear.getName());
        ShapedRecipe recipe = new ShapedRecipe(namespacedKey, stack);
        recipeConsumer.accept(recipe);
        this.plugin.getServer().addRecipe(recipe);
        return im;
    }

    public abstract ItemMeta addRecipe();

    public void onHit(ProjectileHitEvent e) {
        plugin.mech.spearw.remove(e.getEntity());
        e.getEntity().remove();
    }

    public void onHit(EntityDamageByEntityEvent e) {
        plugin.mech.spearw.remove(e.getDamager());
    }
}
