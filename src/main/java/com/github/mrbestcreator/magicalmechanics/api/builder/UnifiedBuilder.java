package com.github.mrbestcreator.magicalmechanics.api.builder;

import com.github.mrbestcreator.magicalmechanics.api.block.BlockEntry;
import com.github.mrbestcreator.magicalmechanics.api.block.builder.BlockBuilder;
import com.github.mrbestcreator.magicalmechanics.api.item.ItemEntry;
import com.github.mrbestcreator.magicalmechanics.api.item.builder.ItemBuilder;
import com.github.mrbestcreator.magicalmechanics.api.unified.UnifiedEntry;
import com.github.mrbestcreator.magicalmechanics.api.unified.UnifiedRegistry;

public class UnifiedBuilder {
    private final String id;
    private BlockBuilder blockBuilder;
    private ItemBuilder itemBuilder;
    
    private UnifiedBuilder(String id) {
        this.id = id;
    }
    
//    UnifiedBuilderをインスタンス化しデータを渡せる待機状態にする
//    Item, Block共通のidをこのタイミングで受け取ることでItem, BlockのBuilderで渡されたidが異なるなどの問題を回避する
    public static UnifiedBuilder create(String id) {
        return new UnifiedBuilder(id);
    }
    
//    BlockBuilderを作成し情報を登録させる
    public UnifiedBuilder block(BlockBuilder block) {
        this.blockBuilder = block;
        return this;
    }
    
//    ItemBuilderを作成し情報を登録させる
    public UnifiedBuilder item(ItemBuilder item) {
        this.itemBuilder = item;
        return this;
    }
    
//    UnifiedBuilderをbuildする
//    UnifiedEntry情報をUnifiedRegistryに渡しいつでも登録できる状態にする
    public void build() {
        BlockEntry block = blockBuilder != null ? blockBuilder.buildEntry(id) : null;
        ItemEntry item = itemBuilder != null ? itemBuilder.buildEntry(id, block != null ? block.id() : null) : null;
        
        UnifiedRegistry.register(new UnifiedEntry(id, block, item));
    }
}
