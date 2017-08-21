package io.egreen.nightking.processmanager

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.egreen.nightking.processmanager.entity.ProcessModel
import java.io.File

class Main {

    companion object {


        @JvmStatic
        fun main(args: Array<String>) {

            val jacksonMapper = ObjectMapper().registerModule(KotlinModule())
            var process: ProcessModel = jacksonMapper.readValue(File("./processes/calculate_epf.json"),ProcessModel::class.java)

            print(process)




        }
    }

}

