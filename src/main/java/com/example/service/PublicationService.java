package com.example.service;

import com.example.model.Publication;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PublicationService {
    List<Publication>  getAllPublications();
    void savePublication(Publication publication);
    Publication getById(long id);
    Page<Publication> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
