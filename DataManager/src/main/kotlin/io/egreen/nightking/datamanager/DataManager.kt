package io.egreen.nightking.datamanager

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.egreen.nightking.datamanager.model.DataModel
import org.apache.metamodel.DataContextFactory
import org.apache.metamodel.UpdateCallback
import org.apache.metamodel.UpdateScript
import org.apache.metamodel.UpdateableDataContext
import org.apache.metamodel.util.SimpleTableDef
import org.apache.metamodel.util.SimpleTableDefParser
import java.io.File

class DataManager {


    lateinit var dataContext: UpdateableDataContext

    fun manage() {


        dataContext = DataContextFactory.createMongoDbDataContext(
                "localhost"
                , 27017,
                "nightking_sample_database", null, null
        )


        val jacksonMapper = ObjectMapper().registerModule(KotlinModule())
        val dataModel = jacksonMapper.readValue<DataModel>(File("./data_entries/table.json"), DataModel::class.java)


        val tableDefs = SimpleTableDefParser.parseTableDefs(dataModel.genTableDefinitions())








        dataContext.executeUpdate(object : UpdateScript {
            override fun run(callback: UpdateCallback?) {
                createAndUpdateTables(tableDefs, callback)
            }
        })


    }


    /**
     * Create Table If not exists
     * If Exists It will be update
     */
    fun createAndUpdateTables(tableDefs: Array<SimpleTableDef>, callback: UpdateCallback?) {
        for (tableDef in tableDefs) {
            val table = tableDef.toTable()
            val schema = dataContext.getSchemaByName(table.name)
            if (schema != null) {
                callback!!.createTable(table.schema, table.name).execute()
            } else {
                callback!!.update(table)
            }
        }
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            DataManager().manage()
        }
    }
}