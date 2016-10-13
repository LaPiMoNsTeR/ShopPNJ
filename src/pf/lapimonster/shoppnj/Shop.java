package pf.lapimonster.shoppnj;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Shop
{
	private String name;
	private Location spawn;
	private Villager villager;
	private Hologram hologram;
	private Inventory inventory;
	private List<ShopItemStack> shopItemstacks = new ArrayList<ShopItemStack>();
	
	private ShopVillagerMoveCanceller villagerMoveCanceller;
	
	private static List<Shop> shops = new ArrayList<Shop>();
	
	public Shop(String name, int iSize)
	{
		shops.add(this);
		this.name = name;
		
		try
		{
			this.spawn = Location.deserialize(ShopPNJ.getInstance().getShopConfig().getConfig().getConfigurationSection(this.name).getValues(false));
		}
		catch(Exception e)
		{
			this.spawn = Bukkit.getServer().getWorlds().get(0).getSpawnLocation();
		}
		
		this.villager = (Villager) this.spawn.getWorld().spawnEntity(this.spawn, EntityType.VILLAGER);
		this.villager.setAdult();
		this.villager.setMetadata("shop", new FixedMetadataValue(ShopPNJ.getInstance(), this.getName()));
		
		this.hologram = new Hologram(new String[] {"§e§lCLIQUE DROIT", "§bMarchant Général"});
		this.hologram.load(this.spawn);
		
		this.villagerMoveCanceller = new ShopVillagerMoveCanceller(this);
		this.villagerMoveCanceller.start();
		
		this.inventory = Bukkit.createInventory(null, iSize, this.name);
		
		
		Bukkit.getServer().getLogger().info("[ShopPNJ] Shop "+this.getName()+" chargé.");
		
		PlayerInteractEntityEvent event = new PlayerInteractEntityEvent(null, this.villager);
		Bukkit.getServer().getPluginManager().callEvent(event);
	}
	
	public void remove()
	{
		shops.remove(this);
	}
	
	public void unspawn()
	{
		this.hologram.unload();
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
			if(i.isSimilar(itemstack))
				return i;
		}
		return null;
	}
	
	public void setSpawn(Location spawn)
	{
		this.spawn = spawn;
		this.villager.teleport(this.spawn);
		ShopPNJ.getInstance().getShopConfig().getConfig().set(this.name, this.spawn.serialize());
		ShopPNJ.getInstance().getShopConfig().saveConfig();
	}
	
	
	public void addItem(ShopItemStack itemstack)
	{
		this.shopItemstacks.add(itemstack);
	}
	
	public void updateInventory()
	{
		this.inventory.setContents(this.shopItemstacks.toArray(new ItemStack[this.shopItemstacks.size()]));
	}
	
	
	public static Shop getByName(String name)
	{
		for(Shop shop : shops)
		{
			if(shop.getName().equals(name))
				return shop;
		}
		
		return null;
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
