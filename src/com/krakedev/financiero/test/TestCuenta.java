package com.krakedev.financiero.test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;

public class TestCuenta {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta("1000");

        System.out.println("=== Cuenta creada con propietario vacio ===");
        // Ya no ocurre NullPointerException: hay un objeto Cliente creado.
        // Sus campos siguen siendo null mientras no se asignen datos.
        cuenta.imprimir();

        System.out.println("=== Cuenta con datos del propietario ===");
        Cliente cliente = new Cliente("1720000001", "Ana", "Perez");
        cuenta.setPropietario(cliente);
        cuenta.imprimir();

        System.out.println("=== Ejemplo del banco ===");
        com.krakedev.financiero.servicios.Banco banco =
                new com.krakedev.financiero.servicios.Banco();
        Cuenta c1 = banco.crearCuenta(cliente);
        Cuenta c2 = banco.crearCuenta(new Cliente("1720000002", "Luis", "Mora"));
        System.out.println("Codigos consecutivos: " + c1.getId() + ", " + c2.getId());

        banco.depositar(200, c1);
        banco.transferir(c1, c2, 50);
        System.out.println("Saldo c1: " + c1.getSaldoActual());
        System.out.println("Saldo c2: " + c2.getSaldoActual());
    }
}
