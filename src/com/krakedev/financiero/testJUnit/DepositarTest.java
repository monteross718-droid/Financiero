package com.krakedev.financiero.testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class DepositarTest {
    @Test
    void depositoPositivoIncrementaSaldo() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        assertTrue(banco.depositar(150.5, cuenta));
        assertEquals(150.5, cuenta.getSaldoActual(), 0.0001);
    }

    @Test
    void depositosAcumulados() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        assertTrue(banco.depositar(100, cuenta));
        assertTrue(banco.depositar(30, cuenta));
        assertEquals(130.0, cuenta.getSaldoActual(), 0.0001);
    }

    @Test
    void depositoCeroSeRechazaSinCambiarSaldo() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        assertFalse(banco.depositar(0, cuenta));
        assertEquals(0.0, cuenta.getSaldoActual(), 0.0001);
    }

    @Test
    void depositoNegativoSeRechaza() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        assertFalse(banco.depositar(-5, cuenta));
        assertEquals(0.0, cuenta.getSaldoActual(), 0.0001);
    }

    @Test
    void depositoConCuentaNulaSeRechaza() {
        assertFalse(new Banco().depositar(100, null));
    }

    @Test
    void depositoNaNSeRechaza() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        assertFalse(banco.depositar(Double.NaN, cuenta));
        assertEquals(0.0, cuenta.getSaldoActual(), 0.0001);
    }
}
