package pf.lapimonster.shoppnj;

import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ShopPNJ extends JavaPlugin
{
	private static ShopPNJ instance;
	
	@Override
	public void onEnable()
	{
		instance = this;
		
		this.getServer().getPluginManager().registerEvents(new ShopListener(), this);
		new ShopManager();
	}
	
	@Override
	public void onDisable()
	{
		for(Player player : this.getServer().getOnlinePlayers())
		{
			if(player.getOpenInventory() == null) continue;
			for(Shop shop : Shop.getShops())
			{
				if(player.getOpenInventory().equals(shop.getInventory()))
					player.closeInventory();;
			}
		}
		
		Iterator<Shop> it = Shop.getShops().iterator();
		while(it.hasNext())
		{
			Shop shop = it.next();
			shop.unspawn();
			it.remove();
		}
	}
	
	
	public static ShopPNJ getInstance()
	{
		return instance;
	}
}
