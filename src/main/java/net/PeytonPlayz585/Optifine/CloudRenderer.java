package net.PeytonPlayz585.Optifine;

import net.lax1dude.eaglercraft.v1_8.opengl.EaglercraftGPU;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;

public class CloudRenderer {
    private boolean updated = false;
    private boolean renderFancy = false;
    int cloudTickCounter;
    float partialTicks;
    private int glListClouds = -1;
    private int cloudTickCounterUpdate = 0;
    private double cloudPlayerX = 0.0D;
    private double cloudPlayerY = 0.0D;
    private double cloudPlayerZ = 0.0D;

    public CloudRenderer() {
        this.glListClouds = GLAllocation.generateDisplayLists();
    }

    public void prepareToRender(boolean p_prepareToRender_1_, int p_prepareToRender_2_, float p_prepareToRender_3_) {
        if (this.renderFancy != p_prepareToRender_1_) {
            this.updated = false;
        }

        this.renderFancy = p_prepareToRender_1_;
        this.cloudTickCounter = p_prepareToRender_2_;
        this.partialTicks = p_prepareToRender_3_;
    }

    public boolean shouldUpdateGlList() {
        if (!this.updated) {
            return true;
        } else if (this.cloudTickCounter >= this.cloudTickCounterUpdate + 20) {
            return true;
        } else {
            Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
            boolean flag = this.cloudPlayerY + (double)entity.getEyeHeight() < 128.0D + (double)(GameSettings.ofCloudsHeight * 128.0F);
            boolean flag1 = entity.prevPosY + (double)entity.getEyeHeight() < 128.0D + (double)(GameSettings.ofCloudsHeight * 128.0F);
            return flag1 != flag;
        }
    }

    public void startUpdateGlList() {
    	EaglercraftGPU.glNewList(this.glListClouds, RealOpenGLEnums.GL_COMPILE);
    }

    public void endUpdateGlList() {
        EaglercraftGPU.glEndList();
        this.cloudTickCounterUpdate = this.cloudTickCounter;
        this.cloudPlayerX = Minecraft.getMinecraft().getRenderViewEntity().prevPosX;
        this.cloudPlayerY = Minecraft.getMinecraft().getRenderViewEntity().prevPosY;
        this.cloudPlayerZ = Minecraft.getMinecraft().getRenderViewEntity().prevPosZ;
        this.updated = true;
        GlStateManager.resetColor();
    }

    public void renderGlList() {
        Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
        double d0 = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)this.partialTicks;
        double d1 = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)this.partialTicks;
        double d2 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)this.partialTicks;
        double d3 = (double)((float)(this.cloudTickCounter - this.cloudTickCounterUpdate) + this.partialTicks);
        float f = (float)(d0 - this.cloudPlayerX + d3 * 0.03D);
        float f1 = (float)(d1 - this.cloudPlayerY);
        float f2 = (float)(d2 - this.cloudPlayerZ);
        GlStateManager.pushMatrix();

        if (this.renderFancy) {
            GlStateManager.translate(-f / 12.0F, -f1, -f2 / 12.0F);
        } else {
            GlStateManager.translate(-f, -f1, -f2);
        }

        GlStateManager.callList(this.glListClouds);
        GlStateManager.popMatrix();
        GlStateManager.resetColor();
    }

    public void reset() {
        this.updated = false;
    }
}