package org.agniaendie.sugarlightforge.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

import java.util.function.ToIntFunction;

public class LightBlockBasic extends Block {
    public LightBlockBasic(Properties properties) {
        super(properties);
        ToIntFunction<BlockState> lightEmission = (property) -> {
            return 12;
        };
        properties.lightLevel(lightEmission);
    }

}
