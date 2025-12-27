package com.github.mrbestcreator.magicalmechanics.content.energyhook;

import com.github.mrbestcreator.magicalmechanics.api.energy.EnergyFlowEntry;
import com.github.mrbestcreator.magicalmechanics.api.energy.EnergyHook;

public class InfiniteEnergyHook implements EnergyHook {
    
    @Override
    public EnergyFlowEntry onExtract(long amount, boolean simulate) {
        return new EnergyFlowEntry(amount, 0); // 減らさない
    }
}
