package com.github.mrbestcreator.magicalmechanics.content.logic;

import com.github.mrbestcreator.magicalmechanics.api.block.BaseBlockEntity;
import com.github.mrbestcreator.magicalmechanics.api.block.BlockLogic;
import com.github.mrbestcreator.magicalmechanics.api.block.INBTSerializableLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;

public class TestLogic extends BlockLogic implements INBTSerializableLogic {
    
    private int counter;
    
    @Override
    public void tick(BaseBlockEntity be) {
        counter++;
        
        if (Math.random() < 0.1) {
            if (be.getLevel() instanceof ServerLevel serverLevel) {
                BlockPos blockPos = be.getBlockPos();
                serverLevel.sendParticles(ParticleTypes.END_ROD, blockPos.getX() + 0.5, blockPos.getY() + 0.5,blockPos.getZ() + 0.5, 1, 0.1, 0.1, 0.1, 0.2);
            }
        }
    }
    
    @Override
    public void saveLogic(CompoundTag tag) {
        tag.putInt("Counter", counter);
    }
    
    @Override
    public void loadLogic(CompoundTag tag) {
        counter = tag.getInt("Counter");
    }
}
