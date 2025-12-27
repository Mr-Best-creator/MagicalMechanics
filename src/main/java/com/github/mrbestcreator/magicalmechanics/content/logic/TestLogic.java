package com.github.mrbestcreator.magicalmechanics.content.logic;

import com.github.mrbestcreator.magicalmechanics.api.block.BaseBlockEntity;
import com.github.mrbestcreator.magicalmechanics.api.block.BlockLogic;
import com.github.mrbestcreator.magicalmechanics.api.block.INBTSerializableLogic;
import net.minecraft.nbt.CompoundTag;

public class TestLogic extends BlockLogic implements INBTSerializableLogic {
    
    private int counter;
    
    @Override
    public void tick(BaseBlockEntity be) {
        counter++;
        
        if (counter % 20 == 0) {
            System.out.println("Tick 動いてるよ！ counter=" + counter);
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
