//RA:2040482513040  Isaac de Souza Polloni
//Disciplina: Linguagem de Programação I 
    
import java.util.Scanner;

public class LP_Atividade_N2_03_2040482513038 {

    public static final int PIN_CORRETO = 1234;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(java.util.Locale.US);
        
        int pinDigitado;
        int tentativas = 0;

        do {

            System.out.print("Informe o PIN de acesso: ");
            pinDigitado = scanner.nextInt();

            tentativas++;

            if (pinDigitado != PIN_CORRETO) {
                System.out.println("PIN incorreto. Tente novamente.");
            }

        } while (pinDigitado != PIN_CORRETO);

        System.out.println("Acesso concedido! Você utilizou "
                + tentativas + " tentativa(s).");

        scanner.close();
    }
}
