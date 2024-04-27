package com.github.hibi_10000.plugins.spear;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    public Main plugin;
    public Mech mech;

    @Override
    public void onEnable() {
        this.plugin = this;
        mech = new Mech(this.plugin);
        getServer().getPluginManager().registerEvents(mech, this);
        mech.addRecipeRegularSpear();
        mech.addRecipeFireSpear();
        mech.addRecipeExplosiveSpear();
        mech.addRecipeZeusSpear();
        mech.addRecipeTeleportSpear();
        mech.addRecipeMobSpear();
    }

    public void giveSpear(Player receiver, ItemMeta data, Material type, int amount) {
        ItemStack is = new ItemStack(type, amount);
        is.setItemMeta(data);
        receiver.getInventory().addItem(is);
    }

    public void giveSpear(CommandSender sender, Player receiver, String type, int amount) {
        SpearType spearType = SpearType.fromNameIgnoreCase(type);
        if (spearType == null) {
            sender.sendMessage(ChatColor.RED + type + ChatColor.WHITE + " is not a correct spear type!");
            return;
        }
        switch (spearType) {
            case REGULAR:
                giveSpear(receiver, mech.addRecipeRegularSpear(), spearType.getMaterial(), amount);
                break;
            case FIRE:
                giveSpear(receiver, mech.addRecipeFireSpear(), spearType.getMaterial(), amount);
                break;
            case EXPLOSIVE:
                giveSpear(receiver, mech.addRecipeExplosiveSpear(), spearType.getMaterial(), amount);
                break;
            case ZEUS:
                giveSpear(receiver, mech.addRecipeZeusSpear(), spearType.getMaterial(), amount);
                break;
            case TELEPORT:
                giveSpear(receiver, mech.addRecipeTeleportSpear(), spearType.getMaterial(), amount);
                break;
            case MOB:
                giveSpear(receiver, mech.addRecipeMobSpear(), spearType.getMaterial(), amount);
                break;
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
                if (tp != null)
                    try {
                        int arg3 = Integer.parseInt(args[2]);
                        giveSpear(sender, tp, args[1], arg3);
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(ChatColor.RED + args[2] + white + " should be an int!");
                    }
            } else {
                sender.sendMessage(ChatColor.RED + "Too many arguments!" + white + " Do this: /givespear (playername) {spearname} [quantity]");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!command.getName().equalsIgnoreCase("givespear")) return null;
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            for (Player p : getServer().getOnlinePlayers()) {
                list.add(p.getName());
            }
            return list;
        }
        if (args.length == 2) {
            return SpearType.names();
        }
        return null;
    }
}
