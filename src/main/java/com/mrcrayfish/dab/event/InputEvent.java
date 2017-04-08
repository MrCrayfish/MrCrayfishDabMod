package com.mrcrayfish.dab.event;

import org.lwjgl.input.Keyboard;

import com.mrcrayfish.dab.init.ModKeys;
import com.mrcrayfish.dab.network.PacketHandler;
import com.mrcrayfish.dab.network.message.MessageDab;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class InputEvent 
{
	public static boolean dabbing = false;
	public static boolean printed = false;
	
	public static final int MAX_DABBING_HELD = 8;
	public static int dabbingHeld = 0;
	public static int prevDabbingHeld = 0;
	
	public static float firstPersonPartialTicks;
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		if(ModKeys.dab.isKeyDown())
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
	
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) 
	{
		prevDabbingHeld = dabbingHeld;
		
		if(dabbing && dabbingHeld < MAX_DABBING_HELD) 
		{
			dabbingHeld++;
		} 
		else if(!dabbing && dabbingHeld > 0)
		{
			dabbingHeld--;
		}
	}
	
	@SubscribeEvent
	public void onJoin(PlayerEvent.PlayerLoggedInEvent event)
	{
		if(!printed)
		{
			event.player.addChatMessage(new TextComponentString(TextFormatting.GOLD.toString() + TextFormatting.BOLD.toString() + "Press " + Keyboard.getKeyName(ModKeys.dab.getKeyCode()) + " to Dab!"));
			printed = true;
		}
	}
	
	@SubscribeEvent
	public void onRender(RenderHandEvent event) 
	{
		firstPersonPartialTicks = event.getPartialTicks();
	}
}
