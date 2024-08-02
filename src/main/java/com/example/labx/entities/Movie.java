package com.example.labx.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id")
    @SequenceGenerator(name = "user_id", initialValue = 6, allocationSize = 1)
    private Long id;
    @Length(max = 100)
    private String title;
    @Min(1900)
    @Max(2100)
    private Integer year;
    @Length(max = 100)
    private String director;
    private LocalTime length;
    @Min(0)
    @Max(10)
    private Integer rating;
}
