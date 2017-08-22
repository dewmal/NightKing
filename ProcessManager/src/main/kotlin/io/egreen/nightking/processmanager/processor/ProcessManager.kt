package io.egreen.nightking.processmanager.processor

import io.egreen.nightking.processmanager.entity.ProcessModel
import io.egreen.nightking.processmanager.entity.VariableModel
import org.apache.commons.jexl3.JexlBuilder
import org.apache.commons.jexl3.MapContext


class ProcessManager {


    fun process(processModel: ProcessModel, listInputs: HashMap<String, Any>): HashMap<String, VariableModel> {

        // Create or retrieve an engine
        val jexl = JexlBuilder().create()
        val jc = MapContext()

        for (entry in listInputs) {
            val variableModel: VariableModel = processModel.inputs.get(entry.key) as VariableModel
            variableModel.value = entry.value
            processModel.inputs.put(entry.key, variableModel)
            jc.set(entry.key, variableModel.value)
        }


        for (constant in processModel.constants) {
            jc.set(constant.key, constant.value.value)
        }

        for (event in processModel.events) {
            // Create an expression
            val jexlExp = event.process
            val e = jexl.createExpression(jexlExp)

            // Now evaluate the expression, getting the result
            val result = e.evaluate(jc)

            val variableModel:VariableModel = processModel.outputs.get(event.output) as VariableModel
            variableModel.value=result
            processModel.outputs.put(event.output,variableModel)

            jc.set(event.output,result)

        }

        return processModel.outputs
    }


}