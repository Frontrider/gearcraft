package data

import hu.frontrider.gearpower.core.World
import hu.frontrider.gearpower.core.data.ComponentDescriptor
import hu.frontrider.gearpower.core.data.Sides
import hu.frontrider.gearpower.core.data.Vector3I
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.converter.ArgumentConverter
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

class WorldSource {
     fun provideArguments(): Stream<out WorldTestDesciptor> {
        return Stream.of(
                WorldTestDesciptor(getWorld1(),getWorld1Result(),4,"vertical tower with source on top"),
                WorldTestDesciptor(getWorld2(),getWorld2Result(),1,"single vertical shaft with power source bellow")
                )
    }


    fun getWorld1(): World {
        val storage = WorldStorage()
                .write(ComponentDescriptor(Vector3I(0,0,0),4,16,16,setOf(), setOf(),true))
                .write(ComponentDescriptor(Vector3I(0,1,0),4,0,3,setOf(Sides.DOWN,Sides.UP),setOf(Sides.DOWN,Sides.UP)))
                .write(ComponentDescriptor(Vector3I(0,2,0),4,0,3,setOf(Sides.DOWN,Sides.UP),setOf(Sides.DOWN,Sides.UP)))
                .write(ComponentDescriptor(Vector3I(0,3,0),4,0,3,setOf(Sides.DOWN,Sides.UP),setOf(Sides.DOWN,Sides.UP)))
                .write(ComponentDescriptor(Vector3I(0,4,0),4,0,3,setOf(Sides.DOWN,Sides.UP),setOf(Sides.DOWN,Sides.UP)))

        return World(storage)
    }

    fun getWorld1Result(): World {
        val storage = WorldStorage()
                .write(ComponentDescriptor(Vector3I(0,0,0),4,16,16,setOf(), setOf(),true))
                .write(ComponentDescriptor(Vector3I(0,1,0),4,3,3,setOf(Sides.DOWN,Sides.UP),setOf(Sides.DOWN,Sides.UP)))
                .write(ComponentDescriptor(Vector3I(0,2,0),4,2,3,setOf(Sides.DOWN,Sides.UP),setOf(Sides.DOWN,Sides.UP)))
                .write(ComponentDescriptor(Vector3I(0,3,0),4,1,3,setOf(Sides.DOWN,Sides.UP),setOf(Sides.DOWN,Sides.UP)))
                .write(ComponentDescriptor(Vector3I(0,4,0),4,0,3,setOf(Sides.DOWN,Sides.UP),setOf(Sides.DOWN,Sides.UP)))

        return World(storage)
    }
    fun getWorld2(): World {
        val storage = WorldStorage()
                .write(ComponentDescriptor(Vector3I(0,0,0),4,16,16,setOf(), setOf(),true))
                .write(ComponentDescriptor(Vector3I(0,1,0),4,13,3,setOf(Sides.DOWN,Sides.UP),setOf(Sides.DOWN,Sides.UP)))

        return World(storage)
    }

    fun getWorld2Result(): World {
        val storage = WorldStorage()
                .write(ComponentDescriptor(Vector3I(0,0,0),4,16,16,setOf(), setOf(),true))
                .write(ComponentDescriptor(Vector3I(0,1,0),4,3,3,setOf(Sides.DOWN,Sides.UP),setOf(Sides.DOWN,Sides.UP)))

        return World(storage)
    }
}

data class WorldTestDesciptor(val source:World,val result:World,val repeat:Int,val name:String)