import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpYandexWeather {
    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.weather.yandex.ru")
                .addPathSegment("v2")
                .addPathSegment("forecast")
                .addQueryParameter("lat=", "55.8204")
                .addQueryParameter("lon=", "37.3302")
                .addQueryParameter("[lang=", "ru_RU"+"]")
                .addQueryParameter("[limit=", "1"+"]")
                .addQueryParameter("[hours=", "true"+"]")
                .addQueryParameter("[extra=", "false"+"]")
                .build();

        Request request = new Request.Builder()
                .addHeader("X-Yandex-API-Key", "81402e9b-8fc1-4e4a-941b-3c101867ebcf")
                .url(url)
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();

        System.out.println(response.code());
        System.out.println(response.headers());
        System.out.println("\nЗапрос к Yandex-API прошёл без ошибок. X-Yandex-API-Key принят.\n");
        String body = response.body().string();
        System.out.println(body + "\n");
        System.out.println("Так выглядит не очень информативно. Выведем в красивой форме:\n");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(body);
        System.out.println("\n" + gson.toJson(je));


    }
}
