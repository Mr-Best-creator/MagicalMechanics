package com.github.mrbestcreator.magicalmechanics.api.unified;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UnifiedRegistry {
    private static final Map<String, UnifiedEntry> ENTRIES = new HashMap<>();
    
//    UnifiedBuilderからUnifiedEntryを受け取り保存する
    public static void register(UnifiedEntry entry) {
        if (ENTRIES.containsKey(entry.id())) {
            throw new IllegalStateException("Duplicate id: " + entry.id());
        }
        ENTRIES.put(entry.id(), entry);
    }
    
    public static Collection<UnifiedEntry> entries() {
        return ENTRIES.values();
    }
}
