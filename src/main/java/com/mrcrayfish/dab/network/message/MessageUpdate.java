package com.mrcrayfish.dab.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdate implements IMessage, IMessageHandler<MessageUpdate, IMessage>
{
	private int playerId;
	private boolean dabbing;

	public MessageUpdate()
	{
	}

	public MessageUpdate(int playerId, boolean dabbing)
	{
		this.playerId = playerId;
		this.dabbing = dabbing;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(playerId);
		buf.writeBoolean(dabbing);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.playerId = buf.readInt();
		this.dabbing = buf.readBoolean();
	}

	@Override
	public IMessage onMessage(MessageUpdate message, MessageContext ctx) 
	{
		Entity entity = Minecraft.getMinecraft().world.getEntityByID(message.playerId);
		if(entity instanceof EntityPlayer)
		{
			entity.getEntityData().setBoolean("dabbing", message.dabbing);
		}
		return null;
	}
}
