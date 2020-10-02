package edu.escuelaing.arep.app;

import edu.escuealing.arep.HttpService.HttpService;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        HttpService service = new HttpService();
        port(getPort());
        secure("keyStores/ecikeystore.p12", "123456", "KeyStores/myTrustStore", "123456");
        get("/hello", (req, res) -> "Hello World");
        post("login",(request, response) -> {
            String res = "Error";
            String req = request.body();
            String[] json = req.replace("\"", "").replace("[", "").replace("]", "").split(",");
            if (json[0].equals("edwin@eci") && json[1].equals("123456")) {
                request.session(true).attribute("isLogin", true);
                res = "Paso";
            } else {
                response.status(401);
            }
            return res;
        });

        get("/isLogin", (request, response) -> {
            response.header("content-Type","aplication/json");
            return String.valueOf(request.session().attribute("isLogin") != null);
        });
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