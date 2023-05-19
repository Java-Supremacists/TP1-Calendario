import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ControlerXmlTest {
    @Test
    public void testConGuardadoDeEventos() {
        var inicia = LocalDateTime.of(2023,5,19,10,30);
        var termina = LocalDateTime.of(2023,5,19,20,0);
        var ev1 = new Evento("Evento1","Esta es la descripcion a cargar",false,inicia,termina);
        var ev2 = new Evento(null,null);
        ByteArrayOutputStream archivo = new ByteArrayOutputStream();
        var xmlManejador = new ControlerXml();


        xmlManejador.generateXml(ev1,"Evento",archivo);
        InputStream cargarArchivo = new ByteArrayInputStream(archivo.toByteArray());
        xmlManejador.cargarXml(ev2,cargarArchivo);


        assertEquals(ev1.getTitulo(),ev2.getTitulo());
        assertEquals(ev1.getDescripcion(),ev2.getDescripcion());
        assertEquals(ev1.esDiaEntero(),ev2.esDiaEntero());
        assertEquals(ev1.cuandoEmpieza(),ev2.cuandoEmpieza());
        assertEquals(ev1.cuandoTermina(),ev2.cuandoTermina());
        assertNotEquals(ev1,ev2);
    }
    @Test
    public void testConGuardadoDeTareas() {
    }
    @Test
    public void testConGuardadoDeAlarmas() {
        var alarm1 = new Alarmas(true,null);
        var alarm2 = new Alarmas();
        List<LocalDateTime> array = new ArrayList<>();
        for (int i = 1; i < 13;i++){
            array.add(LocalDateTime.of(2023,i,i,i,i));
        }
        alarm1.agregarAlarma(array);
        ByteArrayOutputStream archivo = new ByteArrayOutputStream();
        var xmlManejador = new ControlerXml();

        xmlManejador.generateXml(alarm1,"Clase_Alarma",archivo);
        InputStream cargarArchivo = new ByteArrayInputStream(archivo.toByteArray());
        xmlManejador.cargarXml(alarm2,cargarArchivo);

        assertEquals(alarm1.size(),alarm2.size());
        assertEquals(alarm1.repiteLasAlarmas(),alarm2.repiteLasAlarmas());
        assertEquals(alarm1.primerAlarmaASonar(),alarm2.primerAlarmaASonar());
        for (int i = 1; i < 13;i++){
            var alarmax = LocalDateTime.of(2023,i,i,i,i);
            assertEquals(alarm1.size(),alarm2.size());
            assertEquals(alarm1.quedanAlarmas(),alarm2.quedanAlarmas());
            assertEquals(alarm1.existeAlarma(alarmax),alarm2.existeAlarma(alarmax));
            assertEquals(alarm1.primerAlarmaASonar(),alarm2.primerAlarmaASonar());
            alarm1.eliminarAlarma(alarmax);
            alarm2.eliminarAlarma(alarmax);
            assertEquals(alarm1.size(),alarm2.size());
            assertEquals(alarm1.quedanAlarmas(),alarm2.quedanAlarmas());
            assertEquals(alarm1.existeAlarma(alarmax),alarm2.existeAlarma(alarmax));
            assertEquals(alarm1.primerAlarmaASonar(),alarm2.primerAlarmaASonar());
        }
    }
    @Test
    public void testConGuardadoDeCalendarios() {
    }
}