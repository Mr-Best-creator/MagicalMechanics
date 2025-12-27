package com.github.mrbestcreator.magicalmechanics.content.init;

import com.github.mrbestcreator.magicalmechanics.api.block.BaseBlockEntity;
import com.github.mrbestcreator.magicalmechanics.api.block.builder.BlockBuilder;
import com.github.mrbestcreator.magicalmechanics.api.builder.UnifiedBuilder;
import com.github.mrbestcreator.magicalmechanics.api.item.builder.ItemBuilder;
import com.github.mrbestcreator.magicalmechanics.content.energyhook.InfiniteEnergyHook;
import com.github.mrbestcreator.magicalmechanics.content.logic.CreativeEnergyLogic;
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
        UnifiedBuilder.create("creative_energy_cube")
                .block(
                        BlockBuilder.create()
                                .blockProp(BlockBehaviour.Properties.of().strength(-1.0F))
                                .energy(e -> {
                                    e.capacity = Long.MAX_VALUE;
                                    e.maxInput = Long.MAX_VALUE;
                                    e.maxOutput = Long.MAX_VALUE;
                                })
                                .energyHook(new InfiniteEnergyHook())
                                .blockEntitySupplier(BaseBlockEntity::new)
                                .logic(be -> new CreativeEnergyLogic()) // 処理なしでもOK
                )
                .item(ItemBuilder.create())
                .build();
    }
}
