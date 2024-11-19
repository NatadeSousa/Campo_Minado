import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();

        tabuleiro.inicializar_tabuleiro();
        tabuleiro.exibir_tabuleiro_mascarado();

        Scanner scan = new Scanner(System.in);

        while(true){
            System.out.println("As posições possíveis para lin e col vão de 0-8.");
            System.out.print("Digite para onde deseja se mover (lin,col): ");
            List<String> movimento = List.of(scan.nextLine().split(",")); //O USUÁRIO DIGITARÁ LIN E COL SEPARADOS POR ","
            int linha = Integer.parseInt(movimento.get(0));
            int coluna = Integer.parseInt(movimento.get(1));

            if((linha >= 0 && linha <= 8) && (coluna >= 0 && coluna <= 8)){ //SE O MOVIMENTO FOR VÁLIDO
                boolean fim_de_jogo = tabuleiro.realizar_movimento(linha, coluna);//SE O USUÁRIO PISAR EM BOMBA, O RETORNO SERÁ TRUE
                if(fim_de_jogo){
                    break;
                }
            }else{
                System.out.println("Movimento inválido! X e Y precisam estar entre 0 e 8.");
                tabuleiro.exibir_tabuleiro_mascarado();
                break;
            }
        }

    }
}