package com.example.projectlab52.Control;

import com.example.projectlab52.ApiResponse.ApiResponse;
import com.example.projectlab52.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/eventt")
public class EventContrl {
    ArrayList<Event> events=new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getEvent(){
        return ResponseEntity.status(200).body(events);}
    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body("Event added successfully");}
    @PutMapping("/update/{index}")
    public ResponseEntity update(@PathVariable int index,@RequestBody @Valid Event event,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index,event);
        return ResponseEntity.status(200).body(new ApiResponse("Done from Updating")); }
    /////
    @DeleteMapping("/delet/{index}")
    public ResponseEntity delet(@PathVariable int index){
        events.remove(index);
        return ResponseEntity.status(201).body(new ApiResponse("Done from Deleting"));}

    //• Change capacity
    @PutMapping("/change/{index}")
    public ResponseEntity change(@PathVariable int index,@RequestParam int capacity){
        if(index >= 0 && index < events.size()){
            events.get(index).setCapacity(capacity);
        }
        return ResponseEntity.status(201).body(new ApiResponse("capacity is change "));   }

    //Search for a event by given id
    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String id){
        for(Event e:events){
            if(e.getId().equalsIgnoreCase(id)){
            }
        }
        return ResponseEntity.status(201).body(new ApiResponse("Don from searching "+events));
    }


}

