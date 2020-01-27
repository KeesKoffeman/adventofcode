package net.koffeman.aoc.`2019`

import org.junit.Assert
import org.junit.Test

class Day5Test {

    private val day5 = Day5()
    private val input = """1002,4,3,4,99"""

    @Test
    fun testPart1() {
        Assert.assertEquals("", day5.part1(input.lines()))
    }

    @Test
    fun testPart2() {

        Assert.assertEquals(0, day5.part2("3,9,8,9,10,9,4,9,99,-1,8".lines()))
        Assert.assertEquals(0, day5.part2("3,3,1108,-1,8,3,4,3,99".lines()))

        day5.intCodeComputerInput = 8
        Assert.assertEquals(1, day5.part2("3,9,8,9,10,9,4,9,99,-1,8".lines()))
        Assert.assertEquals(1, day5.part2("3,3,1108,-1,8,3,4,3,99".lines()))

        day5.intCodeComputerInput = 2
        Assert.assertEquals(1, day5.part2("3,9,7,9,10,9,4,9,99".lines()))
        Assert.assertEquals(1, day5.part2("3,3,1107,-1,8,3,4,3,99".lines()))

        day5.intCodeComputerInput = 10
        Assert.assertEquals(0, day5.part2("3,9,7,9,10,9,4,9,99".lines()))
        Assert.assertEquals(0, day5.part2("3,3,1107,-1,8,3,4,3,99".lines()))


        Assert.assertEquals(1, day5.part2("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9".lines()))
        Assert.assertEquals(1, day5.part2("3,3,1105,-1,9,1101,0,0,12,4,12,99,1".lines()))

        day5.intCodeComputerInput = 0
        Assert.assertEquals(0, day5.part2("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9".lines()))
        Assert.assertEquals(0, day5.part2("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9".lines()))

        day5.intCodeComputerInput = 1
        Assert.assertEquals(999, day5.part2("""3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99""".lines()))

        day5.intCodeComputerInput = 8
        Assert.assertEquals(1000, day5.part2("""3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99""".lines()))

        day5.intCodeComputerInput = 9
        Assert.assertEquals(1001, day5.part2("""3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99""".lines()))

    }
}
