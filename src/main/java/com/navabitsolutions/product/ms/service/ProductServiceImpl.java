package com.navabitsolutions.product.ms.service;

import com.navabitsolutions.product.ms.events.ProductCreatedEvent;
import com.navabitsolutions.product.ms.request.ProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(ProductRequest productRequest) {

        String productId = UUID.randomUUID().toString();
        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder()
                .withProductId(productId)
                .withTitle(productRequest.getTitle())
                .withPrice(productRequest.getPrice())
                .withQuality(productRequest.getQuality())
                .build();
        CompletableFuture<SendResult<String, ProductCreatedEvent>> future =
                kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent);
        future.whenComplete((result, exception) -> {
            if (exception != null) {
                LOGGER.error("****** Failed to send messages {}", exception.getMessage());
            } else {
                LOGGER.info("******* Message sent successfully {}", result.getRecordMetadata());
            }
        });
        LOGGER.info("****** Returning the productId");
        return productId;
    }
}