package modelo;

import util.AcrescimoMaiorDoQueJurosException;

public class Casa extends Financiamento {

    private double areaConstruida;
    private double areaTerreno;

    public Casa(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, double areaConstruida, double areaTerreno) {
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.areaConstruida = areaConstruida;
        this.areaTerreno = areaTerreno;
    }

    public double getAreaConstruida() {
        return areaConstruida;
    }

    public double getAreaTerreno() {
        return areaTerreno;
    }

    @Override
    public double calcularPagamentoMensal() {
        double pagamentoMensalBase = super.calcularPagamentoMensal();
        double jurosMensal = getJurosMensal();
        double acrescimo = jurosMensal;

        try {
            validarJurosDesconto(jurosMensal, acrescimo);
        } catch (AcrescimoMaiorDoQueJurosException e) {
            System.out.println("Erro: " + e.getMessage());
            // mudar logica dps
            acrescimo += jurosMensal;
        }
        return pagamentoMensalBase + acrescimo;
    }

    private void validarJurosDesconto(double valorJuros, double valorAcrescimo) throws AcrescimoMaiorDoQueJurosException {
        if (valorAcrescimo > valorJuros) {
            throw new AcrescimoMaiorDoQueJurosException("O valor do acréscimo é maior que o valor dos juros.");
        }
    }
}

