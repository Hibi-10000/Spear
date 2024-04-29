package com.github.hibi_10000.plugins.spear;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends JavaPlugin {
    private final Main plugin = this;
    public final Mech mech = new Mech(plugin);

    @Override
    public void onEnable() {
        SpearType.init(plugin);
        plugin.getServer().getPluginManager().registerEvents(mech, plugin);
        for (SpearType type : SpearType.values()) {
            type.getSpear().addRecipe();
        }
    }

    public void giveSpear(CommandSender sender, Player receiver, String type, int amount) {
        SpearType spearType = SpearType.fromNameIgnoreCase(type);
        if (spearType != null) {
            ItemStack is = new ItemStack(spearType.getMaterial(), amount);
            is.setItemMeta(spearType.getSpear().addRecipe());
            receiver.getInventory().addItem(is);
        } else {
            sender.sendMessage(ChatColor.RED + type + ChatColor.WHITE + " is not a correct spear type!");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("givespear")) {
            String white = "";
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!p.hasPermission("spear.give")) return false;
                white = ChatColor.WHITE.toString();
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Too few arguments!" + white + " Do this: /givespear (playername) {spearname} [quantity]");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("options")) {
                    sender.sendMessage(ChatColor.GREEN + "Spear Types: " + white + String.join(", ", SpearType.names()) + ".");
                } else {
                    sender.sendMessage(ChatColor.RED + "Too few arguments!" + white + " Do this: /givespear (playername) {spearname} [quantity]");
                }
            } else if (args.length == 2) {
                Player tp = plugin.getServer().getPlayer(args[0]);
                if (tp != null) {
                    giveSpear(sender, tp, args[1], 1);
                } else {
                    sender.sendMessage("Player not found!");
                }
            } else if (args.length == 3) {
                Player tp = plugin.getServer().getPlayer(args[0]);
                if (tp != null) {
                    try {
                        int arg3 = Integer.parseInt(args[2]);
                        giveSpear(sender, tp, args[1], arg3);
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(ChatColor.RED + args[2] + white + " should be an int!");
                    }
                } else {
                    sender.sendMessage("Player not found!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Too many arguments!" + white + " Do this: /givespear (playername) {spearname} [quantity]");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!command.getName().equalsIgnoreCase("givespear")) return Collections.emptyList();
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                list.add(p.getName());
            }
            return list;
        } else if (args.length == 2) {
            return SpearType.names();
        }
        return Collections.emptyList();
    }
}
