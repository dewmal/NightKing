package io.egreen.nightking.api.manager

import javax.ws.rs.GET
import javax.ws.rs.Path


@Path("apimanager")
open class ApiManager {

    @GET
    fun getVersion():String{
        return "V.0.0.1"
    }

}