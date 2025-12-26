package com.github.mrbestcreator.magicalmechanics.registry.block;

import com.github.mrbestcreator.magicalmechanics.MagicalMechanics;
import com.github.mrbestcreator.magicalmechanics.api.block.BaseBlock;
import com.github.mrbestcreator.magicalmechanics.api.block.BaseEntityBlock;
import com.github.mrbestcreator.magicalmechanics.api.block.BlockEntry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockRegistryImpl {
    
    private static final List<BlockEntry> QUEUE = new ArrayList<>();
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(BuiltInRegistries.BLOCK, MagicalMechanics.MODID);
    private static final Map<String, DeferredHolder<Block, Block>> BLOCKS_BY_ID = new HashMap<>();
    
//    UnifiedRegistryImplからデータを受け取り貯める
    public static void enqueue(BlockEntry entry) {
        QUEUE.add(entry);
    }
    
//    UnifiedRegistryImplからIEventBusを受け取り登録する
    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        
//        QUEUEの中身を一つずつ取り出し登録する
        for (BlockEntry entry : QUEUE) {
            DeferredHolder<Block, Block> holder = BLOCKS.register(entry.id(),
//                    blockEntitySupplierが存在する場合BaseEntityBlockへ存在しない場合はBaseBlockをインスタンス化して返す(BlockEntityを紐づけるかの分岐)
                    () -> entry.blockEntitySupplier() != null ? new BaseEntityBlock(entry.blockProp(), entry) : new BaseBlock(entry.blockProp(), entry)
            );
            BLOCKS_BY_ID.put(entry.id(), holder);
        }
        
        QUEUE.clear(); // 登録後は掃除
    }
    
    public static Block get(String id) {
        DeferredHolder<Block, Block> h = BLOCKS_BY_ID.get(id);
        System.out.println("BlockRegistryImpl#get(String id) id: " + id);
        System.out.println("BLOCKS_BY_ID: " + BLOCKS_BY_ID);
        System.out.println("BLOCKS_BY_ID.get(id): " + h);
        return h != null ? h.value() : null;
    }
    
    public static DeferredHolder<Block, Block> getDeferredHolder(String id) {
        DeferredHolder<Block, Block> h = BLOCKS_BY_ID.get(id);
        return h != null ? h : null;
    }
}
