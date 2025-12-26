package com.github.mrbestcreator.magicalmechanics.api.item;

import net.minecraft.world.item.Item;

public class BaseItem extends Item{
    
    private final ItemEntry entry;
    
    public BaseItem(Properties props, ItemEntry entry) {
        super(props);
        this.entry = entry;
    }
    
    public ItemEntry getEntry() {
        return entry;
    }
}
