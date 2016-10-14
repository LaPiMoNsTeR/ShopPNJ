package pf.lapimonster.shoppnj;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class ShopPNJ extends JavaPlugin
{
	private static ShopPNJ instance;

	private ShopConfig shopConfig;

	private static Economy economy;

	@Override
	public void onEnable()
	{
		instance = this;

		setupEconomy();

		this.shopConfig = new ShopConfig(this);

		this.getCommand("shoppnj").setExecutor(new ShopCommands());
		this.getServer().getPluginManager().registerEvents(new ShopListener(), this);
		new ShopManager();

		// Shop.getRunnable().start();
	}

	@Override
	public void onDisable()
	{
		// Shop.getRunnable().stop();

		for (Player player : this.getServer().getOnlinePlayers())
		{
			if (player.getOpenInventory() == null)
				continue;
			for (Shop shop : Shop.getShops())
			{
				if (player.getOpenInventory().getTitle().equals(shop.getInventory().getTitle()))
					player.closeInventory();
			}
		}

		Iterator<Shop> it = Shop.getShops().iterator();
		while (it.hasNext())
		{
			Shop shop = it.next();
			shop.unspawn();
			it.remove();
		}
	}

	public ShopConfig getShopConfig()
	{
		return shopConfig;
	}

	private static boolean setupEconomy()
	{
		RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null)
		{
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}

	public static Economy getEconomy()
	{
		return economy;
	}

	public static ShopPNJ getInstance()
	{
		return instance;
	}
}
