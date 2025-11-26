package com.scalian.ArquitecturaSpringBoot.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "ORDER_HEADER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_header_seq")
    @SequenceGenerator(name = "order_header_seq", sequenceName = "ORDER_HEADER_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @Column(name = "STATUS", nullable = false)
    private String status;
}
