package com.scalian.ArquitecturaSpringBoot.controller;

import com.scalian.ArquitecturaSpringBoot.model.entity.OrderHeader;
import com.scalian.ArquitecturaSpringBoot.repository.OrderHeaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderHeaderController {

    private final OrderHeaderRepository orderHeaderRepository;

    @GetMapping("/orders")
    public List<OrderHeader> getOrders() {
        return orderHeaderRepository.findAll();
    }
}
