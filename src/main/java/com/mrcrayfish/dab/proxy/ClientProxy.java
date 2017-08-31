package com.mrcrayfish.dab.proxy;

import com.google.common.collect.Lists;
import com.mrcrayfish.dab.client.model.entity.ModelArmorOverride;
import com.mrcrayfish.dab.client.model.entity.ModelPlayerOverride;
import com.mrcrayfish.dab.event.InputEvent;
import com.mrcrayfish.dab.init.ModKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ClientProxy implements IProxy
{
	@Override
	public void init()
	{
		MinecraftForge.EVENT_BUS.register(new InputEvent());

		ModKeys.init();
		ModKeys.register();
	}

	@Override
	public void postInit()
	{
		Map<String, RenderPlayer> skinMap = ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, Minecraft.getMinecraft().getRenderManager(), "field_178636_l");
		if(skinMap != null)
		{
		    RenderPlayer defaultRender = skinMap.get("default");
            setupPlayerModelOverride(defaultRender, false);
            setupArmorModelOverride(defaultRender);

            RenderPlayer slimRender = skinMap.get("slim");
            setupPlayerModelOverride(slimRender, true);
            setupArmorModelOverride(slimRender);
		}
	}

	public void setupPlayerModelOverride(RenderPlayer renderPlayer, boolean slim)
    {
        ModelBase oldModel = ObfuscationReflectionHelper.getPrivateValue(RenderLivingBase.class, renderPlayer, "field_77045_g");
        ObfuscationReflectionHelper.setPrivateValue(RenderLivingBase.class, renderPlayer, new ModelPlayerOverride(oldModel, 0.0F, slim), "field_77045_g");
    }

    public void setupArmorModelOverride(RenderPlayer renderPlayer)
    {
        List<LayerRenderer<EntityLivingBase>> layers = ObfuscationReflectionHelper.getPrivateValue(RenderLivingBase.class, renderPlayer, "field_177097_h");
        if(layers != null)
        {
            LayerRenderer<EntityLivingBase> armorLayer = layers.stream().filter(layer -> layer instanceof LayerBipedArmor).findFirst().orElse(null);
            if(armorLayer != null)
            {
                Field field = ReflectionHelper.<EntityLivingBase>findField(LayerArmorBase.class, ObfuscationReflectionHelper.remapFieldNames(LayerArmorBase.class.getName(), "field_177186_d"));
                field.setAccessible(true);
                try
                {
                    field.set(armorLayer, new ModelArmorOverride());
                }
                catch(IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
