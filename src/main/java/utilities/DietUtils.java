package main.java.utilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public final class DietUtils {

    private DietUtils() {}

    synchronized public static Set<Object> getAnimalDiet(String animal) {

        Map<String, String> defaultDiet = Map.ofEntries(
                entry("rabbit", "clover, grass, crunchy vegetables, carrot, carrots"),
                entry("bear", "meat, fish, nectar, insects"),
                entry("wolf", "meat, moose, deer, wild boar, reindeer, argali, mouflon, wisent, saiga, ibex, chamois, wild goats")
        );

        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                URI.create("https://api.api-ninjas.com/v1/animals?name=" + animal))
                .header("X-Api-Key", "mFFK8IK9VwjQZhBTP0VSdQ==GPOXC73c0RR5cdex")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 Edg/107.0.1418.42") //"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:105.0) Gecko/20100101 Firefox/105.0")
                .header("Origin", "https://api-ninjas.com")
                .build();

        try {
            Thread.sleep(1500);
            var response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.get(5, TimeUnit.SECONDS).statusCode();
            if (statusCode != 200) {

                System.err.println("Error: " + statusCode);
                return Set.of(defaultDiet.get(animal).split(","));

            }
            return response.thenApply(HttpResponse::body).thenApply(DietUtils::parseJSON).get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Set.of(defaultDiet.get(animal).split(","));
    }

    private static Set<Object> parseJSON(String responseBody) {
        JSONArray jsonArray = new JSONArray(responseBody);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        return Arrays.stream(jsonObject.getJSONObject("characteristics")
                .get("prey")
                .toString()
                .toLowerCase(Locale.ROOT)
                .split(",")).collect(Collectors.toSet());
    }


}
