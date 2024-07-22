package modelo;

public class Terreno extends Financiamento {

    public enum TipoLocal {
        RESIDENCIAL,
        COMERCIAL,
        ZONA_RURAL
    }

    private TipoLocal tipoLocal;

    public Terreno(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, TipoLocal tipoLocal) {
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.tipoLocal = tipoLocal;
    }

    public TipoLocal getTipoLocal() {
        return tipoLocal;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaJurosMensal = taxaJurosAnual / 12 / 100.0;
        int prazoFinanciamentoMeses = prazoFinanciamento * 12;
        double pagamentoMensal = (this.valorImovel / prazoFinanciamentoMeses) * (1 + taxaJurosMensal);
        return pagamentoMensal * 1.02; // acrescimo de 2%
    }
}
