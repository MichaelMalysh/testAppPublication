package com.example.service;

import com.example.model.Publication;

import java.util.List;


public interface PublicationService {
    List<Publication>  getAllPublications();
    void savePublication(Publication publication);
    Publication getById(long id);
}
