package net.PeytonPlayz585.Optifine;

import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.Minecraft;

public class Config {

    public static float occlusionLevel = GameSettings.ofAoLevel;

    public static String getVersion() {
        return "OptiFine_1.8.8_HD_U_H8";
    }

    public static void showGuiMessage(String p_showGuiMessage_0_, String p_showGuiMessage_1_) {
        GuiMessage guimessage = new GuiMessage(Minecraft.getMinecraft().currentScreen, p_showGuiMessage_0_, p_showGuiMessage_1_);
        Minecraft.getMinecraft().displayGuiScreen(guimessage);
    }

    public static boolean isAnimatedPortal() {
    	return GameSettings.ofAnimatedPortal == 0 ? true : false;
    }

    public static boolean isAnimatedExplosion() {
        return GameSettings.ofAnimatedExplosion;
    }
    
    public static boolean isWaterParticles() {
        return GameSettings.ofWaterParticles;
    }
    
    public static boolean isVoidParticles() {
        return GameSettings.ofVoidParticles;
    }
    
    public static boolean isAnimatedSmoke() {
        return GameSettings.ofAnimatedSmoke;
    }
    
    public static boolean isPotionParticles() {
        return GameSettings.ofPotionParticles;
    }
    
    public static boolean isAnimatedFlame() {
        return GameSettings.ofAnimatedFlame;
    }
    
    public static boolean isAnimatedRedstone() {
        return GameSettings.ofAnimatedRedstone;
    }
    
    public static boolean isDrippingWaterLava() {
        return GameSettings.ofDrippingWaterLava;
    }
    
    public static boolean isFireworkParticles() {
        return GameSettings.ofFireworkParticles;
    }

    public static int getUpdatesPerFrame() {
        return GameSettings.ofChunkUpdates;
    }

    public static boolean isSmoothWorld() {
        return GameSettings.ofSmoothWorld;
    }

    public static void setAmbientOcclusionLevel() {
		occlusionLevel = GameSettings.ofAoLevel;
		BlockModelRenderer.updateAoLightValue();
	}

    public static float getAmbientOcclusionLevel() {
		return occlusionLevel;
	}

    public static boolean isDynamicFov() {
    	return GameSettings.ofDynamicFov;
    }
}