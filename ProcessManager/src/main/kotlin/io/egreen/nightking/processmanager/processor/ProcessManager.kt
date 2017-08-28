package io.egreen.nightking.processmanager.processor

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.egreen.nightking.processmanager.entity.ProcessModel
import io.egreen.nightking.processmanager.entity.VariableModel
import io.egreen.nightking.processmanager.entity.VariableType
import org.apache.commons.jexl3.JexlBuilder
import org.apache.commons.jexl3.MapContext
import java.io.File
import java.net.URL


class ProcessModelBuilder {

    protected lateinit var processModel: ProcessModel
        get
        private set

    constructor(url: URL) {
        val jacksonMapper = ObjectMapper().registerModule(KotlinModule())
        processModel = jacksonMapper.readValue(url, ProcessModel::class.java)
    }

    fun setVariables(key: String, value: Any): ProcessModelBuilder {
        val variableModel = processModel.variables.get(key) as VariableModel
        variableModel.value=value
        if (variableModel.type != VariableType.FINAL) {
            processModel.variables.put(key, variableModel)
        }
        return this
    }

    fun build(): ProcessModel {
        for (variable in processModel.variables) {
            if (variable.value.required && variable.value.value == null) {
                throw ProcessInitializeException(variable.key + "Required Must be initialize")
            }else if(variable.value.type==VariableType.FINAL&& variable.value.value == null){
                throw ProcessInitializeException(variable.key + "Final Must be initialize")
            }
        }
        return this.processModel
    }
}

class ProcessInitializeException : Exception {
    constructor(message: String?) : super(message)
}

class ProcessManager {

    fun process(processModel: ProcessModel): ProcessModel {

        // Create or retrieve an engine
        val jexl = JexlBuilder().create()



        val jc = MapContext()

        for (entry in processModel.variables) {
            val variableModel = entry.value
            jc.set(entry.key, variableModel.value)
        }



        for (event in processModel.events) {
            // Create an expression
            val jexlExp = event.process
            val e = jexl.createExpression(jexlExp)
            // Now evaluate the expression, getting the result
            val result = e.evaluate(jc)

            val variableModel: VariableModel = processModel.variables.get(event.output) as VariableModel
            variableModel.value = result
            processModel.variables.put(event.output, variableModel)

            jc.set(event.output, result)
        }

        return processModel
    }


}