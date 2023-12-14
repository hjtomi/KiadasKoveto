package com.example.frontend;

import android.content.Context;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlKetelo2 {

    Context context;

    public UrlKetelo2(Context context){
        this.context = context;
    }

    /*public static void main(String[] args) throws IOException, JSONException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Person Info Command Line Editor.");
        System.out.println("(PICLER for short.)");
        System.out.println("Do you want to get or set a person's info?");
        System.out.println("(Type 'get' or 'set' now.)");
        String getOrSet = scanner.nextLine();
        if("get".equalsIgnoreCase(getOrSet)){
            System.out.println("Whose info do you want to get?");
            System.out.println("(Type a person's name now.)");
            String name = scanner.nextLine();

            String jsonString = getPersonData(name);
            JSONObject jsonObject = new JSONObject(jsonString);

            int birthYear = jsonObject.getInt("birthYear");
            System.out.println(name + " was born in " + birthYear + ".");

            String about = jsonObject.getString("about");
            System.out.println(about);
        }
        else if("set".equalsIgnoreCase(getOrSet)){
            System.out.println("Whose info do you want to set?");
            System.out.println("(Type a person's name now.)");
            String name = scanner.nextLine();

            System.out.println("When was " + name + " born?");
            System.out.println("(Type a year now.)");
            String birthYear = scanner.nextLine();

            System.out.println("Can you tell me about " + name + "?");
            System.out.println("(Type a sentence now.)");
            String about = scanner.nextLine();

            setPersonData(name, birthYear, about, password);
        }

        scanner.close();

        System.out.println("Thanks for using PICLER.");

    }*/

    public static String UrlMeghivo(String path) throws IOException{
        HttpURLConnection connection = (HttpURLConnection) new URL("https://127.0.0.1:5000/").openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode(); //Ez már nem működik
        return "GA";
        /*if(responseCode == 200){
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();

            return response;
        }

        // an error happened
        return "Hiba";*/
    }

    /*public static void setPersonData(String name, String birthYear, String about) throws IOException{
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/people/" + name).openConnection();

        connection.setRequestMethod("POST");

        String postData = "name=" + URLEncoder.encode(name);
        postData += "&about=" + URLEncoder.encode(about);
        postData += "&birthYear=" + birthYear;

        connection.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write(postData);
        wr.flush();

        int responseCode = connection.getResponseCode();
        if(responseCode == 200){
            System.out.println("POST was successful.");
        }
        else if(responseCode == 401){
            System.out.println("Wrong password.");
        }
    }*/
}