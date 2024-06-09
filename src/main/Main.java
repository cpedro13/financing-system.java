package main;

import util.InterfaceUsuario;
import modelo.Financiamento;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        double taxaJuros = interfaceUsuario.solicitarTaxaJuros();
        int prazoFinanciamentoAnos = interfaceUsuario.solicitarPrazoFinanciamento();
        double valorImovel = interfaceUsuario.solicitarValorImovel();

        // quatro financiamentos predefinidos
        Financiamento financiamento1 = new Financiamento(200900,25,13 );
        Financiamento financiamento2 = new Financiamento(50650,8,10);
        Financiamento financiamento3 = new Financiamento(350650,35,15);
        Financiamento financiamento4 = new Financiamento(90500,10,10);

        // array list com os financiamentos
        ArrayList<Financiamento> listaFinanciamentos = new ArrayList<>();
        listaFinanciamentos.add(financiamento1);
        listaFinanciamentos.add(financiamento2);
        listaFinanciamentos.add(financiamento3);
        listaFinanciamentos.add(financiamento4);

        double totalFinanciamento = 0; {

        for (Financiamento financiamento : listaFinanciamentos) {
            interfaceUsuario.exibirDetalhesFinanciamento(financiamento);
            totalFinanciamento += financiamento.calcularTotalPagamento();
            }

        Financiamento novoFinanciamento = new Financiamento(valorImovel, prazoFinanciamentoAnos, taxaJuros);
        interfaceUsuario.exibirDetalhesFinanciamento((novoFinanciamento));
        totalFinanciamento += novoFinanciamento.calcularTotalPagamento();

        System.out.println("TOTAL FINANCIADO: " + totalFinanciamento);;
        }
    }
}
