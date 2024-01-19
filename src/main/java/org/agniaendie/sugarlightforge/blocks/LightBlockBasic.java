package org.agniaendie.sugarlightforge.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

import java.util.function.ToIntFunction;

public class LightBlock extends Block {
    public LightBlock(Properties properties) {
        super(properties);
        ToIntFunction<BlockState> lightEmission = (property) -> {
            return 12;
        };
        properties.lightLevel(lightEmission);
    }
    
}
