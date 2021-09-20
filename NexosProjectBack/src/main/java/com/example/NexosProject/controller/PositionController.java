package com.example.NexosProject.controller;

import com.example.NexosProject.model.Position;
import com.example.NexosProject.model.User;
import com.example.NexosProject.services.PositionService;
import com.example.NexosProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @PostMapping(path = "/create/position")
    private ResponseEntity<Position> savePosition (@RequestBody Position position){
        Position positionId = positionService.createPosition(position);

        try{
            return ResponseEntity.created(new URI("/position/create/position" + positionId.getId())).body(positionId);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(path = "/list/position")
    private ResponseEntity<List<Position>> listAllPosition (){
        return ResponseEntity.ok(positionService.getAllPosition());
    }

    @DeleteMapping(path = "/delete/position")
    private ResponseEntity<Void> deletePosition (@RequestBody Position position){
        positionService.deletePosition(position);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get/position/{id}")
    private ResponseEntity<Optional<Position>> getOnePosition (@PathVariable(name = "id") int id){
        return ResponseEntity.ok(positionService.findById(id));
    }
}
