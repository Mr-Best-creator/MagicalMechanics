package com.github.mrbestcreator.magicalmechanics.registry.energy;

import com.github.mrbestcreator.magicalmechanics.MagicalMechanics;
import com.github.mrbestcreator.magicalmechanics.api.block.BaseBlockEntity;
import com.github.mrbestcreator.magicalmechanics.registry.block.BlockEntityRegistryImpl;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;


@EventBusSubscriber(modid = MagicalMechanics.MODID)
public class EnergyCapabilityRegistrar {
    
    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        for (Block block : BlockEntityRegistryImpl.getAllBlocks()) {
            BlockEntityType<?> type = BlockEntityRegistryImpl.get(block);
            if (type == null) continue;
            
            event.registerBlockEntity(
                    Capabilities.EnergyStorage.BLOCK,
                    type,
                    (be, side) -> ((BaseBlockEntity) be).feEnergy()
            );
        }
    }
}
