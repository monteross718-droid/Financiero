package com.krakedev.financiero.testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class CrearCuentaTest {
    @Test
    void primeraCuentaUsaCodigo1000() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("171", "Ana", "Lopez");
        Cuenta cuenta = banco.crearCuenta(cliente);
        assertEquals("1000", cuenta.getId());
        assertEquals("A", cuenta.getTipo());
        assertEquals(0.0, cuenta.getSaldoActual(), 0.0001);
        assertSame(cliente, cuenta.getPropietario());
    }

    @Test
    void codigosSonConsecutivos() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("171", "Ana", "Lopez");
        assertEquals("1000", banco.crearCuenta(cliente).getId());
        assertEquals("1001", banco.crearCuenta(cliente).getId());
        assertEquals("1002", banco.crearCuenta(cliente).getId());
        assertEquals(1003, banco.getUltimoCodigo());
    }

    @Test
    void elCodigoPuedeConfigurarse() {
        Banco banco = new Banco();
        banco.setUltimoCodigo(2000);
        assertEquals("2000", banco.crearCuenta(new Cliente()).getId());
        assertEquals(2001, banco.getUltimoCodigo());
    }
}
