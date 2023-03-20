package net.PeytonPlayz585.Optifine;

import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.Minecraft;

import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;

public class Config {

    public static float occlusionLevel = GameSettings.ofAoLevel;

    private static final Logger LOGGER = LogManager.getLogger();

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

    public static float getFogStart() {
    	return GameSettings.ofFogStart;
    }

    public static boolean isDynamicFov() {
    	return GameSettings.ofDynamicFov;
    }

    public static boolean isFancyFogAvailable() {
        return true;
    }

    public static boolean isFogFancy() {
        return !isFancyFogAvailable() ? false : GameSettings.ofFogType == 2;
    }

    public static boolean isFogFast() {
        return GameSettings.ofFogType == 1;
    }

    public static boolean isFogOff() {
        return GameSettings.ofFogType == 3;
    }

    public static boolean isCloudsOff() {
    	return GameSettings.clouds == 0;
    }
    
    public static boolean isCloudsFast() {
    	return GameSettings.clouds == 1;
    }
    
    public static boolean isCloudsFancy() {
    	return GameSettings.clouds == 2;
    }

    public static boolean isTreesFancy() {
        return GameSettings.ofTrees == 0 ? GameSettings.fancyGraphics : GameSettings.ofTrees != 1;
    }

    public static boolean isTreesSmart() {
        return GameSettings.ofTrees == 4;
    }

    public static boolean isCullFacesLeaves() {
        return GameSettings.ofTrees == 0 ? !GameSettings.fancyGraphics : GameSettings.ofTrees == 4;
    }

    public static void dbg(String p_dbg_0_) {
        LOGGER.info("[Shadow Client] " + p_dbg_0_);
    }

    public static void warn(String p_warn_0_) {
        LOGGER.warn("[Shadow Client] " + p_warn_0_);
    }

    public static ModelManager getModelManager() {
        return Minecraft.getMinecraft().getRenderItem().modelManager;
    }

    public static String arrayToString(Object[] p_arrayToString_0_) {
        if (p_arrayToString_0_ == null) {
            return "";
        } else {
            StringBuffer stringbuffer = new StringBuffer(p_arrayToString_0_.length * 5);

            for (int i = 0; i < p_arrayToString_0_.length; ++i) {
                Object object = p_arrayToString_0_[i];

                if (i > 0) {
                    stringbuffer.append(", ");
                }

                stringbuffer.append(String.valueOf(object));
            }

            return stringbuffer.toString();
        }
    }

    public static String arrayToString(int[] p_arrayToString_0_) {
        if (p_arrayToString_0_ == null) {
            return "";
        } else {
            StringBuffer stringbuffer = new StringBuffer(p_arrayToString_0_.length * 5);

            for (int i = 0; i < p_arrayToString_0_.length; ++i) {
                int j = p_arrayToString_0_[i];

                if (i > 0) {
                    stringbuffer.append(", ");
                }

                stringbuffer.append(String.valueOf(j));
            }

            return stringbuffer.toString();
        }
    }
}