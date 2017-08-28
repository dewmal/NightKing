package io.egreen.nightking.api.manager;

import io.egreen.apistudio.bootstrap.ApiStudio;
import io.egreen.apistudio.bootstrap.config.MSApp;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
@MSApp(name = "KnightKingProcessManager")
public class ApiManagerMain {






    public static void main(String[] args) {
        ApiStudio.boot(ApiManagerMain.class, "0.0.0.0", 2568, "/api",null);
    }
}
