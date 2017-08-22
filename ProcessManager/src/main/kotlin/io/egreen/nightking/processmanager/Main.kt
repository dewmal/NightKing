package io.egreen.nightking.processmanager

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.egreen.nightking.processmanager.entity.ProcessModel
import io.egreen.nightking.processmanager.processor.ProcessManager
import java.io.File

class Main {

    companion object {


        @JvmStatic
        fun main(args: Array<String>) {

            val jacksonMapper = ObjectMapper().registerModule(KotlinModule())
            var process: ProcessModel = jacksonMapper.readValue(File("./processes/calculate_epf.json"),ProcessModel::class.java)

            val listInputs= HashMap<String,Any>()
            listInputs.put("basicSalary",30000)


            for (inPut in listInputs) {
                println(inPut.key+" \t\t- "+inPut.value)
            }
            println()
            println("---------")
            println()
            val processManager = ProcessManager()
            val outPuts=  processManager.process(process,listInputs)

            for (outPut in outPuts) {
                println(outPut.key+" \t\t- "+outPut.value.value)
            }

        }
    }

}

