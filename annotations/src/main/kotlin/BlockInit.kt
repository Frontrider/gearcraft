package hu.frontrider.gearcraft.annotations


/**
 * Marks a function as teh place where block initialisation happens.
 * will generate a function, that registers all the configured blocks.
 * */
@Target(AnnotationTarget.CLASS)
annotation class BlockInit