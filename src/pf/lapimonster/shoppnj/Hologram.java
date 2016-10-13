package pf.lapimonster.shoppnj;

import java.util.Arrays;
import java.util.LinkedList;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class Hologram
{
	private LinkedList<String> lines = new LinkedList<String>();
	private LinkedList<ArmorStand> armorStands = new LinkedList<ArmorStand>();
	
	public Hologram(String... lines)
	{
		this.lines.addAll(Arrays.asList(lines));
	}
	
	public void load(Location location)
	{
		double i = 0;
		for(String str : this.lines)
		{
			ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location.clone().add(0, i, 0), EntityType.ARMOR_STAND);
			as.setCustomName(str);
			as.setCustomNameVisible(true);
			as.setInvulnerable(true);
			as.setCollidable(false);
			as.setGravity(false);
			as.setVisible(false);
			this.armorStands.add(as);
			i += 0.3;
		}
	}
	
	public void unload()
	{
		for(ArmorStand as : this.armorStands)
			as.remove();
		this.armorStands = new LinkedList<ArmorStand>();
	}
	
	public void clear()
	{
		this.unload();
		this.lines = new LinkedList<String>();
	}
	
	public void setLines(String... lines)
	{
		this.lines = new LinkedList<String>();
		this.lines.addAll(Arrays.asList(lines));
	}
}
