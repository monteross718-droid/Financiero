package com.krakedev.financiero.testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class RetirarTest {
    @Test
    void retiroPositivoDescuentaSaldo() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        banco.depositar(100, cuenta);
        assertTrue(banco.retirar(35, cuenta));
        assertEquals(65.0, cuenta.getSaldoActual(), 0.0001);
    }

    @Test
    void retiroDeTodoElSaldoEstaPermitido() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        banco.depositar(90, cuenta);
        assertTrue(banco.retirar(90, cuenta));
        assertEquals(0.0, cuenta.getSaldoActual(), 0.0001);
    }

    @Test
    void retiroMayorAlSaldoSeRechaza() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        banco.depositar(90, cuenta);
        assertFalse(banco.retirar(91, cuenta));
        assertEquals(90.0, cuenta.getSaldoActual(), 0.0001);
    }

    @Test
    void retiroCeroSeRechaza() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        banco.depositar(90, cuenta);
        assertFalse(banco.retirar(0, cuenta));
        assertEquals(90.0, cuenta.getSaldoActual(), 0.0001);
    }

    @Test
    void retiroNegativoSeRechaza() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        banco.depositar(90, cuenta);
        assertFalse(banco.retirar(-10, cuenta));
        assertEquals(90.0, cuenta.getSaldoActual(), 0.0001);
    }

    @Test
    void cuentaNulaNoPermiteRetirar() {
        assertFalse(new Banco().retirar(100, null));
    }
}
