import java.util.Scanner;

public class LP_Atividade_N1_05_2040482513040 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Estoque inicial: ");
        int estoque = scanner.nextInt();

        System.out.print("Quantidade de entrada: ");
        int qtdEntrada = scanner.nextInt();

        System.out.print("Quantidade de saída 1: ");
        int qtdSaida1 = scanner.nextInt();

        System.out.print("Quantidade de saída 2: ");
        int qtdSaida2 = scanner.nextInt();

        System.out.print("Estoque mínimo: ");
        int estoqueMinimo = scanner.nextInt();

        System.out.print("Tamanho do lote (caixa): ");
        int tamanhoLote = scanner.nextInt();

        System.out.println(
                "Estoque inicial: " + estoque + " unidades."
        );

        estoque += qtdEntrada;

        System.out.println(
                "Após entrada (+" + qtdEntrada + "): "
                + estoque + " unidades."
        );

        estoque -= qtdSaida1;

        System.out.println(
                "Após saída (-" + qtdSaida1 + "): "
                + estoque + " unidades."
        );

        estoque -= qtdSaida2;

        System.out.println(
                "Após outra saída (-" + qtdSaida2 + "): "
                + estoque + " unidades."
        );

        boolean abaixoMinimo = estoque < estoqueMinimo;

        System.out.println(
                "O nível de estoque atual (" + estoque
                + ") está abaixo do mínimo (" + estoqueMinimo
                + ")? " + abaixoMinimo
        );

        int unidadesForaCaixa =
                estoque % tamanhoLote;

        System.out.println(
                "Unidades fora de caixas fechadas de "
                + tamanhoLote + ": "
                + unidadesForaCaixa
        );

        boolean caixasCompletas =
                unidadesForaCaixa == 0;

        System.out.println(
                "O estoque está perfeitamente fracionado em caixas completas? "
                + caixasCompletas
        );

        scanner.close();
    }
}