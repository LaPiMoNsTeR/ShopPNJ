package pf.lapimonster.shoppnj;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ShopOpening implements Runnable
{
	private Shop shop;
	private Player player;
	
	private static List<ShopOpening> shopOpenings = new ArrayList<ShopOpening>();
	
	public ShopOpening(Shop shop, Player player)
	{
		shopOpenings.add(this);
		this.shop = shop;
		this.player = player;
	}
	
	public void remove()
	{
		shopOpenings.remove(this);
	}
	
	public boolean isRemoved()
	{
		return !shopOpenings.contains(this);
	}
	
	public Shop getShop()
	{
		return shop;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void openInventory()
	{
		Bukkit.getServer().getScheduler().runTask(ShopPNJ.getInstance(), this);
	}
	
	
	@Override
	public void run()
	{
		this.player.openInventory(this.shop.getInventory());
	}
	
	
	public static ShopOpening getByPlayer(Player player)
	{
		for(ShopOpening shopOpening : shopOpenings)
		{
			if(shopOpening.getPlayer() == player)
				return shopOpening;
		}
		return null;
	}
	
	public static List<ShopOpening> getShopOpenings()
	{
		return shopOpenings;
	}
}
