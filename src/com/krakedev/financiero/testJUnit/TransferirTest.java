package com.krakedev.financiero.testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TransferirTest {
    @Test
    void transferirDosParametrosMueveTodoElSaldo() {
        // Interpretacion documentada: origen y destino, sin monto.
        Banco banco = new Banco();
        Cuenta origen = new Cuenta("1000");
        Cuenta destino = new Cuenta("1001");
        banco.depositar(250, origen);
        assertTrue(banco.transferir(origen, destino));
        assertEquals(0.0, origen.getSaldoActual(), 0.0001);
        assertEquals(250.0, destino.getSaldoActual(), 0.0001);
    }

    @Test
    void transferirMontoEspecifico() {
        Banco banco = new Banco();
        Cuenta origen = new Cuenta("1000");
        Cuenta destino = new Cuenta("1001");
        banco.depositar(200, origen);
        banco.depositar(20, destino);
        assertTrue(banco.transferir(origen, destino, 50));
        assertEquals(150.0, origen.getSaldoActual(), 0.0001);
        assertEquals(70.0, destino.getSaldoActual(), 0.0001);
    }

    @Test
    void transferenciaMayorAlSaldoSeRechaza() {
        Banco banco = new Banco();
        Cuenta origen = new Cuenta("1000");
        Cuenta destino = new Cuenta("1001");
        banco.depositar(30, origen);
        assertFalse(banco.transferir(origen, destino, 40));
        assertEquals(30.0, origen.getSaldoActual(), 0.0001);
        assertEquals(0.0, destino.getSaldoActual(), 0.0001);
    }

    @Test
    void transferenciaDeSaldoCeroSeRechaza() {
        Banco banco = new Banco();
        Cuenta origen = new Cuenta("1000");
        Cuenta destino = new Cuenta("1001");
        assertFalse(banco.transferir(origen, destino));
        assertEquals(0.0, origen.getSaldoActual(), 0.0001);
        assertEquals(0.0, destino.getSaldoActual(), 0.0001);
    }

    @Test
    void transferenciaNegativaSeRechaza() {
        Banco banco = new Banco();
        Cuenta origen = new Cuenta("1000");
        Cuenta destino = new Cuenta("1001");
        banco.depositar(30, origen);
        assertFalse(banco.transferir(origen, destino, -5));
        assertEquals(30.0, origen.getSaldoActual(), 0.0001);
        assertEquals(0.0, destino.getSaldoActual(), 0.0001);
    }

    @Test
    void transferenciaAUnaCuentaNulaSeRechaza() {
        Banco banco = new Banco();
        Cuenta origen = new Cuenta("1000");
        banco.depositar(30, origen);
        assertFalse(banco.transferir(origen, null, 15));
        assertEquals(30.0, origen.getSaldoActual(), 0.0001);
    }

    @Test
    void mismaCuentaNoPuedeSerOrigenYDestino() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        banco.depositar(30, cuenta);
        assertFalse(banco.transferir(cuenta, cuenta, 10));
        assertEquals(30.0, cuenta.getSaldoActual(), 0.0001);
    }
}
