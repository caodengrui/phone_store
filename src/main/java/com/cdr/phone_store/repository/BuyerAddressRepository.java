package com.cdr.phone_store.repository;

import com.cdr.phone_store.entity.BuyerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerAddressRepository extends JpaRepository<BuyerAddress, Integer> {
}
