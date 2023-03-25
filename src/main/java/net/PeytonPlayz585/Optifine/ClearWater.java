package net.PeytonPlayz585.Optifine;

import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ClearWater {
    public static void updateWaterOpacity() {
        if (Minecraft.getMinecraft().gameSettings != null) {
            int i = 3;

            if (GameSettings.ofClearWater) {
                i = 1;
            }

            BlockLeavesBase.setLightOpacity(Blocks.water, i);
            BlockLeavesBase.setLightOpacity(Blocks.flowing_water, i);
        }

        if (Minecraft.getMinecraft().theWorld != null) {
            IChunkProvider ichunkprovider = Minecraft.getMinecraft().theWorld.getChunkProvider();

            if (ichunkprovider != null) {
                Entity entity = Minecraft.getMinecraft().getRenderViewEntity();

                if (entity != null) {
                    int j = (int)entity.posX / 16;
                    int k = (int)entity.posZ / 16;
                    int l = j - 512;
                    int i1 = j + 512;
                    int j1 = k - 512;
                    int k1 = k + 512;
                    int l1 = 0;

                    for (int i2 = l; i2 < i1; ++i2) {
                        for (int j2 = j1; j2 < k1; ++j2) {
                            if (ichunkprovider.chunkExists(i2, j2)) {
                                Chunk chunk = ichunkprovider.provideChunk(i2, j2);

                                if (chunk != null && !(chunk instanceof EmptyChunk)) {
                                    int k2 = i2 << 4;
                                    int l2 = j2 << 4;
                                    int i3 = k2 + 16;
                                    int j3 = l2 + 16;
                                    BlockPosM blockposm = new BlockPosM(0, 0, 0);
                                    BlockPosM blockposm1 = new BlockPosM(0, 0, 0);

                                    for (int k3 = k2; k3 < i3; ++k3) {
                                        for (int l3 = l2; l3 < j3; ++l3) {
                                            blockposm.setXyz(k3, 0, l3);
                                            BlockPos blockpos = Minecraft.getMinecraft().theWorld.getPrecipitationHeight(blockposm);

                                            for (int i4 = 0; i4 < blockpos.getY(); ++i4) {
                                                blockposm1.setXyz(k3, i4, l3);
                                                IBlockState iblockstate = Minecraft.getMinecraft().theWorld.getBlockState(blockposm1);

                                                if (iblockstate.getBlock().getMaterial() == Material.water) {
                                                	Minecraft.getMinecraft().theWorld.markBlocksDirtyVertical(k3, l3, blockposm1.getY(), blockpos.getY());
                                                    ++l1;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (l1 > 0) {
                    	String s;
                        s = "client";

                        Config.dbg("ClearWater (" + s + ") relighted " + l1 + " chunks");
                    }
                }
            }
        }
    }
}