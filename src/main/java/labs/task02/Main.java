package labs.task02;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        double lat = 55.75;
        double lon = 37.62;
        int limit = 3;

        String url = "https://api.weather.yandex.ru/v2/forecast?lat=" + lat + "&lon=" + lon + "&limit=" + limit;

        String apiKey = System.getenv("YANDEX_WEATHER_KEY");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-Yandex-Weather-Key", apiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("HTTP " + response.statusCode());
        System.out.println(response.body());

        if (response.statusCode() == 200) {
            JSONObject weather = new JSONObject(response.body());
            JSONObject fact = weather.getJSONObject("fact");
            int currentTemp = fact.getInt("temp");

            System.out.println("Текущая температура: " + currentTemp + " °C");

            JSONArray forecasts = weather.getJSONArray("forecasts");
            int days = Math.min(limit, forecasts.length());
            double sum = 0;

            for (int i = 0; i < days; i++) {
                JSONObject forecast = forecasts.getJSONObject(i);
                JSONObject parts = forecast.getJSONObject("parts");
                JSONObject day = parts.getJSONObject("day");

                sum += day.getDouble("temp_avg");
            }

            if (days > 0) {
                double average = sum / days;
                System.out.println("Средняя дневная температура за " + days + " дн.: " + average + " °C");
            }
        }
    }
}
