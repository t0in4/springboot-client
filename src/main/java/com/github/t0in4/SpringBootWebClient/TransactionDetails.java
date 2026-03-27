package com.github.t0in4.SpringBootWebClient;

public record TransactionDetails(String txId, float distanceFromLastTransaction, float ratioToMedianPrice, boolean usedChip,
                                 boolean usedPinNumber, boolean onlineOrder) {}
