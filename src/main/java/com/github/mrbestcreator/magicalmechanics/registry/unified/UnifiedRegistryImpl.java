package com.github.mrbestcreator.magicalmechanics.registry.unified;

import com.github.mrbestcreator.magicalmechanics.api.unified.UnifiedEntry;
import com.github.mrbestcreator.magicalmechanics.api.unified.UnifiedRegistry;
import com.github.mrbestcreator.magicalmechanics.registry.block.BlockEntityRegistryImpl;
import com.github.mrbestcreator.magicalmechanics.registry.block.BlockRegistryImpl;
import com.github.mrbestcreator.magicalmechanics.registry.item.ItemRegistryImpl;
import net.neoforged.bus.api.IEventBus;

public class UnifiedRegistryImpl {

//    UnifiedBuilderで作成した情報を元にMinecraftに登録する
    public static void register(IEventBus bus) {
        
//        UnifiedBuilderの情報を元にそれぞれへ必要な情報を送り待機させる
        for (UnifiedEntry entry : UnifiedRegistry.entries()) {
            if (entry.block() != null) {
                BlockRegistryImpl.enqueue(entry.block());
            }
            if (entry.item() != null) {
                ItemRegistryImpl.enqueue(entry.item());
            }
        }
        
//        IEventBusをわたしMinecraftにブロックを登録させる
        BlockRegistryImpl.register(bus);
        
        // ③ 次にアイテムを DeferredRegister に登録
        //     BlockItem はラムダ内で BlockRegistryImpl.get() を呼ぶので、
        //     この時点でブロックは確実に登録済み
        ItemRegistryImpl.register(bus);
        
        for (UnifiedEntry entry : UnifiedRegistry.entries()) {
            if (entry.block() != null && entry.block().blockEntitySupplier() != null) {
                BlockEntityRegistryImpl.enqueue(entry.id(), entry.block());
            }
        }
        BlockEntityRegistryImpl.register(bus);
    }
}
