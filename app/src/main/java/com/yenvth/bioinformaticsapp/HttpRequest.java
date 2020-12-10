package com.yenvth.bioinformaticsapp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequest {
    public final static String USER_AGENT = "Mozilla/5.0";
    public final static String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
    public final static String ACCEPT_LANGUAGE = "en-US,en;q=0.5";

    public static String sendPost(String urlStr, String postParams) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setUseCaches(false);
        con.setDoOutput(true);

        // set properties
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept", ACCEPT);
        con.setRequestProperty("Accept-Language", ACCEPT_LANGUAGE);
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Content-Length", Integer.toString(postParams.length()));

        // Send post request
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        // get content
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static String sendGet(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection con  = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setUseCaches(false);

        // set properties
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept", ACCEPT);
        con.setRequestProperty("Accept-Language", ACCEPT_LANGUAGE);
        // check result code
        // int responseCode = con.getResponseCode();
        // logln("\nSending 'GET' request to URL : " + url);
        // logln("Response Code : " + responseCode);

        // get result content
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
