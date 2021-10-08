package org.kenux.anything.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@SpringBootTest
//@DataRedisTest
//@ContextConfiguration(classes = RedisConfig.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED) // 실제 DB 사용하고 싶을때 NONE 사용
public class WebClientTest {

    @Autowired
    private WebClient webClient;

    @Test
    void test1() throws InterruptedException {
//        webClient.put()
//                .uri("http://192.168.0.85:9452/algorithm/sac/ELDSetting/site/" + "b7e9aceb89")
//                .bodyValue("")
//                .retrieve();

        webClient.put()
                .uri("http://192.168.0.85:9452/algorithm/sac/ELDSetting/site/" + "b7e9aceb89")
                .bodyValue("")
                .retrieve()
//                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
//                        clientResponse -> clientResponse.bodyToMono(String.class)
//                                .map(RuntimeException::new))
                .bodyToMono(String.class)
                .doOnSuccess(request -> System.out.println("성공~~~" + request))
                .doOnError(throwable -> System.out.println("" + throwable.getMessage()))
                .subscribe(it -> {
                    System.out.println("it = " + it);
                });
        Thread.sleep(10000L);

//        mono.subscribe();
//        mono.subscribeOn(Schedulers.elastic())
//                .subscribe();

//        final String response = webClient.put()
//                .uri("http://192.168.0.85:9452/algorithm/sac/ELDSetting/site/" + "b7e9aceb89")
//                .bodyValue("")
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//        System.out.println("response = " + response);
    }
}