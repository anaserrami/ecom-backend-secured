package com.errami.ordersservice;

import com.errami.ordersservice.entities.Order;
import com.errami.ordersservice.enums.OrderState;
import com.errami.ordersservice.entities.ProductItem;
import com.errami.ordersservice.models.Product;
import com.errami.ordersservice.repositories.OrderRepository;
import com.errami.ordersservice.repositories.ProductItemRepository;
import com.errami.ordersservice.restclients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrdersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(
            OrderRepository orderRepository,
            ProductItemRepository productItemRepository,
            InventoryRestClient inventoryRestClient
    ){
        return args -> {
            //List<Product> allProducts = inventoryRestClient.getAllProducts(); we can't use this because we don't have the token at the application startup
            List<String> productsIds = List.of("P01","P02","P03");
            for (int i = 0; i < 5; i++) {
                Order order = Order.builder()
                        .id(UUID.randomUUID().toString())
                        .date(LocalDate.now())
                        .state(OrderState.PENDING)
                        .build();
                Order savedOrder = orderRepository.save(order);

                productsIds.forEach(pId->{
                    ProductItem productItem = ProductItem.builder()
//                            .product(p)
                            .productId(pId)
                            .quantity(new Random().nextInt(20))
                            .price(100.0 * new Random().nextInt(10))
                            .order(savedOrder)
                            .build();
                    productItemRepository.save(productItem);
                });
            }
        };
    }
}