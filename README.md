lsunch springboot-onnx-fraud-detection server
and then launch springboot-client server

make request in terminal $curl localhost:8081/fraud/1234


Companies use Netty directly or via WebFlux for real-time systems, streaming APIs, and high-concurrency services.
For Tomcat 
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
...
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
The embedded approach packages Tomcat inside the application JAR, allowing it to run as a standalone 
Java application—ideal for microservices and cloud deployments.
