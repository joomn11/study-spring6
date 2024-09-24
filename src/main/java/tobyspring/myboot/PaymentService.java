package tobyspring.myboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        // get currency
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = br.lines().collect(Collectors.joining());
        br.close();

        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        BigDecimal exRate = data.rates().get("KRW");

        // calculate convertedAmount
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        // make valid period
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30L);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    public static void main(String[] args) throws IOException {
        PaymentService service = new PaymentService();
        Payment payment = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}


