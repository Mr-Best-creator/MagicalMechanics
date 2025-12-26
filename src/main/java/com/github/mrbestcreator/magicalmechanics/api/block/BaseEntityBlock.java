package com.github.mrbestcreator.magicalmechanics.api.block;

import com.github.mrbestcreator.magicalmechanics.registry.block.BlockEntityRegistryImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BaseEntityBlock extends Block implements EntityBlock {
    
    private final BlockEntry entry;
    
    public BaseEntityBlock(Properties props, BlockEntry entry) {
        super(props);
        this.entry = entry;
    }
    
    public BlockEntry getEntry() {
        return entry;
    }
    
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        BlockEntityType<?> type = BlockEntityRegistryImpl.get(entry.id()); // 登録済みのTypeを取得
        return new BaseBlockEntity(pos, state);
    }
    
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            @NotNull Level level,
            @NotNull BlockState state,
            @NotNull BlockEntityType<T> type
    ) {
        BlockEntityType<?> expected = BlockEntityRegistryImpl.get(entry.id());
        
        if (type == expected) {
            return (lvl, pos, st, be) -> {
                if (be instanceof BaseBlockEntity base) {
                    base.tick();
                }
            };
        }
        
        return null;
    }
}
