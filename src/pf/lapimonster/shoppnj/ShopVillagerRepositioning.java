package pf.lapimonster.shoppnj;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class ShopVillagerRepositioning implements Runnable
{
	private Shop shop;
	private BukkitTask task;
	
	public ShopVillagerRepositioning(Shop shop)
	{
		this.shop = shop;
	}
	
	public void start()
	{
		this.task = Bukkit.getServer().getScheduler().runTaskTimer(ShopPNJ.getInstance(), this, 0L, 5*20L);
	}
	
	public void stop()
	{
		this.task.cancel();
	}

	@Override
	public void run()
	{
		if(this.shop.getVillager().getLocation().getX() != this.shop.getSpawn().getX() ||
				this.shop.getVillager().getLocation().getY() != this.shop.getSpawn().getY() ||
				this.shop.getVillager().getLocation().getZ() != this.shop.getSpawn().getZ())
		{
			this.shop.getVillager().teleport(this.shop.getSpawn());
		}
	}

}
