package com.k0gshole.Scorch;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;

public class CleanBlocks {
	
	ArrayList store;
	
	public CleanBlocks(){
		store  = new ArrayList();
	}
	
	public void update(Location loc, Material mat, Instant now){
		
		ArrayList tList = new ArrayList();
		tList.add(loc);
		tList.add(mat);
		tList.add(now);
		this.store.add(tList);
	}
	
	public void removeIndex(Instant tempInst){
			for (int a = 0;a < this.store.size();a++){
				ArrayList tempList = new ArrayList((ArrayList)this.store.get(a));
				Instant tempInst2 = (Instant)tempList.get(2);

				if(tempInst2 == tempInst){
					this.store.remove(a);
				}
				
			}
			
		
	}
	
	
	public void clearData(){
		this.store.clear();
		Scorch.instance.getServer().broadcastMessage("Clear Data");
	}
	
	public ArrayList returnL(){
		return this.store;
	}

}
/*
 * Version 1.0 
 * By K0Gs
 */