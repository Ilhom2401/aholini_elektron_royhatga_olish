package com.example.buildings.service;

import com.example.buildings.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenericService {

    private final PersonRepository personRepository;

    public boolean checkUserAction(Long userId){
        return personRepository.existsById(userId);
    }
}

