package com.github.mrbestcreator.magicalmechanics.content.logic;

import com.github.mrbestcreator.magicalmechanics.api.block.BaseBlockEntity;
import com.github.mrbestcreator.magicalmechanics.api.block.BlockLogic;

public class CreativeEnergyLogic extends BlockLogic {
    
    private static final long MAX = Long.MAX_VALUE;
    
    public long extract(long amount) {
        return amount;
    }
    
    public long getEnergy() {
        return MAX;
    }
    
    @Override
    public void tick(BaseBlockEntity machine) {
    
    }
}
