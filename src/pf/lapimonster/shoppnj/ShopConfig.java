package pf.lapimonster.shoppnj;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ShopConfig
{
	private File file;
	private FileConfiguration config;
	
	public ShopConfig(JavaPlugin plugin)
	{
		plugin.saveConfig();
		
		this.file = new File(plugin.getDataFolder(), "config.yml");
		this.config = YamlConfiguration.loadConfiguration(this.file);
	}
	
	public FileConfiguration getConfig()
	{
		return this.config;
	}
	
	public void saveConfig()
	{
		try
		{
			this.config.save(this.file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
