package com.rdtech.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rdtech.in.model.Property;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
