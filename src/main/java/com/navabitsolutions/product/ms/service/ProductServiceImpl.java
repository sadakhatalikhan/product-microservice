package com.navabitsolutions.product.ms.service;

import com.kafka.ws.core.ProductCreatedEvent;
import com.navabitsolutions.product.ms.request.ProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(ProductRequest productRequest) throws ExecutionException, InterruptedException {

        String productId = UUID.randomUUID().toString();
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        productCreatedEvent.setProductId(productId);
        productCreatedEvent.setTitle(productRequest.getTitle());
        productCreatedEvent.setPrice(productRequest.getPrice());
        productCreatedEvent.setQuality(productRequest.getQuality());

        /*CompletableFuture<SendResult<String, ProductCreatedEvent>> future =
                kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent);*/

        SendResult<String, ProductCreatedEvent> result =
                kafkaTemplate.send("insync-topic-example", productId, productCreatedEvent).get();

        LOGGER.info("****** Offset from the topic {} ", result.getRecordMetadata().offset());
        LOGGER.info("****** Partition from the topic {} ", result.getRecordMetadata().partition());
        LOGGER.info("****** Topic from the topic {} ", result.getRecordMetadata().topic());

        LOGGER.info("****** Returning the productId");
        return productId;
    }
}
