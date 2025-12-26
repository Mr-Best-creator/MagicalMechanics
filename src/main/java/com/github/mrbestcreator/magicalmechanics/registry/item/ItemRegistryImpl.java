package com.github.mrbestcreator.magicalmechanics.registry.item;

import com.github.mrbestcreator.magicalmechanics.MagicalMechanics;
import com.github.mrbestcreator.magicalmechanics.api.item.BaseItem;
import com.github.mrbestcreator.magicalmechanics.api.item.ItemEntry;
import com.github.mrbestcreator.magicalmechanics.registry.block.BlockRegistryImpl;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.*;

public class ItemRegistryImpl {
    
    private static final List<ItemEntry> QUEUE = new ArrayList<>();
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM, MagicalMechanics.MODID);
    private static final Map<String, DeferredHolder<Item, Item>> ITEMS_BY_ID = new HashMap<>();
    
//    UnifiedRegistryImplからデータを受け取り貯める
    public static void enqueue(ItemEntry entry) {
        QUEUE.add(entry);
    }
    
    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        
        for (ItemEntry entry : QUEUE) {
            DeferredHolder<Item, Item> holder;
            
            if (entry.blockId() != null) {
                holder = ITEMS.register(entry.id(), () -> {
                    Block block = BlockRegistryImpl.get(entry.blockId());
                    return new BlockItem(Objects.requireNonNull(block), new Item.Properties());
                });
            } else {
                holder = ITEMS.register(entry.id(),
                        () -> new BaseItem(new Item.Properties(), entry));
            }
            
            ITEMS_BY_ID.put(entry.id(), holder);
        }
        
        QUEUE.clear();
    }
    
    public static Item get(String id) {
        DeferredHolder<Item, Item> h = ITEMS_BY_ID.get(id);
        return h != null ? h.value() : null;
    }
}
