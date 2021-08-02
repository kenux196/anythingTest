package org.kenux.anything.domain.repository;

import org.junit.jupiter.api.Test;
import org.kenux.anything.domain.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void orderTest() {
        // given
        LocalDateTime now = LocalDateTime.of(2021, 8, 2, 22, 15, 10);


        Order testOrder = new Order("test order");
        Order save = orderRepository.save(testOrder);

        // when
        List<Order> orders = orderRepository.findAll();

        // then
        Order order = orders.get(0);

        System.out.println("order.getCreateDate() = " + order.getCreateDate());
        System.out.println("order.getModifiedDate() = " + order.getModifiedDate());

        assertThat(order.getOrderName()).isEqualTo(testOrder.getOrderName());
        assertThat(order.getCreateDate()).isEqualTo(testOrder.getCreateDate());
        assertThat(order.getCreateDate()).isAfter(now);
        assertThat(order.getModifiedDate()).isAfter(now);
    }
}