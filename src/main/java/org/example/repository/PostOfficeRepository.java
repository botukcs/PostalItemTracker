package org.example.repository;

import org.example.entity.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
    Optional<PostOffice> findByIndex(String index);
}