package hu.frontrider.gearpower.core.data

import java.util.*

interface DataAccessor {

    fun read(x:Int,y:Int,z:Int):Optional<ComponentDescriptor>
    fun read(position:Vector3I):Optional<ComponentDescriptor>

    //adds a new component that should be updated
    fun write(component:ComponentDescriptor):DataAccessor

    //should do the actual update step.
    //makes sure that we update the world only as much as we need to.
    fun updateState()
}