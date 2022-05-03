package az.dev.gateway.clientRequest;

import com.google.gson.Gson;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfiguration {

    @Value("${spring.retrofit.timeout}")
    private Long TIME_OUT_IN_SECS;

    @Bean
    public OkHttpClient secureKeyClient(@Value("${spring.security.user.name}") String secureKeyUsername,
                                        @Value("${spring.security.user.password}") String secureKeyPassword) {
        return createDefaultClientBuilder()
                .addInterceptor(interceptor -> interceptor.proceed(interceptor.request().newBuilder()
                        .header("Authorization", Credentials.basic(secureKeyUsername, secureKeyPassword))
                        .build()))
                .build();
    }

    @Bean
    public Retrofit.Builder secureKeyBuilder(OkHttpClient secureKeyClient, Gson gson) {
        return new Retrofit.Builder()
                .client(secureKeyClient)
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    private OkHttpClient.Builder createDefaultClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT_IN_SECS, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_IN_SECS, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_IN_SECS, TimeUnit.SECONDS);
    }

    @Bean
    public ProductServiceRequest productServiceRequest(Retrofit.Builder secureKeyBuilder,
                                                       @Value("${service.url.product}") String baseUrl) {
        return secureKeyBuilder
                .baseUrl(baseUrl)
                .build()
                .create(ProductServiceRequest.class);
    }

    @Bean
    public TransactionServiceRequest transactionServiceRequest(Retrofit.Builder secureKeyBuilder,
                                                               @Value("${service.url.transaction}") String baseUrl) {
        return secureKeyBuilder
                .baseUrl(baseUrl)
                .build()
                .create(TransactionServiceRequest.class);
    }

}
