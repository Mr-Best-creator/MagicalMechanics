package com.github.mrbestcreator.magicalmechanics.api.block;

import java.util.*;

public class BlockRegistry {
    private static final Map<String, BlockEntry> MACHINES = new HashMap<>();
    
    public static void register(BlockEntry entry) {
        if (MACHINES.containsKey(entry.id())) {
            throw new IllegalArgumentException("Machine ID already registered: " + entry.id());
        }
        MACHINES.put(entry.id(), entry);
    }
    
    public static Collection<BlockEntry> entries() {
        return Collections.unmodifiableCollection(MACHINES.values());
    }
    
    public static BlockEntry get(String id) {
        return MACHINES.get(id);
    }
    
}
