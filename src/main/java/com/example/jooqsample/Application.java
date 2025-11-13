package com.example.jooqsample;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.jooqsample.domain.Order;
import com.example.jooqsample.repository.OrderRepository;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner demo(OrderRepository orders) {
        return args -> {
            orders.deleteAll();

            orders.insert(new Order(null, "Alice", 2));
            orders.insert(new Order(null, "Bob", 3));

            List<Order> all = orders.findAll();
            all.forEach(o -> System.out.println("Order " + o.id() + " for " + o.customerName()));
        };
    }
}
