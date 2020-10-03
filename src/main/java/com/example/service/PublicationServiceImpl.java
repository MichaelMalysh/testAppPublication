package com.example.service;

import com.example.model.Publication;
import com.example.repository.PublicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Misha Malysh
 */
@Service
@Transactional
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    @Override
    public void savePublication(Publication publication) {
        this.publicationRepository.save(publication);
    }

    @Override
    public Publication getById(long id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        Publication publication;
        if (optionalPublication.isPresent()) {
            publication = optionalPublication.get();
        } else {
            throw new RuntimeException("Publication not found for id" + id);
        }
        return publication;
    }
}
