package modelo;

public class Apartamento extends Financiamento {

    private double numeroVagasGaragem;
    private double numeroAndar;

    public Apartamento(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, double numeroVagasGaragem, double numeroAndar) {
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.numeroVagasGaragem = numeroVagasGaragem;
        this.numeroAndar = numeroAndar;
    }

    public int getNumeroVagasGaragem() {
        return (int) numeroVagasGaragem;
    }

    public int getNumeroAndar() {
        return (int) numeroAndar;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaJurosMensal = taxaJurosAnual / 12 / 100.0;
        int prazoFinanciamentoMeses = prazoFinanciamento * 12;
        return (this.valorImovel / prazoFinanciamentoMeses) * (1 + taxaJurosMensal);
    }
}
