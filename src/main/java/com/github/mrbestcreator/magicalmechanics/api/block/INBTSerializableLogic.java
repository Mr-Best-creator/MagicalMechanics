package com.github.mrbestcreator.magicalmechanics.api.block;

import net.minecraft.nbt.CompoundTag;

public interface INBTSerializableLogic {
    void saveLogic(CompoundTag tag);
    void loadLogic(CompoundTag tag);
}

