package com.mrcrayfish.dab.event;

import org.lwjgl.input.Keyboard;

import com.mrcrayfish.dab.network.PacketHandler;
import com.mrcrayfish.dab.network.message.MessageDab;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class InputEvent 
{
	public static boolean dabbing = false;
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		if(GuiScreen.isAltKeyDown())
		{
			if(!dabbing)
			{
				PacketHandler.INSTANCE.sendToServer(new MessageDab(true));
				dabbing = true;
			}
		}
		else
		{
			PacketHandler.INSTANCE.sendToServer(new MessageDab(false));
			dabbing = false;
		}
	}
}
