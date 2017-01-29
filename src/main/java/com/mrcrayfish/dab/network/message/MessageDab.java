package com.mrcrayfish.dab.network.message;

import com.mrcrayfish.dab.network.PacketHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageDab implements IMessage, IMessageHandler<MessageDab, IMessage>
{
	private boolean dabbing;

	public MessageDab()
	{
	}

	public MessageDab(boolean dabbing)
	{
		this.dabbing = dabbing;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(dabbing);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.dabbing = buf.readBoolean();
	}

	@Override
	public IMessage onMessage(MessageDab message, MessageContext ctx) 
	{
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		player.getEntityData().setBoolean("dabbing", message.dabbing);
		PacketHandler.INSTANCE.sendToAllAround(new MessageUpdate(player.getEntityId(), message.dabbing), new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 64));
		return null;
	}
}
