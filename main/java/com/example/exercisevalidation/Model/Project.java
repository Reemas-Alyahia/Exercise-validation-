package com.example.exercisevalidation.Model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotEmpty(message = "your Id must be not Empty!")
    @Size(min = 2,max = 10,message = "id size should be 5 to 10")
    private String id;

    @NotEmpty(message = "your company name must be not Empty!")
    @Size(min = 6,max = 20,message = "companyName size should be 6 to 20")
    private String companyName;

    @NotEmpty (message = "your Id must be not Empty!")
    @Size(min=8,max = 20,message = "title size should be 8 to 20")
    private String title;

    @NotEmpty(message = "your status must be not Empty!")
    @Pattern(regexp = "^(Not Started|in Progress|Completed)$",
            message = "Status must be either 'Not Started', 'in Progress', or 'Completed'.")
    private String status;

    @NotEmpty(message = "your description must be not Empty!")
    @Size(min = 6,max = 40,message = "description size should be 6 to 40")
    private String description;



    private String previousStatus;
    @AssertTrue(message = "Invalid status transition")
public boolean isThereNext() {
        if (previousStatus == null) return true; // أول مرة بدون حالة سابقة

        switch (previousStatus) {
            case "Not Started":
                return status.equals("Not Started") || status.equals("in Progress");
            case "in Progress":
                return status.equals("in Progress") || status.equals("Completed");
            case "Completed":
                return status.equals("Completed");
            default:
                return false;
        }
}
}
