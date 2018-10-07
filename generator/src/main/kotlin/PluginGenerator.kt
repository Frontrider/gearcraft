package hu.frontrider.gearcraft.codegenerator

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec

class PluginGenerator {

    fun run(){

        FileSpec
                .builder("hu.frontrider.grarcraft.plugins","BasePlugin")
                .addType(TypeSpec.classBuilder("BasePlugin")
                        .superclass(Plugin::class.)

                )


    }

}