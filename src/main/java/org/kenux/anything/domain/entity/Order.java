package org.kenux.anything.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oder_name")
    private String orderName;

    public Order(String orderName) {
        this.orderName = orderName;
    }
}
