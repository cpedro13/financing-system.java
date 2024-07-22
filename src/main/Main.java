package main;

import java.io.*;
import util.InterfaceUsuario;
import modelo.Financiamento;
import modelo.Casa;
import modelo.Apartamento;
import modelo.Terreno;
import modelo.Terreno.TipoLocal;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        // financiamentos predefinidos
        Apartamento apartamento1 = new Apartamento(2805000, 10, 15, 2, 10);
        Apartamento apartamento2 = new Apartamento(500000, 14, 10, 1, 4);
        Casa casa1 = new Casa(500000, 19, 10, 150, 220);
        Casa casa2 = new Casa(390000, 30, 15, 90, 130);
        Terreno terreno1 = new Terreno(1000250, 20, 10, TipoLocal.ZONA_RURAL);

        // array list com os financiamentos
        ArrayList<Financiamento> listaFinanciamentos = new ArrayList<>();
        listaFinanciamentos.add(apartamento1);
        listaFinanciamentos.add(apartamento2);
        listaFinanciamentos.add(casa1);
        listaFinanciamentos.add(casa2);
        listaFinanciamentos.add(terreno1);

        double totalFinanciamento = 0;

        // exibir detalhes dos financiamentos predefinidos e calcular o total
        for (Financiamento financiamento : listaFinanciamentos) {
            interfaceUsuario.exibirDetalhesFinanciamento(financiamento);
            totalFinanciamento += financiamento.calcularTotalPagamento();
        }

        // criar um novo financiamento com base na escolha do usuário
        Financiamento novoFinanciamento = interfaceUsuario.criarNovoFinanciamento();
        interfaceUsuario.exibirDetalhesFinanciamento(novoFinanciamento);
        listaFinanciamentos.add(novoFinanciamento);
        totalFinanciamento += novoFinanciamento.calcularTotalPagamento();

        System.out.println("TOTAL FINANCIADO: " + totalFinanciamento);

        // salvar os detalhes dos financiamentos em um arquivo
        salvarDetalhesFinanciamentos(listaFinanciamentos, "detalhesFinanciamentos.txt");

        // ler os detalhes dos financiamentos do arquivo e exibir no console
        System.out.println("Leitura dos detalhes dos financiamentos do arquivo:");
        lerDetalhesFinanciamentos("detalhesFinanciamentos.txt");
    }

    // escrita dos financiamentos da array
    public static void salvarDetalhesFinanciamentos(ArrayList<Financiamento> financiamentos, String nomeArquivo) {
        try (FileWriter fw = new FileWriter(nomeArquivo, false)) {
            for (Financiamento financiamento : financiamentos) {
                fw.write(financiamento.getClass().getSimpleName() + "\n");
                fw.write("Valor do Imóvel: " + financiamento.getValorImovel() + "\n");
                fw.write("Prazo do Financiamento: " + financiamento.getPrazoFinanciamentoAnos() + " anos\n");
                fw.write("Taxa de Juros: " + financiamento.getTaxaJuros() + "%\n");
                fw.write("Pagamento Mensal: " + financiamento.calcularPagamentoMensal() + "\n");
                fw.write("Total a Pagar: " + financiamento.calcularTotalPagamento() + "\n");

                // Atributos específicos das subclasses
                switch (financiamento) {
                    case Apartamento apt -> {
                        fw.write("Número de Vagas de Garagem: " + apt.getNumeroVagasGaragem() + "\n");
                        fw.write("Número do Andar: " + apt.getNumeroAndar() + "\n");
                    }
                    case Casa casa -> {
                        fw.write("Área Construída: " + casa.getAreaConstruida() + "\n");
                        fw.write("Área do Terreno: " + casa.getAreaTerreno() + "\n");
                    }
                    case Terreno terreno -> fw.write("Tipo do Local: " + terreno.getTipoLocal() + "\n");
                    default -> {
                    }
                }

                fw.write("=================================\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // leitor
    public static void lerDetalhesFinanciamentos(String nomeArquivo) {
        try (FileReader fr = new FileReader(nomeArquivo); BufferedReader br = new BufferedReader(fr)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

