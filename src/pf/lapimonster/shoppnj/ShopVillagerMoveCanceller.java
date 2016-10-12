package pf.lapimonster.shoppnj;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class ShopVillagerMoveCanceller implements Runnable
{
	private Shop shop;
	private BukkitTask task;

	public ShopVillagerMoveCanceller(Shop shop)
	{
		this.shop = shop;
	}
	
	public void start()
	{
		this.task = Bukkit.getServer().getScheduler().runTaskTimer(ShopPNJ.getInstance(), this, 0L, 10L);
	}
	
	public void stop()
	{
		this.task.cancel();
	}
	
	@Override
	public void run()
	{
		this.shop.getVillager().teleport(this.shop.getSpawn());
	}

}
