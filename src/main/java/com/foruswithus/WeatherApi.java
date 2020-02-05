package com.foruswithus;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WeatherApi {


    private static String API_KEY = "650610f9646225df51c099f45e0ef4e1";
    private static HttpURLConnection connection;


    public static void main(String[] args) {
        doSomeNetworking();
    }

    public static void doSomeNetworking() {
        try {
            URL BASE_URL = new URL("http://api.openweathermap.org/data/2.5/forecast");
            connection = (HttpURLConnection) BASE_URL.openConnection();
            connection.setRequestMethod("GET");

            Map<String, String> parameters = new HashMap<>();
            parameters.put("APPID", API_KEY);

            connection.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();

            int status = connection.getResponseCode();
            System.out.println(status + " - " + connection.getResponseMessage() );


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
