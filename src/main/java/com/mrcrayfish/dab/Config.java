package com.mrcrayfish.dab;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config
{
	public static Configuration config;
	
	public static final String CATEGORY_GENERAL = "general";
	
	public static boolean showHint;
	
	public static void init(File file)
	{
		if (config == null)
		{
			config = new Configuration(file);
		}
		loadConfig();
		config.save();
	}
	
	public static void loadConfig()
	{
		showHint = config.getBoolean("show-hint", CATEGORY_GENERAL, true, "Hint the key to Dab upon joining a world.");
	}
}
