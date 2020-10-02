package edu.escuelaing.arep.app;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        port(getPort());
        secure("keyStores/ecikeystore.p12", "123456","KeyStores/myTrustStore", "123456");
        //secure("keyStores/ecikeyarep.p12", "123456","myTrustStore","123456");
        get("/hello", (req, res) -> "Hello World");   
        get("/palabras", (req, res) -> "Se ha conectado con la otra aplicación");
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
}