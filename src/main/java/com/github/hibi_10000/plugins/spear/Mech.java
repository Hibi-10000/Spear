package com.github.hibi_10000.plugins.spear;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

public class Mech
		implements Listener {
	public Spear plugin;
	public HashMap<Entity, String> spearw = new HashMap<>();
	public String reg_spear = "reg_spear";
	public String fire_spear = "fire_spear";
	public String gun_spear = "gun_spear";
	public String zeus_spear = "zeus_spear";
	public String tele_spear = "tele_spear";
	public String mob_spear = "mob_spear";

	public Mech(Spear plugin) {
		this.plugin = plugin;
	}

	public ItemMeta addRecipeSpear() {
		ArrayList<String> lore = new ArrayList<>();
		ItemStack stick = new ItemStack(Material.STICK, 1);
		ItemMeta im = stick.getItemMeta();
		lore.add(ChatColor.BLUE + " Simple, but deadly.");
		lore.add(ChatColor.RED + " Damage: 15");
		im.setDisplayName("Spear");
		im.setLore(lore);
		stick.setItemMeta(im);
		ShapedRecipe spearr = new ShapedRecipe(new NamespacedKey(plugin,"spear"),stick);
		spearr.shape("F  ", " S ", "  S");
		spearr.setIngredient('S', Material.STICK);
		spearr.setIngredient('F', Material.FLINT);
		try {
			this.plugin.getServer().addRecipe(spearr);
		} catch (IllegalStateException ignored) {}
		return im;
	}

	public ItemMeta addRecipeFireSpear() {
		ItemStack rod = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta im = rod.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add(ChatColor.BLUE + " Set your enemy on fire!");
		lore.add(ChatColor.RED + " Damage: 15 + fire damage");
		im.setLore(lore);
		im.setDisplayName("Fire Spear");
		rod.setItemMeta(im);
		ShapedRecipe firespear = new ShapedRecipe(new NamespacedKey(plugin,"firespear"),rod);
		firespear.shape("P  ", " B ", "  B");
		firespear.setIngredient('B', Material.BLAZE_ROD);
		firespear.setIngredient('P', Material.BLAZE_POWDER);
		try {
			this.plugin.getServer().addRecipe(firespear);
		} catch (IllegalStateException ignored) {}
		return im;
	}

	public ItemMeta addRecipeGunSpear() {
		ItemStack stick = new ItemStack(Material.STICK, 1);
		ItemMeta im = stick.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add(ChatColor.BLUE + " Create a deadly explosion!");
		lore.add(ChatColor.RED + " Damage: 15 + explosion damage");
		im.setLore(lore);
		im.setDisplayName("Explosive Spear");
		stick.setItemMeta(im);
		ShapedRecipe gunspear = new ShapedRecipe(new NamespacedKey(plugin,"explosivespear"),stick);
		gunspear.shape("U  ", " S ", "  S");
		gunspear.setIngredient('S', Material.STICK);
		gunspear.setIngredient('U', Material.GUNPOWDER);
		try {
			this.plugin.getServer().addRecipe(gunspear);
		} catch (IllegalStateException ignored) {}
		return im;
	}

	public ItemMeta addRecipeZeusSpear() {
		ItemStack blaze = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta im = blaze.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add(ChatColor.BLUE + " Harness the power of the skies!");
		lore.add(ChatColor.RED + " Damage: 30 + explosion damage");
		im.setLore(lore);
		im.setDisplayName("Zeus Spear");
		blaze.setItemMeta(im);
		ShapedRecipe zeusspear = new ShapedRecipe(new NamespacedKey(plugin,"zeusspear"),blaze);
		zeusspear.shape("DD ", "DB ", "  B");
		zeusspear.setIngredient('B', Material.BLAZE_ROD);
		zeusspear.setIngredient('D', Material.DIAMOND);
		try {
			this.plugin.getServer().addRecipe(zeusspear);
		} catch (IllegalStateException ignored) {}
		return im;
	}

	public ItemMeta addRecipeTeleSpear() {
		ItemStack stick = new ItemStack(Material.STICK, 1);
		ItemMeta im = stick.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add(ChatColor.BLUE + " Teleport yourself or others!");
		lore.add(ChatColor.RED + " Damage: 10");
		im.setLore(lore);
		im.setDisplayName("Tele Spear");
		stick.setItemMeta(im);
		ShapedRecipe telespear = new ShapedRecipe(new NamespacedKey(plugin,"telespear"),stick);
		telespear.shape("YE ", "ES ", "  S");
		telespear.setIngredient('S', Material.STICK);
		telespear.setIngredient('E', Material.ENDER_PEARL);
		telespear.setIngredient('Y', Material.ENDER_EYE);
		try {
			this.plugin.getServer().addRecipe(telespear);
		} catch (IllegalStateException ignored) {}
		return im;
	}

	public ItemMeta addRecipeSpawnSpear() {
		ItemStack bone = new ItemStack(Material.BONE, 1);
		ItemMeta im = bone.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add(ChatColor.BLUE + " Welcome to the dark side.");
		lore.add(ChatColor.RED + " Damage: 20");
		im.setLore(lore);
		im.setDisplayName("Mob Spear");
		bone.setItemMeta(im);
		ShapedRecipe spawnspear = new ShapedRecipe(new NamespacedKey(plugin,"mobspear"),bone);
		spawnspear.shape("EE ", "EB ", "  B");
		spawnspear.setIngredient('B', Material.BONE);
		spawnspear.setIngredient('E', Material.EGG);
		try {
			this.plugin.getServer().addRecipe(spawnspear);
		} catch (IllegalStateException ignored) {}
		return im;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.LEFT_CLICK_AIR)
		{
			if (p.getItemInHand().getItemMeta() != null)
			{
				if (p.getItemInHand().getItemMeta().getDisplayName().equals("Spear")) {

					if (p.hasPermission("Spear.normal")) {

						if (p.getItemInHand().getAmount() >= 2) {

							p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							Arrow arrow = p.launchProjectile(Arrow.class);
							this.spearw.put(arrow, this.reg_spear);
						}
						else if (p.getItemInHand().getAmount() == 1) {

							p.getInventory().clear(p.getInventory().getHeldItemSlot());
							Arrow arrow = p.launchProjectile(Arrow.class);
							this.spearw.put(arrow, this.reg_spear);
						}
					} else {

						p.sendMessage("You do not have sufficient permissions");
					}

				} else if (p.getItemInHand().getItemMeta().getDisplayName().equals("Fire Spear")) {

					if (p.hasPermission("Spear.fire")) {

						if (p.getItemInHand().getAmount() >= 2) {

							p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							Arrow arrow = p.launchProjectile(Arrow.class);
							this.spearw.put(arrow, this.fire_spear);
						}
						else if (p.getItemInHand().getAmount() == 1) {

							p.getInventory().clear(p.getInventory().getHeldItemSlot());
							Arrow arrow = p.launchProjectile(Arrow.class);
							this.spearw.put(arrow, this.fire_spear);
						}
					} else {

						p.sendMessage("You do not have sufficient permissions");
					}

				} else if (p.getItemInHand().getItemMeta().getDisplayName().equals("Explosive Spear")) {

					if (p.hasPermission("Spear.explosive")) {

						if (p.getItemInHand().getAmount() >= 2) {

							p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							Arrow arrow = p.launchProjectile(Arrow.class);
							this.spearw.put(arrow, this.gun_spear);
						}
						else if (p.getItemInHand().getAmount() == 1) {

							p.getInventory().clear(p.getInventory().getHeldItemSlot());
							Arrow arrow = p.launchProjectile(Arrow.class);
							this.spearw.put(arrow, this.gun_spear);
						}
					} else {

						p.sendMessage("You do not have sufficient permissions");
					}

				} else if (p.getItemInHand().getItemMeta().getDisplayName().equals("Zeus Spear")) {

					if (p.hasPermission("Spear.zeus")) {

						if (p.getItemInHand().getAmount() >= 2) {

							p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							Arrow arrow = p.launchProjectile(Arrow.class);
							this.spearw.put(arrow, this.zeus_spear);
						}
						else if (p.getItemInHand().getAmount() == 1) {

							p.getInventory().clear(p.getInventory().getHeldItemSlot());
							Arrow arrow = p.launchProjectile(Arrow.class);
							this.spearw.put(arrow, this.zeus_spear);
						}
					} else {

						p.sendMessage("You do not have sufficient permissions");
					}

				} else if (p.getItemInHand().getItemMeta().getDisplayName().equals("Tele Spear")) {

					if (p.hasPermission("Spear.tele")) {

						if (p.getItemInHand().getAmount() >= 2) {

							p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							Arrow arrow = p.launchProjectile(Arrow.class);
							this.spearw.put(arrow, this.tele_spear);
						}
						else if (p.getItemInHand().getAmount() == 1) {

							p.getInventory().clear(p.getInventory().getHeldItemSlot());
							Arrow arrow = p.launchProjectile(Arrow.class);
							this.spearw.put(arrow, this.tele_spear);
						}
					} else {

						p.sendMessage("You do not have sufficient permissions");
					}

				} else if (p.getItemInHand().getItemMeta().getDisplayName().equals("Mob Spear")) {

					if (p.hasPermission("Spear.mob")) {

						if (p.getItemInHand().getAmount() >= 2) {

							p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							Arrow arrow = p.launchProjectile(Arrow.class);
							this.spearw.put(arrow, this.mob_spear);
						}
						else if (p.getItemInHand().getAmount() == 1) {

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
			if (e.getEntity() instanceof Arrow)
			{
				if (Mech.this.spearw.containsKey(e.getEntity()))
				{
					if (Mech.this.spearw.containsValue(Mech.this.reg_spear)) {

						Mech.this.spearw.remove(e.getEntity());
						e.getEntity().remove();
					}
					else if (Mech.this.spearw.containsValue(Mech.this.fire_spear)) {

						final Block block = e.getEntity().getLocation().getBlock();
						block.setType(Material.FIRE);
						Bukkit.getScheduler().scheduleSyncDelayedTask(Mech.this.plugin, () -> {
							if (block.getType() == Material.FIRE)
							{
								block.setType(Material.AIR);
							}
						},  200L);
						Mech.this.spearw.remove(e.getEntity());
						e.getEntity().remove();
					}
					else if (Mech.this.spearw.containsValue(Mech.this.gun_spear)) {

						e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 1.0F);
						Mech.this.spearw.remove(e.getEntity());
						e.getEntity().remove();
					}
					else if (Mech.this.spearw.containsValue(Mech.this.zeus_spear)) {

						e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
						Mech.this.spearw.remove(e.getEntity());
						e.getEntity().remove();
					}
					else if (Mech.this.spearw.containsValue(Mech.this.tele_spear)) {

						Player p = (Player)e.getEntity().getShooter();
						Location loc = e.getEntity().getLocation();
						Mech.this.spearw.remove(e.getEntity());
						e.getEntity().remove();
						p.teleport(loc);
					}
					else if (Mech.this.spearw.containsValue(Mech.this.mob_spear)) {

						World world = e.getEntity().getWorld();
						Location loc = e.getEntity().getLocation();
						Random r = new Random();
						int next = r.nextInt(5);
						if (next == 1) {

							world.spawnEntity(loc, EntityType.ZOMBIE);
							Mech.this.spearw.remove(e.getEntity());
							e.getEntity().remove();
						}
						else if (next == 2) {

							world.spawnEntity(loc, EntityType.CREEPER);
							Mech.this.spearw.remove(e.getEntity());
							e.getEntity().remove();
						}
						else if (next == 3) {

							world.spawnEntity(loc, EntityType.SKELETON);
							Mech.this.spearw.remove(e.getEntity());
							e.getEntity().remove();
						}
						else {

							world.spawnEntity(loc, EntityType.SPIDER);
							Mech.this.spearw.remove(e.getEntity());
							e.getEntity().remove();
						}
					}
				}
			}
		},1L);
	}

	@EventHandler
	public void onHit2(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Arrow)
		{
			if (this.spearw.containsKey(e.getDamager()))
			{
				if (this.spearw.containsValue(this.reg_spear)) {

					e.setDamage(15);
					this.spearw.remove(e.getDamager());
				}
				else if (this.spearw.containsValue(this.fire_spear)) {

					e.getEntity().setFireTicks(200);
					e.setDamage(15);
					this.spearw.remove(e.getDamager());
				}
				else if (this.spearw.containsValue(this.gun_spear)) {

					e.setDamage(15);
					e.getDamager().getWorld().createExplosion(e.getEntity().getLocation(), 2.0F);
					this.spearw.remove(e.getDamager());
				}
				else if (this.spearw.containsValue(this.zeus_spear)) {

					e.setDamage(30);
					e.getDamager().getWorld().strikeLightning(e.getDamager().getLocation());
					e.getDamager().getWorld().createExplosion(e.getDamager().getLocation(), 3.0F);
					this.spearw.remove(e.getDamager());
				}
				else if (this.spearw.containsValue(this.tele_spear)) {

					Player p1 = (Player)((Arrow)e.getDamager()).getShooter();
					Entity p2 = e.getEntity();
					Location p2loc = p2.getLocation();
					Location p1loc = p1.getLocation();
					if (p2 instanceof Player)
					{
						if (p1 == p2)
						{
							p1.sendMessage("Teleportation failure!");
							e.setDamage(5);
						}
						else
						{
							e.setDamage(10);
							p1.getWorld().playEffect(p1loc, Effect.SMOKE, 10);
							p2.getWorld().playEffect(p2loc, Effect.SMOKE, 10);
							p1.teleport(p2loc);
							p2.teleport(p1loc);
							this.spearw.remove(e.getDamager());
						}

					}
				} else if (this.spearw.containsValue(this.mob_spear)) {

					if (e.getEntity() instanceof Player p) {

						e.setDamage(20);
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