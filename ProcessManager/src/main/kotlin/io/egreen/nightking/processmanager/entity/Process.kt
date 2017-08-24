package io.egreen.nightking.processmanager.entity

class ProcessModel {

    lateinit var name: String
    lateinit var version: String
    lateinit var author: String
    lateinit var published: String
    lateinit var variables: HashMap<String, VariableModel>
    lateinit var events: List<EventModel>


    override fun toString(): String {
        return "ProcessModel(name='$name', version='$version', author='$author', published='$published', variables=$variables, events=$events)"
    }


}

class VariableModel {

    lateinit var name: String
    var default: Any = 0
    var value: Any = 0
    var valueType: String = "Object"
    var required: Boolean = false
    var type: VariableType = VariableType.VARIABLE

    override fun toString(): String {
        return "VariableModel(name='$name', default=$default, value=$value, valueType='$valueType', required=$required, type=$type)"
    }


}

enum class VariableType {
    FINAL, VARIABLE
}

class EventModel {
    lateinit var name: String
    lateinit var process: String
    lateinit var output: String

    override fun toString(): String {
        return "EventModel(name='$name', process='$process', output='$output')"
    }


}