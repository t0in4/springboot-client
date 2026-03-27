package com.github.t0in4.SpringBootWebClient;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TransactionController {
    private final Map<String, TransactionDetails> txs = new HashMap<>();
    private final WebClient webClient;

    @PostConstruct
    public void populateData() {
        txs.put("1234", new TransactionDetails("1234",
                0.3111400080477545f,
                1.9459399775518593f,
                true, true, false));

        txs.put("5678", new TransactionDetails("5678",
                0.3111400080477545f,
                1.9459399775518593f,
                true, false, false));

    }

    public TransactionController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    @GetMapping("/fraud/{txId}")
    FraudResponse detectFraud(@PathVariable String txId) {
        final TransactionDetails transaction = findTransactionById(txId);
        return webClient
                .post()
                .uri("/inference")
                .bodyValue(transaction)
                .retrieve()
                .bodyToMono(FraudResponse.class)
                .block();
    }

    private TransactionDetails findTransactionById(String txId) {
        //return new TransactionDetails();
        return txs.get(txId);
    }
}
