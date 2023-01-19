package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
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

    private Span status;
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

        HorizontalLayout dialogo = new HorizontalLayout();
        FormLayout formulario = new FormLayout();

        FormLayout nuevo = new FormLayout();


        Grid<ClassDato> grid = new Grid<>(ClassDato.class, false);
        grid.addColumn(ClassDato::getIp_from).setHeader("Ip_from");
        grid.addColumn(ClassDato::getIp_to).setHeader("Ip_To");
        grid.addColumn(ClassDato::getCountry_code).setHeader("CountryCode");
        grid.addColumn(ClassDato::getCountry_name).setHeader("CountryName");
        grid.addColumn(ClassDato::getRegion_name).setHeader("RegionName");
        grid.addColumn(ClassDato::getCity_name).setHeader("CityName");
        grid.addColumn(ClassDato::getLatitude).setHeader("Latitude");
        grid.addColumn(ClassDato::getLongitude).setHeader("Longuitud");
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


        grid.addItemDoubleClickListener(e->{
            status = new Span();
            status.setVisible(false);
            formulario.removeAll();


            Dialog dialog = new Dialog();

            TextField Ip_from = new TextField("Codigo de IP from");
            TextField Ip_To = new TextField("Codigo de IP to");
            TextField CountryCode = new TextField("Codigo de pais");
            TextField CountryName = new TextField("Nombre de pais");
            TextField RegionName = new TextField("Nombre de region");
            TextField CityName = new TextField("Nombre de ciudad");
            TextField Latitude = new TextField("Latitud");
            TextField Longitude = new TextField("Longuitud");
            TextField ZipCode = new TextField("Codigo zip");
            TextField TimeZone = new TextField("Tiempo de zona");

            ClassDato fila = e.getItem();


            Ip_from.setValue(String.valueOf(fila.getIp_from()));
            Ip_from.setEnabled(false);
            Ip_To.setValue(String.valueOf(fila.getIp_to()));
            CountryCode.setValue(String.valueOf(fila.getCountry_code()));
            CountryName.setValue(String.valueOf(fila.getCountry_name()));
            RegionName.setValue(String.valueOf(fila.getRegion_name()));
            CityName.setValue(fila.getCity_name());
            Latitude.setValue(String.valueOf(fila.getLatitude()));
            Longitude.setValue(String.valueOf(fila.getLongitude()));
            ZipCode.setValue(fila.getZip_code());
            TimeZone.setValue(fila.getTime_zone());



            formulario.add(Ip_from, Ip_To, CountryCode, CountryName, RegionName, CityName, Latitude, Longitude, ZipCode, TimeZone);
            formulario.setResponsiveSteps(
                    // Use one column by default
                    new FormLayout.ResponsiveStep("0", 1),
                    // Use two columns, if layout's width exceeds 500px
                    new FormLayout.ResponsiveStep("500px", 2));
            // Stretch the username field over 2 columns



            dialog.add(formulario);
            Button cancelar= new Button("Cancelar",
                    cierre->{
                        dialog.close();
                    });
            Button confirmar= new Button("Confirmar",
                    confirmacion->{

                        ClassDato modificado = new ClassDato();

                        modificado.setIp_from(Long.valueOf(Ip_from.getValue()));
                        modificado.setIp_to(Long.valueOf(Ip_To.getValue()));
                        modificado.setCountry_code(String.valueOf(CountryCode.getValue()));
                        modificado.setCountry_name(String.valueOf(CountryName.getValue()));
                        modificado.setRegion_name(String.valueOf(RegionName.getValue()));
                        modificado.setCity_name(String.valueOf(CityName.getValue()));
                        modificado.setLatitude(Double.parseDouble(Latitude.getValue()));
                        modificado.setLongitude(Double.parseDouble(Longitude.getValue()));
                        modificado.setZip_code(String.valueOf(ZipCode.getValue()));
                        modificado.setTime_zone(String.valueOf(TimeZone.getValue()));



                        System.out.println(modificado.getIp_from());
                        System.out.println(modificado.getIp_to());
                        System.out.println(modificado.getCountry_name());
                        System.out.println(modificado.getCountry_code());
                        System.out.println(modificado.getRegion_name());
                        System.out.println(modificado.getCity_name());
                        System.out.println(modificado.getLatitude());
                        System.out.println(modificado.getLongitude());
                        System.out.println(modificado.getZip_code());
                        System.out.println(modificado.getTime_zone());

                        System.out.println();
                        System.out.println("Los del service");

                        try {
                            service.Put(modificado, Long.valueOf(Ip_from.getValue()));
                            //grid.setItems(service.leeCasos());
                            //results.add(grid);
                        } catch (URISyntaxException | IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }


                        dialog.close();

                    });

            dialog.open();
            status.setVisible(false);

            dialogo.add(status);
            add(dialogo);
            formulario.add(cancelar);
            formulario.add(confirmar);

        });






        Button crearnuevo = new Button("CrearNuevoObjeto",
                pe-> {
                    status = new Span();
                    status.setVisible(false);
                    nuevo.removeAll();


                    Dialog dialog = new Dialog();

                    TextField Ip_from = new TextField("Codigo de IP from");
                    TextField Ip_To = new TextField("Codigo de IP to");
                    TextField CountryCode = new TextField("Codigo de pais");
                    TextField CountryName = new TextField("Nombre de pais");
                    TextField RegionName = new TextField("Nombre de region");
                    TextField CityName = new TextField("Nombre de ciudad");
                    TextField Latitude = new TextField("Latitud");
                    TextField Longitude = new TextField("Longuitud");
                    TextField ZipCode = new TextField("Codigo zip");
                    TextField TimeZone = new TextField("Tiempo de zona");


                    nuevo.add(Ip_from,Ip_To,CountryCode,CountryName,RegionName,CityName, Latitude, Longitude, ZipCode, TimeZone);
                    nuevo.setResponsiveSteps(
                            // Use one column by default
                            new FormLayout.ResponsiveStep("0", 1),
                            // Use two columns, if layout's width exceeds 500px
                            new FormLayout.ResponsiveStep("500px", 2));
                    // Stretch the username field over 2 columns



                    dialog.add(nuevo);
                    Button cancelar= new Button("Cancelar",
                            cierre->{
                                dialog.close();
                            });

                    Button confirmar1= new Button("Confirmar",
                            confirmar->{

                                ClassDato modificado = new ClassDato();

                                modificado.setIp_from(Long.valueOf(Ip_from.getValue()));
                                modificado.setIp_to(Long.valueOf(Ip_To.getValue()));
                                modificado.setCountry_code(String.valueOf(CountryCode.getValue()));
                                modificado.setCountry_name(String.valueOf(CountryName.getValue()));
                                modificado.setRegion_name(String.valueOf(RegionName.getValue()));
                                modificado.setCity_name(String.valueOf(CityName.getValue()));
                                modificado.setLatitude(Double.parseDouble(Latitude.getValue()));
                                modificado.setLongitude(Double.parseDouble(Longitude.getValue()));
                                modificado.setZip_code(String.valueOf(ZipCode.getValue()));
                                modificado.setTime_zone(String.valueOf(TimeZone.getValue()));


                                System.out.println(modificado.getIp_from());
                                System.out.println(modificado.getIp_to());
                                System.out.println(modificado.getCountry_name());
                                System.out.println(modificado.getCountry_code());
                                System.out.println(modificado.getRegion_name());
                                System.out.println(modificado.getCity_name());
                                System.out.println(modificado.getLatitude());
                                System.out.println(modificado.getLongitude());
                                System.out.println(modificado.getZip_code());
                                System.out.println(modificado.getTime_zone());

                                System.out.println();
                                System.out.println("Los del service");


                                try {
                                    service.Post(modificado);
                                    //grid.setItems(service.leeCasos());
                                } catch (URISyntaxException | IOException ex) {
                                    throw new RuntimeException(ex);
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }
                                dialog.close();
                            });


                    dialog.add(nuevo);
                    dialog.open();
                    status.setVisible(false);

                    dialogo.add(status);
                    add(dialogo);
                    nuevo.add(cancelar,confirmar1);


                });







        grid.setItems(people);
        primeracapa.add(grid);


        add(primeracapa,crearnuevo);



    }

}
