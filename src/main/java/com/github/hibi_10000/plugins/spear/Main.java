package com.github.hibi_10000.plugins.spear;

import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  public final Logger logger = Logger.getLogger("minecraft");
  
  public Main plugin;
  
  public String reg_spear;
  
  public HashMap<Entity, String> spearw;
  
  public void onDisable() {}
  
  public void onEnable() {
    this.plugin = this;
    Mech M = new Mech(this.plugin);
    getServer().getPluginManager().registerEvents(M, (Plugin)this);
    M.addRecipeSpear();
    M.addRecipeFireSpear();
    M.addRecipeGunSpear();
    M.addRecipeZeusSpear();
    M.addRecipeTeleSpear();
    M.addRecipeSpawnSpear();
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("givespear")) {
      Mech M = new Mech(this.plugin);
      if (sender instanceof Player) {
        Player p = (Player)sender;
        int arg3 = 0;
        if (p.hasPermission("spearmod.give"))
          if (args.length == 0) {
            p.sendMessage(ChatColor.RED + "Too few arguments!" + ChatColor.WHITE + " Do this: /givespear (playername) {spearname} [quantity]");
          } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("options")) {
              p.sendMessage(ChatColor.GREEN + "Spear Types: " + ChatColor.WHITE + "spear, firespear, explosivespear, zuesspear, telespear, mobspear.");
            } else {
              p.sendMessage(ChatColor.RED + "Too few arguments!" + ChatColor.WHITE + " Do this: /givespear (playername) {spearname} [quantity]");
            } 
          } else if (args.length == 2) {
            Player tp = p.getServer().getPlayer(args[0]);
            if (tp instanceof Player) {
              if (args[1].equalsIgnoreCase("spear")) {
                ItemMeta im = M.addRecipeSpear();
                ItemStack is = new ItemStack(Material.STICK, 1);
                is.setItemMeta(im);
                tp.getInventory().addItem(new ItemStack[] { is });
              } else if (args[1].equalsIgnoreCase("firespear")) {
                ItemMeta im = M.addRecipeFireSpear();
                ItemStack is = new ItemStack(Material.BLAZE_ROD, 1);
                is.setItemMeta(im);
                tp.getInventory().addItem(new ItemStack[] { is });
              } else if (args[1].equalsIgnoreCase("explosivespear")) {
                ItemMeta im = M.addRecipeGunSpear();
                ItemStack is = new ItemStack(Material.STICK, 1);
                is.setItemMeta(im);
                tp.getInventory().addItem(new ItemStack[] { is });
              } else if (args[1].equalsIgnoreCase("zuesspear")) {
                ItemMeta im = M.addRecipeZeusSpear();
                ItemStack is = new ItemStack(Material.BLAZE_ROD, 1);
                is.setItemMeta(im);
                tp.getInventory().addItem(new ItemStack[] { is });
              } else if (args[1].equalsIgnoreCase("telespear")) {
                ItemMeta im = M.addRecipeTeleSpear();
                ItemStack is = new ItemStack(Material.STICK, 1);
                is.setItemMeta(im);
                tp.getInventory().addItem(new ItemStack[] { is });
              } else if (args[1].equalsIgnoreCase("mobspear")) {
                ItemMeta im = M.addRecipeSpawnSpear();
                ItemStack is = new ItemStack(Material.BONE, 1);
                is.setItemMeta(im);
                tp.getInventory().addItem(new ItemStack[] { is });
              } else {
                p.sendMessage(ChatColor.RED + args[1] + ChatColor.WHITE + " is not a correct spear type!");
              } 
            } else {
              p.sendMessage("Player not found!");
            } 
          } else if (args.length == 3) {
            Player tp = p.getServer().getPlayer(args[0]);
            if (tp instanceof Player)
              try {
                arg3 = Integer.parseInt(args[2]);
                if (args[1].equalsIgnoreCase("spear")) {
                  ItemMeta im = M.addRecipeSpear();
                  ItemStack is = new ItemStack(Material.STICK, arg3);
                  is.setItemMeta(im);
                  tp.getInventory().addItem(new ItemStack[] { is });
                } else if (args[1].equalsIgnoreCase("firespear")) {
                  ItemMeta im = M.addRecipeFireSpear();
                  ItemStack is = new ItemStack(Material.BLAZE_ROD, arg3);
                  is.setItemMeta(im);
                  tp.getInventory().addItem(new ItemStack[] { is });
                } else if (args[1].equalsIgnoreCase("explosivespear")) {
                  ItemMeta im = M.addRecipeGunSpear();
                  ItemStack is = new ItemStack(Material.STICK, arg3);
                  is.setItemMeta(im);
                  tp.getInventory().addItem(new ItemStack[] { is });
                } else if (args[1].equalsIgnoreCase("zuesspear")) {
                  ItemMeta im = M.addRecipeZeusSpear();
                  ItemStack is = new ItemStack(Material.BLAZE_ROD, arg3);
                  is.setItemMeta(im);
                  tp.getInventory().addItem(new ItemStack[] { is });
                } else if (args[1].equalsIgnoreCase("telespear")) {
                  ItemMeta im = M.addRecipeTeleSpear();
                  ItemStack is = new ItemStack(Material.STICK, arg3);
                  is.setItemMeta(im);
                  tp.getInventory().addItem(new ItemStack[] { is });
                } else if (args[1].equalsIgnoreCase("mobspear")) {
                  ItemMeta im = M.addRecipeSpawnSpear();
                  ItemStack is = new ItemStack(Material.BONE, arg3);
                  is.setItemMeta(im);
                  tp.getInventory().addItem(new ItemStack[] { is });
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
            s.sendMessage(ChatColor.GREEN + "Spear Types: " + ChatColor.WHITE + "spear, firespear, explosivespear, zuesspear, telespear, mobspear.");
          } else {
            s.sendMessage(ChatColor.RED + "Too few arguments!" + ChatColor.WHITE + " Do this: /givespear (playername) {spearname} [quantity]");
          } 
        } else if (args.length == 2) {
          Player tp = s.getServer().getPlayer(args[0]);
          if (tp instanceof Player) {
            if (args[1].equalsIgnoreCase("spear")) {
              ItemMeta im = M.addRecipeSpear();
              ItemStack is = new ItemStack(Material.STICK, 1);
              is.setItemMeta(im);
              tp.getInventory().addItem(new ItemStack[] { is });
            } else if (args[1].equalsIgnoreCase("firespear")) {
              ItemMeta im = M.addRecipeFireSpear();
              ItemStack is = new ItemStack(Material.BLAZE_ROD, 1);
              is.setItemMeta(im);
              tp.getInventory().addItem(new ItemStack[] { is });
            } else if (args[1].equalsIgnoreCase("explosivespear")) {
              ItemMeta im = M.addRecipeGunSpear();
              ItemStack is = new ItemStack(Material.STICK, 1);
              is.setItemMeta(im);
              tp.getInventory().addItem(new ItemStack[] { is });
            } else if (args[1].equalsIgnoreCase("zuesspear")) {
              ItemMeta im = M.addRecipeZeusSpear();
              ItemStack is = new ItemStack(Material.BLAZE_ROD, 1);
              is.setItemMeta(im);
              tp.getInventory().addItem(new ItemStack[] { is });
            } else if (args[1].equalsIgnoreCase("telespear")) {
              ItemMeta im = M.addRecipeTeleSpear();
              ItemStack is = new ItemStack(Material.STICK, 1);
              is.setItemMeta(im);
              tp.getInventory().addItem(new ItemStack[] { is });
            } else if (args[1].equalsIgnoreCase("mobspear")) {
              ItemMeta im = M.addRecipeSpawnSpear();
              ItemStack is = new ItemStack(Material.BONE, 1);
              is.setItemMeta(im);
              tp.getInventory().addItem(new ItemStack[] { is });
            } else {
              s.sendMessage(ChatColor.RED + args[1] + " is not a correct spear type!");
            } 
          } else {
            s.sendMessage("Player not found!");
          } 
        } else if (args.length == 3) {
          Player tp = s.getServer().getPlayer(args[0]);
          if (tp instanceof Player)
            try {
              int arg3 = 0;
              arg3 = Integer.parseInt(args[2]);
              if (args[1].equalsIgnoreCase("spear")) {
                ItemMeta im = M.addRecipeSpear();
                ItemStack is = new ItemStack(Material.STICK, arg3);
                is.setItemMeta(im);
                tp.getInventory().addItem(new ItemStack[] { is });
              } else if (args[1].equalsIgnoreCase("firespear")) {
                ItemMeta im = M.addRecipeFireSpear();
                ItemStack is = new ItemStack(Material.BLAZE_ROD, arg3);
                is.setItemMeta(im);
                tp.getInventory().addItem(new ItemStack[] { is });
              } else if (args[1].equalsIgnoreCase("explosivespear")) {
                ItemMeta im = M.addRecipeGunSpear();
                ItemStack is = new ItemStack(Material.STICK, arg3);
                is.setItemMeta(im);
                tp.getInventory().addItem(new ItemStack[] { is });
              } else if (args[1].equalsIgnoreCase("zuesspear")) {
                ItemMeta im = M.addRecipeZeusSpear();
                ItemStack is = new ItemStack(Material.BLAZE_ROD, arg3);
                is.setItemMeta(im);
                tp.getInventory().addItem(new ItemStack[] { is });
              } else if (args[1].equalsIgnoreCase("telespear")) {
                ItemMeta im = M.addRecipeTeleSpear();
                ItemStack is = new ItemStack(Material.STICK, arg3);
                is.setItemMeta(im);
                tp.getInventory().addItem(new ItemStack[] { is });
              } else if (args[1].equalsIgnoreCase("mobspear")) {
                ItemMeta im = M.addRecipeSpawnSpear();
                ItemStack is = new ItemStack(Material.BONE, arg3);
                is.setItemMeta(im);
                tp.getInventory().addItem(new ItemStack[] { is });
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
