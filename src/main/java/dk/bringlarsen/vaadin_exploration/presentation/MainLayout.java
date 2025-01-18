package dk.bringlarsen.vaadin_exploration.presentation;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Route;

@Route("")
public class MainLayout extends AppLayout {

    public MainLayout() {
        addToNavbar(createHeader());
        addToDrawer(createNavigation());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();
        nav.addItem(new SideNavItem("Persons", PersonView.class, VaadinIcon.HEART.create()));
        nav.addItem(new SideNavItem("Settings", SettingsView.class, VaadinIcon.SUN_O.create()));
        return nav;
    }

    private HorizontalLayout createHeader() {
        return new HorizontalLayout(new DrawerToggle(), new H1("Vaadin Exploration"));
    }
}
