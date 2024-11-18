package com.example.exercisevalidation.Control;

import com.example.exercisevalidation.ApiResponse.ApiResponse;
import com.example.exercisevalidation.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping ("/api/v1/project")
public class ProjectContr {
ArrayList<Project> projects=new ArrayList<>();

@GetMapping("/get")
public ResponseEntity getProjects() {
return ResponseEntity.status(200).body(projects);
}
@PostMapping("/add")
public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors) {
    if (errors.hasErrors()) {
        String message=errors.getFieldError().getDefaultMessage();

return ResponseEntity.status(400).body(message);
    }
    projects.add(project);
return ResponseEntity.status(201).body("project added successfully");}


    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody @Valid Project project, Errors errors) {
if (errors.hasErrors()) {
    String message=errors.getFieldError().getDefaultMessage();
return ResponseEntity.status(400).body(message);
}
projects.set(index, project);
    return ResponseEntity.status(201).body("project updated successfully");}

    @DeleteMapping("/delet/{index}")
public ResponseEntity deleteProject(@PathVariable int index) {
    projects.remove(index);
    return ResponseEntity.status(201).body("project deleted successfully");
}

//////////////
@PutMapping("/change/{index}")
public ResponseEntity<?> change(@PathVariable int index, @RequestBody @Valid Project project, Errors errors) {
    if (errors.hasErrors()) {
        return ResponseEntity.status(400).body(errors.getAllErrors().get(0).getDefaultMessage());
    }
    if (index >= 0 && index < projects.size()) {
        Project newP = projects.get(index);
        project.setPreviousStatus(newP.getStatus());
        newP.setStatus(project.getStatus());
        return ResponseEntity.status(200).body("Status updated successfully");
    }

    return ResponseEntity.status(404).body("Invalid index");
}

//////Search for a project by given title
    @GetMapping("/search")
    public ResponseEntity search(@RequestParam  String title){
    for(Project p:projects){
        if(p.getTitle().equalsIgnoreCase(title)){
        }
    }
    return ResponseEntity.status(200).body(new ApiResponse("Done from searching: "+projects));
    }

//• Display All project for one company by companyName.
    @GetMapping("/desplay")
    public ResponseEntity display(@RequestParam @Valid String companyName ){
    ArrayList<Project>newP=new ArrayList<>();
    for(Project p:projects){
        if(p.getCompanyName().equalsIgnoreCase(companyName)){
            newP.add(p);
        }
    }
    return ResponseEntity.status(201).body(new ApiResponse("Done from Display: "+newP));
    }

}

