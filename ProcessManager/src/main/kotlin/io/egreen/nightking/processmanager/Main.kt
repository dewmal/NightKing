package io.egreen.nightking.processmanager

import io.egreen.nightking.processmanager.processor.ProcessManager
import io.egreen.nightking.processmanager.processor.ProcessModelBuilder
import java.io.File

class Main {

    companion object {


        @JvmStatic
        fun main(args: Array<String>) {


            val processModel = ProcessModelBuilder(File("./processes/calculate_epf.json").toURI().toURL())
                    .setVariables("basicSalary", 45000)
                    .build()

            val process = ProcessManager().process(processModel)

            for (variable in process.variables) {
                println(variable.key + " " + variable.value.value)
            }


        }
    }

}

