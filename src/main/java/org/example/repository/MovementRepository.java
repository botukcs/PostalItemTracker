package org.example.repository;

import org.example.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByPostalItemIdOrderByEventTime(Long postalItemId);
}