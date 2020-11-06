package com.example.vijaya.earthquakeapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;

public class QueryUtils {
    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link Earthquake} objects.
     */
    public static List<Earthquake> fetchEarthquakeData2(String requestUrl) {
        // An empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();
        //  URL object to store the url for a given string
        URL url = null;
        // A string to store the response obtained from rest call in the form of string
        String jsonResponse = "";
        try {
            // Reassigns url to requestUrl string
            url = new URL(requestUrl);
            // Opens the connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Local string for consistent appending
            String output;
            // Creation of BufferedReader wrapper for efficient reading
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            // While it's not the end of the data, appends to the jsonResponse string
            while ((output = in.readLine()) != null){
                jsonResponse += output;
            }
            // Closes the connection on completion
            in.close();
            // Creation of a singular json object from the string
            JSONObject obj = new JSONObject(jsonResponse);
            // Splits the JSONObject obj into an array of JSONObjects with the delineator of "features"
            JSONArray jsonArray = obj.getJSONArray("features");

            // Iterates through the json array to create the necessary objects
            for (int i = 0; i < jsonArray.length(); i++){
                // Creates an initial temp json object from index i for further nested parsing
                JSONObject temp = jsonArray.getJSONObject(i);
                // Further parsing of nested json to get to properties needed
                JSONObject prop = temp.getJSONObject("properties");
                // Implicit calls of constructor for each json in the array
                Earthquake z = new Earthquake(prop.getDouble("mag"), prop.getString("place")
                        , prop.getLong("time"), prop.getString("url"));
                // Appends the newly created object to the earthquakes array
                earthquakes.add(z);
            }
            // Return the list of earthquakes

        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception:  ", e);
        }
        // Return the list of earthquakes
        return earthquakes;
    }
}
