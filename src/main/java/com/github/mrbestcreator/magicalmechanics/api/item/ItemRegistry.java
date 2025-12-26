package com.github.mrbestcreator.magicalmechanics.api.item;

import java.util.*;

public class ItemRegistry {
    private static final Map<String, ItemEntry> ITEMS = new HashMap<>();
    
    public static void register(ItemEntry entry) {
        if (ITEMS.containsKey(entry.id())) {
            throw new IllegalArgumentException("Item ID already registered: " + entry.id());
        }
        ITEMS.put(entry.id(), entry);
    }
    
    public static Collection<ItemEntry> entries() {
        return Collections.unmodifiableCollection(ITEMS.values());
    }
    
    public static ItemEntry get(String id) {
        return ITEMS.get(id);
    }
    
}
