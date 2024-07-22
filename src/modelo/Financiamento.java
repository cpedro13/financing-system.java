package modelo;
import java.io.Serializable;

public abstract class Financiamento implements Serializable {

    // atributos da classe
    protected double valorImovel;
    protected int prazoFinanciamento;
    protected double taxaJurosAnual;

    // construtor
    public Financiamento(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual) {
        if (taxaJurosAnual < 10 || taxaJurosAnual > 15) {
            throw new IllegalArgumentException("A taxa de juros deve estar entre 10% e 15%. Taxa fornecida: " + taxaJurosAnual);
        }

        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamentoAnos;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    // métodos getters
    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazoFinanciamentoAnos() {
        return prazoFinanciamento;
    }

    public double getTaxaJuros() {
        return taxaJurosAnual;
    }

    // Método para obter o valor dos juros mensais
    public double getJurosMensal() {
        double taxaJurosMensal = taxaJurosAnual / 12 / 100.0;
        int prazoFinanciamentoMeses = prazoFinanciamento * 12;
        return valorImovel * taxaJurosMensal / (1 - Math.pow(1 + taxaJurosMensal, -prazoFinanciamentoMeses));
    }

    // Métodos para cálculo do financiamento
    public double calcularPagamentoMensal() {
        double taxaJurosMensal = taxaJurosAnual / 12 / 100.0;
        int prazoFinanciamentoMeses = prazoFinanciamento * 12;
        return (this.valorImovel / prazoFinanciamentoMeses) * (1 + taxaJurosMensal);
    }

    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * prazoFinanciamento * 12;
    }
}

