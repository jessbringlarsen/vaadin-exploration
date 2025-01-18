package dk.bringlarsen.vaadin_exploration.presentation;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import dk.bringlarsen.vaadin_exploration.person.PersonModel;
import dk.bringlarsen.vaadin_exploration.person.PersonRepository;

@Route(value = "persons", layout = MainLayout.class)
public class PersonView extends VerticalLayout {

    private final PersonRepository repository;
    private final Grid<PersonModel> grid;

    public PersonView(PersonRepository repo) {
        repository = repo;

        grid = new Grid<>(PersonModel.class);
        grid.setColumns("firstName", "lastName", "age");
        grid.setAllRowsVisible(true); // dynamic height

        TextField filter = new TextField();
        filter.setPlaceholder("Filter by last name");
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateEmployees(e.getValue()));

        new PersonContextMenu(grid);
        var actionsLayout = new HorizontalLayout(filter);
        add(actionsLayout, grid);
        updateEmployees("");
    }

    private void updateEmployees(String filterText) {
        if (filterText.isEmpty()) {
            grid.setItems(repository.findAll());
        } else {
            grid.setItems(repository.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }

    private class PersonContextMenu extends GridContextMenu<PersonModel> {
        public PersonContextMenu(Grid<PersonModel> target) {
            super(target);

            addItem("Edit", e -> e.getItem().ifPresent(person -> {
                // System.out.printf("Edit: %s%n", person.getFullName());
            }));
            addItem("Delete", e -> e.getItem().ifPresent(person -> {
                repository.delete(person.id());
                updateEmployees("");
                Notification.show("%s %s deleted".formatted(person.firstName(), person.lastName()));
            }));

            setDynamicContentHandler(person -> {
                // Do not show context menu when header is clicked
                if (person == null) {
                    return false;
                }
                return true;
            });
        }
    }
}
