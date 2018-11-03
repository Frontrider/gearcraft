package hu.frontrider.gearcraft2.blocks

import hu.frontrider.gearcraft2.api.BlockStates.FACING
import hu.frontrider.gearcraft2.api.BlockStates.INVERTED
import net.minecraft.block.Block
import net.minecraft.block.BlockPillar
import net.minecraft.block.state.BlockState
import net.minecraft.util.math.Facing


abstract class InvertedDirectionalBlockBase(builder: Block.Builder) : Block(builder) {
    init {
        this.defaultState = this.defaultState.with(FACING, Facing.UP).with(INVERTED,false) as BlockState
    }

}
