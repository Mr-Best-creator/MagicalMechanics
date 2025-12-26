package com.github.mrbestcreator.magicalmechanics.api.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class InventoryDef {
    public final Map<SlotType, TagKey<Item>> slots = new HashMap<>();
    
    public void addSlot(SlotType type, TagKey<Item> tag) {
        slots.put(type, tag);
    }
}
