package com.errami.ordersservice.repositories;

import com.errami.ordersservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}