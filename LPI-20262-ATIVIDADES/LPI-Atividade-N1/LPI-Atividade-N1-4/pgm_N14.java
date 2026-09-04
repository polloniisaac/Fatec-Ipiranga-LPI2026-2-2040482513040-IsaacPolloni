/*--------------------------------------------------------*
* Disciplina: Linguagem de Programacao I                   *
*          Prof. Verissimo                                *
*---------------------------------------------------------*
* Objetivo do Programa: Simular o Xeque Pastor em matriz   *
*                       8x8, exibindo cada movimentacao.  *
* Data - 03/09/2026                                       *
* Autor: Isaac de Souza Polloni                           *
*---------------------------------------------------------*/

public class pgm_N14 {

    private static final int TAMANHO_TABULEIRO = 8;
    private static final String CASA_VAZIA = "   ";
    private static final String ORIGEM_MOVIMENTADA = "...";
    private static final String SEPARADOR = "==========================================";

    public static void main(String[] args) {
        String[][] tabuleiro = inicializarTabuleiro();
        apresentarSequenciaXequePastor(tabuleiro);
    }

    // A linha 0 representa a fileira 8; a coluna 0 representa a coluna a.
    private static String[][] inicializarTabuleiro() {
        String[][] tabuleiro = new String[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];

        for (int linha = 0; linha < TAMANHO_TABULEIRO; linha++) {
            for (int coluna = 0; coluna < TAMANHO_TABULEIRO; coluna++) {
                tabuleiro[linha][coluna] = CASA_VAZIA;
            }
        }

        // Identificadores da posicao inicial apresentada na figura do enunciado.
        String[] pecasPretas = {"tP1", "cP2", "bP3", "dP4", "rP5", "bP6", "cP7", "tP8"};
        String[] pecasBrancas = {"TB1", "CB2", "BB3", "DB4", "RB5", "BB6", "CB7", "TB8"};

        for (int coluna = 0; coluna < TAMANHO_TABULEIRO; coluna++) {
            tabuleiro[0][coluna] = pecasPretas[coluna];
            tabuleiro[1][coluna] = "PP" + (coluna + 1);
            tabuleiro[6][coluna] = "PB" + (coluna + 1);
            tabuleiro[7][coluna] = pecasBrancas[coluna];
        }

        return tabuleiro;
    }

    private static void exibirTabuleiro(String[][] tabuleiro) {
        System.out.println("  -a- -b- -c- -d- -e- -f- -g- -h-");

        for (int linha = 0; linha < TAMANHO_TABULEIRO; linha++) {
            String linhaExibida = (TAMANHO_TABULEIRO - linha) + " ";

            for (int coluna = 0; coluna < TAMANHO_TABULEIRO; coluna++) {
                linhaExibida += tabuleiro[linha][coluna] + " ";
            }

            System.out.println(linhaExibida);
        }

        System.out.println();
    }

    // Converte a fileira da notacao de xadrez para o indice da matriz.
    private static int obterLinha(String posicao) {
        int fileira = posicao.charAt(1) - '0';
        return TAMANHO_TABULEIRO - fileira;
    }

    // As letras a ate h correspondem aos indices 0 ate 7.
    private static int obterColuna(String posicao) {
        return posicao.charAt(0) - 'a';
    }

    private static void movimentarPeca(String[][] tabuleiro, String origem, String destino) {
        int linhaOrigem = obterLinha(origem);
        int colunaOrigem = obterColuna(origem);
        int linhaDestino = obterLinha(destino);
        int colunaDestino = obterColuna(destino);

        // Transfere a mesma peca, preservando seu identificador durante a partida.
        // Se o destino estiver ocupado, sua peca e substituida, realizando a captura.
        tabuleiro[linhaDestino][colunaDestino] = tabuleiro[linhaOrigem][colunaOrigem];

        // A marca permanece na casa desocupada, conforme as figuras da atividade.
        tabuleiro[linhaOrigem][colunaOrigem] = ORIGEM_MOVIMENTADA;
    }

    private static void demonstrarMovimento(String[][] tabuleiro, String descricao,
                                           String origem, String destino) {
        String pecaMovimentada = tabuleiro[obterLinha(origem)][obterColuna(origem)];
        String pecaCapturada = tabuleiro[obterLinha(destino)][obterColuna(destino)];

        System.out.println("---------- " + descricao + " ----------");
        System.out.println("Antes da movimentacao: " + origem + " -> " + destino);
        exibirTabuleiro(tabuleiro);

        movimentarPeca(tabuleiro, origem, destino);

        System.out.println("Posicao origem: " + ORIGEM_MOVIMENTADA + " (" + origem + ")"
                + "    Posicao final: " + pecaMovimentada + " (" + destino + ")");

        // Tanto as casas vazias quanto as marcadas com reticencias estao desocupadas.
        if (!CASA_VAZIA.equals(pecaCapturada) && !ORIGEM_MOVIMENTADA.equals(pecaCapturada)) {
            System.out.println("Peca capturada: " + pecaCapturada + " em " + destino + ".");
        }

        System.out.println("Depois da movimentacao:");
        exibirTabuleiro(tabuleiro);
    }

    private static void exibirTitulo(String titulo) {
        System.out.println(SEPARADOR);
        System.out.println(titulo);
        System.out.println(SEPARADOR);
    }

    private static void executarJogada1(String[][] tabuleiro) {
        exibirTitulo("Jogada #1");
        demonstrarMovimento(tabuleiro, "Brancas jogam e4", "e2", "e4");
        demonstrarMovimento(tabuleiro, "Pretas jogam e5", "e7", "e5");
    }

    private static void executarJogada2(String[][] tabuleiro) {
        exibirTitulo("Jogada #2");
        demonstrarMovimento(tabuleiro, "Brancas jogam Bc4", "f1", "c4");
        demonstrarMovimento(tabuleiro, "Pretas jogam Cc6", "b8", "c6");
    }

    private static void executarJogada3(String[][] tabuleiro) {
        exibirTitulo("Jogada #3");
        demonstrarMovimento(tabuleiro, "Brancas jogam Dh5", "d1", "h5");
        demonstrarMovimento(tabuleiro, "Pretas jogam Cf6", "g8", "f6");
    }

    private static void executarJogada4(String[][] tabuleiro) {
        exibirTitulo("Jogada #4 - Lance Final: Xeque Mate");

        // Dxf7# captura PP6 em f7, como mostra a figura do lance final.
        demonstrarMovimento(tabuleiro, "Brancas capturam PP6 em f7 (Dxf7#)", "h5", "f7");
        System.out.println("Xeque Mate! As brancas vencem com o Xeque Pastor.");
    }

    private static void apresentarSequenciaXequePastor(String[][] tabuleiro) {
        exibirTitulo("Posicao Inicial do tabuleiro");
        exibirTabuleiro(tabuleiro);

        // As brancas iniciam todos os lances. O quarto termina sem resposta das pretas.
        executarJogada1(tabuleiro);
        executarJogada2(tabuleiro);
        executarJogada3(tabuleiro);
        executarJogada4(tabuleiro);
    }
}
