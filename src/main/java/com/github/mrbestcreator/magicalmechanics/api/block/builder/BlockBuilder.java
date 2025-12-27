package com.github.mrbestcreator.magicalmechanics.api.block.builder;

import com.github.mrbestcreator.magicalmechanics.api.block.*;
import com.github.mrbestcreator.magicalmechanics.api.energy.EnergyHook;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlockBuilder {
    
    private BlockBehaviour.Properties blockProp;
    private BlockEntityType.BlockEntitySupplier<? extends BaseBlockEntity> blockEntitySupplier;
    private EnergyDef energy;
    private final List<EnergyHook> energyHooks = new ArrayList<>();
    private InventoryDef inventory;
    private Function<BaseBlockEntity, BlockLogic> logic;
    private final List<BlockHook> hooks = new ArrayList<>();
    
    private BlockBuilder() {
    }
    
    public static BlockBuilder create() {
        return new BlockBuilder();
    }
    
    public BlockBuilder blockProp(BlockBehaviour.Properties blockProp) {
        this.blockProp = blockProp;
        return this;
    }
    
//    TODO BlockEntityTypeの渡し方の確認と最適化
    public BlockBuilder blockEntitySupplier(BlockEntityType.BlockEntitySupplier<? extends BaseBlockEntity> blockEntitySupplier) {
        this.blockEntitySupplier = blockEntitySupplier;
        return this;
    }
    
    public BlockBuilder energy(Consumer<EnergyDef> c) {
        this.energy = new EnergyDef();
        c.accept(this.energy);
        return this;
    }
    
    public BlockBuilder energyHook(EnergyHook hook) {
        this.energyHooks.add(hook);
        return this;
    }
    
    public BlockBuilder inventory(Consumer<InventoryDef> c) {
        this.inventory = new InventoryDef();
        c.accept(this.inventory);
        return this;
    }
    
    public BlockBuilder logic(Function<BaseBlockEntity, BlockLogic> logic) {
        this.logic = logic;
        return this;
    }
    
    public BlockBuilder hook(BlockHook hook) {
        this.hooks.add(hook);
        return this;
    }
    
//    UnifiedBuilderからidをもらってきてそれと自身のデータを合わせてBlockEntryを作成しUnifiedBuilderに返す
    public BlockEntry buildEntry(String id) {
        if (blockProp == null) {
            blockProp = BlockBehaviour.Properties.of();
        }
        return new BlockEntry(id, blockProp, blockEntitySupplier, energy, List.copyOf(energyHooks), inventory, logic, List.copyOf(hooks));
    }
    
}
