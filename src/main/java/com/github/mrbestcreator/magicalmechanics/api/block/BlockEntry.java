package com.github.mrbestcreator.magicalmechanics.api.block;

import com.github.mrbestcreator.magicalmechanics.api.energy.EnergyHook;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;
import java.util.function.Function;

public record BlockEntry(
        String id,
        BlockBehaviour.Properties blockProp,
        BlockEntityType.BlockEntitySupplier<? extends BaseBlockEntity> blockEntitySupplier,// nullable
        EnergyDef energy,
        List<EnergyHook> energyHooks,
        InventoryDef inventory,
        Function<BaseBlockEntity, BlockLogic> logic,
        List<BlockHook> hooks
) {}
