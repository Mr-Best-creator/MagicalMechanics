package com.github.mrbestcreator.magicalmechanics.api.block;

import com.github.mrbestcreator.magicalmechanics.api.energy.EnergyHook;
import com.github.mrbestcreator.magicalmechanics.api.energy.FEEnergyBridge;
import com.github.mrbestcreator.magicalmechanics.registry.block.BlockEntityRegistryImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BaseBlockEntity extends BlockEntity {
    
    private BlockContext context;
    private BlockLogic logic;
    private FEEnergyBridge feBridge;
    private EnergyComponent energy;
    
    
    public BaseBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistryImpl.get(state), pos, state);
        BlockEntry e = entry();
        if (e.logic() != null) {
            this.logic = e.logic().apply(this);
        }
        
        if (e.energy() != null) {
            this.energy = new EnergyComponent(e.energy());
        }
        
        if (e.energy() != null) {
            this.energy = new EnergyComponent(e.energy());
            for (EnergyHook hook : e.energyHooks()) {
                this.energy.addHook(hook);
            }
        }
    }
    
    public BlockEntry entry() {
        return ((BaseEntityBlock) getBlockState().getBlock()).getEntry();
    }
    
    private BlockContext context() {
        if (context == null) {
            context = new BlockContext(this);
        }
        return context;
    }
    
    public void tick() {
        Level level = this.level;
        if (level == null || level.isClientSide) return;
        
        BlockEntry e = entry();
        BlockContext ctx = context();
        
        for (BlockHook hook : e.hooks()) {
            hook.beforeTick(ctx);
        }
        
        if (e.logic() != null) {
            logic.tick(ctx.be());
        }
        
        for (BlockHook hook : e.hooks()) {
            hook.afterTick(ctx);
        }
    }
    
    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        if (logic instanceof INBTSerializableLogic serializable) {
            serializable.saveLogic(tag);
        }
        if (energy != null) energy.save(tag);
        
    }
    
    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        if (logic instanceof INBTSerializableLogic serializable) {
            serializable.loadLogic(tag);
        }
        if (energy != null) energy.load(tag);
        
    }
    
    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
//        return super.getUpdateTag(registries);
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
        
    }
    
    @Override
    public void handleUpdateTag(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider lookupProvider) {
//        super.handleUpdateTag(tag, lookupProvider);
        loadAdditional(tag, lookupProvider);
        
    }
    
    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
//        return super.getUpdatePacket();
        return ClientboundBlockEntityDataPacket.create(this);
        
    }
    
    @Override
    public void onDataPacket(@NotNull Connection net, @NotNull ClientboundBlockEntityDataPacket pkt, HolderLookup.@NotNull Provider lookupProvider) {
//        super.onDataPacket(net, pkt, lookupProvider);
        handleUpdateTag(pkt.getTag(), lookupProvider);
        
    }
    
    public void sync() {
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            setChanged();
        }
    }
    
    public FEEnergyBridge feEnergy() {
        if (energy == null) return null;
        if (feBridge == null) {
            feBridge = new FEEnergyBridge(energy);
        }
        return feBridge;
    }
    
    public @Nullable IEnergy energy() {
        return energy;
    }
    
    
}
