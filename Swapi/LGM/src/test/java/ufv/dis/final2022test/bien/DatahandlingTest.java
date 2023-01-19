package ufv.dis.final2022test.bien;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class DatahandlingTest {
    Inforecibida recibido;
    @BeforeEach
    void setUp() {
        recibido= new Inforecibida("people",4 );

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void comprobarelemento() throws URISyntaxException, IOException, InterruptedException {
        Datahandling datahandling = new Datahandling();
        Character cha= datahandling.getCharacter(recibido);
        assertTrue(datahandling.comprobarelemento(cha));
    }
}