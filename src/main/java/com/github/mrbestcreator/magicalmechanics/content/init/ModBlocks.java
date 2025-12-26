package com.github.mrbestcreator.magicalmechanics.content.init;

import com.github.mrbestcreator.magicalmechanics.api.block.BaseBlockEntity;
import com.github.mrbestcreator.magicalmechanics.api.block.builder.BlockBuilder;
import com.github.mrbestcreator.magicalmechanics.api.builder.UnifiedBuilder;
import com.github.mrbestcreator.magicalmechanics.api.item.builder.ItemBuilder;
import com.github.mrbestcreator.magicalmechanics.content.logic.TestLogic;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class ModBlocks {
    public static void define() {
        UnifiedBuilder.create("example_block1")
                .block(BlockBuilder.create()
                        .blockProp(BlockBehaviour.Properties.of().strength(3.0f, 6.0f))
                        .blockEntitySupplier(BaseBlockEntity::new)
                        .logic(be -> new TestLogic())
                )
                .item(ItemBuilder.create())
                .build();
        UnifiedBuilder.create("example_block2")
                .block(BlockBuilder.create())
                .item(ItemBuilder.create())
                .build();
    }
}
