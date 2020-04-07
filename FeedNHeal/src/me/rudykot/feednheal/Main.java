package me.rudykot.feednheal;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();;
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("feed")){
        	
        	if(args.length>0) {
        		if(args[0]!=null) {
        			if(sender.hasPermission("feednheal.feed.others")) {
        				Player p =Bukkit.getPlayer(args[0]);
        				if(p!=null) {
        					p.setFoodLevel(20);
        					p.sendMessage(getPlayerMessage("fed-other",p.getName()));
        				}
        				else {
        					sender.sendMessage(getMessage("no-player-online"));
        				}
        			}
        			else {
        				sender.sendMessage(getMessage("no-permission"));
        			}
        			return true;
        		}
        	}
        	
            if(sender.hasPermission("feednheal.feed")){
                if(sender instanceof Player){
                    ((Player)sender).setFoodLevel(20);
                    sender.sendMessage(getMessage("fed-yourself"));
                }
                else{
                    sender.sendMessage(getMessage("only-for-players"));
                }
            }
            else{
                sender.sendMessage(getMessage("no-permission"));
            }
        }

        if(command.getName().equalsIgnoreCase("heal")){
        	
        	if(args.length>0) {
        		if(args[0]!=null) {
        			if(sender.hasPermission("feednheal.heal.others")) {
        				Player p =Bukkit.getPlayer(args[0]);
        				if(p!=null) {
        					p.setHealth(20);
        					p.sendMessage(getPlayerMessage("healed-other",p.getName()));
        				}
        				else {
        					sender.sendMessage(getMessage("no-player-online"));
        				}
        			}
        			else {
        				sender.sendMessage(getMessage("no-permission"));
        			}
        			return true;
        		}
        	}
        	
            if(sender.hasPermission("feednheal.heal")){
                if(sender instanceof Player){
                    ((Player)sender).setHealth(20);
                    sender.sendMessage(getMessage("healed-yourself"));
                }
                else{
                    sender.sendMessage(getMessage("only-for-players"));
                }
            }
            else{
                sender.sendMessage(getMessage("no-permission"));
            }
        }
        return true;
    }

    public String getMessage(String path){
        return getConfig().getString("messages."+path).replace('&', ChatColor.COLOR_CHAR);
    }
    
    public String getPlayerMessage(String path, String player){
        return getConfig().getString("messages."+path).replace('&', ChatColor.COLOR_CHAR).replace("%player%", player);
    }
}

