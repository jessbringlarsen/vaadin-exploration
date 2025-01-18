package dk.bringlarsen.vaadin_exploration.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record PersonInputModel(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Positive
        int age) {
}
