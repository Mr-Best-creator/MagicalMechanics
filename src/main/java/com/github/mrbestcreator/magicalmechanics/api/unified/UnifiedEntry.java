package com.github.mrbestcreator.magicalmechanics.api.unified;

import com.github.mrbestcreator.magicalmechanics.api.block.BlockEntry;
import com.github.mrbestcreator.magicalmechanics.api.item.ItemEntry;

import javax.annotation.Nullable;

public record UnifiedEntry(
        String id,
        @Nullable BlockEntry block,
        @Nullable ItemEntry item
) {}
