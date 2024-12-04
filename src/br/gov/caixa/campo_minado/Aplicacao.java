package br.gov.caixa.campo_minado;

import br.gov.caixa.campo_minado.modelo.Tabuleiro;
import br.gov.caixa.campo_minado.visual.TabuleiroConsole;

import java.util.*;
import java.util.stream.Stream;

public class Aplicacao {

    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(9, 9, 9);
        new TabuleiroConsole(tabuleiro);

        }

    }

