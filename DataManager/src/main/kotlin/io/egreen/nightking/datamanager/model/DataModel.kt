package io.egreen.nightking.datamanager.model

class DataModel {

    lateinit var tables: HashMap<String, TableModel>




    fun genTableDefinitions():String{
        var definitions=""

        for (table in tables) {
            definitions+=table.value.definition+";"
        }



        return definitions
    }


}

class TableModel {
    lateinit var name: String
    lateinit var definition: String
}