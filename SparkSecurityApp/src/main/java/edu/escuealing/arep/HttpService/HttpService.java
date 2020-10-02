package edu.escuealing.arep.HttpService;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class HttpService {
    private String url;
    public HttpService() {
        this.url = "https://localhost:4444";
        secureUrlReader();
    }

    public String getPalabras() {
        try {
            URL siteUrl = new URL(url + "/palabras");
            URLConnection urlConnection = siteUrl.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine = null;
            String res = "";
            while ((inputLine = reader.readLine()) != null) {
                res += inputLine + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String addPalabra(String palabra) {
        try {
            URL siteURL = new URL(url + "/palabras");
            HttpsURLConnection urlConnection = (HttpsURLConnection) siteURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Contemt-Type","application/json");
            urlConnection.setDoOutput(true);
            PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream(),true);
            printWriter.write(palabra);
            printWriter.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine = null;
            String res = "";
            while ((inputLine = reader.readLine()) != null) {
                res += inputLine + "\n";
            }
            return res;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void secureUrlReader() {
        try {

            File trustStoreFile = new File("keystores/myServiceTrust");
            char[] trustStorePassword = "123456".toCharArray();
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(trustStore);
            for(TrustManager t: tmf.getTrustManagers()){
                System.out.println(t);
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);
            javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
