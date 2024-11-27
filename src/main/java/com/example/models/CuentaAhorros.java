package com.example.models;

public class CuentaAhorros extends Cuenta {
    private boolean isActive = true;
    private int limit = 10000;

    public CuentaAhorros(float saldo, int numeroAbonos, int numeroRetiros, float tasaAnual, float comisionMensual) {
        super(saldo, numeroAbonos, numeroRetiros, tasaAnual, comisionMensual);
        this.isActive = saldo >= limit;
    }

    public CuentaAhorros() {
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    void ingresar(float cantidad) {
        if (isActive) {
            super.ingresar(cantidad);

            if (saldo >= 10000) {
                isActive = true;
            } else {
                isActive = false;
            }
        } else {
            System.out.println("La cuenta está inactiva");
        }
    }

    @Override
    void retirar(float cantidad) {
        if (isActive) {
            super.retirar(cantidad);

            if (saldo >= limit) {
                isActive = true;
            } else {
                isActive = false;
            }
        } else {
            System.out.println("La cuenta está inactiva");
        }
    }

    @Override
    void extractoMensual() {
        if (numeroRetiros > 4) {
            comisionMensual += (numeroRetiros - 4) * 1000;
        }

        super.extractoMensual();
        isActive = saldo >= 10000;
    }

    @Override
    public void imprimir() {
        super.imprimir();

        System.out.println("El estado de la Cuenta de Ahorros: " + (isActive ? "ACTIVADA" : "DESACTIVADA"));
        System.out.println("Número de transacciones realizadas: " + (numeroAbonos - numeroRetiros));
    }
}
