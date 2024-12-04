package br.gov.caixa.campo_minado.modelo;

import br.gov.caixa.campo_minado.excecoes.ExplosionException;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private final int linha;
    private final int coluna;
    private int qntd_minas_ao_redor;

    public boolean aberto = false; //O CAMPO ABERTO É O QUE JÁ FOI SELECIONADO
    private boolean marcado = false; //O CAMPO MARCADO É AQUELE QUE FOI SINALIZADO COMO PERIGOSO
    private boolean minado = false;
    private boolean fim_de_jogo = false; //UTILIZADA PARA EXIBIR TODOS OS CAMPOS MINADOS SEM GERAR EXCEÇÃO

    public Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    List<Campo> vizinhos = new ArrayList<>();

    public boolean adicionar_vizinho(Campo vizinho){
        boolean linha_diferente = linha != vizinho.linha;
        boolean coluna_diferente = coluna != vizinho.coluna;
        boolean diagonal = linha_diferente && coluna_diferente;

        //delta == diferença
        int delta_linha = Math.abs(linha - vizinho.linha);
        int delta_coluna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = delta_linha + delta_coluna; //QUANDO UM VIZINHO ESTÁ NA DIAGONAL O DELTA É == 2
                                                     //QUANDO UM VIZINHO ESTÁ NA MESMA LIN OU COL O DELTA É == 1

        if(deltaGeral == 1 && !diagonal){ //É NECESSÁRIO FAZER AS DUAS VERIFICAÇÕES DE DIAGONAL E DELTA, POIS EXISTEM
            vizinhos.add(vizinho);        //SITUAÇÕES EM QUE O DELTA VAI SER 1, PORÉM NÃO SERÃO VIZINHOS
            return true;
        }else if(deltaGeral == 2 && diagonal){ //EXISTEM SITUAÇÕES TAMBÉM EM QUE O DELTA É 2 E NÃO SERÃO VIZINHOS
            vizinhos.add(vizinho);
            return true;
        }else{
            return false;
        }
    }

    public void alternarMarcacao(){
        if(!aberto){ //SE O CAMPO ESTIVER ABERTO, NÃO HÁ COMO MARCÁ-LO; A VERIFICAÇÃO SÓ É FEITA COM O ESTADO FECHADO.
            marcado = !marcado; //SE MARCADO FOR TRUE, VIRARÁ !TRUE(FALSE). SE FOR FALSE, VIRARÁ !FALSE(TRUE)
        }
    }

    public boolean abrirCampo(){
        if(fim_de_jogo && minado){//Quando o usuário tiver pisado em uma mina, todas as minas serão expostas
            aberto = true;
        }

        if(!aberto && !marcado){
            aberto = true;

            if(minado){
                throw new ExplosionException();
            }

            if(verificarSegurancaVizinhanca()){
                vizinhos.forEach(vizinho -> vizinho.abrirCampo());
            }

            return true;
        }
        return false;
    }

    public boolean verificarSegurancaVizinhanca(){
        return vizinhos.stream().noneMatch(vizinho -> vizinho.minado);
    }

    public void minar(){
        minado = true;
    }

    public boolean isMinado(){
        return minado;
    }

    public boolean isAberto(){
        return aberto;
    }

    public int getLinha(){
        return linha;
    }

    public int getColuna(){
        return coluna;
    }

    public void resetarCampo(){
        minado = false;
        marcado = false;
        aberto = false;
        fim_de_jogo = false;
    }

    public boolean campoExplorado(){
        boolean protegido = marcado && minado;
        boolean desvendado =  aberto && !minado;
        return protegido || desvendado;
    }

    public long getQntdMinasAoRedor(){
        return vizinhos.stream().filter(vizinho -> vizinho.minado).count();
    }

    public void fim_de_jogo(){
        fim_de_jogo = !fim_de_jogo; //Se for fim_de_jogo, as minas serão exibidas. Caso contrário, eles serão ocultadas novamente.
    }

    @Override
    public String toString() {
        if(fim_de_jogo){
            return " * ";
        }
        if(minado && aberto){
            return " * ";
        }else if(marcado){
            return " X ";
        }else if(aberto && getQntdMinasAoRedor() > 0){
            return " " + getQntdMinasAoRedor() + " ";
        }else if(aberto){
            return "   ";
        }else{
            return " - ";
        }
    }

    //MÉTODOS IMPLEMENTADO UNICAMENTE PARA REALIZAÇÃO DE TESTES
    public void retirarMina(){
        minado = false;
    }

    public List<Campo> getVizinhos(){
        return vizinhos;
    }
}
