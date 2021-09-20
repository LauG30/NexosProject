package com.example.NexosProject.services;

import com.example.NexosProject.model.Position;
import com.example.NexosProject.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public Position createPosition (Position position){
        return positionRepository.save(position);
    }

    public List<Position> getAllPosition (){
        return positionRepository.findAll();
    }

    public void deletePosition (Position position){
        positionRepository.delete(position);
    }

    public Optional<Position> findById(int id){
        return positionRepository.findById(id);
    }
}
