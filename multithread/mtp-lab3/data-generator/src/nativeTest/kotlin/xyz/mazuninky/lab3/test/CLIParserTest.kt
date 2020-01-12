package xyz.mazuninky.lab3.test

import xyz.mazuninky.lab3.*
import kotlin.math.abs
import kotlin.test.Test

class CLIParserTest {
    @Test
    fun testParse() {
        val cli = parse("-mode logistic -param mu=50 -param s=15 -count 12000".split(" ").toTypedArray())
        assert(cli.params["mu"] == "50")
        assert(cli.params["s"] == "15")
        assert(cli.args["mode"] == "logistic")
        assert(cli.args["count"] == "12000")
    }
}
