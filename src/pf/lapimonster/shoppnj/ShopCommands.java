package pf.lapimonster.shoppnj;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(sender instanceof Player == false)
		{
			sender.sendMessage("[ShopPNJ] Vous ne pouvez pas utiliser cette commande avec la console.");
			return true;
		}
		
		
		Player player = (Player) sender;
		
		if(args.length == 0)
		{
			player.sendMessage("[ShopPNJ] §e/shoppnj setloc <menu>");
		}
		else if(args[0].equalsIgnoreCase("setloc"))
		{
			if(args.length >= 2)
			{
				String name = "";
				for(int i=1;i<args.length;i++)
					name += args[i]+" ";
				name = name.substring(0, name.length()-1);
				
				player.sendMessage(name);
				
				Shop shop = Shop.getByName(name);
				
				if(shop == null)
					player.sendMessage("[ShopPNJ] §cShop introuvable.");
				else
				{
					shop.setSpawn(player.getLocation());
					player.sendMessage("[ShopPNJ] §aLocation Villager du shop §c"+shop.getName()+" §amise à jour.");
				}
			}
		}
		return true;
	}

}
