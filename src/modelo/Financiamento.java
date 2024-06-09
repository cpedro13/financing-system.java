package modelo;

public class Financiamento {

    // Atributos da classe
    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

    // Construtor
    public Financiamento(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual) {
        if (taxaJurosAnual < 10) {
            throw new IllegalArgumentException("A taxa inserida é inferior à porcentagem da qual aceitamos (10 a 15%)");
        }
        if (taxaJurosAnual > 15) {
            throw new IllegalArgumentException("A taxa de juros é superior à porcentagem da qual aceitamos (10 a 15%)");
        }

        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamentoAnos;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    // Métodos getters
    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazoFinanciamentoAnos() {
        return prazoFinanciamento;
    }

    public double getTaxaJuros() {
        return taxaJurosAnual;
    }

    // metodos para calculo do financiamento
    public double calcularPagamentoMensal() {
        double taxaJurosMensal = taxaJurosAnual / 12 / 100;
        int prazoFinanciamentoMeses = prazoFinanciamento * 12;
        return (this.valorImovel / (this.prazoFinanciamento * 12)) * (1 + (this.taxaJurosAnual / 12));
    }

    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * prazoFinanciamento * 12;
    }
}
