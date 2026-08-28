import java.util.Locale;
import java.util.Scanner;

public class LP_Atividade_N1_08_2040482513038{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.print(
                "Informe o valor total da compra em reais: "
        );

        double valorTotalReais =
                scanner.nextDouble();

        System.out.print(
                "Informe o número de parcelas: "
        );

        int numeroParcelas =
                scanner.nextInt();

        long valorTotalCentavos =
                Math.round(valorTotalReais * 100);

        long valorParcelaCentavos =
                valorTotalCentavos / numeroParcelas;

        System.out.println(
                "--- Calculadora Financeira Escalar (Mapeamento em Centavos) ---"
        );

        System.out.println(
                "Valor total convertido: "
                + valorTotalCentavos
                + " centavos."
        );

        System.out.printf(
                Locale.US,
                "Divisão de R$%.2f por %d em centavos: %d centavos por parcela.%n",
                valorTotalReais,
                numeroParcelas,
                valorParcelaCentavos
        );

        double valorParcelaReais =
                valorParcelaCentavos / 100.0;

        System.out.printf(
                Locale.US,
                "Valor convertido para exibição: R$ %.2f%n",
                valorParcelaReais
        );

        scanner.close();
    }
}