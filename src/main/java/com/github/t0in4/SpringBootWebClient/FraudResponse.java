package com.github.t0in4.SpringBootWebClient;

public record FraudResponse(String txId, boolean fraud) {
}
