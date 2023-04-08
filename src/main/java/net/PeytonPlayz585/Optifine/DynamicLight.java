package net.PeytonPlayz585.Optifine;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import net.minecraft.client.Minecraft;

public class DynamicLight {
    private static Entity entity = null;
    private static double offsetY = 0.0D;
    private static double lastPosX = -2.147483648E9D;
    private static double lastPosY = -2.147483648E9D;
    private static double lastPosZ = -2.147483648E9D;
    private static int lastLightLevel = 0;
    private static boolean underwater = false;
    private static long timeCheckMs = 0L;
    private static Set<BlockPos> setLitChunkPos = new HashSet();
    private static BlockPos.MutableBlockPos blockPosMutable = new BlockPos.MutableBlockPos();

    public DynamicLight(Entity p_i36_1_) {
        entity = p_i36_1_;
        offsetY = (double)p_i36_1_.getEyeHeight();
    }

    public static void update() {
        if (Config.isDynamicLightsFast()) {
            long i = System.currentTimeMillis();

            if (i < timeCheckMs + 500L) {
                return;
            }

            timeCheckMs = i;
        }

        double d6 = entity.posX - 0.5D;
        double d0 = entity.posY - 0.5D + offsetY;
        double d1 = entity.posZ - 0.5D;
        int j = DynamicLights.getLightLevel(entity);
        double d2 = d6 - lastPosX;
        double d3 = d0 - lastPosY;
        double d4 = d1 - lastPosZ;
        double d5 = 0.1D;

        if (Math.abs(d2) > d5 || Math.abs(d3) > d5 || Math.abs(d4) > d5 || lastLightLevel != j) {
            lastPosX = d6;
            lastPosY = d0;
            lastPosZ = d1;
            lastLightLevel = j;
            underwater = false;
            World world = Minecraft.getMinecraft().renderGlobal.getWorld();

            if (world != null) {
                blockPosMutable.func_181079_c(MathHelper.floor_double(d6), MathHelper.floor_double(d0), MathHelper.floor_double(d1));
                IBlockState iblockstate = world.getBlockState(blockPosMutable);
                Block block = iblockstate.getBlock();
                underwater = block == Blocks.water;
            }

            Set<BlockPos> set = new HashSet();

            if (j > 0) {
                EnumFacing enumfacing2 = (MathHelper.floor_double(d6) & 15) >= 8 ? EnumFacing.EAST : EnumFacing.WEST;
                EnumFacing enumfacing = (MathHelper.floor_double(d0) & 15) >= 8 ? EnumFacing.UP : EnumFacing.DOWN;
                EnumFacing enumfacing1 = (MathHelper.floor_double(d1) & 15) >= 8 ? EnumFacing.SOUTH : EnumFacing.NORTH;
                BlockPos blockpos = new BlockPos(d6, d0, d1);
                RenderChunk renderchunk = Minecraft.getMinecraft().renderGlobal.getRenderChunk(blockpos);
                RenderChunk renderchunk1 = Minecraft.getMinecraft().renderGlobal.getRenderChunk(renderchunk, enumfacing2);
                RenderChunk renderchunk2 = Minecraft.getMinecraft().renderGlobal.getRenderChunk(renderchunk, enumfacing1);
                RenderChunk renderchunk3 = Minecraft.getMinecraft().renderGlobal.getRenderChunk(renderchunk1, enumfacing1);
                RenderChunk renderchunk4 = Minecraft.getMinecraft().renderGlobal.getRenderChunk(renderchunk, enumfacing);
                RenderChunk renderchunk5 = Minecraft.getMinecraft().renderGlobal.getRenderChunk(renderchunk4, enumfacing2);
                RenderChunk renderchunk6 = Minecraft.getMinecraft().renderGlobal.getRenderChunk(renderchunk4, enumfacing1);
                RenderChunk renderchunk7 = Minecraft.getMinecraft().renderGlobal.getRenderChunk(renderchunk5, enumfacing1);
                updateChunkLight(renderchunk, setLitChunkPos, set);
                updateChunkLight(renderchunk1, setLitChunkPos, set);
                updateChunkLight(renderchunk2, setLitChunkPos, set);
                updateChunkLight(renderchunk3, setLitChunkPos, set);
                updateChunkLight(renderchunk4, setLitChunkPos, set);
                updateChunkLight(renderchunk5, setLitChunkPos, set);
                updateChunkLight(renderchunk6, setLitChunkPos, set);
                updateChunkLight(renderchunk7, setLitChunkPos, set);
            }

            updateLitChunks();
            setLitChunkPos = set;
        }
    }

    private static void updateChunkLight(RenderChunk p_updateChunkLight_1_, Set<BlockPos> p_updateChunkLight_2_, Set<BlockPos> p_updateChunkLight_3_) {
        if (p_updateChunkLight_1_ != null) {
            CompiledChunk compiledchunk = p_updateChunkLight_1_.getCompiledChunk();

            if (compiledchunk != null && !compiledchunk.isEmpty()) {
                p_updateChunkLight_1_.setNeedsUpdate(true);
            }

            BlockPos blockpos = p_updateChunkLight_1_.getPosition();

            if (p_updateChunkLight_2_ != null) {
                p_updateChunkLight_2_.remove(blockpos);
            }

            if (p_updateChunkLight_3_ != null) {
                p_updateChunkLight_3_.add(blockpos);
            }
        }
    }

    public static void updateLitChunks() {
        for (BlockPos blockpos : setLitChunkPos) {
            RenderChunk renderchunk = Minecraft.getMinecraft().renderGlobal.getRenderChunk(blockpos);
            updateChunkLight(renderchunk, (Set<BlockPos>)null, (Set<BlockPos>)null);
        }
    }

    public static Entity getEntity() {
        return entity;
    }

    public static double getLastPosX() {
        return lastPosX;
    }

    public static double getLastPosY() {
        return lastPosY;
    }

    public static double getLastPosZ() {
        return lastPosZ;
    }

    public static int getLastLightLevel() {
        return lastLightLevel;
    }

    public static boolean isUnderwater() {
        return underwater;
    }

    public static double getOffsetY() {
        return offsetY;
    }

    public String toString() {
        return "Entity: " + entity + ", offsetY: " + offsetY;
    }
}