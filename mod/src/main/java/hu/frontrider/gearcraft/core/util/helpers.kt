package hu.frontrider.gearcraft.core.util

fun Int.cap(cap:Int):Int{
    return if(this>cap)
        cap
    else
        this
}