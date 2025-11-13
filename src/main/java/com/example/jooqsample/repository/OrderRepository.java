package com.example.jooqsample.repository;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import com.example.jooqsample.domain.Order;

@Repository
public class OrderRepository {

    private final DSLContext dsl;

    public OrderRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void deleteAll() {
        dsl.deleteFrom(table("orders")).execute();
    }

    public Order insert(Order order) {
        Record record = dsl.insertInto(table("orders"))
                .set(field("customer_name"), order.customerName())
                .set(field("quantity"), order.quantity())
                .returningResult(field("id"), field("customer_name"), field("quantity"))
                .fetchOne();
        Long id = record.get("id", Long.class);
        String customerName = record.get("customer_name", String.class);
        Integer quantity = record.get("quantity", Integer.class);
        return new Order(id, customerName, quantity);
    }

    public List<Order> findAll() {
        return dsl.select(field("id"), field("customer_name"), field("quantity"))
                .from(table("orders"))
                .fetch(record -> new Order(
                        record.get("id", Long.class),
                        record.get("customer_name", String.class),
                        record.get("quantity", Integer.class)
                ));
    }
}
