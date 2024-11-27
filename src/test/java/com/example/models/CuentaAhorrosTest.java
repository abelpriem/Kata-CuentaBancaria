package com.example.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CuentaAhorrosTest {

    CuentaAhorros cuentaAhorros = new CuentaAhorros();

    @Test
    void testIsActive() {
        boolean result = cuentaAhorros.isActive();
        assertTrue(result);
    }

    @Test
    void testIngresar() {
        cuentaAhorros.setSaldo(12000);
        cuentaAhorros.ingresar(240);

        assertEquals(12240, cuentaAhorros.getSaldo());
        assertTrue(cuentaAhorros.isActive());
    }

    @Test
    void testIngresarDesactivada() {
        cuentaAhorros.setSaldo(8000);
        cuentaAhorros.ingresar(240);

        assertEquals(8240, cuentaAhorros.getSaldo());
        assertFalse(cuentaAhorros.isActive());
    }

    @Test
    void testRetirar() {
        cuentaAhorros.setSaldo(12000);
        cuentaAhorros.retirar(1180);

        assertEquals(10820, cuentaAhorros.getSaldo());
        assertTrue(cuentaAhorros.isActive());
    }

    @Test
    void testRetirarDesactivada() {
        cuentaAhorros.setSaldo(10000);
        cuentaAhorros.retirar(560);

        assertEquals(9440, cuentaAhorros.getSaldo());
        assertFalse(cuentaAhorros.isActive());
    }

    @Test
    void testExtractoMensualSinMaxRetiros() {
        cuentaAhorros.setSaldo(12000);
        cuentaAhorros.setTasaAnual(0.05f);
        cuentaAhorros.setComisionMensual(50);

        cuentaAhorros.retirar(200);

        cuentaAhorros.extractoMensual();

        float interesEsperado = (cuentaAhorros.getTasaAnual() / 12) * cuentaAhorros.getSaldo();
        assertTrue(cuentaAhorros.isActive());
        assertEquals(cuentaAhorros.getSaldo() - cuentaAhorros.getComisionMensual() + interesEsperado,
                cuentaAhorros.getSaldo(), 0.01);
    }

    @Test
    void testExtractoMensualConMaxRetiros() {
        cuentaAhorros.setSaldo(12000);
        cuentaAhorros.setTasaAnual(0.05f);
        cuentaAhorros.setComisionMensual(50);

        cuentaAhorros.retirar(200);
        cuentaAhorros.retirar(1500);
        cuentaAhorros.retirar(3000);
        cuentaAhorros.retirar(150);

        cuentaAhorros.extractoMensual();

        float interesEsperado = (cuentaAhorros.getTasaAnual() / 12) * cuentaAhorros.getSaldo();
        assertFalse(cuentaAhorros.isActive());
        assertEquals(cuentaAhorros.getSaldo() - cuentaAhorros.getComisionMensual() + interesEsperado,
                cuentaAhorros.getSaldo(), 0.01);
    }

    @Test
    void testImprimir() {
        cuentaAhorros.setSaldo(12000);
        cuentaAhorros.ingresar(1500);
        cuentaAhorros.retirar(200);

        cuentaAhorros.imprimir();

        assertEquals(13300, cuentaAhorros.getSaldo());
        assertEquals(1, cuentaAhorros.getNumeroAbonos());
        assertEquals(1, cuentaAhorros.getNumeroRetiros());
        assertTrue(cuentaAhorros.isActive());
    }

}
