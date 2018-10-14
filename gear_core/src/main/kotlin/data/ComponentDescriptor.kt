package hu.frontrider.gearpower.core.data

data class ComponentDescriptor(val position: Vector3I,
                               val power: Int,
                               val strength: Int,
                               val maxStrength:Int,
                               val output: Set<Sides>,
                               val input:Set<Sides>,
                               val producer:Boolean = false,
                               val transmission:Boolean=false)

enum class Sides {
    UP, DOWN, NORTH, SOUTH, EAST, WEST
}