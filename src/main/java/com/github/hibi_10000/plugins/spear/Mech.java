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
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.HashMap;
import java.util.Random;

public class Mech implements Listener {
    public final Main plugin;

    public final HashMap<Entity, SpearType> spearw = new HashMap<>();

    public Mech(Main plugin) {
        this.plugin = plugin;
    }

    public void shotSpear(Player p, PlayerInventory inv, ItemStack is, SpearType type) {
        if (p.hasPermission("spear.use." + type.getName())) {
            int amount = is.getAmount();
            if (amount >= 2) {
                is.setAmount(amount - 1);
            } else if (amount == 1) {
                inv.clear(inv.getHeldItemSlot());
            } else {
                return;
            }
            Arrow arrow = p.launchProjectile(Arrow.class);
            this.spearw.put(arrow, type);
        } else {
            p.sendMessage("You do not have sufficient permissions");
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.LEFT_CLICK_AIR) return;
        Player p = e.getPlayer();
        PlayerInventory inv = p.getInventory();
        ItemStack is = p.getInventory().getItemInHand();
        ItemMeta im = is.getItemMeta();
        if (im == null) return;
        NamespacedKey key = new NamespacedKey(this.plugin, "spear_type");
        String tag = im.getCustomTagContainer().getCustomTag(key, ItemTagType.STRING);
        SpearType type = SpearType.fromName(tag);
        if (type != null) shotSpear(p, inv, is, type);
    }

    @EventHandler
    public void onHit(final ProjectileHitEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
            if (e.getEntity() instanceof Arrow) {
                SpearType type = this.spearw.get(e.getEntity());
                if (type != null) {
                    if (type == SpearType.REGULAR) {
                        this.spearw.remove(e.getEntity());
                        e.getEntity().remove();
                    } else if (type == SpearType.FIRE) {
                        final Block block = e.getEntity().getLocation().getBlock();
                        block.setType(Material.FIRE);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
                            if (block.getType() == Material.FIRE)
                                block.setType(Material.AIR);
                        }, 200L);
                        this.spearw.remove(e.getEntity());
                        e.getEntity().remove();
                    } else if (type == SpearType.EXPLOSIVE) {
                        e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 1.0F);
                        this.spearw.remove(e.getEntity());
                        e.getEntity().remove();
                    } else if (type == SpearType.ZEUS) {
                        e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
                        this.spearw.remove(e.getEntity());
                        e.getEntity().remove();
                    } else if (type == SpearType.TELEPORT) {
                        Player p = (Player) e.getEntity().getShooter();
                        Location loc = e.getEntity().getLocation();
                        this.spearw.remove(e.getEntity());
                        e.getEntity().remove();
                        p.teleport(loc);
                    } else if (type == SpearType.MOB) {
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
                        this.spearw.remove(e.getEntity());
                        e.getEntity().remove();
                    }
                }
            }
        }, 1L);
    }

    @EventHandler
    public void onHit2(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Arrow) {
            SpearType type = this.spearw.get(e.getDamager());
            if (type != null) {
                if (type == SpearType.REGULAR) {
                    e.setDamage(15);
                    this.spearw.remove(e.getDamager());
                } else if (type == SpearType.FIRE) {
                    e.getEntity().setFireTicks(200);
                    e.setDamage(15);
                    this.spearw.remove(e.getDamager());
                } else if (type == SpearType.EXPLOSIVE) {
                    e.setDamage(15);
                    e.getDamager().getWorld().createExplosion(e.getEntity().getLocation(), 2.0F);
                    this.spearw.remove(e.getDamager());
                } else if (type == SpearType.ZEUS) {
                    e.setDamage(30);
                    e.getDamager().getWorld().strikeLightning(e.getDamager().getLocation());
                    e.getDamager().getWorld().createExplosion(e.getDamager().getLocation(), 3.0F);
                    this.spearw.remove(e.getDamager());
                } else if (type == SpearType.TELEPORT) {
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
                } else if (type == SpearType.MOB) {
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
