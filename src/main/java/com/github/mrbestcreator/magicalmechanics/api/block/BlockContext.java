package com.github.mrbestcreator.magicalmechanics.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class BlockContext {
    
    private final BaseBlockEntity be;
    
    public BlockContext(BaseBlockEntity be) {
        this.be = be;
    }
    
    public Level level() {
        return be.getLevel();
    }
    
    public BlockPos pos() {
        return be.getBlockPos();
    }
    
    public BaseBlockEntity be() {
        return be;
    }
    
    public BlockEntry entry() {
        return be.entry();
    }
}
