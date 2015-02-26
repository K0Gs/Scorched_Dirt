package com.k0gshole.Scorch;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;

import com.k0gshole.Scorch.FiredListen;



public class Scorch extends JavaPlugin{
	
	public static Scorch instance;
	private Scorch plugin;
	
	public Inventory displayWeaponsGui;
	public ItemStack currentLogItem;
	public List lores;
	public  ArrayList placedBlocks;
	public ArrayList tempList;
	CleanBlocks cBlocks;
	FiredListen fListn;
	
	public Scorch() {
		lores = new ArrayList();
		placedBlocks = new ArrayList();
		tempList = new ArrayList();
		cBlocks = new CleanBlocks();
		fListn = new FiredListen(instance);
	}
	

	
	@EventHandler
	public void onEnable(){
		instance = this;
		instance.getServer().broadcastMessage("[Scorched Dirt] Now Loading...]");
		Bukkit.getPluginManager().registerEvents(new ClickListen(this), this);
		Bukkit.getPluginManager().registerEvents(new FiredListen(this), this);
		

		registerGameTickEvent();
		
		

		
		
	}
	private GameTickEvent gametickevent = new GameTickEvent();
	private void registerGameTickEvent(){
		Bukkit.getScheduler().runTaskTimer(this, new Runnable(){
		public void run(){
			Bukkit.getPluginManager().callEvent(gametickevent);
		}
		}, 1, 1);
	}
	
	public static Scorch getInstance(){
		return instance;
	}
	
	@EventHandler
	public void onDisable(){
		instance.getServer().broadcastMessage("[Scorched Dirt] Good bye...]");
	}
	

    

	

	
	public Inventory displayWeapons(Player player){
		
		displayWeaponsGui = Bukkit.createInventory(null, 9, "\2479Weapon");
		
		ItemMeta meta4 = null;
		


		lores.clear();
		currentLogItem = new ItemStack(Material.PORK, 1);
		meta4 = currentLogItem.getItemMeta();

		meta4.setDisplayName("\247lExit Weapons");
		lores.add("\247eClick here to");
		lores.add("\247eexit weapon selection...");
		meta4.setLore(lores);
		currentLogItem.setItemMeta(meta4);
		displayWeaponsGui.addItem(new ItemStack[] {
				currentLogItem
		});
		lores.clear();
		
		currentLogItem = new ItemStack(Material.ARROW, 1);
		meta4 = currentLogItem.getItemMeta();

		meta4.setDisplayName("\247lPlain");
		lores.add("\247eClick here to");
		lores.add("\247eset plain arrows...");
		meta4.setLore(lores);
		currentLogItem.setItemMeta(meta4);
		displayWeaponsGui.addItem(new ItemStack[] {
				currentLogItem
		});
		lores.clear();
		
		currentLogItem = new ItemStack(Material.FIREBALL, 1);
		meta4 = currentLogItem.getItemMeta();

		meta4.setDisplayName("\247lFire");
		lores.add("\247eClick here to");
		lores.add("\247eset fire arrows...");
		meta4.setLore(lores);
		currentLogItem.setItemMeta(meta4);
		displayWeaponsGui.addItem(new ItemStack[] {
				currentLogItem
		});
		lores.clear();
		
		currentLogItem = new ItemStack(Material.ICE, 1);
		meta4 = currentLogItem.getItemMeta();

		meta4.setDisplayName("\247lFreeze");
		lores.add("\247eClick here to");
		lores.add("\247eset freeze arrows...");
		meta4.setLore(lores);
		currentLogItem.setItemMeta(meta4);
		displayWeaponsGui.addItem(new ItemStack[] {
				currentLogItem
		});
		lores.clear();
		
		currentLogItem = new ItemStack(Material.ARROW, 1);
		meta4 = currentLogItem.getItemMeta();

		meta4.setDisplayName("\247lKnockback");
		lores.add("\247eClick here to");
		lores.add("\247eset knockback arrows...");
		meta4.setLore(lores);
		currentLogItem.setItemMeta(meta4);
		displayWeaponsGui.addItem(new ItemStack[] {
				currentLogItem
		});
		lores.clear();
		
		return displayWeaponsGui;
		
	}

}
