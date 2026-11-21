package com.krakedev.financiero.testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;

public class CuentaClienteTest {

    @Test
    void cuentaIniciaConSaldoCeroYTipoAhorros() {
        Cuenta cuenta = new Cuenta("1000");
        assertEquals("1000", cuenta.getId());
        assertEquals(0.0, cuenta.getSaldoActual(), 0.0001);
        assertEquals("A", cuenta.getTipo());
    }

    @Test
    void clienteSeInicializaConDatos() {
        Cliente cliente = new Cliente("1712345678", "Ana", "Lopez");
        assertEquals("1712345678", cliente.getCedula());
        assertEquals("Ana", cliente.getNombre());
        assertEquals("Lopez", cliente.getApellido());
    }

    @Test
    void sePuedenModificarLosDatosConSetters() {
        Cliente cliente = new Cliente();
        cliente.setCedula("12");
        cliente.setNombre("Sara");
        cliente.setApellido("Mena");
        assertEquals("12", cliente.getCedula());
        assertEquals("Sara", cliente.getNombre());
        assertEquals("Mena", cliente.getApellido());

        Cuenta cuenta = new Cuenta("X");
        cuenta.setId("Y");
        cuenta.setTipo("C");
        cuenta.setSaldoActual(100.5);
        cuenta.setPropietario(cliente);
        assertEquals("Y", cuenta.getId());
        assertEquals("C", cuenta.getTipo());
        assertEquals(100.5, cuenta.getSaldoActual(), 0.0001);
        assertSame(cliente, cuenta.getPropietario());
    }

    @Test
    void constructorDeCuentaEvitaPropietarioNulo() {
        Cuenta cuenta = new Cuenta("1000");
        assertNotNull(cuenta.getPropietario());
        assertNull(cuenta.getPropietario().getCedula());
        assertDoesNotThrow(cuenta::imprimir);
    }

    @Test
    void establecerPropietarioNuloReproduceNullPointerException() {
        // Ilustra el fallo original, no la solucion final del constructor.
        Cuenta cuenta = new Cuenta("1000");
        cuenta.setPropietario(null);
        assertThrows(NullPointerException.class, cuenta::imprimir);
    }
}
