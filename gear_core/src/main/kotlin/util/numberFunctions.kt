package hu.frontrider.gearpower.core.util

fun Int.cap(cap: Int): Int {
    return if (this > cap) cap else this
}

fun Int.lower(): Int {
    val result = this - 1

    return if (result <= 0) 0 else result
}