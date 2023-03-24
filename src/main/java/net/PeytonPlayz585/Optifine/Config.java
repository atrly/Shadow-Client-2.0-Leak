package net.PeytonPlayz585.Optifine;

import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.Minecraft;

import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;

import net.minecraft.client.multiplayer.WorldClient;

public class Config {

    public static float occlusionLevel = GameSettings.ofAoLevel;

    private static final Logger LOGGER = LogManager.getLogger();

    private static Thread minecraftThread = null;

    public static boolean chunkFix = true;
    public static boolean chunkFixNether = false;
    public static boolean chunkFixEnd = false;

    public static WorldClient worldClient = null;

    public static void initDisplay() {
        minecraftThread = Thread.currentThread();
        updateThreadPriorities();
    }

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

    public static boolean isSingleProcessor() {
        return true;
    }

    public static void updateThreadPriorities() {
        if (isSingleProcessor()) {
            if (isSmoothWorld()) {
                minecraftThread.setPriority(10);
                setThreadPriority("Server thread", 1);
            } else {
                minecraftThread.setPriority(5);
                setThreadPriority("Server thread", 5);
            }
        } else {
            minecraftThread.setPriority(10);
            setThreadPriority("Server thread", 5);
        }
    }

    private static void setThreadPriority(String p_setThreadPriority_0_, int p_setThreadPriority_1_) {
        try {
            ThreadGroup threadgroup = Thread.currentThread().getThreadGroup();

            if (threadgroup == null) {
                return;
            }

            int i = (threadgroup.activeCount() + 10) * 2;
            Thread[] athread = new Thread[i];
            threadgroup.enumerate(athread, false);

            for (int j = 0; j < athread.length; ++j) {
                Thread thread = athread[j];

                if (thread != null && thread.getName().startsWith(p_setThreadPriority_0_)) {
                    thread.setPriority(p_setThreadPriority_1_);
                }
            }
        } catch (Throwable throwable) {
            warn(throwable.getClass().getName() + ": " + throwable.getMessage());
        }
    }

    public static boolean isMinecraftThread() {
        return Thread.currentThread() == minecraftThread;
    }

    public static void fixChunkLoading() {
        if (chunkFix) {
            if (worldClient != null) {
                Minecraft.getMinecraft().renderGlobal.loadRenderers();
                Minecraft.getMinecraft().renderGlobal.setWorldAndLoadRenderers(worldClient);
                worldClient.updateBlocks();
                chunkFix = false;
            }
        }

        if (chunkFixNether) {
            if (worldClient != null) {
                Minecraft.getMinecraft().renderGlobal.loadRenderers();
                Minecraft.getMinecraft().renderGlobal.setWorldAndLoadRenderers(worldClient);
                worldClient.updateBlocks();
                chunkFixNether = false;
            }
        }

        if (chunkFixEnd) {
            if (worldClient != null) {
                Minecraft.getMinecraft().renderGlobal.loadRenderers();
                Minecraft.getMinecraft().renderGlobal.setWorldAndLoadRenderers(worldClient);
                worldClient.updateBlocks();
                chunkFixEnd = false;
            }
        }
    }

    public static int limit(int p_limit_0_, int p_limit_1_, int p_limit_2_) {
        return p_limit_0_ < p_limit_1_ ? p_limit_1_ : (p_limit_0_ > p_limit_2_ ? p_limit_2_ : p_limit_0_);
    }

    public static float limit(float p_limit_0_, float p_limit_1_, float p_limit_2_) {
        return p_limit_0_ < p_limit_1_ ? p_limit_1_ : (p_limit_0_ > p_limit_2_ ? p_limit_2_ : p_limit_0_);
    }

    public static double limit(double p_limit_0_, double p_limit_2_, double p_limit_4_) {
        return p_limit_0_ < p_limit_2_ ? p_limit_2_ : (p_limit_0_ > p_limit_4_ ? p_limit_4_ : p_limit_0_);
    }

    public static float limitTo1(float p_limitTo1_0_) {
        return p_limitTo1_0_ < 0.0F ? 0.0F : (p_limitTo1_0_ > 1.0F ? 1.0F : p_limitTo1_0_);
    }

    public static boolean isDynamicLights() {
        return GameSettings.ofDynamicLights != 3;
    }

    public static boolean isDynamicLightsFast() {
        return GameSettings.ofDynamicLights == 1;
    }

    public static boolean isDynamicHandLight() {
        return !isDynamicLights() ? false : true;
    }

    public static boolean isBetterGrass() {
        return GameSettings.ofBetterGrass != 3;
    }

    public static boolean isBetterGrassFancy() {
        return GameSettings.ofBetterGrass == 2;
    }

    public static boolean isRainFancy() {
        return GameSettings.ofRain == 0 ? GameSettings.fancyGraphics : GameSettings.ofRain == 2;
    }

    public static boolean isRainOff() {
        return GameSettings.ofRain == 3;
    }

    public static boolean isPasswordHidden() {
        return GameSettings.hidePassword;
    }

    public static boolean isSkyEnabled() {
        return GameSettings.ofSky;
    }
    
    public static boolean isStarsEnabled() {
        return GameSettings.ofStars;
    }
    
    public static boolean isSunMoonEnabled() {
        return GameSettings.ofSunMoon;
    }

    public static boolean isSunTexture() {
        //return !isSunMoonEnabled() ? false : !isShaders() || Shaders.isSun();
    	return isSunMoonEnabled() ? true : false;
    }

    public static boolean isMoonTexture() {
        //return !isSunMoonEnabled() ? false : !isShaders() || Shaders.isMoon();
    	return isSunMoonEnabled() ? true : false;
    }
}