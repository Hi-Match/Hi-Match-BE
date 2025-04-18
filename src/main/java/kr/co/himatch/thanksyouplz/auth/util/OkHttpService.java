package kr.co.himatch.thanksyouplz.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.retry.annotation.Retry;
import kr.co.himatch.thanksyouplz.company.dto.CompanyLicenseResponseDTO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class OkHttpService {

    private final OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=UTF-8");

    @Bulkhead(name = "okhttpBulkheadWithRetry")
    @Retry(name = "okhttpRetry", fallbackMethod = "retryFallback")
    public String callPostApi(String url, String jsonData) throws IOException {

        RequestBody body = okhttp3.RequestBody.create(jsonData, JSON);
        Request request = new Request.Builder()
                .url(url)
                .header("accept", "*/*")
                .header("Content-Type", "*/*")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Request failed with code: " + response.code());
        }

        return response.body().string();
    }

    public String retryFallback(String url, Throwable t) {
        log.error("url : " + url + ", message : " + t.getMessage());
        return "Fallback response after retries";
    }
}
