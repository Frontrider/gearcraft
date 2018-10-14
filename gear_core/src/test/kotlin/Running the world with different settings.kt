import data.WorldSource
import data.WorldStorage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class `Running the world with different settings` {


    @TestFactory
    fun `Testing configurations`(): Stream<DynamicTest> {

        val worldSource = WorldSource()
        // sample input and output

        var index = 0
        return worldSource.provideArguments()
                .map {
                    val (source, target, repeat,name) = it

                    DynamicTest.dynamicTest("${++index}->$name") {
                        for (i in 0..repeat) {
                            for (key in (source.accessor as WorldStorage).store.keys) {
                                source.update(key)
                            }
                        }
                        assertEquals(source, target, "the world does not produce the expected result")

                    }

                }
    }
}
