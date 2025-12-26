package com.github.mrbestcreator.magicalmechanics.api.block;

import net.minecraft.world.level.block.Block;

public class BaseBlock extends Block {
    
    private final BlockEntry entry;
    
    public BaseBlock(Properties props, BlockEntry entry) {
        super(props);
        this.entry = entry;
    }
    
    public BlockEntry getEntry() {
        return entry;
    }
}
