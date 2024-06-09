package util;
import java.util.Scanner;

public class InterfaceUsuario {

    private Scanner scanner;
    public InterfaceUsuario() {
        this.scanner = new Scanner(System.in);
        System.out.println("SIMULADOR DE FINANCIAMENTOS\n===========================\n" +
                "Insira seu nome:");
        String id = scanner.nextLine();
        System.out.println("Bem-vindo, " + id + "!");
    }

    public double solicitarValorImovel() {
        double valorImovel = 0;
        do {
            System.out.println("Insira o valor do imóvel desejado:");
            valorImovel = scanner.nextDouble();
            if (valorImovel <= 10000) {
                System.out.println("Valor inválido (Valor mínimo financiável é de 10000).");
            }
        } while (valorImovel <= 10000);
        return valorImovel;
    }

    public int solicitarPrazoFinanciamento() {
        int prazoFinanciamento =0;
        do {
            System.out.println("Insira o prazo de financiamento desejado (em anos):");
            prazoFinanciamento = scanner.nextInt();
            if (prazoFinanciamento <= 0) {
                System.out.println("Prazo inválido, o prazo deve ser um número positivo.");
            } else if (prazoFinanciamento > 35) {
                System.out.println("O prazo máximo que oferecemos é de 35 anos.");
            }
        } while (prazoFinanciamento <= 0 || prazoFinanciamento > 35);
        return prazoFinanciamento;
    }

    public double solicitarTaxaJuros() {
        double taxaJuros = 0;
        do {
            System.out.println("Insira a taxa de juros desejada (10 a 15%):");
            taxaJuros = scanner.nextDouble();
            if (taxaJuros < 10) {
                System.out.println("Taxa de juros inválida, o valor deverá ser positivo.");
            } else if (taxaJuros > 15) {
                System.out.println("Taxa de juros inválida, o valor não poderá ser maior que 15%.");
            }
        } while (taxaJuros < 10 || taxaJuros > 15);
        return taxaJuros;
    }

    public void exibirDetalhesFinanciamento(modelo.Financiamento financiamento) {
        System.out.println();
        System.out.println("SIMULAÇÃO DO FINANCIAMENTO\n===========================");
        System.out.println("Valor do Imóvel: " + financiamento.getValorImovel());
        System.out.println("Prazo do Financiamento: " + financiamento.getPrazoFinanciamentoAnos() + " anos");
        System.out.println("Taxa de Juros: " + financiamento.getTaxaJuros() + "%");
        System.out.println("Pagamento Mensal: " + financiamento.calcularPagamentoMensal());
        System.out.println("Total a Pagar: " + financiamento.calcularTotalPagamento());
        System.out.println();
    }
}



