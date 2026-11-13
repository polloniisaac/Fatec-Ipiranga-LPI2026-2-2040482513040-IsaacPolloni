import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Aluno(a): Isaac de Souza Polloni
 * RA: 2040482513040
 *
 * Reflexão:
 *
 * 1. Se o departamento Comercial passar a se chamar Vendas,
 * basta alterar a linha correspondente em departamentos.txt.
 * Os chamados já gravados não mudam porque armazenam o nome
 * existente na data do registro. Isso preserva o histórico,
 * mas pode fazer relatórios antigos mostrarem nomes diferentes
 * para o mesmo setor.
 *
 * 2. Sem o modo append, o arquivo seria recriado a cada gravação,
 * apagando os chamados anteriores. Como o próximo protocolo é
 * calculado pela quantidade de linhas existentes, a numeração
 * voltaria ao início. A persistência em arquivo é o conceito que
 * permite manter os dados mesmo após o programa ser encerrado.
 *
 * 3. Um exemplo é o SIGA. Informamos a sigla da disciplina e o
 * sistema exibe o nome completo da matéria no histórico escolar.
 */
public class ServiceDesk {

    static final int MAX_DEPARTAMENTOS = 20;
    static final String ARQ_DEPARTAMENTOS = "departamentos.txt";
    static final String ARQ_CHAMADOS = "chamados.txt";
    static final String ARQ_RESUMO = "resumo_atendimento.txt";

    public static void main(String[] args) {
        int[] codigos = new int[MAX_DEPARTAMENTOS];
        String[] nomes = new String[MAX_DEPARTAMENTOS];
        int[] contagem = new int[MAX_DEPARTAMENTOS];
        int qtd, protocolo, cod, indice;
        boolean registrou;
        String entrada, solicitante, descricao;
        Scanner teclado = new Scanner(System.in);

        qtd = carregarDepartamentos(ARQ_DEPARTAMENTOS, codigos, nomes);

        if (qtd == 0) {
            System.out.println("Não foi possível carregar os departamentos. Verifique o arquivo "
                    + ARQ_DEPARTAMENTOS + ".");
            teclado.close();
            return;
        }

        exibirDepartamentos(codigos, nomes, qtd);
        protocolo = proximoProtocolo(ARQ_CHAMADOS);

        cod = -1;
        while (cod != 0) {
            System.out.print("Código do departamento (0 = encerrar): ");
            entrada = teclado.nextLine().trim();
            try {
                cod = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números.");
                cod = -1;
                continue;
            }

            if (cod != 0) {
                indice = buscarDepartamento(cod, codigos, qtd);
                if (indice == -1) {
                    System.out.println("Código " + cod + " não cadastrado.");
                    exibirDepartamentos(codigos, nomes, qtd);
                } else {
                    System.out.println("Departamento: " + nomes[indice]);
                    System.out.print("Solicitante: ");
                    solicitante = limpar(teclado.nextLine());
                    System.out.print("Descrição do problema: ");
                    descricao = limpar(teclado.nextLine());

                    registrou = registrarChamado(ARQ_CHAMADOS, protocolo, solicitante, nomes[indice], descricao);
                    if (registrou) {
                        System.out.printf("Chamado #%04d registrado para %s.%n", protocolo, nomes[indice]);
                        contagem[indice]++;
                        protocolo++;
                    } else {
                        System.out.println("Não foi possível gravar o chamado.");
                    }
                }
            }
        }

        gravarResumo(ARQ_RESUMO, nomes, contagem, qtd);
        System.out.println("--- " + ARQ_RESUMO + " ---");
        exibirArquivo(ARQ_RESUMO);
        teclado.close();
    }

    public static int carregarDepartamentos(String arquivo, int[] codigos, String[] nomes) {
        int qtd = 0, numeroLinha = 1;
        String linha;
        String[] partes;
        int codigo;

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            leitor.readLine(); // descarta cabeçalho
            linha = leitor.readLine();

            while (linha != null) {
                numeroLinha++;

                try {
                    partes = linha.split(";");

                    if (partes.length != 2) {
                        throw new ArrayIndexOutOfBoundsException();
                    }

                    codigo = Integer.parseInt(partes[0].trim());

                    if (qtd < MAX_DEPARTAMENTOS) {
                        codigos[qtd] = codigo;
                        nomes[qtd] = partes[1].trim();
                        qtd++;
                    } else {
                        System.out.println("Linha " + numeroLinha
                                + " ignorada: limite de departamentos atingido.");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Linha " + numeroLinha + " ignorada: " + linha);
                }

                linha = leitor.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + arquivo + " não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + arquivo + ".");
        }

        return qtd;
    }

    public static void exibirDepartamentos(int[] codigos, String[] nomes, int qtd) {
        int i;
        System.out.println("--- Departamentos disponíveis ---");
        for (i = 0; i < qtd; i++) {
            System.out.printf("%d - %s%n", codigos[i], nomes[i]);
        }
    }

    public static int buscarDepartamento(int cod, int[] codigos, int qtd) {
        int i;
        for (i = 0; i < qtd; i++) {
            if (codigos[i] == cod) {
                return i;
            }
        }
        return -1;
    }

    public static int proximoProtocolo(String arquivo) {
        int total = 0;
        String linha;

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            linha = leitor.readLine();
            while (linha != null) {
                total++;
                linha = leitor.readLine();
            }
        } catch (FileNotFoundException e) {
            return 1; // primeira execução
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + arquivo + ".");
        }
        return total + 1;
    }

    public static String limpar(String texto) {
        return texto.replace(";", ",").trim();
    }

    public static boolean registrarChamado(String arquivo, int protocolo, String solicitante, String departamento, String descricao) {
        boolean gravou = false;

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo, true))) {
            escritor.write(String.format("%04d;%s;%s;%s", protocolo, solicitante, departamento, descricao));
            escritor.newLine();
            gravou = true;
        } catch (IOException e) {
            System.out.println("Erro ao gravar em " + arquivo + ".");
        }
        return gravou;
    }

    public static void gravarResumo(String arquivo, String[] nomes, int[] contagem, int qtd) {
        int i;
        int total = 0;

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo))) {
            escritor.write("DEPARTAMENTO;CHAMADOS");
            escritor.newLine();
            for (i = 0; i < qtd; i++) {
                if (contagem[i] > 0) {
                    escritor.write(nomes[i] + ";" + contagem[i]);
                    escritor.newLine();
                    total = total + contagem[i];
                }
            }
            escritor.write("TOTAL;" + total);
            escritor.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao gravar o resumo em " + arquivo + ".");
        }
    }

    public static void exibirArquivo(String arquivo) {
        String linha;

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            linha = leitor.readLine();
            while (linha != null) {
                System.out.println(linha);
                linha = leitor.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + arquivo + " não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + arquivo + ".");
        }
    }
}