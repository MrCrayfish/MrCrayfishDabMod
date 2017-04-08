package com.mrcrayfish.dab.client.model.entity;

import com.mrcrayfish.dab.event.InputEvent;

import api.player.model.ModelPlayerAPI;
import api.player.model.ModelPlayerBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelPlayerOverride extends ModelPlayerBase
{
	private static float thirdPersonPartialTicks;
	
	public ModelPlayerOverride(ModelPlayerAPI modelPlayerAPI)
	{
		super(modelPlayerAPI);
	}
	
	@Override
	public void afterSetLivingAnimations(EntityLivingBase arg0, float arg1, float arg2, float partialTicks) 
	{
		thirdPersonPartialTicks = partialTicks;
	}

	@Override
	public void afterSetRotationAngles(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, net.minecraft.entity.Entity paramEntity)
	{
		boolean isOurPlayer = paramEntity.getEntityId() == Minecraft.getMinecraft().thePlayer.getEntityId();
		
		if(paramEntity.getEntityData().getBoolean("dabbing") || (isOurPlayer && InputEvent.prevDabbingHeld > 0))
		{
			float heldPercent = (InputEvent.prevDabbingHeld + (InputEvent.dabbingHeld - InputEvent.prevDabbingHeld) * thirdPersonPartialTicks) / InputEvent.MAX_DABBING_HELD;
			
			if(!isOurPlayer) heldPercent = 1.0F;
			
			this.modelPlayer.bipedRightArm.rotateAngleX = (float) Math.toRadians(-90F * heldPercent);
			this.modelPlayer.bipedRightArm.rotateAngleY = (float) Math.toRadians(-35F * heldPercent);
			
			this.modelPlayer.bipedRightArmwear.rotateAngleX = (float) Math.toRadians(-90F * heldPercent);
			this.modelPlayer.bipedRightArmwear.rotateAngleY = (float) Math.toRadians(-35F * heldPercent);
			
			this.modelPlayer.bipedLeftArm.rotateAngleX = (float) Math.toRadians(15F * heldPercent);
			this.modelPlayer.bipedLeftArm.rotateAngleY = (float) Math.toRadians(15F * heldPercent);
			this.modelPlayer.bipedLeftArm.rotateAngleZ = (float) Math.toRadians(-110F * heldPercent);
			
			this.modelPlayer.bipedLeftArmwear.rotateAngleX = (float) Math.toRadians(15F * heldPercent);
			this.modelPlayer.bipedLeftArmwear.rotateAngleY = (float) Math.toRadians(15F * heldPercent);
			this.modelPlayer.bipedLeftArmwear.rotateAngleZ = (float) Math.toRadians(-110F * heldPercent);
			
			this.modelPlayer.bipedHead.rotateAngleX = (float) Math.toRadians(45F * heldPercent);
			this.modelPlayer.bipedHead.rotateAngleY = (float) Math.toRadians(35F * heldPercent);
			
			this.modelPlayer.bipedHeadwear.rotateAngleX = (float) Math.toRadians(45F * heldPercent);
			this.modelPlayer.bipedHeadwear.rotateAngleY = (float) Math.toRadians(35F * heldPercent);
			
			if(isOurPlayer)
			{
				if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
				{
					heldPercent = (InputEvent.prevDabbingHeld + (InputEvent.dabbingHeld - InputEvent.prevDabbingHeld) * InputEvent.firstPersonPartialTicks) / InputEvent.MAX_DABBING_HELD;

					GlStateManager.rotate(-50F * heldPercent, 1, 0, 0);
					GlStateManager.rotate(30F * heldPercent, 0, 1, 0);
					GlStateManager.rotate(-30F * heldPercent, 0, 0, 1);
					GlStateManager.translate(-0.3 * heldPercent, -0.2 * heldPercent, -0.5 * heldPercent);
				}
			}
		}
	}
}
