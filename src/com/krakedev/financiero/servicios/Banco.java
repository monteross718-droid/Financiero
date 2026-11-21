package com.krakedev.financiero.servicios;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;

public class Banco {
    private int ultimoCodigo = 1000;

    public Banco() {
    }

    public int getUltimoCodigo() {
        return ultimoCodigo;
    }

    public void setUltimoCodigo(int ultimoCodigo) {
        this.ultimoCodigo = ultimoCodigo;
    }

    public Cuenta crearCuenta(Cliente cliente) {
        String codigoStr = ultimoCodigo + "";
        ultimoCodigo++;
        Cuenta cuenta = new Cuenta(codigoStr);
        cuenta.setPropietario(cliente);
        return cuenta;
    }

    public boolean depositar(double monto, Cuenta cuenta) {
        if (cuenta == null || !Double.isFinite(monto) || monto <= 0) {
            return false;
        }
        double nuevoSaldo = cuenta.getSaldoActual() + monto;
        if (!Double.isFinite(nuevoSaldo)) {
            return false;
        }
        cuenta.setSaldoActual(nuevoSaldo);
        return true;
    }

    public boolean retirar(double monto, Cuenta cuenta) {
        if (cuenta == null || !Double.isFinite(monto) || monto <= 0
                || monto > cuenta.getSaldoActual()) {
            return false;
        }
        cuenta.setSaldoActual(cuenta.getSaldoActual() - monto);
        return true;
    }

    // El PDF solo indica origen y destino, sin monto. Interpretacion
    // documentada: este metodo transfiere todo el saldo disponible.
    public boolean transferir(Cuenta origen, Cuenta destino) {
        if (origen == null) {
            return false;
        }
        return transferir(origen, destino, origen.getSaldoActual());
    }

    // Sobrecarga adicional para transferir una cantidad especifica.
    public boolean transferir(Cuenta origen, Cuenta destino, double monto) {
        if (origen == null || destino == null || origen == destino
                || !Double.isFinite(monto) || monto <= 0
                || monto > origen.getSaldoActual()) {
            return false;
        }
        // Comprobacion previa para evitar saldos no finitos en destino.
        if (!Double.isFinite(destino.getSaldoActual() + monto)) {
            return false;
        }
        if (!retirar(monto, origen)) {
            return false;
        }
        if (depositar(monto, destino)) {
            return true;
        }
        // Reversion defensiva si el deposito falla.
        depositar(monto, origen);
        return false;
    }
}
