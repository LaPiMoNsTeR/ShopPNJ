package pf.lapimonster.shoppnj;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ShopListener implements Listener
{
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e)
	{
		if(e.getRightClicked() instanceof Villager)
		{
			if(Shop.getByVillager((Villager) e.getRightClicked()) != null)
			{
				ShopOpening shopOpening = new ShopOpening(Shop.getByVillager((Villager) e.getRightClicked()), e.getPlayer());
				shopOpening.openInventory();
			}
		}
	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent e)
	{
		if(e.getPlayer() instanceof Player)
		{
			ShopOpening shopOpening = ShopOpening.getByPlayer((Player) e.getPlayer());
			
			if(shopOpening != null)
			{
				Shop shop = shopOpening.getShop();
				
				if(!shopOpening.getShop().getInventory().equals(e.getInventory()))
				{
					e.setCancelled(true);
					e.getPlayer().openInventory(shop.getInventory());
				}
			}
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		if(e.getCurrentItem() == null)
			return;
		if(e.getCurrentItem().getType() == Material.AIR)
			return;
		
		ShopOpening shopOpening = ShopOpening.getByPlayer((Player) e.getWhoClicked());
		
		if(shopOpening != null)
		{
			ShopItemStack itemstack = shopOpening.getShop().getShopItemStack(e.getCurrentItem());
			((Player) e.getWhoClicked()).sendMessage(itemstack.getName());
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e)
	{
		ShopOpening shopOpening = ShopOpening.getByPlayer((Player) e.getPlayer());
		
		if(shopOpening != null)
		{
			shopOpening.remove();
		}
	}
	
	@EventHandler
	public void onEntityMove(EntityDamageEvent e)
	{
		if(e.getEntity() instanceof Villager)
		{
			if(Shop.getByVillager((Villager) e.getEntity()) != null)
				e.setCancelled(true);
		}
	}
}

