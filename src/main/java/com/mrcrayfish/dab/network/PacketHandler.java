package com.mrcrayfish.dab.network;

import com.mrcrayfish.dab.Reference;
import com.mrcrayfish.dab.network.message.MessageDab;
import com.mrcrayfish.dab.network.message.MessageUpdate;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

	public static void init()
	{
		INSTANCE.registerMessage(MessageDab.class, MessageDab.class, 0, Side.SERVER);
		INSTANCE.registerMessage(MessageUpdate.class, MessageUpdate.class, 1, Side.CLIENT);
	}
}
