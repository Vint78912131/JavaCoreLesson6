import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpURLDemo {

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://geekbrains.ru");

        HttpURLConnection hpCon =
                (HttpURLConnection) url.openConnection();

        // Получение информации о запросе
        System.out.println("Метод запроса:" + hpCon.getRequestMethod());
        System.out.println("Код ответа:" + hpCon.getResponseCode());
        System.out.println("Ответное сообщение:" + hpCon.getResponseMessage());

        // Получение заголовков
        Map<String, List<String>> headers = hpCon.getHeaderFields();
        for (String k: headers.keySet()) {
            System.out.printf("Ключ: %s, значение: %s \n", k, headers.get(k));
        }
    }
}
