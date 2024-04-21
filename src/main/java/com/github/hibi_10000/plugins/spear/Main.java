package com.github.hibi_10000.plugins.spear;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public Main plugin;

    @Override
    public void onEnable() {
        this.plugin = this;
        Mech M = new Mech(this.plugin);
        getServer().getPluginManager().registerEvents(M, this);
        M.addRecipeRegularSpear();
        M.addRecipeFireSpear();
        M.addRecipeExplosiveSpear();
        M.addRecipeZeusSpear();
        M.addRecipeTeleportSpear();
        M.addRecipeMobSpear();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("givespear")) {
            Mech M = new Mech(this.plugin);
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("spear.give"))
                    if (args.length == 0) {
                        p.sendMessage(ChatColor.RED + "Too few arguments!" + ChatColor.WHITE + " Do this: /givespear (playername) {spearname} [quantity]");
                    } else if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("options")) {
                            p.sendMessage(ChatColor.GREEN + "Spear Types: " + ChatColor.WHITE + "spear, fire_spear, explosive_spear, zeus_spear, teleport_spear, mob_spear.");
                        } else {
                            p.sendMessage(ChatColor.RED + "Too few arguments!" + ChatColor.WHITE + " Do this: /givespear (playername) {spearname} [quantity]");
                        }
                    } else if (args.length == 2) {
                        Player tp = p.getServer().getPlayer(args[0]);
                        if (tp != null) {
                            if (args[1].equalsIgnoreCase("spear")) {
                                ItemMeta im = M.addRecipeRegularSpear();
                                ItemStack is = new ItemStack(Material.STICK, 1);
                                is.setItemMeta(im);
                                tp.getInventory().addItem(is);
                            } else if (args[1].equalsIgnoreCase("fire_spear")) {
                                ItemMeta im = M.addRecipeFireSpear();
                                ItemStack is = new ItemStack(Material.BLAZE_ROD, 1);
                                is.setItemMeta(im);
                                tp.getInventory().addItem(is);
                            } else if (args[1].equalsIgnoreCase("explosive_spear")) {
                                ItemMeta im = M.addRecipeExplosiveSpear();
                                ItemStack is = new ItemStack(Material.STICK, 1);
                                is.setItemMeta(im);
                                tp.getInventory().addItem(is);
                            } else if (args[1].equalsIgnoreCase("zeus_spear")) {
                                ItemMeta im = M.addRecipeZeusSpear();
                                ItemStack is = new ItemStack(Material.BLAZE_ROD, 1);
                                is.setItemMeta(im);
                                tp.getInventory().addItem(is);
                            } else if (args[1].equalsIgnoreCase("teleport_spear")) {
                                ItemMeta im = M.addRecipeTeleportSpear();
                                ItemStack is = new ItemStack(Material.STICK, 1);
                                is.setItemMeta(im);
                                tp.getInventory().addItem(is);
                            } else if (args[1].equalsIgnoreCase("mob_spear")) {
                                ItemMeta im = M.addRecipeMobSpear();
                                ItemStack is = new ItemStack(Material.BONE, 1);
                                is.setItemMeta(im);
                                tp.getInventory().addItem(is);
                            } else {
                                p.sendMessage(ChatColor.RED + args[1] + ChatColor.WHITE + " is not a correct spear type!");
                            }
                        } else {
                            p.sendMessage("Player not found!");
                        }
                    } else if (args.length == 3) {
                        Player tp = p.getServer().getPlayer(args[0]);
                        if (tp != null)
                            try {
                                int arg3 = Integer.parseInt(args[2]);
                                if (args[1].equalsIgnoreCase("spear")) {
                                    ItemMeta im = M.addRecipeRegularSpear();
                                    ItemStack is = new ItemStack(Material.STICK, arg3);
                                    is.setItemMeta(im);
                                    tp.getInventory().addItem(is);
                                } else if (args[1].equalsIgnoreCase("fire_spear")) {
                                    ItemMeta im = M.addRecipeFireSpear();
                                    ItemStack is = new ItemStack(Material.BLAZE_ROD, arg3);
                                    is.setItemMeta(im);
                                    tp.getInventory().addItem(is);
                                } else if (args[1].equalsIgnoreCase("explosive_spear")) {
                                    ItemMeta im = M.addRecipeExplosiveSpear();
                                    ItemStack is = new ItemStack(Material.STICK, arg3);
                                    is.setItemMeta(im);
                                    tp.getInventory().addItem(is);
                                } else if (args[1].equalsIgnoreCase("zeus_spear")) {
                                    ItemMeta im = M.addRecipeZeusSpear();
                                    ItemStack is = new ItemStack(Material.BLAZE_ROD, arg3);
                                    is.setItemMeta(im);
                                    tp.getInventory().addItem(is);
                                } else if (args[1].equalsIgnoreCase("teleport_spear")) {
                                    ItemMeta im = M.addRecipeTeleportSpear();
                                    ItemStack is = new ItemStack(Material.STICK, arg3);
                                    is.setItemMeta(im);
                                    tp.getInventory().addItem(is);
                                } else if (args[1].equalsIgnoreCase("mob_spear")) {
                                    ItemMeta im = M.addRecipeMobSpear();
                                    ItemStack is = new ItemStack(Material.BONE, arg3);
                                    is.setItemMeta(im);
                                    tp.getInventory().addItem(is);
                                } else {
                                    p.sendMessage(ChatColor.RED + args[1] + ChatColor.WHITE + " is not a correct spear type!");
                                }
                            } catch (NumberFormatException ex) {
                                p.sendMessage(ChatColor.RED + args[2] + ChatColor.WHITE + " should be an int!");
                            }
                    } else if (args.length > 3) {
                        p.sendMessage(ChatColor.RED + "Too many arguments!" + ChatColor.WHITE + " Do this: /givespear (playername) {spearname} [quantity]");
                    }
            } else {
                CommandSender s = sender;
                if (args.length == 0) {
                    s.sendMessage(ChatColor.RED + "Too few arguments! Do this: /givespear (playername) {spearname} [quantity]");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("options")) {
                        s.sendMessage(ChatColor.GREEN + "Spear Types: " + ChatColor.WHITE + "spear, fire_spear, explosive_spear, zeus_spear, teleport_spear, mob_spear.");
                    } else {
                        s.sendMessage(ChatColor.RED + "Too few arguments!" + ChatColor.WHITE + " Do this: /givespear (playername) {spearname} [quantity]");
                    }
                } else if (args.length == 2) {
                    Player tp = s.getServer().getPlayer(args[0]);
                    if (tp != null) {
                        if (args[1].equalsIgnoreCase("spear")) {
                            ItemMeta im = M.addRecipeRegularSpear();
                            ItemStack is = new ItemStack(Material.STICK, 1);
                            is.setItemMeta(im);
                            tp.getInventory().addItem(is);
                        } else if (args[1].equalsIgnoreCase("fire_spear")) {
                            ItemMeta im = M.addRecipeFireSpear();
                            ItemStack is = new ItemStack(Material.BLAZE_ROD, 1);
                            is.setItemMeta(im);
                            tp.getInventory().addItem(is);
                        } else if (args[1].equalsIgnoreCase("explosive_spear")) {
                            ItemMeta im = M.addRecipeExplosiveSpear();
                            ItemStack is = new ItemStack(Material.STICK, 1);
                            is.setItemMeta(im);
                            tp.getInventory().addItem(is);
                        } else if (args[1].equalsIgnoreCase("zeus_spear")) {
                            ItemMeta im = M.addRecipeZeusSpear();
                            ItemStack is = new ItemStack(Material.BLAZE_ROD, 1);
                            is.setItemMeta(im);
                            tp.getInventory().addItem(is);
                        } else if (args[1].equalsIgnoreCase("teleport_spear")) {
                            ItemMeta im = M.addRecipeTeleportSpear();
                            ItemStack is = new ItemStack(Material.STICK, 1);
                            is.setItemMeta(im);
                            tp.getInventory().addItem(is);
                        } else if (args[1].equalsIgnoreCase("mob_spear")) {
                            ItemMeta im = M.addRecipeMobSpear();
                            ItemStack is = new ItemStack(Material.BONE, 1);
                            is.setItemMeta(im);
                            tp.getInventory().addItem(is);
                        } else {
                            s.sendMessage(ChatColor.RED + args[1] + " is not a correct spear type!");
                        }
                    } else {
                        s.sendMessage("Player not found!");
                    }
                } else if (args.length == 3) {
                    Player tp = s.getServer().getPlayer(args[0]);
                    if (tp != null)
                        try {
                            int arg3 = Integer.parseInt(args[2]);
                            if (args[1].equalsIgnoreCase("spear")) {
                                ItemMeta im = M.addRecipeRegularSpear();
                                ItemStack is = new ItemStack(Material.STICK, arg3);
                                is.setItemMeta(im);
                                tp.getInventory().addItem(is);
                            } else if (args[1].equalsIgnoreCase("fire_spear")) {
                                ItemMeta im = M.addRecipeFireSpear();
                                ItemStack is = new ItemStack(Material.BLAZE_ROD, arg3);
                                is.setItemMeta(im);
                                tp.getInventory().addItem(is);
                            } else if (args[1].equalsIgnoreCase("explosive_spear")) {
                                ItemMeta im = M.addRecipeExplosiveSpear();
                                ItemStack is = new ItemStack(Material.STICK, arg3);
                                is.setItemMeta(im);
                                tp.getInventory().addItem(is);
                            } else if (args[1].equalsIgnoreCase("zeus_spear")) {
                                ItemMeta im = M.addRecipeZeusSpear();
                                ItemStack is = new ItemStack(Material.BLAZE_ROD, arg3);
                                is.setItemMeta(im);
                                tp.getInventory().addItem(is);
                            } else if (args[1].equalsIgnoreCase("teleport_spear")) {
                                ItemMeta im = M.addRecipeTeleportSpear();
                                ItemStack is = new ItemStack(Material.STICK, arg3);
                                is.setItemMeta(im);
                                tp.getInventory().addItem(is);
                            } else if (args[1].equalsIgnoreCase("mob_spear")) {
                                ItemMeta im = M.addRecipeMobSpear();
                                ItemStack is = new ItemStack(Material.BONE, arg3);
                                is.setItemMeta(im);
                                tp.getInventory().addItem(is);
                            } else {
                                s.sendMessage(ChatColor.RED + args[1] + ChatColor.WHITE + " is not a correct spear type!");
                            }
                        } catch (NumberFormatException ex) {
                            s.sendMessage(ChatColor.RED + args[2] + " should be an int!");
                        }
                } else if (args.length > 3) {
                    s.sendMessage(ChatColor.RED + "Too many arguments! Do this: /givespear (playername) {spearname} [quantity]");
                }
            }
        }
        return false;
    }
}
