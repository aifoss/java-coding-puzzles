import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sofia on 2018-12-01.
 */

/**
 * Code written during a timed online coding test.
 */

/**
 * Problem:
 *
 * Given a string, call HTTP GET method to retrieve movie data from the specified API URL.
 * First, populate String[] with total movie data.
 * Then populate String[] with titles of movies.
 * Return the result in sorted order.
 */
public class MovieTitles {

    private static final String URL_PREFIX = "https://jsonmock.hackerrank.com/api/movies/search/?Title=";


    static String[] getMovieTitles(String substr) throws Exception {
        String urlStr = URL_PREFIX + substr;
        URL url = new URL(urlStr);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = br.readLine();

        JsonElement jsonElement = new JsonParser().parse(line);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("data");

        List<JsonElement> jsonElements = new ArrayList<>();
        for (JsonElement elem : jsonArray) {
            jsonElements.add(elem);
        }

        List<String> titles = jsonElements.stream()
                .map(k -> k.getAsJsonObject().get("Title").getAsString())
                .sorted()
                .collect(Collectors.toList());

        return titles.toArray(new String[titles.size()]);
    }





    public static void main(String[] args) throws Exception {
        String[] movieTitles = getMovieTitles("Spiderman");
        for (String movieTitle : movieTitles) {
            System.out.println(movieTitle);
        }
    }

}
