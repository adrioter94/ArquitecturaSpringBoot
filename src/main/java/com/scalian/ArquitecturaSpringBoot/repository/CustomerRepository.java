package com.scalian.ArquitecturaSpringBoot.repository;

import com.scalian.ArquitecturaSpringBoot.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}

