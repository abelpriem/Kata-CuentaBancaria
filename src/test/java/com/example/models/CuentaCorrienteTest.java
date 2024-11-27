package com.example.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CuentaCorrienteTest {

    CuentaCorriente cuentaCorriente = new CuentaCorriente();

    @Test
    void testIngresarSinSobregiro() {
        cuentaCorriente.setSaldo(11000);
        cuentaCorriente.ingresar(5000);

        assertEquals(16000, cuentaCorriente.getSaldo());
    }

    @Test
    void testIngresarConSobregiroMayor() {
        cuentaCorriente.setSaldo(11000);
        cuentaCorriente.setSobregiro(3000);
        cuentaCorriente.ingresar(5000);

        assertEquals(13000, cuentaCorriente.getSaldo());
    }

    @Test
    void testIngresarConSobregiroMenor() {
        cuentaCorriente.setSaldo(11000);
        cuentaCorriente.setSobregiro(3000);
        cuentaCorriente.ingresar(1000);

        assertEquals(2000, cuentaCorriente.getSobregiro());
    }
}
