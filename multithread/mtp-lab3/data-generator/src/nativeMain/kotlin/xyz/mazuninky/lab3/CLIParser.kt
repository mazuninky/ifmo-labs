@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package xyz.mazuninky.lab3

const val PARAM_OPTION = "-param"

data class CLIParser(val args: Map<String, String>,
                     val params: Map<String, String>)


fun <T> CLIParser.parseParam(name: String, mapFunc: (String) -> T): T? {
    return if (params.containsKey(name))
        mapFunc(params.getValue(name))
    else
        null
}

fun CLIParser.getFloatParam(name: String): Float? {
    return parseParam(name, String::toFloat)
}

fun CLIParser.getArg(name: String): String? {
    return args[name]
}

fun parse(args: Array<String>): CLIParser {
    val argsMap = mutableMapOf<String, String>()
    val paramsMap = mutableMapOf<String, String>()

    var i = 0
    while (i < args.size) {
        val value = args[i]
        if (value.startsWith(PARAM_OPTION) && i + 1 < args.size) {
            val paramString = args[i + 1].split("=")
            check(paramString.size == 2)
            paramsMap[paramString.first().trim()] = paramString[1].trim()
            i += 2
        } else
            if (value.startsWith("-") && i + 1 < args.size) {
                argsMap[value.removePrefix("-")] = args[i + 1]
                i += 2
            } else {
                i++
            }
    }

    return CLIParser(argsMap, paramsMap)
}