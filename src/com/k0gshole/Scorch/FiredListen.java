package com.k0gshole.Scorch;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



import java.util.Map;

import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.world.ChunkEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

public class FiredListen implements Listener{
	public static Scorch plugin;
	public static List fireArrows;
	public static List iceArrows;
	public List knockArrows;
	public Map<Object, Instant> mash;
	public static List transList;
	public static ArrayList tempList;
	CleanBlocks cBlocks;
	public int counter = 0;
	public ArrayList icedPlayer;
	public ArrayList burnables = new ArrayList();
	public ArrayList eggArrows;
	public ArrayList clustArrows;
	
	public FiredListen(Scorch plugin){
		this.plugin = plugin;
		fireArrows = new ArrayList();
		iceArrows = new ArrayList();
		knockArrows = new ArrayList();
		cBlocks = new CleanBlocks();
		icedPlayer = new ArrayList();
		eggArrows = new ArrayList();
		burnables.add("LONG_GRASS");
		burnables.add("AIR");
		clustArrows = new ArrayList();
	}
	


	
	public void wildCleanUp() {
		

		ArrayList placedBlocks = new ArrayList();
		for (int b = 0;b < cBlocks.returnL().size();b++){
			placedBlocks.add(cBlocks.returnL().get(b));
		}
		
			//plugin.getServer().broadcastMessage(Integer.toString(placedBlocks.size()));
			//System.out.println(placedBlocks);
			for(int k = 0; k < placedBlocks.size();k++){

				//plugin.getServer().broadcastMessage(placedBlocks.toString());
				tempList = new ArrayList((ArrayList) placedBlocks.get(k));
				Location tempBlock = (Location) tempList.get(0);
				Material tempMat = (Material) tempList.get(1);

				//plugin.getServer().broadcastMessage(tempBlock.getWorld().getName());
				Instant tempInst = (Instant) tempList.get(2);
				//plugin.getServer().broadcastMessage("Material."+tempMat.name());
				//System.out.println(tempInst.getNano());
				//plugin.getServer().broadcastMessage(tempInst.toString());
				Instant nowTime = Instant.now();
				//System.out.println(nowTime.compareTo(tempInst));
				String tempInt = Integer.toString(nowTime.compareTo(tempInst));
				//plugin.getServer().broadcastMessage(tempInt);
				Block curBlock = plugin.getServer().getWorld(tempBlock.getWorld().getName()).getBlockAt(tempBlock);
				//plugin.getServer().broadcastMessage(Integer.toString(nowTime.compareTo(tempInst)));
				
				if (nowTime.compareTo(tempInst) == 1){
				curBlock.setType(Material.AIR);

				cBlocks.removeIndex(tempInst);
				}
				
			}

			




	}
	

	

	

	
	public ArrayList getEntityList(){
		ArrayList EntityList = new ArrayList();
			for(int e = 0;e < plugin.getServer().getWorlds().size();e++){
				World world = plugin.getServer().getWorlds().get(e);
				for(int f = 0; f < world.getLivingEntities().size();f++){
				EntityList.add(world.getLivingEntities().get(f));
				}
			}
		return EntityList;
	}
	
