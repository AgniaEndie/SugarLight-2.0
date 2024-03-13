package org.agniaendie.sugarlightforge.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

import java.util.function.ToIntFunction;

public class BlockRegistry {

    static ToIntFunction<BlockState> lightEmission = (property) -> {
        return 0;
    };


    public static final Block LIGHT_BLOCK_BASIC = new LightBlockBasic(AbstractBlock.Properties.of(Material.BARRIER, MaterialColor.COLOR_BLUE).lightLevel(lightEmission)).setRegistryName("light_block_basic");
    public static final Block LIGHT_BLOCK = new LightBlock(AbstractBlock.Properties.of(Material.BARRIER, MaterialColor.COLOR_RED).lightLevel(lightEmission)).setRegistryName("light_block");

}
