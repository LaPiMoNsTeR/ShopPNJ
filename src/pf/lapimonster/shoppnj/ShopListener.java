package pf.lapimonster.shoppnj;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import net.milkbowl.vault.economy.Economy;

public class ShopListener implements Listener
{
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e)
	{
		if(e.getRightClicked() instanceof Villager && e.getPlayer() != null)
		{
			Villager villager = (Villager) e.getRightClicked();
			if(villager.hasMetadata("shop"))
			{
				String shop = villager.getMetadata("shop").get(0).asString();
				ShopOpening shopOpening = new ShopOpening(Shop.getByName(shop), e.getPlayer());
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
				if(e.getInventory().getName().equals("mob.villager"))
				{
					e.setCancelled(true);
					return;
				}
				
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
		ShopOpening shopOpening = ShopOpening.getByPlayer((Player) e.getWhoClicked());
		
		if(shopOpening != null)
		{
			if(e.getCurrentItem() == null)
				return;
			if(e.getCurrentItem().getType() == Material.AIR)
				return;
			
			if(e.getClickedInventory() == e.getWhoClicked().getInventory())
			{
				e.setCancelled(true);
				return;
			}
			
			ShopItemStack itemstack = shopOpening.getShop().getShopItemStack(e.getCurrentItem());
			
			Economy eco = ShopPNJ.getEconomy();
			
			if(eco.has((OfflinePlayer) e.getWhoClicked(), itemstack.getRinaCoins()))
			{
				((Player) e.getWhoClicked()).sendMessage("Vous avez acheté 1 §a"+itemstack.getName()+" §rà §c"+itemstack.getRinaCoins()+" Rinacoins§r.");
				e.getWhoClicked().getInventory().addItem(itemstack.getProduct());
				eco.withdrawPlayer((OfflinePlayer) e.getWhoClicked(), itemstack.getRinaCoins());
			}
			else e.getWhoClicked().sendMessage("§cVous n'avez pas assez d'argent pour acheter cette item.");
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
			Villager villager = (Villager) e.getEntity();
			if(villager.hasMetadata("shop"))
			{
				Shop shop = Shop.getByName(villager.getMetadata("shop").get(0).asString());
				if(shop != null)
					e.setCancelled(true);
			}
		}
	}
}

