package dk.bringlarsen.vaadin_exploration.person;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PersonRepository {

    private final Map<UUID, PersonModel> store = new ConcurrentHashMap<>();

    public Collection<PersonModel> findAll() {
        return store.values();
    }

    public Optional<PersonModel> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public PersonModel save(PersonInputModel person) {
        UUID id = UUID.randomUUID();
        PersonModel result = new PersonModel(id, person.firstName(), person.lastName(), person.age());
        store.put(id, result);
        return result;
    }

    public Collection<PersonModel> findByLastNameStartsWithIgnoreCase(String searchPhrase) {
        if (searchPhrase == null || searchPhrase.isBlank()) {
            return Collections.emptyList();
        }
        return store.values().stream()
                .filter(personModel -> personModel.lastName().toLowerCase().startsWith(searchPhrase.toLowerCase()))
                .toList();
    }

    public boolean delete(UUID id) {
        return store.remove(id) != null;
    }
}
