package com.github.mrbestcreator.magicalmechanics.api.block;

import com.github.mrbestcreator.magicalmechanics.registry.block.BlockEntityRegistryImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BaseBlockEntity extends BlockEntity {
    
    private BlockContext context;
    private BlockLogic logic;
    
    public BaseBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistryImpl.get(state), pos, state);
        BlockEntry e = entry();
        if (e.logic() != null) {
            this.logic = e.logic().apply(this);
        }
        
    }
    
    public BlockEntry entry() {
        return ((BaseEntityBlock) getBlockState().getBlock()).getEntry();
    }
    
    private BlockContext context() {
        if (context == null) {
            context = new BlockContext(this);
        }
        return context;
    }
    
    public void tick() {
        Level level = this.level;
        if (level == null || level.isClientSide) return;
        
        BlockEntry e = entry();
        BlockContext ctx = context();
        
        for (BlockHook hook : e.hooks()) {
            hook.beforeTick(ctx);
        }
        
        if (e.logic() != null) {
            logic.tick(ctx.be());
        }
        
        for (BlockHook hook : e.hooks()) {
            hook.afterTick(ctx);
        }
    }
}
