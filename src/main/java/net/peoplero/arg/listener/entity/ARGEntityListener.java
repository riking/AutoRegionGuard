package net.peoplero.arg.listener.entity;

import net.peoplero.arg.ARG;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;

public class ARGEntityListener extends EntityListener {
	

    private final ARG plugin;
    public static boolean explosionprotection;

    public ARGEntityListener(final ARG plugin) {
        this.plugin = plugin;
    }
    
    @Override
	public void onEntityDamage(EntityDamageEvent event){
    	Entity entity = event.getEntity();
    	if (entity instanceof Player) {
            Player player = (Player) entity;
            if (ARG.isGod(player.getName().toLowerCase())) event.setCancelled(true);
		}
	}
    
//    @Override
//	public void onEntityCombust(EntityCombustEvent event){
//    	Entity entity = event.getEntity();
//    	if (entity instanceof Creeper) event.setCancelled(true);
//	}
    
    @Override
	public void onEntityExplode(EntityExplodeEvent event){
    	if (explosionprotection){
    		for (Block block : event.blockList()){
    			if (plugin.RegionHandler.ClaimCheck(block.getChunk()) != "") event.setCancelled(true);
    		}
    	}
	}
    
}
