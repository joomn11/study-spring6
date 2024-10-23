package tobyspring.myboot.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpClientApiExecutor implements ApiExecutor {

    @Override
    public String execute(URI uri) throws IOException {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            return HttpClient.newBuilder().build().send(httpRequest, BodyHandlers.ofString()).body();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
