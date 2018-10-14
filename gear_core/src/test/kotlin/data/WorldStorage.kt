package data

import hu.frontrider.gearpower.core.data.ComponentDescriptor
import hu.frontrider.gearpower.core.data.DataAccessor
import hu.frontrider.gearpower.core.data.Vector3I
import java.util.*

class WorldStorage:DataAccessor {
    override fun updateState() {

    }

    val store = HashMap<Vector3I,ComponentDescriptor>()

    override fun write(component: ComponentDescriptor): DataAccessor {
        store[component.position] = component

        return this
    }

    override fun read(x: Int, y: Int, z: Int): Optional<ComponentDescriptor> {
        return read(Vector3I(x, y, z))
    }

    override fun read(position: Vector3I):  Optional<ComponentDescriptor> {
        if(store.containsKey(position))
            return Optional.ofNullable(store[position])

        return Optional.empty()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WorldStorage

        if (store != other.store) return false

        return true
    }

    override fun hashCode(): Int {
        return store.hashCode()
    }

    override fun toString(): String {
        return "WorldStorage(store=$store)"
    }

}