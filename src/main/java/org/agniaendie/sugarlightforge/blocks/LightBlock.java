package org.agniaendie.sugarlightforge.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.agniaendie.sugarlightforge.blocks.tileentity.TileEntityLight;
import org.agniaendie.sugarlightforge.blocks.tileentity.TileRegistry;
import javax.annotation.Nonnull;
import java.util.Random;

public class LightBlock extends Block {
    public static final IntegerProperty POWER = BlockStateProperties.POWER;
    Properties props;
    public LightBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
        this.props = p_i48440_1_;
        this.registerDefaultState(this.stateDefinition.any().setValue(POWER, 15));
    }
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(POWER);

    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(this.asItem());
    }

    private int updatedLight = 15;
    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState state, World world, @Nonnull BlockPos pos,@Nonnull PlayerEntity entityIn,@Nonnull Hand handIn,@Nonnull BlockRayTraceResult hit) {
        if(!world.isClientSide()){
            TileEntity tile = world.getBlockEntity(pos);
            if(tile instanceof TileEntityLight){
                int currentLight = ((TileEntityLight) tile).getLightLevel();
                CompoundNBT nbt = new CompoundNBT();
                if(entityIn.isShiftKeyDown()){
                    updatedLight = currentLight;
                    if(currentLight > 0){
                        updatedLight-=1;
                    }
                }else{
                    if(currentLight < 15){
                        updatedLight +=1;
                    }
                }
                getLightValue(state,world,pos);
                this.properties.lightLevel(BlockState -> updatedLight);
                ((TileEntityLight) tile).setLightLevel(updatedLight);
                tile.save(nbt);
                //world.setBlock(pos, state.setValue(POWER, updatedLight), 512);
                //world.setBlockAndUpdate(pos,state.setValue(POWER,updatedLight));
                world.setBlockAndUpdate(pos,this.defaultBlockState());

            }else{
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        TileEntity entity = world.getBlockEntity(pos);
        if(entity instanceof TileEntityLight){
            CompoundNBT compoundNBT = new CompoundNBT();
            entity.load(state,compoundNBT);
        }
        return updatedLight;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, @Nonnull BlockPos pos,@Nonnull Random random) {
        world.setBlock(pos,state.setValue(POWER, updatedLight),2);
    }

    @Override
    public int getLightBlock(BlockState state, IBlockReader reader, BlockPos pos) {
        return this.updatedLight;
    }



    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileRegistry.LIGHT_TILE.get().create();
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}
