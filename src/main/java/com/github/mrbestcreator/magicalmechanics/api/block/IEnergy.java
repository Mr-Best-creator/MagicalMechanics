package com.github.mrbestcreator.magicalmechanics.api.block;

public interface IEnergy {
    long extract(long amount, boolean simulate);
    long receive(long amount, boolean simulate);
    
    long getEnergy();
    long getCapacity();
    
    boolean canExtract();
    boolean canReceive();
}
