package util;

import modelo.Apartamento;
import modelo.Casa;
import modelo.Financiamento;
import modelo.Terreno;
import modelo.Terreno.TipoLocal;
import java.util.InputMismatchException;
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
        while (true) {
            try {
                System.out.println("Insira o valor do imóvel desejado:");
                valorImovel = scanner.nextDouble();
                if (valorImovel <= 10000) {
                    System.out.println("Valor inválido (Valor mínimo financiável é de 10000).");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
        }
        return valorImovel;
    }

    public int solicitarPrazoFinanciamento() {
        int prazoFinanciamento = 0;
        while (true) {
            try {
                System.out.println("Insira o prazo de financiamento desejado (em anos):");
                prazoFinanciamento = scanner.nextInt();
                if (prazoFinanciamento <= 0) {
                    System.out.println("Prazo inválido, o prazo deve ser um número positivo.");
                } else if (prazoFinanciamento > 35) {
                    System.out.println("O prazo máximo que oferecemos é de 35 anos.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
        }
        return prazoFinanciamento;
    }

    public double solicitarTaxaJuros() {
        double taxaJuros = 0;
        while (true) {
            try {
                System.out.println("Insira a taxa de juros desejada (10 a 15%):");
                taxaJuros = scanner.nextDouble();
                if (taxaJuros < 10) {
                    System.out.println("Taxa de juros inválida, o valor deverá ser positivo.");
                } else if (taxaJuros > 15) {
                    System.out.println("Taxa de juros inválida, o valor não poderá ser maior que 15%.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
        }
        return taxaJuros;
    }

    private Casa solicitarDadosCasa(double valorImovel, int prazoFinanciamento, double taxaJuros) {
        double areaConstruida = 0;
        double areaTerreno = 0;
        while (true) {
            try {
                System.out.println("Insira a área construída da casa:");
                areaConstruida = scanner.nextDouble();
                System.out.println("Insira a área do terreno da casa:");
                areaTerreno = scanner.nextDouble();
                if (areaConstruida > areaTerreno) {
                    System.out.println("Área construída não pode ser maior que a área do terreno.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
        }
        return new Casa(valorImovel, prazoFinanciamento, taxaJuros, areaConstruida, areaTerreno);
    }

    private Apartamento solicitarDadosApartamento(double valorImovel, int prazoFinanciamento, double taxaJuros) {
        double numeroVagasGaragem = 0;
        double numeroAndar = 0;
        while (true) {
            try {
                System.out.println("Insira o número de vagas de garagem:");
                numeroVagasGaragem = scanner.nextDouble();
                if (numeroVagasGaragem < 1) {
                    System.out.println("O número de vagas de garagem deve ser no mínimo 1.");
                    continue;
                }
                System.out.println("Insira o número do andar:");
                numeroAndar = scanner.nextDouble();
                if (numeroAndar < 1) {
                    System.out.println("O número do andar deve ser no mínimo 1.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
        }
        return new Apartamento(valorImovel, prazoFinanciamento, taxaJuros, numeroVagasGaragem, numeroAndar);
    }

    private Terreno solicitarDadosTerreno(double valorImovel, int prazoFinanciamento, double taxaJuros) {
        TipoLocal tipoLocal = null;
        while (true) {
            try {
                System.out.println("Escolha o tipo de local do terreno:\n1. Residencial\n2. Comercial\n3. Zona Rural");
                int tipoLocalEscolha = scanner.nextInt();
                switch (tipoLocalEscolha) {
                    case 1:
                        tipoLocal = TipoLocal.RESIDENCIAL;
                        break;
                    case 2:
                        tipoLocal = TipoLocal.COMERCIAL;
                        break;
                    case 3:
                        tipoLocal = TipoLocal.ZONA_RURAL;
                        break;
                    default:
                        System.out.println("Escolha inválida.");
                        continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
        }
        return new Terreno(valorImovel, prazoFinanciamento, taxaJuros, tipoLocal);
    }

    public Financiamento criarNovoFinanciamento() {
        double valorImovel = solicitarValorImovel();
        int prazoFinanciamento = solicitarPrazoFinanciamento();
        double taxaJuros = solicitarTaxaJuros();

        Financiamento novoFinanciamento = null;
        while (novoFinanciamento == null) {
            try {
                System.out.println("Escolha o tipo de financiamento:\n1. Casa\n2. Apartamento\n3. Terreno");
                int escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                        novoFinanciamento = solicitarDadosCasa(valorImovel, prazoFinanciamento, taxaJuros);
                        break;
                    case 2:
                        novoFinanciamento = solicitarDadosApartamento(valorImovel, prazoFinanciamento, taxaJuros);
                        break;
                    case 3:
                        novoFinanciamento = solicitarDadosTerreno(valorImovel, prazoFinanciamento, taxaJuros);
                        break;
                    default:
                        System.out.println("Escolha inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
        }
        return novoFinanciamento;
    }

    public void exibirDetalhesFinanciamento(Financiamento financiamento) {
        System.out.println();
        System.out.println("SIMULAÇÃO DO FINANCIAMENTO\n===========================");
        System.out.println("Valor do Imóvel: " + financiamento.getValorImovel());
        System.out.println("Prazo do Financiamento: " + financiamento.getPrazoFinanciamentoAnos() + " anos");
        System.out.println("Taxa de Juros: " + financiamento.getTaxaJuros() + "%");
        System.out.println("Pagamento Mensal: " + financiamento.calcularPagamentoMensal());
        System.out.println("Valor total financiado: " + financiamento.calcularTotalPagamento());
        System.out.println();

    }
}
