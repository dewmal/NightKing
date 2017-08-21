package io.egreen.nightking.processmanager.entity

class ProcessModel{

    lateinit var name:String
    lateinit var version:String
    lateinit var author:String
    lateinit var published:String
    lateinit var constants:List<VariableModel>
    lateinit var inputs:List<VariableModel>
    lateinit var outputs:List<VariableModel>
    lateinit var events:List<EventModel>
    override fun toString(): String {
        return "ProcessModel(name='$name', version='$version', author='$author', constants=$constants, inputs=$inputs, outputs=$outputs, events=$events)"
    }


}

class VariableModel {

    lateinit var name:String
    lateinit var type:String
    var default:Any=0
    var value:Any=0
    var required:Boolean=false


    override fun toString(): String {
        return "VariableModel(name='$name', type='$type', default=$default, value=$value, required=$required)"
    }


}

class EventModel {
    lateinit var name:String
    lateinit var process:String
    lateinit var output:String

    override fun toString(): String {
        return "EventModel(name='$name', process='$process', output='$output')"
    }


}