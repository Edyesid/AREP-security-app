package edu.escuelaing.arep.app;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        port(getPort());
        secure("keyStores/ecikeystore.p12", "123456", null, null);
        get("/hello", (req, res) -> "Hello World");
    }

    /**
     * Da el puerto solicitado por la aplicación
     * @return puerto
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}