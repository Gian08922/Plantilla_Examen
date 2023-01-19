package org.vaadin.example;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service
     *            The message service. Automatically injected Spring managed
     *            bean.
     */
    public MainView(@Autowired GreetService service) {

        HorizontalLayout primeracapa=new HorizontalLayout();

        primeracapa.setWidth("1100px");
        primeracapa.addClassName("left-content");


        Grid<ClassDato> grid = new Grid<>(ClassDato.class, false);
        grid.addColumn(ClassDato::getIp_from).setHeader("Ip_from");
        grid.addColumn(ClassDato::getIp_to).setHeader("Ip_To");
        grid.addColumn(ClassDato::getCountry_code).setHeader("CountryCode");
        grid.addColumn(ClassDato::getCountry_name).setHeader("CuntryName");
        grid.addColumn(ClassDato::getRegion_name).setHeader("RegionName");
        grid.addColumn(ClassDato::getCity_name).setHeader("CityNAme");
        grid.addColumn(ClassDato::getZip_code).setHeader("ZipCode");
        grid.addColumn(ClassDato::getTime_zone).setHeader("TimeZone");




        ArrayList<ClassDato> people = null;
        try {
            people = service.leeCasos();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        grid.setItems(people);
        primeracapa.add(grid);


        add(primeracapa);



    }

}
