package com.github.mrbestcreator.magicalmechanics.api.block;

import com.github.mrbestcreator.magicalmechanics.api.energy.EnergyFlowEntry;
import com.github.mrbestcreator.magicalmechanics.api.energy.EnergyHook;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.List;

public class EnergyComponent implements IEnergy {
    
    private final EnergyDef def;
    private long energy;
    private final List<EnergyHook> hooks = new ArrayList<>();
    
    public EnergyComponent(EnergyDef def) {
        this.def = def;
        this.energy = 0;
    }
    
    @Override
    public long extract(long amount, boolean simulate) {
        if (!canExtract()) return 0;
        EnergyFlowEntry entry = new EnergyFlowEntry(amount, amount);
        for (EnergyHook h : hooks) {
            entry = h.onExtract(amount, simulate);
            if (entry.transferAmount() <= 0) return 0;
        }
        long transfer = entry.transferAmount();
        long storage = entry.storageAmount();
        long extracted = Math.min(transfer, Math.min(def.maxOutput, energy)
        );
        if (!simulate && storage > 0) {
            energy -= Math.min(storage, extracted);
        }
        return extracted;
    }
    
    @Override
    public long receive(long amount, boolean simulate) {
        if (!canReceive()) return 0;
        EnergyFlowEntry entry = new EnergyFlowEntry(amount, amount);
        for (EnergyHook h : hooks) {
            entry = h.onReceive(amount, simulate);
            if (entry.transferAmount() <= 0) return 0;
        }
        long transfer = entry.transferAmount();
        long storage = entry.storageAmount();
        long accepted = Math.min(transfer, Math.min(def.maxInput, def.capacity - energy)
        );
        if (!simulate && storage > 0) {
            energy += Math.min(storage, accepted);
        }
        return accepted;
    }
    
    @Override
    public long getEnergy() {
        return energy;
    }
    
    @Override
    public long getCapacity() {
        return def.capacity;
    }
    
    @Override
    public boolean canExtract() {
        return def.maxOutput > 0;
    }
    
    @Override
    public boolean canReceive() {
        return def.maxInput > 0;
    }
    
    public void save(CompoundTag tag) {
        tag.putLong("Energy", energy);
    }
    
    public void load(CompoundTag tag) {
        energy = tag.getLong("Energy");
    }
    
    public void addHook(EnergyHook hook) {
        hooks.add(hook);
    }
}
