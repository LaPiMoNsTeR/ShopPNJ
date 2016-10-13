package pf.lapimonster.shoppnj;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopItemStack extends ItemStack
{
	private String name;
	private int rinaCoins;
	private ItemStack product;
	
	public ShopItemStack(String name, ItemStack itemstack, int rinaCoins, ItemStack product)
	{
		super(itemstack);
		this.name = name;
		this.rinaCoins = rinaCoins;
		
		ItemMeta meta = this.getItemMeta();
		meta.setDisplayName("§e"+this.name);
		List<String> lore = new ArrayList<String>();
		lore.add("§7Achat: §6"+this.rinaCoins+" Rinacoins");
		lore.add("");
		lore.add("§bCliquez-gauche §7pour acheter §aà l'unité §7!");
		meta.setLore(lore);
		this.setItemMeta(meta);
		
		this.product = product;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getRinaCoins()
	{
		return rinaCoins;
	}
	
	public ItemStack getProduct()
	{
		return product;
	}
	
	public void setRinaCoins(int rinaCoins)
	{
		this.rinaCoins = rinaCoins;
	}
	
	public void setProduct(ItemStack product)
	{
		this.product = product;
	}
}
