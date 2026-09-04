//RA:2040482513040
//Nome: Isaac de Souza Polloni
//Disciplina: Linguagem de Programação (LPI)


import java.util.Locale;
import java.util.Scanner;

public class pgm_N13 {
    public static void main(String[] args) {

        try (Scanner teclado = new Scanner(System.in)) {
            teclado.useLocale(Locale.US);

            System.out.print("Quantidade de leituras: ");
            int quantidadeLeituras = teclado.nextInt();

            double[] temperaturas = new double[quantidadeLeituras];

            for (int i = 0; i < temperaturas.length; i++) {
                System.out.print("Temperatura (°C): ");
                temperaturas[i] = teclado.nextDouble();
            }

            System.out.println("-- Leituras Registradas --");

            for (int i = 0; i < temperaturas.length; i++) {
                System.out.printf("Leitura [%d]: %.1f°C%n", i, temperaturas[i]);
            }

            System.out.println("-- Acesso Direto --");

            System.out.printf("Primeira leitura (indice 0): %.1f°C%n", temperaturas[0]);

            System.out.printf(
                    "Ultima leitura (indice %d): %.1f°C%n",
                    temperaturas.length - 1,
                    temperaturas[temperaturas.length - 1]
            );

            double maior = temperaturas[0];
            double menor = temperaturas[0];

            int indiceMaior = 0;
            int indiceMenor = 0;

            for (int i = 1; i < temperaturas.length; i++) {

                if (temperaturas[i] > maior) {
                    maior = temperaturas[i];
                    indiceMaior = i;
                }

                if (temperaturas[i] < menor) {
                    menor = temperaturas[i];
                    indiceMenor = i;
                }
            }

            System.out.println("-- Busca Linear: Extremos --");

            System.out.printf("Maior temperatura: %.1f°C (indice %d)%n", maior, indiceMaior);
            System.out.printf("Menor temperatura: %.1f°C (indice %d)%n", menor, indiceMenor);

            System.out.print("Temperatura crítica de alerta (°C): ");
            double temperaturaCritica = teclado.nextDouble();

            boolean encontrou = false;
            int indiceAlerta = -1;

            for (int i = 0; i < temperaturas.length; i++) {

                if (temperaturas[i] >= temperaturaCritica) {
                    encontrou = true;
                    indiceAlerta = i;
                    break;
                }
            }

            System.out.println("-- Busca Linear: Alerta --");

            if (encontrou) {
                System.out.printf(
                        "Alerta: temperatura critica atingida na leitura de indice %d (%.1f°C).%n",
                        indiceAlerta,
                        temperaturas[indiceAlerta]
                );
            } else {
                System.out.println(
                        "Nenhuma leitura atingiu ou ultrapassou a temperatura critica informada."
                );
            }
        }
    }
}
