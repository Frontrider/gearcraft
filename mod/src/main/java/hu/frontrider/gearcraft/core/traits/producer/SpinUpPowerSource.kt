package hu.frontrider.gearcraft.core.traits.producer

import hu.frontrider.arcana.util.strings.ChatFormat
import hu.frontrider.arcana.util.strings.formatTranslate
import hu.frontrider.gearcraft.api.BlockStates
import hu.frontrider.gearcraft.api.traits.block.IMetaBlock
import hu.frontrider.gearcraft.api.traits.power.IGearPowered
import hu.frontrider.gearcraft.api.traits.ITooltipped
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class SpinUpPowerSource(val power: Int,val outputSide:EnumFacing,val spinUplevel:Int=3) : IGearPowered, ITooltipped, IMetaBlock {
    override fun getMaxMeta(): Int {
        return 15
    }

    override fun getPower(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Int {
        if (getStrength(world, blockPos, blockState, side) > 0)
            return power
        return 0
    }

    override fun getStrength(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Int {
        val stage = blockState!!.getValue(BlockStates.SPIN)
        return if (stage >= spinUplevel) 4 else 0

    }

    override fun isSideSupported(world: World?, blockPos: BlockPos?, blockState: IBlockState?, side: EnumFacing?): Boolean {
        return side ==outputSide
    }

    override fun setTooltip(tooltip: MutableList<String>) {
        tooltip.add(formatTranslate("gearcraft.spin_up",ChatFormat.YELLOW))
        tooltip.add(formatTranslate("gearcraft.spin_level",spinUplevel.toString(),ChatFormat.YELLOW))
    }

    fun doSpinUp(world: World, blockPos: BlockPos, blockState: IBlockState, random: Random,controlFlag:Boolean,doDoubleStep:Boolean =true,canSpinDown:Boolean=false){

        val value = blockState.getValue(BlockStates.SPIN)

        if(checkoverload(world, blockPos, blockState))
            return

        if (controlFlag) {
            //prevents it from becoming automated with vanilla tools.
            if (random.nextBoolean() && value < 14 && doDoubleStep) {
                charge(world, blockPos, blockState, value+2)
            } else {
                charge(world, blockPos, blockState, value+1)
            }

        } else {
            if(canSpinDown) {
                if (value > 0) {
                    charge(world, blockPos, blockState, value-1)
                }
            }
        }
    }

    fun charge(world: World,blockPos: BlockPos,blockState: IBlockState,value:Int){
        world.setBlockState(blockPos, blockState.withProperty(BlockStates.SPIN, value))
        world.notifyNeighborsOfStateChange(blockPos, blockState.block, true)
    }

    fun increment(world: World,blockPos: BlockPos,blockState: IBlockState){
        if(checkoverload(world, blockPos, blockState))
            return
        val value = blockState.getValue(BlockStates.SPIN)
        charge(world, blockPos, blockState, value+1)
    }

    fun checkoverload(world: World,blockPos: BlockPos,blockState: IBlockState):Boolean{
        val value = blockState.getValue(BlockStates.SPIN)
        return if (value == 15) {
            world.destroyBlock(blockPos, false)
            true
        }
        else {
            false
        }
    }


}