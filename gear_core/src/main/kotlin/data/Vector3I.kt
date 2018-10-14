package hu.frontrider.gearpower.core.data

data class Vector3I(var x: Int, var y: Int, var z: Int) : Comparable<Vector3I> {
    override fun compareTo(other: Vector3I): Int {
        if (x != other.x) return Integer.compare(x, other.x)
        if (y != other.y) return Integer.compare(y, other.y)
        return Integer.compare(z, other.z)
    }

    fun up(amount:Int): Vector3I {
        return Vector3I(x,y+amount,z)
    }

    fun up(): Vector3I {
        return up(1)
    }


    fun down(amount:Int): Vector3I {
        return Vector3I(x,y-amount,z)
    }

    fun down(): Vector3I {
        return down(1)
    }
}