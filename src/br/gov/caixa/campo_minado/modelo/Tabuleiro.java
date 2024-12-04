package br.gov.caixa.campo_minado.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    private int quantidadeDeLinhas;
    private int quantidadeDeColunas;
    private int quantidadeDeMinas;

    private final List<Campo> campos = new ArrayList<>();

    public Tabuleiro(int quantidadeLinhas, int quantidadeDeColunas, int quantidadeDeMinas){

        this.quantidadeDeLinhas = quantidadeLinhas;
        this.quantidadeDeColunas = quantidadeDeColunas;
        this.quantidadeDeMinas = quantidadeDeMinas;

        preencherCampos();
        interligarVizinhos();
        distribuirMinas();
    }


    @Override
    public String toString() {
        int indexCampo = 0;
        StringBuilder sb = new StringBuilder();

        for(int l = 0; l < quantidadeDeLinhas; l++){

            for(int c = 0; c < quantidadeDeColunas; c++){
                sb.append(campos.get(indexCampo));
                indexCampo++;
            }
            sb.append("\n");

        }

        return sb.toString();
    }

    public void abrirCampo(int linhaEscolhida, int colunaEscolhida){
        campos.parallelStream()
                .filter(c -> c.getLinha() == linhaEscolhida && c.getColuna() == colunaEscolhida)
                .findFirst()
                .ifPresent(c -> c.abrirCampo());
    }

    public void alterarMarcacaoCampo(int linhaEscolhida, int colunaEscolhida){
        campos.parallelStream()
                .filter(c -> c.getLinha() == linhaEscolhida && c.getColuna() == colunaEscolhida)
                .findFirst()
                .ifPresent(c -> c.alternarMarcacao());
    }

    private void preencherCampos(){
        for(int linha = 0; linha < quantidadeDeLinhas; linha++){
            for(int coluna = 0; coluna < quantidadeDeColunas; coluna++){
                campos.add(new Campo(linha, coluna));
            }
        }
    }

    public void interligarVizinhos(){
        for(Campo c1: campos){
            for(Campo c2: campos){
                c1.adicionar_vizinho(c2);
            }
        }
    }

    private void distribuirMinas(){
        long quantidadeMinasAlocadas = 0;

        while(quantidadeMinasAlocadas < quantidadeDeMinas){
            quantidadeMinasAlocadas = campos.stream().filter(campo -> campo.isMinado()).count();

            int indexRecebedorMina = (int) (Math.random() * campos.size()); //O resultado sempre será de size() - 1 para baixo

            if(!campos.get(indexRecebedorMina).isMinado()){ //O campo só receberá a mina caso não seja minado.
                campos.get(indexRecebedorMina).minar();
            }
        }
    }

    public boolean isJogoFinalizado(){
        return campos.stream().allMatch(campo -> campo.campoExplorado());//Quando todos os campos sem mina estiverem
                                                                         //abertos, o jogo será finalizado.
    }

    public void reiniciarJogo(){
        campos.stream().forEach(campo -> campo.resetarCampo());
        distribuirMinas();
    }

    public void revelar_minas(){
        campos.stream().filter(campo -> campo.isMinado()).forEach(campo -> campo.fim_de_jogo());
    }
}
