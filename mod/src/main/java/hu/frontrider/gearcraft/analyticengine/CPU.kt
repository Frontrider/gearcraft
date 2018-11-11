package hu.frontrider.gearcraft.analyticengine

import org.apache.logging.log4j.ThreadContext.containsKey
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class CPU {
    lateinit var ram: Array<String>
    private var rom: HashMap<String, Instruction>
    private var accumulator: Double = 0.toDouble()
    private var instructionPointer: Int = 0

    init {
        rom = HashMap()
        rom["READ"] = Read()
        rom["LOAD"] = Load()
        rom["LOAD#"] = LoadDirect()
        rom["LOAD@"] = LoadIndirect()
        rom["STORE"] = Store()
        rom["ADD"] = Add()
        rom["ADD#"] = AddDirect()
        rom["SUB"] = Sub()
        rom["SUB#"] = SubDirect()
        rom["MUL"] = Mul()
        rom["DIV"] = Div()
        rom["MOD"] = Mod()
        rom["POW"] = Pow()
        rom["SHL"] = ShiftLeft()
        rom["SHR"] = ShiftRight()
        rom["AND"] = And()
        rom["OR"] = Or()
        rom["NOT"] = Not()
        rom["XOR"] = Xor()
        rom["JZ"] = JumpIfZero()
        rom["JNZ"] = JumpIfNotZero()
        rom["JUMP"] = Jump()
        rom["JGZ"] = JumpIfGreaterThanZero()
        rom["JGEZ"] = JumpIfGreaterOrEqualsThanZero()
        rom["JLZ"] = JumpIfLessThanZero()
        rom["JLEZ"] = JumpIfLessOrEqualsThanZero()
        rom["PRINT"] = Print()
        rom["ECHO"] = Echo()
        rom["HALT"] = Halt()
    }

    fun start() {
        while (instructionPointer >= 0) {
            fetch(ram[instructionPointer])
        }
    }

    fun load(ram: Array<String>) {
        this.ram = ram
    }

    fun dump() {
        for (i in ram.indices)
            if (ram[i] != null)
                println(String.format("%d.\t%s", i, ram[i]))
    }

    fun fetch(instruction: String) {
        try {
            val pattern = Pattern.compile("([^\\s]+)")
            val match = pattern.matcher(instruction)
            val executor = nextToken(match).toUpperCase()
            if (rom.containsKey(executor)) {
                val cmd = rom[executor]
                cmd?.execute(match)
            } else {
                throw RuntimeException("Unknow Instruction")
            }
        } catch (e: Exception) {
            println("Address: $instructionPointer\tInterrupt: ")
            e.printStackTrace()
            fetch("HALT")
        }

    }

    private fun nextToken(match: Matcher): String {
        if (!match.find()) throw RuntimeException("Command malformed")
        return match.group()
    }

    private inner class Load : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator = java.lang.Double.parseDouble(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()])
            instructionPointer++
        }
    }

    private inner class LoadDirect : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator = java.lang.Double.parseDouble(nextToken(matcher))
            instructionPointer++
        }
    }

    private inner class LoadIndirect : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator = java.lang.Double.parseDouble(ram[java.lang.Double.parseDouble(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()]).toInt()])
            instructionPointer++
        }
    }

    private inner class Store : Instruction {
        override fun execute(matcher: Matcher) {
            ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()] = java.lang.Double.toString(accumulator)
            instructionPointer++
        }
    }

    private inner class Add : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator += java.lang.Double.parseDouble(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()])
            instructionPointer++
        }
    }

    private inner class AddDirect : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator += java.lang.Double.parseDouble(nextToken(matcher))
            instructionPointer++
        }
    }

    private inner class JumpIfZero : Instruction {
        override  fun execute(matcher: Matcher) {
            if (accumulator == 0.0) {
                instructionPointer = java.lang.Double.parseDouble(nextToken(matcher)).toInt()
                return
            }
            instructionPointer++
        }
    }

    private inner class JumpIfNotZero : Instruction {
        override fun execute(matcher: Matcher) {
            if (accumulator != 0.0) {
                instructionPointer = java.lang.Double.parseDouble(nextToken(matcher)).toInt()
                return
            }
            instructionPointer++
        }
    }

    private inner class Jump : Instruction {
        override fun execute(matcher: Matcher) {
            instructionPointer = java.lang.Double.parseDouble(nextToken(matcher)).toInt()
        }
    }

    private inner class Print : Instruction {
        override fun execute(matcher: Matcher) {
            println(accumulator)
            instructionPointer++
        }
    }

    private inner class Halt : Instruction {
        override fun execute(matcher: Matcher) {
            instructionPointer = -1
        }
    }

    private inner class JumpIfGreaterThanZero : Instruction {
        override fun execute(matcher: Matcher) {
            if (accumulator > 0) {
                instructionPointer = java.lang.Double.parseDouble(nextToken(matcher)).toInt()
                return
            }
            instructionPointer++
        }
    }

    private inner class JumpIfGreaterOrEqualsThanZero : Instruction {
        override fun execute(matcher: Matcher) {
            if (accumulator >= 0) {
                instructionPointer = java.lang.Double.parseDouble(nextToken(matcher)).toInt()
                return
            }
            instructionPointer++
        }
    }

    private inner class JumpIfLessThanZero : Instruction {
        override fun execute(matcher: Matcher) {
            if (accumulator < 0) {
                instructionPointer = java.lang.Double.parseDouble(nextToken(matcher)).toInt()
                return
            }
            instructionPointer++
        }
    }

    private inner class JumpIfLessOrEqualsThanZero : Instruction {
        override  fun execute(matcher: Matcher) {
            if (accumulator <= 0) {
                instructionPointer = java.lang.Double.parseDouble(nextToken(matcher)).toInt()
                return
            }
            instructionPointer++
        }
    }

    private inner class Sub : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator -= java.lang.Double.parseDouble(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()])
            instructionPointer++
        }
    }

    private inner class SubDirect : Instruction {
        override  fun execute(matcher: Matcher) {
            accumulator -= java.lang.Double.parseDouble(nextToken(matcher))
            instructionPointer++
        }
    }

    private inner class Mul : Instruction {
        override  fun execute(matcher: Matcher) {
            accumulator *= java.lang.Double.parseDouble(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()])
            instructionPointer++
        }
    }

    private inner class Div : Instruction {
        override  fun execute(matcher: Matcher) {
            val d = java.lang.Double.parseDouble(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()])
            if (d == 0.0) throw RuntimeException("Division by Zero")
            accumulator /= d
            instructionPointer++
        }
    }

    private inner class Mod : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator %= java.lang.Double.parseDouble(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()])
            instructionPointer++
        }
    }

    /* base = accumulator; exponent = param */
    private inner class Pow : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator = Math.pow(accumulator, java.lang.Double.parseDouble(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()]))
            instructionPointer++
        }
    }

    private inner class ShiftLeft : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator = (accumulator.toInt() shl Integer.parseInt(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()])).toDouble()
            instructionPointer++
        }
    }

    private inner class ShiftRight : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator = (accumulator.toInt() shr Integer.parseInt(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()])).toDouble()
            instructionPointer++
        }
    }

    private inner class And : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator = (accumulator.toInt() and Integer.parseInt(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()])).toDouble()
            instructionPointer++
        }
    }

    private inner class Or : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator = (accumulator.toInt() or Integer.parseInt(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()])).toDouble()
            instructionPointer++
        }
    }


    private inner class Not : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator = accumulator.toInt().inv().toDouble()
            instructionPointer++
        }
    }

    private inner class Xor : Instruction {
        override fun execute(matcher: Matcher) {
            accumulator = (accumulator.toInt() xor Integer.parseInt(ram[java.lang.Double.parseDouble(nextToken(matcher)).toInt()])).toDouble()
            instructionPointer++
        }
    }

    private inner class Read : Instruction {
        override  fun execute(matcher: Matcher) {
            val sc = Scanner(System.`in`)
            accumulator = sc.nextDouble()
            instructionPointer++
        }
    }

    private inner class Echo : Instruction {
        override   fun execute(matcher: Matcher) {
            var message = ""
            while (matcher.find()) {
                message += matcher.group()
                message += " "
            }
            if (message[0] == '"' && message[message.length - 2] == '"') {
                println(message.substring(1, message.length - 2))
                instructionPointer++
                return
            }
            throw IllegalArgumentException("Instruction Malformed")
        }
    }
}