public class Tabuleiro {
    private int qntd_linhas = 9;
    private int qntd_colunas = 9;
    private int qntd_bombas_posicionadas;
    private String[][] tabuleiro = new String[qntd_linhas][qntd_colunas];


    void inicializar_tabuleiro(){
        for(int l = 0; l < qntd_linhas; l++){
            for(int c = 0; c < qntd_colunas; c++){
                if(Math.random() >= 0.88 && qntd_bombas_posicionadas <= 9){ //UMA BOMBA SERÁ INSERIDA NA POSIC.
                    tabuleiro[l][c] = "X";                              //CASO A COND. SEJA SATISFEITA
                    qntd_bombas_posicionadas++;
                }else{
                    tabuleiro[l][c] = "O";
                }
            }
        }
        while(true){
            if(qntd_bombas_posicionadas != 10){ //CASO TODAS AS BOMBAS NÃO TENHAM SIDO POSICIONADAS
                int linha_aleatoria = (int) Math.round(Math.random() * 10);
                int coluna_aleatoria = (int) Math.round(Math.random() * 10);
                if(linha_aleatoria == 9) linha_aleatoria--; //PARA NÃO SAIR DO LIMITE DO ÍNDICE (8)
                if(coluna_aleatoria == 9) linha_aleatoria--; //PARA NÃO SAIR DO LIMITE DO ÍNDICE (8)
                if(tabuleiro[linha_aleatoria][coluna_aleatoria] == "O"){//CASO A POSIÇÃO ESTEJA VAZIA, INSIRA A BOMBA
                    tabuleiro[linha_aleatoria][coluna_aleatoria] = "X";
                    qntd_bombas_posicionadas++;
                }
            }else{
                break;
            }
        }
    }

    void exibir_tabuleiro() {
        for (int l = 0; l < qntd_linhas; l++) {
            for (int c = 0; c < qntd_colunas; c++) {
                System.out.print("[ " + tabuleiro[l][c] + " ]");
            }
            System.out.println();
        }
    }

    void limpar_tabuleiro(){
        for(int l = 0; l < qntd_linhas; l++){
            for(int c = 0; c < qntd_colunas; c++){
                tabuleiro[l][c] = "   ";
            }
        }
    }

}
