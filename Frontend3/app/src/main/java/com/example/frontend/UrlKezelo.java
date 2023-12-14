package com.example.frontend;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlKezelo {
    Context context;
    Loop loop;

    UrlKezelo(Context context, Loop loop){
        this.context = context;
        this.loop = loop;
    }

    public String URLhivas(String path) throws IOException {


        URL url = new URL("https://127.0.0.1:5000/");

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        return urlConnection.getResponseMessage();
        /*try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            readStream(in);
        } finally {
            urlConnection.disconnect();
        }*/


    }

    private void readStream(InputStream in) {
        loop.setSzoveg2(in.toString());
    }

    public String nyugtasKiadas() throws IOException {

        return URLhivas("proba/");
    }
}
