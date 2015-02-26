package com.k0gshole.Scorch;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClickListen implements Listener{
	
	private Scorch plugin;
	public int lastBow;
	public Inventory tempInv;
	public ItemStack tempStack;
	public ItemMeta tempMeta;
	List lores;
	
	public ClickListen(Scorch plugin){
		this.plugin = plugin;
		lores = new ArrayList();
	}
	
	@EventHandler
	public void onBowClick(InventoryClickEvent event){
		
		Player player = (Player) event.getWhoClicked();
		if(event.isRightClick()){
		if (event.getCurrentItem().getType().equals(Material.BOW)){
			//player.sendMessage("Opening Menu...");
			lastBow = event.getSlot();
			//event.isCancelled();
			if (event.getInventory().getName().equalsIgnoreCase("\2479Weapon")) {
				event.setCancelled(true);
			}
			else {
			event.setCancelled(true);
			player.closeInventory();
			player.openInventory(Scorch.getInstance().displayWeapons(player));
			}
		}
	    }
		
		if (event.getInventory().getName().equalsIgnoreCase("\2479Weapon")) {
			event.setCancelled(true);
			player = (Player)event.getWhoClicked();
			if (event.getSlot() < 0 || event.getSlot() > 35) {
				return;
			}
			//if(){
			if(event.getCurrentItem().getAmount() != 0){
				if(!event.getSlotType().name().equals("QUICKBAR")){
					if(!event.getClickedInventory().getName().equals("container.inventory")){
				if(event.getCurrentItem().hasItemMeta()){
				

				
				

				
				if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("\247lExit Weapons")) {
				
				
				player.closeInventory();
				
				
				}
				
				else{
					
					//player.sendMessage("Selected: " + event.getCurrentItem().getItemMeta().getDisplayName());
					lores.clear();
					tempInv = player.getInventory();
					tempStack = tempInv.getItem(lastBow);
					tempMeta = tempStack.getItemMeta();
					
					if (event.getCurrentItem().getItemMeta().getDisplayName().equals("\247lPlain")){
						
					}
					else{
						lores.add(event.getCurrentItem().getItemMeta().getDisplayName());	
					}
					
					
					tempMeta.setLore(lores);
					tempStack.setItemMeta(tempMeta);
					tempInv.setItem(lastBow, tempStack);
					player.getInventory().setContents(tempInv.getContents());
					
					player.closeInventory();
					
				}
				
			}
			}
		}
		}
		}
		
	}
	
	
	

}
/*
 * Version 1.0 
 * By K0Gs
 */
