package org.agniaendie.sugarlightforge.blocks.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static org.agniaendie.sugarlightforge.blocks.BlockRegistry.LIGHT_BLOCK;


public class TileRegistry {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(
            ForgeRegistries.TILE_ENTITIES, "sugarlight"
    );

    public static final RegistryObject<TileEntityType<TileEntityLight>> LIGHT_TILE = TILE_ENTITIES.register(
            "light_tile_entity", () -> TileEntityType.Builder.of(
                      TileEntityLight::new, LIGHT_BLOCK.getBlock()
            ).build(null));
    public static void register(IEventBus eventBus){
        TILE_ENTITIES.register(eventBus);
    }
}
