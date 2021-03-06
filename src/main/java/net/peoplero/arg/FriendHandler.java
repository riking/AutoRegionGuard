package net.peoplero.arg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FriendHandler {
	
	ARG plugin;
	static Map<String, Set<String>> FriendsList = new HashMap<String, Set<String>>(); 

	FriendHandler(ARG plugin) {
		this.plugin = plugin;
	}
	
	public void addfriend(Player player, String friend) {
		String playername = player.getName().toLowerCase();
		if (FriendsList.containsKey(playername) == false){
			Set<String> blanklist = new HashSet<String>();
			FriendsList.put(playername, blanklist);
		}
		Set<String> list = FriendsList.get(playername);
		list.add(friend.toLowerCase());
		FriendsList.put(playername, list);
		player.sendMessage(ChatColor.YELLOW + "Added friend: " + friend);
	}

	public void removefriend(Player player, String friend) {
		String playername = player.getName().toLowerCase();
		if (FriendsList.containsKey(playername) == false){
			player.sendMessage(ChatColor.RED + "You don't have any friends!");
			return;
		}
		if (FriendsList.get(playername).remove(friend.toLowerCase())){
			player.sendMessage(ChatColor.YELLOW + "Removed friend " + friend + ".");
		} else player.sendMessage(ChatColor.RED + "You don't have a friend named " + friend + ".");
	}
	
	public boolean isafriend(Player player, String owner){
		String playername = player.getName().toLowerCase();
		if (FriendsList.containsKey(owner.toLowerCase())){
			if (FriendsList.get(owner.toLowerCase()).contains(playername)) return true;
		}
		return false;
	}

	public void saveFriends() {
		FileHandler.saveMultiMap(FriendsList, "FriendsList.txt");
	}

	public void loadFriends() {
		FriendsList = FileHandler.loadMultiMap("FriendsList.txt");
	}

	public String listFriends(Player player) {
		String friends = "";
		String playername = player.getName().toLowerCase();
		if (FriendsList.containsKey(playername)){
			for (String friend : FriendsList.get(playername)){
				friends = friends + " " + friend;
			}
			friends = friends.trim().replace(" ", ", ");
		}
		return friends;
	}

}
