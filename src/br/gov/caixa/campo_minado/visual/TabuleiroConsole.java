package br.gov.caixa.campo_minado.visual;

import br.gov.caixa.campo_minado.excecoes.ExitException;
import br.gov.caixa.campo_minado.excecoes.ExplosionException;
import br.gov.caixa.campo_minado.modelo.Tabuleiro;

import java.util.*;

public class TabuleiroConsole {

    private final Tabuleiro tabuleiro;
    private final Scanner scanner = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro){

        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {

        try{
            boolean continuar = true;

            while(continuar) {
                iniciarInteracaoDoUsuario();


                System.out.print("Deseja sair do jogo? (S/n)");
                String decisao = scanner.nextLine();

                if (decisao.equalsIgnoreCase("S")) {
                    throw new ExitException();
                } else {
                    tabuleiro.reiniciarJogo();
                }
            }

        }catch(ExitException e){
            System.out.println("Jogo finalizado!!!");
        }finally {
            scanner.close();
        }
    }

    private void iniciarInteracaoDoUsuario() {
        try{
            while(!tabuleiro.isJogoFinalizado()){
                System.out.println(tabuleiro);
                String entrada = receber_entrada("Digite (x,y)\n=> ");
                int linha_escolhida = 0;
                int coluna_escolhida = 0;
                try {
                    Iterator<Integer> xy = Arrays.stream(entrada.split(","))
                            .map(str -> Integer.parseInt(str.trim())).iterator();

                    linha_escolhida = xy.next();
                    coluna_escolhida = xy.next();
                }catch(NumberFormatException e){
                    System.out.println("\n=> Informe apenas números separados por vírgula!!!\n");
                    continue;
                }catch(NoSuchElementException e){
                    System.out.println("\n=> Informe dois números separados por vírgula!!!\n");
                    continue;
                }

                entrada = receber_entrada("1 - Abrir campo\n2 - (Des)marcar\n=> ");

                if("1".equals(entrada)){
                    tabuleiro.abrirCampo(linha_escolhida, coluna_escolhida);
                }else if("2".equals(entrada)){
                    tabuleiro.alterarMarcacaoCampo(linha_escolhida, coluna_escolhida);
                }else{
                    System.out.println("Opção inválida!!!");
                }
            }
            System.out.println(tabuleiro);
            System.out.println("Parabéns!!! Você VENCEU o jogo!!!");
        }catch(ExplosionException e){
            tabuleiro.revelar_minas(); //Quando o usuário pisar em uma mina, o jogo será finalizado e todas as outras
            System.out.println(tabuleiro); //serão exibidas,
            System.out.println("\nVocê perdeu o jogo!!!\n");
        }
    }

    private String receber_entrada(String text){
        System.out.print(text);
        String resposta = scanner.nextLine();

        if(resposta.equalsIgnoreCase("sair")){
            throw new ExitException();
        }
        return resposta;
    }

}
