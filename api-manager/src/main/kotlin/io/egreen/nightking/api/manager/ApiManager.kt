package io.egreen.nightking.api.manager

import io.egreen.apistudio.bootstrap.ApiStudio
import io.egreen.apistudio.bootstrap.config.MSApp
import javax.ws.rs.ApplicationPath

@ApplicationPath("/")
@MSApp(name = "KnightKingProcessManager")
open class ApiManager {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ApiStudio.boot(ApiManager.javaClass, "0.0.0.0", 2568, "/api",null)
        }
    }

}