package com.example.projectlab52.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
    @NotEmpty(message = "Id can't be Empty!")
    @Size(min=2, max=10, message = "Id must be 2 to 10")
    private String id;

    @NotEmpty(message = "description can't be Empty!")
    @Size(min=12 ,max = 30,message = "description must be 12 to 30")
    private String description;
    @NotNull(message = "Capacity cannot be empty!")
    @Min(value = 25, message = "Capacity must be a number between 25 and 100")
    @Max(value = 100, message = "Capacity must be a number between 25 and 100")
    private Integer capacity;

    @NotNull(message = "Start date must not be null")
    @PastOrPresent(message = "Start date must be in the past or present")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "End date must not be null")
    @FutureOrPresent(message = "End date must be in the future or present")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
