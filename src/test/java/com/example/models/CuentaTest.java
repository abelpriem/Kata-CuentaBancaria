package com.example.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CuentaTest {

    Cuenta cuenta = new Cuenta();

    @Test
    void testGettersAndSetters() {
        cuenta.setSaldo(12500);
        cuenta.setNumeroAbonos(45);
        cuenta.setNumeroRetiros(17);
        cuenta.setTasaAnual(3);
        cuenta.setComisionMensual(2);

        assertEquals(12500, cuenta.getSaldo());
        assertEquals(45, cuenta.getNumeroAbonos());
        assertEquals(17, cuenta.getNumeroRetiros());
        assertEquals(3, cuenta.getTasaAnual());
        assertEquals(2, cuenta.getComisionMensual());
    }

    @Test
    void testIngresar() {
        cuenta.setSaldo(12500);
        float cantidad = 2375;

        cuenta.ingresar(cantidad);

        assertEquals(1, cuenta.getNumeroAbonos());
        assertEquals(14875, cuenta.getSaldo());
    }

    @Test
    void testRetirar() {
        cuenta.setSaldo(14375);
        float cantidad = 1000;

        cuenta.retirar(cantidad);

        assertEquals(13375, cuenta.getSaldo());
    }

    @Test
    void testCalcularInteres() {
        cuenta.setSaldo(10000);
        cuenta.setTasaAnual(0.05f);

        cuenta.calcularInteres();

        float interesEsperado = (0.05f / 12) * 10000;
        assertEquals(10000 + interesEsperado, cuenta.getSaldo(), 0.01);
    }

    @Test
    void testExtractoMensual() {
        cuenta.setSaldo(12000);
        cuenta.setTasaAnual(0.05f);
        cuenta.setComisionMensual(50);

        cuenta.ingresar(500);
        cuenta.retirar(200);

        cuenta.extractoMensual();

        float interesEsperado = (0.05f / 12) * cuenta.getSaldo();
        assertEquals(12300 - 50 + interesEsperado, cuenta.getSaldo(), 0.01);
    }

    @Test
    void testImprimir() {
        cuenta.setSaldo(12000);
        cuenta.ingresar(1500);
        cuenta.retirar(200);

        cuenta.imprimir();

        assertEquals(13300, cuenta.getSaldo());
        assertEquals(1, cuenta.getNumeroAbonos());
        assertEquals(1, cuenta.getNumeroRetiros());
    }
}
