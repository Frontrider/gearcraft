package hu.frontrider.gearpower.core

import hu.frontrider.gearpower.core.data.ComponentDescriptor
import hu.frontrider.gearpower.core.data.DataAccessor
import hu.frontrider.gearpower.core.data.Sides
import hu.frontrider.gearpower.core.data.Vector3I
import hu.frontrider.gearpower.core.util.cap
import hu.frontrider.gearpower.core.util.lower

class World(val accessor: DataAccessor) {

    fun update(pos: Vector3I) {
        val value = accessor.read(pos)

        val component = fixComponent(if (value.isPresent) value.get() else return)
        if (component.input.contains(Sides.DOWN)) {
            updateComponent(component, component.position.down())
        }
        if (component.input.contains(Sides.UP)) {
            updateComponent(component, component.position.up())
        }
        accessor.updateState()

    }

    override fun toString(): String {
        return "World(accessor=$accessor)"
    }

    fun updateComponent(component: ComponentDescriptor, from: Vector3I) {
        val value = accessor.read(from)
        val checkedComponent = fixComponent(if (value.isPresent) value.get() else return)

        if (checkedComponent.power >= component.power && (checkedComponent.strength > 0 || checkedComponent.producer)){
            accessor.write(component.copy(strength = checkedComponent.strength.cap(component.maxStrength+1).lower()))
        }

        accessor.write(component)
    }

    //fix the component data to make sure that it's valid
    fun fixComponent(component: ComponentDescriptor): ComponentDescriptor {

        if (!component.producer) {
            if (component.strength > component.maxStrength) {
                return component.copy(strength = component.maxStrength)
            }
        } else {
            //if it's a producer, than it has no strength.
            return component.copy(strength = 0, maxStrength = 0)
        }

        return component
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as World

        if (accessor != other.accessor) return false

        return true
    }

    override fun hashCode(): Int {
        return accessor.hashCode()
    }


}