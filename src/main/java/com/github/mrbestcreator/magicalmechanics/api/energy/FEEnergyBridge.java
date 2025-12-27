package com.github.mrbestcreator.magicalmechanics.api.energy;

import com.github.mrbestcreator.magicalmechanics.api.block.IEnergy;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class FEEnergyBridge implements IEnergyStorage {
    
    private final IEnergy energy;
    
    public FEEnergyBridge(IEnergy energy) {
        this.energy = energy;
    }
    
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        long r = energy.receive(maxReceive, simulate);
        return (int) Math.min(Integer.MAX_VALUE, r);
    }
    
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        long e = energy.extract(maxExtract, simulate);
        return (int) Math.min(Integer.MAX_VALUE, e);
    }
    
    @Override
    public int getEnergyStored() {
        return (int) Math.min(Integer.MAX_VALUE, energy.getEnergy());
    }
    
    @Override
    public int getMaxEnergyStored() {
        return (int) Math.min(Integer.MAX_VALUE, energy.getCapacity());
    }
    
    @Override
    public boolean canExtract() {
        return energy.canExtract();
    }
    
    @Override
    public boolean canReceive() {
        return energy.canReceive();
    }
}
