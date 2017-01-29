package com.mrcrayfish.dab.proxy;

import com.mrcrayfish.dab.Reference;
import com.mrcrayfish.dab.client.model.entity.ModelPlayerOverride;
import com.mrcrayfish.dab.event.InputEvent;

import api.player.model.ModelPlayerAPI;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy implements IProxy
{
	@Override
	public void init()
	{
		MinecraftForge.EVENT_BUS.register(new InputEvent());
		ModelPlayerAPI.register(Reference.MOD_ID, ModelPlayerOverride.class);
	}
}
