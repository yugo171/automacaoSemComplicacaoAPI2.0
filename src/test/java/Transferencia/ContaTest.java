package Transferencia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    Cliente SilvioSantos;

    Cliente Xuxa;

    Conta contaSilvioSantos;

    Conta contaXuxa;

    @BeforeEach
    void setUp() {
        Xuxa = new Cliente("Xuxa","1234518972", "12312321125");
        SilvioSantos = new Cliente("Silvio Santos", "324789324987", "234591239x");

        contaXuxa = new Conta("0025", "57839",2500.00, Xuxa);
        contaSilvioSantos = new Conta("0023","21387",3000.00,SilvioSantos);
    }

    @Test
    public void realizarTransacao (){
        contaXuxa.realizarTransferencia(1000.00, contaSilvioSantos);

        assertEquals(1500.00, contaXuxa.getSaldo());
        assertEquals(4000.00,contaSilvioSantos.getSaldo());
    }

    @Test
    public void ValidarTransferenciaInvalida(){
        boolean resultado = contaXuxa.realizarTransferencia(2700,contaSilvioSantos);

        assertFalse(resultado);
    }
}