package com.github.mrbestcreator.magicalmechanics.api.block;

public interface BlockHook {
    
    default void beforeTick(BlockContext ctx) {}
    
    default void afterTick(BlockContext ctx) {}
}
