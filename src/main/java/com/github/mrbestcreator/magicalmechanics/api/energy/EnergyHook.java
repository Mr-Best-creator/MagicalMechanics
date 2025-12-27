package com.github.mrbestcreator.magicalmechanics.api.energy;

public interface EnergyHook {
    
    default EnergyFlowEntry onReceive(long amount, boolean simulate) {
        return new EnergyFlowEntry(amount, amount);
    }
    
    default EnergyFlowEntry onExtract(long amount, boolean simulate) {
        return new EnergyFlowEntry(amount, amount);
    }
}
