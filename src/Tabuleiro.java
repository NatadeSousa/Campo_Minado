public class Tabuleiro {
    private final int qntd_linhas = 9;
    private final int qntd_colunas = 9;
    private int qntd_bombas_posicionadas; //No total no tabuleiro
    private int qntd_bombas_ao_redor;
    private final String[][] tabuleiro = new String[qntd_linhas][qntd_colunas];
    private final String[][] tabuleiro_mascarado = new String[qntd_linhas][qntd_colunas];
    private final Integer[] qntd_bombas_ao_redor_da_pos = new Integer[81]; //Marca a qntd de bombas que tem ao redor de cada uma das posições

    private int linha_anterior;
    private int linha_atual;
    private int linha_posterior;


    void inicializar_tabuleiro(){
        for(int l = 0; l < qntd_linhas; l++){
            for(int c = 0; c < qntd_colunas; c++){
                tabuleiro_mascarado[l][c] = " ? ";
                if(Math.random() >= 0.88 && qntd_bombas_posicionadas <= 9){ //UMA BOMBA SERÁ INSERIDA NA POSIC.
                    tabuleiro[l][c] = "*";                                  //CASO A COND. SEJA SATISFEITA
                    qntd_bombas_posicionadas++;
                }else{
                    tabuleiro[l][c] = " ";
                }
            }
        }
        while(true){
            if(qntd_bombas_posicionadas != 10){ //CASO TODAS AS BOMBAS NÃO TENHAM SIDO POSICIONADAS
                int linha_aleatoria = (int) Math.round(Math.random() * 10);
                int coluna_aleatoria = (int) Math.round(Math.random() * 10);
                if(linha_aleatoria >= 9) linha_aleatoria = 8; //PARA NÃO SAIR DO LIMITE DO ÍNDICE (8)
                if(coluna_aleatoria >= 9) coluna_aleatoria = 8; //PARA NÃO SAIR DO LIMITE DO ÍNDICE (8)
                if(tabuleiro[linha_aleatoria][coluna_aleatoria].equals(" ")){//CASO A POSIÇÃO ESTEJA VAZIA, INSIRA A BOMBA
                    tabuleiro[linha_aleatoria][coluna_aleatoria] = "*";
                    qntd_bombas_posicionadas++;
                }
            }else{
                break;
            }
        }
    }

    void exibir_tabuleiro() { //SÓ É EXIBIDO QUANDO UM USUÁRIO PISA NUMA BOMBA, POIS ESTE TABULEIRO EXIBE A POS. DAS BOMBAS
        System.out.println("   ___________________________________");
        for (int l = 0; l < qntd_linhas; l++) {
            System.out.print("   |");
            for (int c = 0; c < qntd_colunas; c++) {
                System.out.print(" " + tabuleiro[l][c] + " ");
            }
            System.out.println(); //Não preciso pular linha na última execução(o fim do for pula automaticmt)
        }
        System.out.println("   ___________________________________");
    }

    void exibir_tabuleiro_mascarado() { //É EXIBIDO SEMPRE QUE UM USUÁRIO REALIZA UM MOVIMENTO, SALVO QND PISA NUMA BOMBA
        System.out.println("   ___________________________________");
        for (int l = 0; l < qntd_linhas; l++) {
            System.out.print("  ");
            for (int c = 0; c < qntd_colunas; c++) {
                System.out.print(tabuleiro_mascarado[l][c]);
            }
            System.out.println();
        }
        System.out.println("   ___________________________________");
    }

    boolean realizar_movimento(int linha, int coluna){ //O RETORNO SERÁ TRUE CASO O USUÁRIO PISE EM UMA BOMBA E FALSO CASO CONTRÁRIO
        if(tabuleiro[linha][coluna].equals("*")){ //CASO O USUÁRIO TENHA PISADO EM UMA BOMBA
            tabuleiro[linha][coluna] = "(*)";
            exibir_tabuleiro();
            System.out.println("VOCÊ PISOU EM UMA BOMBA NA LINHA " + linha + " COLUNA " + coluna + "!");
            return true;
        }else if(tabuleiro_mascarado[linha][coluna].equals("   ")){ //CASO O USUÁRIO TENTE SE MOVER PARA UMA POS. JÁ SELECIONADA
            System.out.println("Você já selecionou essa posição!");
            exibir_tabuleiro_mascarado();
            return false;
        }else{ //CASO O USUÁRIO NÃO TENHA PISADO EM NENHUMA BOMBA E A POSIÇÃO NÃO TENHA SIDO SELECIONADA AINDA
            for(int l = linha - 1; l >= linha - 1 && l <= linha + 1 && l >= 0 && l <= 8; l++){
                for(int c = coluna - 1; c >= coluna - 1 && c <= coluna + 1 && c >= 0 && c <= 8; c++){
                    if(tabuleiro[l][c].equals("*")){
                        qntd_bombas_ao_redor++;
                    }else{
                        linha = l;
                        coluna = c;
                        break;
                    }
                }
            }
            if(qntd_bombas_ao_redor == 0){
                tabuleiro_mascarado[linha][coluna] = " ";
            }else{
                tabuleiro_mascarado[linha][coluna] = String.valueOf(qntd_bombas_ao_redor);
            }

            exibir_tabuleiro_mascarado();
            return false;
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
