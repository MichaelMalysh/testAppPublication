package com.example.repository;

import com.example.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
        List<Publication> findByTheme(String theme);
        List<Publication> findByTitle(String theme);
}
