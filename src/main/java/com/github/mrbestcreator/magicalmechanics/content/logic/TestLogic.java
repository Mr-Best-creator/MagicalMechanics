package com.github.mrbestcreator.magicalmechanics.content.logic;

import com.github.mrbestcreator.magicalmechanics.api.block.BaseBlockEntity;
import com.github.mrbestcreator.magicalmechanics.api.block.BlockLogic;

public class TestLogic extends BlockLogic {
    
    private int counter = 0;
    
    @Override
    public void tick(BaseBlockEntity be) {
        counter++;
        
        if (counter % 20 == 0) {
            System.out.println("Tick 動いてるよ！ counter=" + counter);
        }
    }
}
