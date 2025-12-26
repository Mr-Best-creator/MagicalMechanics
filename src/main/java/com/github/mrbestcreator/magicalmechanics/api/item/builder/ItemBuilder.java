package com.github.mrbestcreator.magicalmechanics.api.item.builder;

import com.github.mrbestcreator.magicalmechanics.api.item.EnergyDef;
import com.github.mrbestcreator.magicalmechanics.api.item.ItemEntry;
import com.github.mrbestcreator.magicalmechanics.api.item.ItemHook;
import com.github.mrbestcreator.magicalmechanics.api.item.ItemLogic;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBuilder {
    private EnergyDef energy;
    private ItemLogic logic;
    private List<ItemHook> hooks = List.of();
    
    private ItemBuilder() {
    }
    
    public static ItemBuilder create() {
        return new ItemBuilder();
    }
    
    public ItemBuilder energy(EnergyDef energy) {
        this.energy = energy;
        return this;
    }
    
    public ItemBuilder logic(ItemLogic logic) {
        this.logic = logic;
        return this;
    }
    
    public ItemBuilder hooks(List<ItemHook> hooks) {
        this.hooks = hooks;
        return this;
    }
    
//    UnifiedBuilderからidを受け取って自身のデータと合わせてItemEntryを作成し返す
    public ItemEntry buildEntry(String id, @Nullable String blockId) {
        return new ItemEntry(id, energy, logic, List.copyOf(hooks), blockId);
    }
}