	public void freezeEntity(){
		ArrayList entityList = getEntityList();
		for(int e = 0; e < entityList.size();e++){
		for(int k = 0; k < icedPlayer.size();k++){
			Entity entity = (Entity) entityList.get(e);
			tempList = new ArrayList((ArrayList) icedPlayer.get(k));
			String player = (String) tempList.get(0);
			Instant tempInst = (Instant) tempList.get(1);
			//plugin.getServer().broadcastMessage(player);
			Vector velocity = new Vector(0, 0, 0);
			//plugin.getServer().broadcastMessage(entity.getUniqueId().toString());
			//System.out.println(tempInst.getNano());
			//plugin.getServer().broadcastMessage(tempInst.toString());
			Instant nowTime = Instant.now();
			//System.out.println(nowTime.compareTo(tempInst));
			//String world = (String) tempList.get(3);
			String tempInt = Integer.toString(nowTime.compareTo(tempInst));
			//plugin.getServer().broadcastMessage(tempInt);
			//Block curBlock = plugin.getServer().getWorld(tempBlock.getWorld().getName()).getBlockAt(tempBlock);
			//plugin.getServer().broadcastMessage(Integer.toString(nowTime.compareTo(tempInst)));
			
			if (nowTime.compareTo(tempInst) != 1){
				if(player.equals(entity.getUniqueId().toString())){
					
					//plugin.getServer().broadcastMessage("Freeze...");
					//plugin.getServer().broadcastMessage(Boolean.toString(event.isCancelled()));
					//event.getPlayer().setWalkSpeed(0);
					//event.getPlayer().setFlySpeed(0);
					//event.setTo(event.getPlayer().getLocation());
					entity.setVelocity(velocity);
					entity.playEffect(EntityEffect.HURT);
					
				}
			
			}

			
			
			
			
			
			
			
		}
		}
		
	}
	public int compareToNow(Instant compInst){
		Instant nowTime = Instant.now();
		String tempInt = "";
		return Integer.valueOf(tempInt = Integer.toString(nowTime.compareTo(compInst)));

	}
	
	
	public void listCleaner(){
		ArrayList tempList = new ArrayList();
		
		for(int a = 0;a < fireArrows.size();a++){
		tempList = new ArrayList();
		tempList = (ArrayList) fireArrows.get(a);
		int compInt = compareToNow((Instant)tempList.get(1));
		if (compInt == 1){
			fireArrows.remove(a);
		}
		
		}
		
		for(int a = 0;a < iceArrows.size();a++){
		tempList = new ArrayList();
		tempList = (ArrayList) iceArrows.get(a);
		int compInt = compareToNow((Instant)tempList.get(1));
		if (compInt == 1){
			iceArrows.remove(a);
		}
		
		}
		
		for(int a = 0;a < knockArrows.size();a++){
		tempList = new ArrayList();
		tempList = (ArrayList) knockArrows.get(a);
		int compInt = compareToNow((Instant)tempList.get(1));
		if (compInt == 1){
			knockArrows.remove(a);
		}
		
		}
		
		for(int a = 0;a < icedPlayer.size();a++){
		tempList = new ArrayList();
		tempList = (ArrayList) icedPlayer.get(a);
		int compInt = compareToNow((Instant)tempList.get(1));
		if (compInt == 1){
			icedPlayer.remove(a);
		}
		
		}
		
		for(int a = 0;a < clustArrows.size();a++){
		tempList = new ArrayList();
		tempList = (ArrayList) clustArrows.get(a);
		int compInt = compareToNow((Instant)tempList.get(1));
		if (compInt == 1){
			clustArrows.remove(a);
		}
		
		}
		

		
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event){
		
		ArrayList tempIcedList = new ArrayList();
		for (int c = 0; c < icedPlayer.size();c++){
			tempIcedList.add(icedPlayer.get(c));
		}
		
		//plugin.getServer().broadcastMessage(event.toString());
		//plugin.getServer().broadcastMessage();
		//plugin.getServer().broadcastMessage(event.getPlayer().getUniqueId().toString());
		
		for(int k = 0; k < icedPlayer.size();k++){
;
			//plugin.getServer().broadcastMessage(placedBlocks.toString());
			tempList = new ArrayList((ArrayList) icedPlayer.get(k));
			String player = (String) tempList.get(0);
			Instant tempInst = (Instant) tempList.get(1);
			//plugin.getServer().broadcastMessage(player);
			//plugin.getServer().broadcastMessage(event.getPlayer().getUniqueId().toString());
			//System.out.println(tempInst.getNano());
			//plugin.getServer().broadcastMessage(tempInst.toString());
			Instant nowTime = Instant.now();
			//System.out.println(nowTime.compareTo(tempInst));
			//String world = (String) tempList.get(3);
			String tempInt = Integer.toString(nowTime.compareTo(tempInst));
			//plugin.getServer().broadcastMessage(tempInt);
			//Block curBlock = plugin.getServer().getWorld(tempBlock.getWorld().getName()).getBlockAt(tempBlock);
			//plugin.getServer().broadcastMessage(Integer.toString(nowTime.compareTo(tempInst)));
			
			if (nowTime.compareTo(tempInst) != 1){
				if(player.equals(event.getPlayer().getUniqueId().toString())){
					
					//plugin.getServer().broadcastMessage("Freeze...");

					//plugin.getServer().broadcastMessage(Boolean.toString(event.isCancelled()));
					//event.getPlayer().setWalkSpeed(0);
					//event.getPlayer().setFlySpeed(0);
					Vector tempVec = new Vector(0,0,0);
					Location tempLoc = null;
					Double pX = event.getFrom().getX();
					Double pY = event.getFrom().getY();
					Double pZ = event.getFrom().getZ();
					Float pPitch = event.getTo().getPitch();
					Float pYaw = event.getTo().getYaw();
					World world = event.getPlayer().getWorld();
					tempLoc = new Location(world, pX, pY, pZ, pYaw, pPitch);
					event.setTo(tempLoc);
					event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.PARTICLE_SMOKE, 5*20);
					
				}
			
			}
			//else if (nowTime.compareTo(tempInst) == 1){

				//event.getPlayer().setWalkSpeed(Float.valueOf("0.2"));
				//event.getPlayer().setFlySpeed(Float.valueOf("0.2"));
				
			//}
		}
		
		
	}
	

	
	@EventHandler
	public void onGameTickEvent(GameTickEvent event){
		counter++;
		
		if(counter == 19){
			counter = 0;

			listCleaner();
			wildCleanUp();
		}
			freezeEntity();
			
			
		
	}
	
	@EventHandler
	public void onentityDamageEntity(EntityDamageByEntityEvent event){
		
		for(int c = 0;c < clustArrows.size();c++){
			ArrayList tempList = new ArrayList();
			tempList = (ArrayList) clustArrows.get(c);
			if(tempList.get(0).toString().equals(event.getDamager().getUniqueId().toString())){
				//plugin.getServer().broadcastMessage("Cluster Hit: "+event.getEntityType().name());
				
				//event.setCancelled(true);
				//event.getDamager().remove();
			}
			
		}
		
	}
	
	@EventHandler
	public void entityHitEntity(EntityDamageByEntityEvent event){
	for(int a = 0; a < fireArrows.size();a++){
		ArrayList tempList = new ArrayList();
		tempList = (ArrayList) fireArrows.get(a);
		if (tempList.get(0).toString().equals(event.getDamager().getUniqueId().toString())) {
			//plugin.getServer().broadcastMessage("Fire Arrow hit Entity"+event.getEntity().toString());
			event.getEntity().setFireTicks(20*10);

		}
	}
	
	for(int a = 0; a < knockArrows.size();a++){
		ArrayList tempList = new ArrayList();
		tempList = (ArrayList) knockArrows.get(a);
		if(tempList.get(0).toString().equals(event.getDamager().getUniqueId().toString())){
			//plugin.getServer().broadcastMessage("Cluster Arrows hit Entity"+event.getEntity().toString());
			event.getEntity().setVelocity(event.getDamager().getVelocity());
			event.setDamage(event.getFinalDamage()*2);

		}
	}
	for(int a = 0; a < iceArrows.size();a++){
		ArrayList tempList = new ArrayList();
		tempList = (ArrayList) iceArrows.get(a);
		if(tempList.get(0).toString().equals(event.getDamager().getUniqueId().toString())){
			//plugin.getServer().broadcastMessage("Cluster Arrows hit Entity"+event.getEntity().toString());
			//event.getEntity().setVelocity(event.getDamager().getVelocity());
			//event.setDamage(event.getFinalDamage()*2);
			//System.out.println(event.getEntityType().name());
			//event.getEntityType().name().equals("HUMAN");
				ArrayList tempIced = new ArrayList();
				tempIced.add(event.getEntity().getUniqueId().toString());
				
				tempIced.add(Instant.now().plusSeconds(5));
				icedPlayer.add(tempIced);

		}
	}
	

	
	}
	

	
	@EventHandler
	public void onBowFire(EntityShootBowEvent event){
		if(event.getEntityType().name().equals("PLAYER")){
		
		Player player = (Player) event.getEntity();
		ArrayList tempBowFire = new ArrayList();
		//player.sendMessage(event.getBow().getItemMeta().getLore().get(0).toString());
		//player.sendMessage(event.getProjectile().getUniqueId().toString());
		
		if (event.getBow().getItemMeta().getLore().get(0).toString().equals("\247lFire")){
			tempBowFire = new ArrayList();
			 //player.sendMessage("Bow is type Fire...");
			tempBowFire.add(event.getProjectile().getUniqueId().toString());
			tempBowFire.add(Instant.now().plusSeconds(10));
			fireArrows.add(tempBowFire);
		}
		
		if (event.getBow().getItemMeta().getLore().get(0).toString().equals("\247lFreeze")){
			tempBowFire = new ArrayList();
			 //player.sendMessage("Bow is type Freeze...");
			tempBowFire.add(event.getProjectile().getUniqueId().toString());

			tempBowFire.add(Instant.now().plusSeconds(10));
			iceArrows.add(tempBowFire);
		}
		
		if (event.getBow().getItemMeta().getLore().get(0).toString().equals("\247lKnockback")){
			tempBowFire = new ArrayList();
			// player.sendMessage("Bow is type Knockback...");
			tempBowFire.add(event.getProjectile().getUniqueId().toString());


			tempBowFire.add(Instant.now().plusSeconds(10));
			knockArrows.add(tempBowFire);
		}
		
		if (event.getBow().getItemMeta().getLore().get(0).toString().equals("\247lEgg")){
			tempBowFire = new ArrayList();
			// player.sendMessage("Bow is type egg...");
			tempBowFire.add(event.getProjectile().getUniqueId().toString());
			//Arrow arrow = player.launchProjectile(Arrow.class);
			//.setVelocity(event.getProjectile().getVelocity());
			event.getEntity().throwEgg().setVelocity(event.getProjectile().getVelocity());;
			tempBowFire.add(Instant.now().plusSeconds(10));
			eggArrows.add(tempBowFire);
			event.getProjectile().remove();
		}
		
		if (event.getBow().getItemMeta().getLore().get(0).toString().equals("\247lCluster")){
			tempBowFire = new ArrayList();
			// player.sendMessage("Bow is type Cluster...");
			//tempBowFire.add(event.getProjectile().getUniqueId().toString());
			//tempBowFire.add(Instant.now());
			//clustArrows.add(tempBowFire);
			Location tempLoc = player.getEyeLocation();
			Vector dir = player.getEyeLocation().getDirection();
			Vector actDir = dir.clone().normalize();
			Vector finVec = tempLoc.toVector().add(actDir.multiply(2));
			

			tempLoc.setX(finVec.getX());
			tempLoc.setY(finVec.getY());
			tempLoc.setZ(finVec.getZ());
			World world = tempLoc.getWorld();
			Float accel = 2.0f;
			Vector velocity = event.getProjectile().getVelocity();
			//tempLoc.setX(x+1);
			//player.sendMessage(event.getProjectile().getVelocity().toString());
			clusterArrowFire(player, tempLoc, dir, finVec, accel, velocity);
			//player.sendMessage(Float.toString(player.getLocation().getYaw()));
			//player.sendMessage(getNameDirection(player));
			
		}
		
		
		}


	}
	
	public String getNameDirection(Player player){
		String dirText = "Blank";
		double rotation = (double)player.getLocation().getYaw();
		
		if(rotation < 0){
			if(0 >= rotation && rotation > -45.0){
				dirText = "South";
			}
			if(-45.0 >= rotation && rotation > -135.0){
				dirText = "East";
			}
			if(-135.0 >= rotation && rotation > -225.0){
				dirText = "North";
			}
			if(-225.0 >= rotation && rotation > -315.0){
				dirText = "West";
			}
			if(-315.0 >= rotation && rotation >= -360.0){
				dirText = "South";
			}
		}
		else if(rotation > 0){
			if(0 <= rotation && rotation < 45.0){
				dirText = "South";
			}
			if(45.0 <= rotation && rotation < 135.0){
				dirText = "West";
			}
			if(135.0 <= rotation && rotation < 225.0){
				dirText = "North";
			}
			if(225.0 <= rotation && rotation < 315.0){
				dirText = "East";
			}
			if(315.0 <= rotation && rotation <= 360.0){
				dirText = "South";
			}	
			
		}
		
		
		return dirText;
	}
	
	public void clusterArrowFire(Player player, Location loc, Vector dir, Vector finVec, Float power, Vector velocity){
		ArrayList tempBowFire = new ArrayList();
		Vector actDir = dir.clone().normalize();
		World world = loc.getWorld();
		String dirText = getNameDirection(player);
		
		if(dirText.equals("North") || dirText.equals("South")){
			//player.sendMessage("North/South");
		loc.setX(finVec.getX()+0.5);
//		world.spawnArrow(loc, actDir, power, 1.0f);
		Arrow arrow = (Arrow) world.spawnEntity(loc, EntityType.ARROW);
		arrow.setBounce(false);
		arrow.setShooter(player);
		arrow.setVelocity(velocity);
		tempBowFire.clear();
		tempBowFire.add(arrow.getUniqueId().toString());
		tempBowFire.add(Instant.now().plusSeconds(5));
		clustArrows.add(tempBowFire);
		
		loc.setX(finVec.getX()-0.5);

		Arrow arrow2 = (Arrow) world.spawnEntity(loc, EntityType.ARROW);
		arrow2.setBounce(false);
		arrow2.setShooter(player);
		arrow2.setVelocity(velocity);
		tempBowFire.clear();
		tempBowFire.add(arrow2.getUniqueId().toString());
		tempBowFire.add(Instant.now().plusSeconds(5));
		clustArrows.add(tempBowFire);
		
		loc.setX(finVec.getX());
		}
		else
			if(dirText.equals("East") || dirText.equals("West")){
				//player.sendMessage("East/West");
			loc.setZ(finVec.getZ()+0.5);

			Arrow arrow = (Arrow) world.spawnEntity(loc, EntityType.ARROW);
			arrow.setBounce(false);
			arrow.setShooter(player);
			arrow.setVelocity(velocity);
			tempBowFire.clear();
			tempBowFire.add(arrow.getUniqueId().toString());
			tempBowFire.add(Instant.now().plusSeconds(5));
			clustArrows.add(tempBowFire);
			
			loc.setZ(finVec.getZ()-0.5);

			Arrow arrow2 = (Arrow) world.spawnEntity(loc, EntityType.ARROW);
			arrow2.setBounce(false);
			arrow2.setShooter(player);
			arrow2.setVelocity(velocity);
			tempBowFire.clear();
			tempBowFire.add(arrow2.getUniqueId().toString());
			tempBowFire.add(Instant.now().plusSeconds(5));
			clustArrows.add(tempBowFire);
			
			loc.setZ(finVec.getZ());
			}	
		
		
		loc.setY(finVec.getY()+0.5);

		Arrow arrow3 = (Arrow) world.spawnEntity(loc, EntityType.ARROW);
		arrow3.setBounce(false);
		arrow3.setShooter(player);
		arrow3.setVelocity(velocity);
		tempBowFire.clear();
		tempBowFire.add(arrow3.getUniqueId().toString());
		tempBowFire.add(Instant.now().plusSeconds(5));
		clustArrows.add(tempBowFire);
		
		loc.setY(finVec.getY()-0.5);

		Arrow arrow4 = (Arrow) world.spawnEntity(loc, EntityType.ARROW);
		arrow4.setBounce(false);
		arrow4.setShooter(player);
		arrow4.setVelocity(velocity);
		tempBowFire.clear();
		tempBowFire.add(arrow4.getUniqueId().toString());
		tempBowFire.add(Instant.now().plusSeconds(5));
		clustArrows.add(tempBowFire);
		
	}

	
	@EventHandler
	public void onArrowHit(ProjectileHitEvent event){
		ArrayList tempList = new ArrayList();

		
		for(int a = 0; a < fireArrows.size();a++){
			tempList = new ArrayList();
			tempList = (ArrayList) fireArrows.get(a);
		//plugin.getServer().broadcastMessage(fireArrows.get(a).toString());	
		if (tempList.get(0).toString().equals(event.getEntity().getUniqueId().toString())) {
			//plugin.getServer().broadcastMessage("Fire Arrow");
			//plugin.getServer().broadcastMessage(nearBy.toString());

			
			

			
			Block tempBlock;
			//plugin.getServer().broadcastMessage(Double.toString(event.getEntity().getLocation().getY()));
			//String splitStart = Double.toString(event.getEntity().getLocation().getY());
			//String[] splitList1 = splitStart.split(".");
			//String[] splitList2 = splitList1[1].split("");
			//plugin.getServer().broadcastMessage(splitList2[0]);
			
			
			
			
			
			tempBlock = event.getEntity().getWorld().getBlockAt(event.getEntity().getLocation().getBlockX(), event.getEntity().getLocation().getBlockY(), event.getEntity().getLocation().getBlockZ());
			
			//plugin.getServer().broadcastMessage(tempBlock.getType().name());

			if(burnables.contains(tempBlock.getType().name())){
			cBlocks.update(tempBlock.getLocation(), tempBlock.getType(), Instant.now().plusSeconds(5));
			tempBlock.setType(Material.FIRE);
			}
			tempBlock = event.getEntity().getWorld().getBlockAt(event.getEntity().getLocation().getBlockX()+1, event.getEntity().getLocation().getBlockY(), event.getEntity().getLocation().getBlockZ());
			//plugin.getServer().broadcastMessage(tempBlock.getType().name());
			if(burnables.contains(tempBlock.getType().name())){
			cBlocks.update(tempBlock.getLocation(), tempBlock.getType(), Instant.now().plusSeconds(5));
			tempBlock.setType(Material.FIRE);
			}
			
			tempBlock = event.getEntity().getWorld().getBlockAt(event.getEntity().getLocation().getBlockX()-1, event.getEntity().getLocation().getBlockY(), event.getEntity().getLocation().getBlockZ());
			//plugin.getServer().broadcastMessage(tempBlock.getType().name());
			if(burnables.contains(tempBlock.getType().name())){
			cBlocks.update(tempBlock.getLocation(), tempBlock.getType(), Instant.now().plusSeconds(5));
			tempBlock.setType(Material.FIRE);
			}
			
			tempBlock = event.getEntity().getWorld().getBlockAt(event.getEntity().getLocation().getBlockX(), event.getEntity().getLocation().getBlockY(), event.getEntity().getLocation().getBlockZ()+1);
			//plugin.getServer().broadcastMessage(tempBlock.getType().name());
			if(burnables.contains(tempBlock.getType().name())){
			cBlocks.update(tempBlock.getLocation(), tempBlock.getType(), Instant.now().plusSeconds(5));
			tempBlock.setType(Material.FIRE);
			}
			
			tempBlock = event.getEntity().getWorld().getBlockAt(event.getEntity().getLocation().getBlockX(), event.getEntity().getLocation().getBlockY(), event.getEntity().getLocation().getBlockZ()-1);
			//plugin.getServer().broadcastMessage(tempBlock.getType().name());
			if(burnables.contains(tempBlock.getType().name())){
			cBlocks.update(tempBlock.getLocation(), tempBlock.getType(), Instant.now().plusSeconds(5));
			
			tempBlock.setType(Material.FIRE);
			}
			event.getEntity().remove();
			
			}
		}	
		
		
		for(int a = 0; a < iceArrows.size();a++){
			tempList = new ArrayList(); 
			tempList = (ArrayList) iceArrows.get(a);
		//plugin.getServer().broadcastMessage(iceArrows.get(a).toString());	
		if (tempList.get(0).toString().equals(event.getEntity().getUniqueId().toString())) {
			//plugin.getServer().broadcastMessage("Freeze Arrow");

			Block tempBlock;
			


			tempBlock = event.getEntity().getWorld().getBlockAt(event.getEntity().getLocation().getBlockX()+1, event.getEntity().getLocation().getBlockY(), event.getEntity().getLocation().getBlockZ());
			if(burnables.contains(tempBlock.getType().name())){
			cBlocks.update(tempBlock.getLocation(), tempBlock.getType(), Instant.now().plusSeconds(5));
			tempBlock.setType(Material.SNOW);
			}
			
			tempBlock = event.getEntity().getWorld().getBlockAt(event.getEntity().getLocation().getBlockX()-1, event.getEntity().getLocation().getBlockY(), event.getEntity().getLocation().getBlockZ());
			if(burnables.contains(tempBlock.getType().name())){
			cBlocks.update(tempBlock.getLocation(), tempBlock.getType(), Instant.now().plusSeconds(5));
			tempBlock.setType(Material.SNOW);
			}
			
			
			tempBlock = event.getEntity().getWorld().getBlockAt(event.getEntity().getLocation().getBlockX(), event.getEntity().getLocation().getBlockY(), event.getEntity().getLocation().getBlockZ()+1);
			if(burnables.contains(tempBlock.getType().name())){
			cBlocks.update(tempBlock.getLocation(), tempBlock.getType(), Instant.now().plusSeconds(5));
			tempBlock.setType(Material.SNOW);
			}
			
			tempBlock = event.getEntity().getWorld().getBlockAt(event.getEntity().getLocation().getBlockX(), event.getEntity().getLocation().getBlockY(), event.getEntity().getLocation().getBlockZ()-1);
			if(burnables.contains(tempBlock.getType().name())){
			cBlocks.update(tempBlock.getLocation(), tempBlock.getType(), Instant.now().plusSeconds(5));
			tempBlock.setType(Material.SNOW);
			}
			
			
			
			tempBlock = event.getEntity().getWorld().getBlockAt(event.getEntity().getLocation().getBlockX(), event.getEntity().getLocation().getBlockY(), event.getEntity().getLocation().getBlockZ());
			if(burnables.contains(tempBlock.getType().name())){
			cBlocks.update(tempBlock.getLocation(), tempBlock.getType(), Instant.now().plusSeconds(5));
			tempBlock.setType(Material.SNOW);
			}
			
			event.getEntity().remove();
			}
		}	
		
		for(int d = 0; d < clustArrows.size();d++){
			tempList = new ArrayList();
			tempList = (ArrayList) clustArrows.get(d);
			if(event.getEntity().getUniqueId().toString().equals(tempList.get(0))){
				//event.getEntity().remove();
				
			}
			
		}

	}

}
/*
 * Version 1.2
 * By K0Gs
 */