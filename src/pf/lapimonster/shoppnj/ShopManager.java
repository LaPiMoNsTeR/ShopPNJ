package pf.lapimonster.shoppnj;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ShopManager
{
	@SuppressWarnings("deprecation")
	public ShopManager()
	{
		Shop shop = new Shop("Shop-1", 9);
		
		ItemStack poisson = new ItemStack(Material.RAW_FISH, 1);
		ItemStack patate = new ItemStack(392, 1);
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 1);
		ItemStack pomme = new ItemStack(Material.APPLE, 1);
		ItemStack pain = new ItemStack(Material.BREAD, 1);
		
		shop.addItem(new ShopItemStack("Poisson", poisson, 4, new ItemStack(Material.RAW_FISH, 1)));
		shop.addItem(new ShopItemStack("Patate", patate, 4, new ItemStack(392, 1)));
		shop.addItem(new ShopItemStack("Steak cuit", steak, 10, new ItemStack(Material.COOKED_BEEF, 1)));
		shop.addItem(new ShopItemStack("Pomme", pomme, 5, new ItemStack(Material.APPLE, 1)));
		shop.addItem(new ShopItemStack("Pain", pain, 5, new ItemStack(Material.BREAD, 1)));
		
		shop.updateInventory();
	}
}
