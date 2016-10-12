package pf.lapimonster.shoppnj;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Shop
{
	private String name;
	private Location spawn;
	private Villager villager;
	private Inventory inventory;
	private List<ShopItemStack> shopItemstacks = new ArrayList<ShopItemStack>();
	
	private ShopVillagerMoveCanceller villagerMoveCanceller;
	
	private static List<Shop> shops = new ArrayList<Shop>();
	
	public Shop(String name, Location location, int iSize)
	{
		shops.add(this);
		this.name = name;
		this.spawn = location;
		this.villager = (Villager) this.spawn.getWorld().spawnEntity(this.spawn, EntityType.VILLAGER);
		this.villager.setAdult();
		this.villager.setCustomName(this.name);
		this.villager.setCustomNameVisible(false);
		
		this.villagerMoveCanceller = new ShopVillagerMoveCanceller(this);
		this.villagerMoveCanceller.start();
		
		this.inventory = Bukkit.createInventory(null, iSize, "");
	}
	
	public void remove()
	{
		shops.remove(this);
	}
	
	public void unspawn()
	{
		this.villagerMoveCanceller.stop();
		this.villager.setHealth(0D);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Location getSpawn()
	{
		return spawn;
	}
	
	public Villager getVillager()
	{
		return this.villager;
	}
	
	public Inventory getInventory()
	{
		return this.inventory;
	}
	
	public ShopItemStack getShopItemStack(ItemStack itemstack)
	{
		for(ShopItemStack i : this.shopItemstacks)
		{
			System.out.println("test : "+i.toString());
			System.out.println("sujet : "+itemstack.toString());
			if(i.isSimilar(itemstack))
				return i;
			
			System.out.println("______");
		}
		
		return null;
	}
	
	
	public void addItem(ShopItemStack itemstack)
	{
		this.shopItemstacks.add(itemstack);
	}
	
	public void updateInventory()
	{
		this.inventory.setContents(this.shopItemstacks.toArray(new ItemStack[this.shopItemstacks.size()]));
	}
	
	
	public static Shop getByVillager(Villager villager)
	{
		for(Shop shop : shops)
		{
			if(shop.getVillager() == villager)
				return shop;
		}
		return null;
	}
	
	public static List<Shop> getShops()
	{
		return shops;
	}
}
