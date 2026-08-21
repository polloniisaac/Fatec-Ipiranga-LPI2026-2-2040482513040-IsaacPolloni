import java.util.Scanner;

public class LP_Atividade_N1_04_2040482513040 {

    public static final String INSTITUICAO = "FATEC Ipiranga";
    public static final int PRAZO_MAXIMO_EMPRESTIMO = 14;

    public static int calcularDiasDevolucao(final int diasEmprestimo) {

        if (diasEmprestimo > PRAZO_MAXIMO_EMPRESTIMO) {
            return PRAZO_MAXIMO_EMPRESTIMO;
        }

        return diasEmprestimo;
    }

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o número de dias do empréstimo: ");
        final int diasEmprestimo = scanner.nextInt();

        final int diasDevolucao =
                calcularDiasDevolucao(diasEmprestimo);

        // diasEmprestimo = 20; // Erro: variável final não pode ser reatribuída.

        System.out.println("Instituição: " + INSTITUICAO);

        System.out.println(
                "Prazo Máximo Padrão: "
                + PRAZO_MAXIMO_EMPRESTIMO
                + " dias."
        );

        System.out.println(
                "Dias calculados para devolução: "
                + diasDevolucao
        );

        scanner.close();
    }
}