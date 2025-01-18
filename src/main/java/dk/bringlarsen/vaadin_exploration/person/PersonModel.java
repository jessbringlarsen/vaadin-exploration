package dk.bringlarsen.vaadin_exploration.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record PersonModel(
        @NotNull
        UUID id,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Positive
        int age) {

    public PersonModel(String firstName, String lastName, int age) {
        this(null, firstName, lastName, age);
    }
}