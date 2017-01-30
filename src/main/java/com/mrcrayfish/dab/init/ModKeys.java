package com.mrcrayfish.dab.init;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModKeys 
{
	public static KeyBinding dab;
	
	public static void init()
	{
		dab = new KeyBinding("key.dab", Keyboard.KEY_LMENU, "key.categories.Dab");
	}
	
	public static void register()
	{
		ClientRegistry.registerKeyBinding(dab);
	}
}
