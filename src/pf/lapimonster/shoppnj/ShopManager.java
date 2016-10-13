package pf.lapimonster.shoppnj;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ShopManager
{
	@SuppressWarnings("deprecation")
	public ShopManager()
	{
		Shop shop1 = new Shop("Shop-1", 9);
		
		ItemStack poisson = new ItemStack(Material.RAW_FISH, 1);
		ItemStack patate = new ItemStack(392, 1);
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 1);
		ItemStack pomme = new ItemStack(Material.APPLE, 1);
		ItemStack pain = new ItemStack(Material.BREAD, 1);
		
		shop1.addItem(new ShopItemStack("Poisson", poisson, 4, new ItemStack(Material.RAW_FISH, 1)));
		shop1.addItem(new ShopItemStack("Patate", patate, 4, new ItemStack(392, 1)));
		shop1.addItem(new ShopItemStack("Steak cuit", steak, 10, new ItemStack(Material.COOKED_BEEF, 1)));
		shop1.addItem(new ShopItemStack("Pomme", pomme, 5, new ItemStack(Material.APPLE, 1)));
		shop1.addItem(new ShopItemStack("Pain", pain, 5, new ItemStack(Material.BREAD, 1)));
		
		shop1.updateInventory();
		
		
		Shop shop2 = new Shop("Shop-2", 3*9);
		ItemStack i1 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemStack i2 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		ItemStack i3 = new ItemStack(Material.DIAMOND_SPADE, 1);
		ItemStack i4 = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemStack i5 = new ItemStack(Material.DIAMOND_HOE, 1);
		
		shop2.addItem(new ShopItemStack("Epée en diamond", i1, 987, new ItemStack(Material.DIAMOND_SWORD, 1)));
		shop2.addItem(new ShopItemStack("Pioche en diamond", i2, 987, new ItemStack(Material.DIAMOND_PICKAXE, 1)));
		shop2.addItem(new ShopItemStack("Pelle en diamond", i3, 987, new ItemStack(Material.DIAMOND_SPADE, 1)));
		shop2.addItem(new ShopItemStack("Hache en diamond", i4, 987, new ItemStack(Material.DIAMOND_AXE, 1)));
		shop2.addItem(new ShopItemStack("Houe en diamond", i5, 987, new ItemStack(Material.DIAMOND_HOE, 1)));
		
		shop2.updateInventory();
		
		shop2.getHologram().unload();
		shop2.getHologram().setLines(new String[] {"§e§lCLIQUE DROIT", "§bMarchant d'armes"});
		shop2.getHologram().load(shop2.getSpawn());
	}
}
