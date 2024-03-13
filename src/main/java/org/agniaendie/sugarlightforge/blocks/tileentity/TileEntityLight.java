package org.agniaendie.sugarlightforge.blocks.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityLight extends TileEntity {
    private int lightLevel = 15;

    public TileEntityLight(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }
    public TileEntityLight(){
        this(TileRegistry.LIGHT_TILE.get());
    }
    public int getLightLevel(){
        return lightLevel;
    }
    public void setLightLevel(int level){

        this.lightLevel = level;
    }
    @Override
    public void load(BlockState state, CompoundNBT nbt){
        nbt.getInt("sugar_level");
        //System.out.println(nbt.getInt("sugar_level"));
        super.load(state,nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt){
        //nbt.put("sugar_level", IntNBT.valueOf(lightLevel));
        nbt.putInt("sugar_level", lightLevel);
        //System.out.println(lightLevel + "lightLevel");
        return super.save(nbt);
    }


}
