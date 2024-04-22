package com.github.hibi_10000.plugins.spear;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Consumer;

public class Mech implements Listener {
    public Main plugin;

    public HashMap<Entity, String> spearw = new HashMap<>();

    public String regular_spear = "regular_spear";
    public String fire_spear = "fire_spear";
    public String explosive_spear = "explosive_spear";
    public String zeus_spear = "zeus_spear";
    public String teleport_spear = "teleport_spear";
    public String mob_spear = "mob_spear";

    public Mech(Main plugin) {
        this.plugin = plugin;
    }

    public ItemMeta addRecipeSpear(Material type, String name, ArrayList<String> lore, String key, Consumer<ShapedRecipe> recipeConsumer) {
        ItemStack stack = new ItemStack(type, 1);
        ItemMeta im = stack.getItemMeta();
        im.setDisplayName(name);
        im.setLore(lore);
        stack.setItemMeta(im);
        NamespacedKey namespacedKey = new NamespacedKey(this.plugin, key);
        ShapedRecipe recipe = new ShapedRecipe(namespacedKey, stack);
        recipeConsumer.accept(recipe);
        this.plugin.getServer().addRecipe(recipe);
        return im;
    }

    public ItemMeta addRecipeRegularSpear() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + " Simple, but deadly.");
        lore.add(ChatColor.RED + " Damage: 15");
        return addRecipeSpear(Material.STICK, "Spear", lore, this.regular_spear, recipe -> {
            recipe.shape(
                "F  ",
                " S ",
                "  S"
            );
            recipe.setIngredient('S', Material.STICK);
            recipe.setIngredient('F', Material.FLINT);
        });
    }

    public ItemMeta addRecipeFireSpear() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + " Set your enemy on fire!");
        lore.add(ChatColor.RED + " Damage: 15 + fire damage");
        return addRecipeSpear(Material.BLAZE_ROD, "Fire Spear", lore, this.fire_spear, recipe -> {
            recipe.shape(
                "P  ",
                " B ",
                "  B"
            );
            recipe.setIngredient('B', Material.BLAZE_ROD);
            recipe.setIngredient('P', Material.BLAZE_POWDER);
        });
    }

    public ItemMeta addRecipeExplosiveSpear() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + " Create a deadly explosion!");
        lore.add(ChatColor.RED + " Damage: 15 + explosion damage");
        return addRecipeSpear(Material.STICK, "Explosive Spear", lore, this.explosive_spear, recipe -> {
            recipe.shape(
                "U  ",
                " S ",
                "  S"
            );
            recipe.setIngredient('S', Material.STICK);
            recipe.setIngredient('U', Material.GUNPOWDER);
        });
    }

    public ItemMeta addRecipeZeusSpear() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + " Harness the power of the skies!");
        lore.add(ChatColor.RED + " Damage: 30 + explosion damage");
        return addRecipeSpear(Material.BLAZE_ROD, "Zeus Spear", lore, this.zeus_spear, recipe -> {
            recipe.shape(
                "DD ",
                "DB ",
                "  B"
            );
            recipe.setIngredient('B', Material.BLAZE_ROD);
            recipe.setIngredient('D', Material.DIAMOND);
        });
    }

    public ItemMeta addRecipeTeleportSpear() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + " Teleport yourself or others!");
        lore.add(ChatColor.RED + " Damage: 10");
        return addRecipeSpear(Material.STICK, "Teleport Spear", lore, this.teleport_spear, recipe -> {
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

    public ItemMeta addRecipeMobSpear() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + " Welcome to the dark side.");
        lore.add(ChatColor.RED + " Damage: 20");
        return addRecipeSpear(Material.BONE, "Mob Spear", lore, this.mob_spear, recipe -> {
            recipe.shape(
                "EE ",
                "EB ",
                "  B"
            );
            recipe.setIngredient('B', Material.BONE);
            recipe.setIngredient('E', Material.EGG);
        });
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.LEFT_CLICK_AIR) {
            if (p.getItemInHand().getItemMeta() != null) {
                if (p.getItemInHand().getItemMeta().getDisplayName() == "Spear") {
                    if (p.hasPermission("spear.use.spear")) {
                        if (p.getItemInHand().getAmount() >= 2) {
                            p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
                            Arrow arrow = p.launchProjectile(Arrow.class);
                            this.spearw.put(arrow, this.regular_spear);
                        } else if (p.getItemInHand().getAmount() == 1) {
                            p.getInventory().clear(p.getInventory().getHeldItemSlot());
                            Arrow arrow = p.launchProjectile(Arrow.class);
                            this.spearw.put(arrow, this.regular_spear);
                        }
                    } else {
                        p.sendMessage("You do not have sufficient permissions");
                    }
                } else if (p.getItemInHand().getItemMeta().getDisplayName() == "Fire Spear") {
                    if (p.hasPermission("spear.use.fire_spear")) {
                        if (p.getItemInHand().getAmount() >= 2) {
                            p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
                            Arrow arrow = p.launchProjectile(Arrow.class);
                            this.spearw.put(arrow, this.fire_spear);
                        } else if (p.getItemInHand().getAmount() == 1) {
                            p.getInventory().clear(p.getInventory().getHeldItemSlot());
                            Arrow arrow = p.launchProjectile(Arrow.class);
                            this.spearw.put(arrow, this.fire_spear);
                        }
                    } else {
                        p.sendMessage("You do not have sufficient permissions");
                    }
                } else if (p.getItemInHand().getItemMeta().getDisplayName() == "Explosive Spear") {
                    if (p.hasPermission("spear.use.explosive_spear")) {
                        if (p.getItemInHand().getAmount() >= 2) {
                            p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
                            Arrow arrow = p.launchProjectile(Arrow.class);
                            this.spearw.put(arrow, this.explosive_spear);
                        } else if (p.getItemInHand().getAmount() == 1) {
                            p.getInventory().clear(p.getInventory().getHeldItemSlot());
                            Arrow arrow = p.launchProjectile(Arrow.class);
                            this.spearw.put(arrow, this.explosive_spear);
                        }
                    } else {
                        p.sendMessage("You do not have sufficient permissions");
                    }
                } else if (p.getItemInHand().getItemMeta().getDisplayName() == "Zeus Spear") {
                    if (p.hasPermission("spear.use.zeus_spear")) {
                        if (p.getItemInHand().getAmount() >= 2) {
                            p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
                            Arrow arrow = p.launchProjectile(Arrow.class);
                            this.spearw.put(arrow, this.zeus_spear);
                        } else if (p.getItemInHand().getAmount() == 1) {
                            p.getInventory().clear(p.getInventory().getHeldItemSlot());
                            Arrow arrow = p.launchProjectile(Arrow.class);
                            this.spearw.put(arrow, this.zeus_spear);
                        }
                    } else {
                        p.sendMessage("You do not have sufficient permissions");
                    }
                } else if (p.getItemInHand().getItemMeta().getDisplayName() == "Teleport Spear") {
                    if (p.hasPermission("spear.use.teleport_spear")) {
                        if (p.getItemInHand().getAmount() >= 2) {
                            p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
                            Arrow arrow = p.launchProjectile(Arrow.class);
                            this.spearw.put(arrow, this.teleport_spear);
                        } else if (p.getItemInHand().getAmount() == 1) {
                            p.getInventory().clear(p.getInventory().getHeldItemSlot());
                            Arrow arrow = p.launchProjectile(Arrow.class);
                            this.spearw.put(arrow, this.teleport_spear);
                        }
                    } else {
                        p.sendMessage("You do not have sufficient permissions");
                    }
                } else if (p.getItemInHand().getItemMeta().getDisplayName() == "Mob Spear") {
                    if (p.hasPermission("spear.use.mob_spear")) {
                        if (p.getItemInHand().getAmount() >= 2) {
                            p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
                            Arrow arrow = p.launchProjectile(Arrow.class);
                            this.spearw.put(arrow, this.mob_spear);
                        } else if (p.getItemInHand().getAmount() == 1) {
                            p.getInventory().clear(p.getInventory().getHeldItemSlot());
                            Arrow arrow = p.launchProjectile(Arrow.class);
                            this.spearw.put(arrow, this.mob_spear);
                        }
                    } else {
                        p.sendMessage("You do not have sufficient permissions");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onHit(final ProjectileHitEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
            if (e.getEntity() instanceof Arrow) {
                if (Mech.this.spearw.containsKey(e.getEntity())) {
                    if (Mech.this.spearw.containsValue(Mech.this.regular_spear)) {
                        Mech.this.spearw.remove(e.getEntity());
                        e.getEntity().remove();
                    } else if (Mech.this.spearw.containsValue(Mech.this.fire_spear)) {
                        final Block block = e.getEntity().getLocation().getBlock();
                        block.setType(Material.FIRE);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Mech.this.plugin, () -> {
                            if (block.getType() == Material.FIRE)
                                block.setType(Material.AIR);
                        }, 200L);
                        Mech.this.spearw.remove(e.getEntity());
                        e.getEntity().remove();
                    } else if (Mech.this.spearw.containsValue(Mech.this.explosive_spear)) {
                        e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 1.0F);
                        Mech.this.spearw.remove(e.getEntity());
                        e.getEntity().remove();
                    } else if (Mech.this.spearw.containsValue(Mech.this.zeus_spear)) {
                        e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
                        Mech.this.spearw.remove(e.getEntity());
                        e.getEntity().remove();
                    } else if (Mech.this.spearw.containsValue(Mech.this.teleport_spear)) {
                        Player p = (Player) e.getEntity().getShooter();
                        Location loc = e.getEntity().getLocation();
                        Mech.this.spearw.remove(e.getEntity());
                        e.getEntity().remove();
                        p.teleport(loc);
                    } else if (Mech.this.spearw.containsValue(Mech.this.mob_spear)) {
                        World world = e.getEntity().getWorld();
                        Location loc = e.getEntity().getLocation();
                        Random r = new Random();
                        int next = r.nextInt(5);
                        if (next == 1) {
                            world.spawnEntity(loc, EntityType.ZOMBIE);
                            Mech.this.spearw.remove(e.getEntity());
                            e.getEntity().remove();
                        } else if (next == 2) {
                            world.spawnEntity(loc, EntityType.CREEPER);
                            Mech.this.spearw.remove(e.getEntity());
                            e.getEntity().remove();
                        } else if (next == 3) {
                            world.spawnEntity(loc, EntityType.SKELETON);
                            Mech.this.spearw.remove(e.getEntity());
                            e.getEntity().remove();
                        } else {
                            world.spawnEntity(loc, EntityType.SPIDER);
                            Mech.this.spearw.remove(e.getEntity());
                            e.getEntity().remove();
                        }
                    }
                }
            }
        }, 1L);
    }

    @EventHandler
    public void onHit2(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Arrow) {
            if (this.spearw.containsKey(e.getDamager())) {
                if (this.spearw.containsValue(this.regular_spear)) {
                    e.setDamage(15);
                    this.spearw.remove(e.getDamager());
                } else if (this.spearw.containsValue(this.fire_spear)) {
                    e.getEntity().setFireTicks(200);
                    e.setDamage(15);
                    this.spearw.remove(e.getDamager());
                } else if (this.spearw.containsValue(this.explosive_spear)) {
                    e.setDamage(15);
                    e.getDamager().getWorld().createExplosion(e.getEntity().getLocation(), 2.0F);
                    this.spearw.remove(e.getDamager());
                } else if (this.spearw.containsValue(this.zeus_spear)) {
                    e.setDamage(30);
                    e.getDamager().getWorld().strikeLightning(e.getDamager().getLocation());
                    e.getDamager().getWorld().createExplosion(e.getDamager().getLocation(), 3.0F);
                    this.spearw.remove(e.getDamager());
                } else if (this.spearw.containsValue(this.teleport_spear)) {
                    Player p1 = (Player) ((Arrow) e.getDamager()).getShooter();
                    Entity p2 = e.getEntity();
                    Location p2loc = p2.getLocation();
                    Location p1loc = p1.getLocation();
                    if (p2 instanceof Player)
                        if (p1 == p2) {
                            p1.sendMessage("Teleportation failure!");
                            e.setDamage(5);
                        } else {
                            e.setDamage(10);
                            p1.getWorld().playEffect(p1loc, Effect.SMOKE, 10);
                            p2.getWorld().playEffect(p2loc, Effect.SMOKE, 10);
                            p1.teleport(p2loc);
                            p2.teleport(p1loc);
                            this.spearw.remove(e.getDamager());
                        }
                } else if (this.spearw.containsValue(this.mob_spear)) {
                    if (e.getEntity() instanceof Player) {
                        e.setDamage(20);
                        Player p = (Player) e.getEntity();
                        if (p.getHealth() <= 5) {
                            p.setHealth(0);
                            e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.ZOMBIE);
                            this.spearw.remove(e.getDamager());
                        }
                    }
                }
            }
        }
    }
}
