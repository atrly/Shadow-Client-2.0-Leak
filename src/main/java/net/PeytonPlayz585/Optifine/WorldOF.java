package net.PeytonPlayz585.Optifine;

import net.minecraft.client.Minecraft;

public class WorldOF {
    public static void tick() {
        Minecraft.getMinecraft().theWorld.tick();

        if (Minecraft.getMinecraft().theWorld != null && Config.waterOpacityChanged) {
            Config.waterOpacityChanged = false;
            ClearWater.updateWaterOpacity();
        }
    }
}