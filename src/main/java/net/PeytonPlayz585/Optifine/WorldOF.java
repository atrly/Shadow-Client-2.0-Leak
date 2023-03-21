package net.PeytonPlayz585.Optifine;

import net.minecraft.client.Minecraft;

public class WorldOF {
    public static void tick() {
        if(Config.isDynamicLights()) {
			DynamicLights.update();
		}

        Minecraft.getMinecraft().theWorld.tick();
    }
}