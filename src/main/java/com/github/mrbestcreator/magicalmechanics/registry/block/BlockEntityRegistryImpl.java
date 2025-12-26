package com.github.mrbestcreator.magicalmechanics.registry.block;

import com.github.mrbestcreator.magicalmechanics.MagicalMechanics;
import com.github.mrbestcreator.magicalmechanics.api.block.BaseBlockEntity;
import com.github.mrbestcreator.magicalmechanics.api.block.BlockEntry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockEntityRegistryImpl {
    
    private static final List<BlockData> QUEUE = new ArrayList<>();
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(net.minecraft.core.registries.BuiltInRegistries.BLOCK_ENTITY_TYPE, MagicalMechanics.MODID);
    private static final Map<String, DeferredHolder<BlockEntityType<?>, BlockEntityType<?>>> BLOCKS_BY_ID = new HashMap<>();
    private static final Map<Block, BlockEntityType<?>> TYPES_BY_BLOCK = new HashMap<>();
    
    
    // UnifiedBuilder からの受け皿
    public static void enqueue(String id, BlockEntry entry) {
        QUEUE.add(new BlockData(id, entry, BlockRegistryImpl.getDeferredHolder(id)));
    }
    
    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
        
        for (BlockData blockData : QUEUE) {
            String id = blockData.block.getId().getPath();
            
            
            final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>>[] ref = new DeferredHolder[1];
            
            ref[0] = BLOCK_ENTITIES.register(
                    id,
                    () -> {
                        Block block = blockData.block.get();
                        BlockEntityType.BlockEntitySupplier<? extends BaseBlockEntity> supplier =
                                blockData.entry.blockEntitySupplier();
                        BlockEntityType<?> type = BlockEntityType.Builder
                                .of(supplier, block)
                                .build(null);
                        
                        TYPES_BY_BLOCK.put(block, type);
                        return type;
                    }
            );
            
            BLOCKS_BY_ID.put(id, ref[0]);
            
        }
        
        QUEUE.clear(); // 登録後は掃除
    }
    
    public static BlockEntityType<?> get(String id) {
        DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> h = BLOCKS_BY_ID.get(id);
        return h != null ? h.value() : null;
    }
    public static BlockEntityType<?> get(Block block) {
        return TYPES_BY_BLOCK.get(block);
    }
    
    public static BlockEntityType<?> get(BlockState state) {
        return TYPES_BY_BLOCK.get(state.getBlock());
    }
    
    
    private record BlockData (
            String id,
            BlockEntry entry,
            DeferredHolder<Block, Block> block
    ){}
}
