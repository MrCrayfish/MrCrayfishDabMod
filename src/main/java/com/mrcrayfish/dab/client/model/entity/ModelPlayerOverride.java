package com.mrcrayfish.dab.client.model.entity;

import com.mrcrayfish.dab.event.InputEvent;

import api.player.model.ModelPlayerAPI;
import api.player.model.ModelPlayerBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelPlayerOverride extends ModelPlayerBase
{
	public ModelPlayerOverride(ModelPlayerAPI modelPlayerAPI)
	{
		super(modelPlayerAPI);
	}

	@Override
	public void afterSetRotationAngles(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, net.minecraft.entity.Entity paramEntity)
	{
		//TODO Add animation
		if(paramEntity.getEntityData().getBoolean("dabbing"))
		{
			this.modelPlayer.bipedRightArm.rotateAngleX = (float) Math.toRadians(-90F);
			this.modelPlayer.bipedRightArm.rotateAngleY = (float) Math.toRadians(-35F);
			
			this.modelPlayer.bipedRightArmwear.rotateAngleX = (float) Math.toRadians(-90F);
			this.modelPlayer.bipedRightArmwear.rotateAngleY = (float) Math.toRadians(-35F);
			
			this.modelPlayer.bipedLeftArm.rotateAngleX = (float) Math.toRadians(15F);
			this.modelPlayer.bipedLeftArm.rotateAngleY = (float) Math.toRadians(15F);
			this.modelPlayer.bipedLeftArm.rotateAngleZ = (float) Math.toRadians(-110F);
			
			this.modelPlayer.bipedLeftArmwear.rotateAngleX = (float) Math.toRadians(15F);
			this.modelPlayer.bipedLeftArmwear.rotateAngleY = (float) Math.toRadians(15F);
			this.modelPlayer.bipedLeftArmwear.rotateAngleZ = (float) Math.toRadians(-110F);
			
			this.modelPlayer.bipedHead.rotateAngleX = (float) Math.toRadians(45F);
			this.modelPlayer.bipedHead.rotateAngleY = (float) Math.toRadians(35F);
			
			this.modelPlayer.bipedHeadwear.rotateAngleX = (float) Math.toRadians(45F);
			this.modelPlayer.bipedHeadwear.rotateAngleY = (float) Math.toRadians(35F);
			
			if(paramEntity.getEntityId() == Minecraft.getMinecraft().thePlayer.getEntityId())
			{
				if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
				{
					this.modelPlayer.bipedRightArm.rotateAngleY -= (float) Math.toRadians(125F);
					this.modelPlayer.bipedRightArmwear.rotateAngleY -= (float) Math.toRadians(125F);
					GlStateManager.rotate(-50F, 1, 0, 0);
					GlStateManager.rotate(30F, 0, 1, 0);
					GlStateManager.rotate(-30F, 0, 0, 1);
					GlStateManager.translate(-0.3, -0.2, -0.5);
					GlStateManager.scale(1.5, 1.5, 1.5);
				}
			}
		}
	}
}
