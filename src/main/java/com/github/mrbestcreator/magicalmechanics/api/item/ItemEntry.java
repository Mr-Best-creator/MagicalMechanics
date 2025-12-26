package com.github.mrbestcreator.magicalmechanics.api.item;

import javax.annotation.Nullable;
import java.util.List;

public record ItemEntry(
        String id,
        EnergyDef energy,
        ItemLogic logic,
        List<ItemHook> hooks,
        @Nullable String blockId
) {}
