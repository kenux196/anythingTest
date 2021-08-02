package org.kenux.anything.domain.repository;

import org.kenux.anything.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
