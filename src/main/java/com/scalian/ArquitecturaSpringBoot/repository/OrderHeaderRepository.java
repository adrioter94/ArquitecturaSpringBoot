package com.scalian.ArquitecturaSpringBoot.repository;

import com.scalian.ArquitecturaSpringBoot.model.entity.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {}

