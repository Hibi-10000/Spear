package com.github.hibi_10000.plugins.spear;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Spear extends JavaPlugin {
	//public final Logger logger = Logger.getLogger("minecraft");

	//public String reg_spear;

	//public HashMap<Entity, String> spearw;

	public void onDisable() {}

	public void onEnable() {
		Mech M = new Mech(this);
		getServer().getPluginManager().registerEvents(M, this);
		M.addRecipeSpear();
		M.addRecipeFireSpear();
		M.addRecipeGunSpear();
		M.addRecipeZeusSpear();
		M.addRecipeTeleSpear();
		M.addRecipeSpawnSpear();
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("givespear")) {
			Mech M = new Mech(this);
			if (sender instanceof Player p) {
				int arg3;
				if (p.hasPermission("spear.give")) {
					if (args.length == 0) {
						p.sendMessage(ChatColor.RED + "Too few arguments!" + ChatColor.WHITE + " Do this: /givespear (playername) {spearname} [quantity]");
					}
					else if (args.length == 1) {
						if (args[0].equalsIgnoreCase("options")) {
							p.sendMessage(ChatColor.GREEN + "Spear Types: " + ChatColor.WHITE + "spear, firespear, explosivespear, zuesspear, telespear, mobspear.");
						} else {
							p.sendMessage(ChatColor.RED + "Too few arguments!" + ChatColor.WHITE + " Do this: /givespear (playername) {spearname} [quantity]");
						}

					} else if (args.length == 2) {
						Player tp = p.getServer().getPlayer(args[0]);
						if (tp != null) {
							if (args[1].equalsIgnoreCase("spear")) {
								ItemMeta im = M.addRecipeSpear();
								ItemStack is = new ItemStack(Material.STICK, 1);
								is.setItemMeta(im);
								tp.getInventory().addItem(is);
							}
							else if (args[1].equalsIgnoreCase("firespear")) {
								ItemMeta im = M.addRecipeFireSpear();
								ItemStack is = new ItemStack(Material.BLAZE_ROD, 1);
								is.setItemMeta(im);
								tp.getInventory().addItem(is);
							}
							else if (args[1].equalsIgnoreCase("explosivespear")) {
								ItemMeta im = M.addRecipeGunSpear();
								ItemStack is = new ItemStack(Material.STICK, 1);
								is.setItemMeta(im);
								tp.getInventory().addItem(is);
							}
							else if (args[1].equalsIgnoreCase("zuesspear")) {
								ItemMeta im = M.addRecipeZeusSpear();
								ItemStack is = new ItemStack(Material.BLAZE_ROD, 1);
								is.setItemMeta(im);
								tp.getInventory().addItem(is);
							}
							else if (args[1].equalsIgnoreCase("telespear")) {
								ItemMeta im = M.addRecipeTeleSpear();
								ItemStack is = new ItemStack(Material.STICK, 1);
								is.setItemMeta(im);
								tp.getInventory().addItem(is);
							}
							else if (args[1].equalsIgnoreCase("mobspear")) {
								ItemMeta im = M.addRecipeSpawnSpear();
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
						if (tp != null) {
							try {
								arg3 = Integer.parseInt(args[2]);
								if (args[1].equalsIgnoreCase("spear")) {
									ItemMeta im = M.addRecipeSpear();
									ItemStack is = new ItemStack(Material.STICK, arg3);
									is.setItemMeta(im);
									tp.getInventory().addItem(is);
								}
								else if (args[1].equalsIgnoreCase("firespear")) {
									ItemMeta im = M.addRecipeFireSpear();
									ItemStack is = new ItemStack(Material.BLAZE_ROD, arg3);
									is.setItemMeta(im);
									tp.getInventory().addItem(is);
								}
								else if (args[1].equalsIgnoreCase("explosivespear")) {
									ItemMeta im = M.addRecipeGunSpear();
									ItemStack is = new ItemStack(Material.STICK, arg3);
									is.setItemMeta(im);
									tp.getInventory().addItem(is);
								}
								else if (args[1].equalsIgnoreCase("zuesspear")) {
									ItemMeta im = M.addRecipeZeusSpear();
									ItemStack is = new ItemStack(Material.BLAZE_ROD, arg3);
									is.setItemMeta(im);
									tp.getInventory().addItem(is);
								}
								else if (args[1].equalsIgnoreCase("telespear")) {
									ItemMeta im = M.addRecipeTeleSpear();
									ItemStack is = new ItemStack(Material.STICK, arg3);
									is.setItemMeta(im);
									tp.getInventory().addItem(is);
								}
								else if (args[1].equalsIgnoreCase("mobspear")) {
									ItemMeta im = M.addRecipeSpawnSpear();
									ItemStack is = new ItemStack(Material.BONE, arg3);
									is.setItemMeta(im);
									tp.getInventory().addItem(is);
								} else {

									p.sendMessage(ChatColor.RED + args[1] + ChatColor.WHITE + " is not a correct spear type!");
								}

							} catch (NumberFormatException ex) {
								p.sendMessage(ChatColor.RED + args[2] + ChatColor.WHITE + " should be an int!");
							}

						}
					} else {
						p.sendMessage(ChatColor.RED + "Too many arguments!" + ChatColor.WHITE + " Do this: /givespear (playername) {spearname} [quantity]");
					}
				}
			} else {

				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Too few arguments! Do this: /givespear (playername) {spearname} [quantity]");
				}
				else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("options")) {
						sender.sendMessage(ChatColor.GREEN + "Spear Types: " + ChatColor.WHITE + "spear, firespear, explosivespear, zuesspear, telespear, mobspear.");
					} else {

						sender.sendMessage(ChatColor.RED + "Too few arguments!" + ChatColor.WHITE + " Do this: /givespear (playername) {spearname} [quantity]");
					}

				} else if (args.length == 2) {
					Player tp = sender.getServer().getPlayer(args[0]);
					if (tp != null) {
						if (args[1].equalsIgnoreCase("spear")) {
							ItemMeta im = M.addRecipeSpear();
							ItemStack is = new ItemStack(Material.STICK, 1);
							is.setItemMeta(im);
							tp.getInventory().addItem(is);
						}
						else if (args[1].equalsIgnoreCase("firespear")) {
							ItemMeta im = M.addRecipeFireSpear();
							ItemStack is = new ItemStack(Material.BLAZE_ROD, 1);
							is.setItemMeta(im);
							tp.getInventory().addItem(is);
						}
						else if (args[1].equalsIgnoreCase("explosivespear")) {
							ItemMeta im = M.addRecipeGunSpear();
							ItemStack is = new ItemStack(Material.STICK, 1);
							is.setItemMeta(im);
							tp.getInventory().addItem(is);
						}
						else if (args[1].equalsIgnoreCase("zuesspear")) {
							ItemMeta im = M.addRecipeZeusSpear();
							ItemStack is = new ItemStack(Material.BLAZE_ROD, 1);
							is.setItemMeta(im);
							tp.getInventory().addItem(is);
						}
						else if (args[1].equalsIgnoreCase("telespear")) {
							ItemMeta im = M.addRecipeTeleSpear();
							ItemStack is = new ItemStack(Material.STICK, 1);
							is.setItemMeta(im);
							tp.getInventory().addItem(is);
						}
						else if (args[1].equalsIgnoreCase("mobspear")) {
							ItemMeta im = M.addRecipeSpawnSpear();
							ItemStack is = new ItemStack(Material.BONE, 1);
							is.setItemMeta(im);
							tp.getInventory().addItem(is);
						} else {

							sender.sendMessage(ChatColor.RED + args[1] + " is not a correct spear type!");
						}
					} else {

						sender.sendMessage("Player not found!");
					}

				} else if (args.length == 3) {
					Player tp = sender.getServer().getPlayer(args[0]);
					if (tp != null) {
						try {
							int arg3;
							arg3 = Integer.parseInt(args[2]);
							if (args[1].equalsIgnoreCase("spear")) {
								ItemMeta im = M.addRecipeSpear();
								ItemStack is = new ItemStack(Material.STICK, arg3);
								is.setItemMeta(im);
								tp.getInventory().addItem(is);
							}
							else if (args[1].equalsIgnoreCase("firespear")) {
								ItemMeta im = M.addRecipeFireSpear();
								ItemStack is = new ItemStack(Material.BLAZE_ROD, arg3);
								is.setItemMeta(im);
								tp.getInventory().addItem(is);
							}
							else if (args[1].equalsIgnoreCase("explosivespear")) {
								ItemMeta im = M.addRecipeGunSpear();
								ItemStack is = new ItemStack(Material.STICK, arg3);
								is.setItemMeta(im);
								tp.getInventory().addItem(is);
							}
							else if (args[1].equalsIgnoreCase("zuesspear")) {
								ItemMeta im = M.addRecipeZeusSpear();
								ItemStack is = new ItemStack(Material.BLAZE_ROD, arg3);
								is.setItemMeta(im);
								tp.getInventory().addItem(is);
							}
							else if (args[1].equalsIgnoreCase("telespear")) {
								ItemMeta im = M.addRecipeTeleSpear();
								ItemStack is = new ItemStack(Material.STICK, arg3);
								is.setItemMeta(im);
								tp.getInventory().addItem(is);
							}
							else if (args[1].equalsIgnoreCase("mobspear")) {
								ItemMeta im = M.addRecipeSpawnSpear();
								ItemStack is = new ItemStack(Material.BONE, arg3);
								is.setItemMeta(im);
								tp.getInventory().addItem(is);
							} else {

								sender.sendMessage(ChatColor.RED + args[1] + ChatColor.WHITE + " is not a correct spear type!");
							}

						} catch (NumberFormatException ex) {
							sender.sendMessage(ChatColor.RED + args[2] + " should be an int!");
						}

					}
				} else {
					sender.sendMessage(ChatColor.RED + "Too many arguments! Do this: /givespear (playername) {spearname} [quantity]");
				}
			}
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("givespear")) {
			return null;
		}
		if (args.length == 1) {
			List<String> list = new ArrayList<>();
			for (Player p : Bukkit.getOnlinePlayers()) {
				list.add(p.getName());
			}
			return list;
		}
		if (args.length == 2) {
			return new ArrayList<>(List.of(new String[]{"spear", "firespear", "explosivespear", "zuesspear", "telespear", "mobspear"}));
		}
		return null;
	}
}
