package com.github.mrbestcreator.magicalmechanics.content.init;

import com.github.mrbestcreator.magicalmechanics.api.builder.UnifiedBuilder;
import com.github.mrbestcreator.magicalmechanics.api.item.builder.ItemBuilder;

public final class ModItems {
    public static void define() {
        UnifiedBuilder.create("example_item1")
                .item(ItemBuilder.create())
                .build();
        UnifiedBuilder.create("example_item2")
                .item(ItemBuilder.create())
                .build();
    }
}
