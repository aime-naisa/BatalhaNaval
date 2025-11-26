import java.util.Scanner;
import java.util.Random;

public class trabalhoFinal_BatalhaNaval {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Random sorteio = new Random();

        int tamanho = 8;

        char[][] tabuleiroVisivel = new char[tamanho][tamanho];
        char[][] tabuleiroNavios = new char[tamanho][tamanho];
        int[][] tabuleiroIds = new int[tamanho][tamanho];

        inicializarMatrizes(tabuleiroVisivel, tabuleiroNavios, tabuleiroIds, tamanho);

        int[] tamanhosNavios = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        char[] tiposNavios   = {'P', 'C', 'C', 'D', 'D', 'D', 'S', 'S', 'S', 'S'};
        int quantidadeNavios = tamanhosNavios.length;

        int[] celulasRestantes = new int[quantidadeNavios];

        int i = 0;
        while (i < quantidadeNavios) {
            celulasRestantes[i] = tamanhosNavios[i];
            i = i + 1;
        }

        int totalCelulasNavios = 0;
        i = 0;
        while (i < quantidadeNavios) {
            totalCelulasNavios = totalCelulasNavios + tamanhosNavios[i];
            i = i + 1;
        }

        posicionarNavios(tabuleiroNavios, tabuleiroIds, tamanhosNavios, tiposNavios, sorteio, tamanho);

        int tentativas = 0;
        int limiteTentativas = 30;
        int acertos = 0;
        int erros = 0;
        int naviosAfundados = 0;
        boolean venceu = false;

        System.out.println("========================================");
        System.out.println("           BATALHA NAVAL 8x8           ");
        System.out.println("========================================");

        while (tentativas < limiteTentativas && acertos < totalCelulasNavios) {

            System.out.println();

            int jogadas = acertos + erros;
            double taxa = 0.0;
            if (jogadas > 0) {
                taxa = (acertos * 100.0) / jogadas;
            }

            System.out.printf("Tentativa %d/%d | Acertos: %d | Taxa: %.1f%%%n",
                    (tentativas + 1), limiteTentativas, acertos, taxa);

            System.out.println();
            imprimirTabuleiro(tabuleiroVisivel, tamanho);

            int linha = lerCoordenada(entrada, "linha", tamanho);
            int coluna = lerCoordenada(entrada, "coluna", tamanho);

            if (tabuleiroVisivel[linha][coluna] == 'A' || tabuleiroVisivel[linha][coluna] == 'X') {
                System.out.println("Você já atacou essa posição! Escolha outra.");
                continue;
            }

            char conteudo = tabuleiroNavios[linha][coluna];

            if (conteudo == '~') {
                System.out.println("ERROU! Nenhum navio foi atingido!");
                tabuleiroVisivel[linha][coluna] = 'X';
                erros = erros + 1;
            } else {
                int idNavio = tabuleiroIds[linha][coluna];
                System.out.println("ACERTOU! Um navio foi atingido!");
                tabuleiroVisivel[linha][coluna] = 'A';
                acertos = acertos + 1;

                celulasRestantes[idNavio] = celulasRestantes[idNavio] - 1;

                if (celulasRestantes[idNavio] == 0) {
                    naviosAfundados = naviosAfundados + 1;
                    System.out.print("AFUNDOU! Você destruiu um ");
                    mostrarNomeNavio(tiposNavios[idNavio]);
                    System.out.println("!");
                }
            }

            tentativas = tentativas + 1;
        }

        if (acertos == totalCelulasNavios) {
            venceu = true;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("          ESTATÍSTICAS FINAIS          ");
        System.out.println("========================================\n");

        if (venceu) {
            System.out.println("Status: VITÓRIA!");
        } else {
            System.out.println("Status: DERROTA!");
        }

        System.out.println("Tentativas usadas: " + tentativas + "/" + limiteTentativas);
        System.out.println("Total de acertos: " + acertos);
        System.out.println("Total de erros: " + erros);

        double taxaFinal = 0.0;
        if (acertos + erros > 0) {
            taxaFinal = (acertos * 100.0) / (acertos + erros);
        }
        System.out.printf("Taxa de acerto: %.2f%%\n", taxaFinal);

        System.out.println("Navios afundados: " + naviosAfundados + "/" + quantidadeNavios);
        imprimirResumoNavios(tiposNavios, celulasRestantes);

        System.out.println("\n========================================");
        System.out.println("Tabuleiro final:");
        imprimirTabuleiro(tabuleiroNavios, tamanho);

        entrada.close();
    }

    public static void inicializarMatrizes(char[][] visivel, char[][] navios, int[][] ids, int tamanho) {
        int i = 0;
        while (i < tamanho) {
            int j = 0;
            while (j < tamanho) {
                visivel[i][j] = '~';
                navios[i][j] = '~';
                ids[i][j] = -1;
                j = j + 1;
            }
            i = i + 1;
        }
    }

    public static void posicionarNavios(
            char[][] tabuleiroNavios,
            int[][] tabuleiroIds,
            int[] tamanhosNavios,
            char[] tiposNavios,
            Random sorteio,
            int tamanho
    ) {
        int quantidadeNavios = tamanhosNavios.length;
        int indiceNavio = 0;

        while (indiceNavio < quantidadeNavios) {
            boolean colocado = false;

            while (!colocado) {
                int orientacao = sorteio.nextInt(2);
                int linhaInicial = sorteio.nextInt(tamanho);
                int colunaInicial = sorteio.nextInt(tamanho);
                int tamanhoNavio = tamanhosNavios[indiceNavio];
                char tipoNavio = tiposNavios[indiceNavio];

                if (orientacao == 0) {
                    int colunaFinal = colunaInicial + tamanhoNavio - 1;

                    if (colunaFinal < tamanho) {
                        boolean livre = true;
                        int c = colunaInicial;
                        while (c <= colunaFinal && livre) {

                            int li = linhaInicial - 1;
                            int lf = linhaInicial + 1;
                            int ci = c - 1;
                            int cf = c + 1;

                            if (li < 0) {
                                li = 0;
                            }
                            if (lf >= tamanho) {
                                lf = tamanho - 1;
                            }
                            if (ci < 0) {
                                ci = 0;
                            }
                            if (cf >= tamanho) {
                                cf = tamanho - 1;
                            }

                            int l = li;
                            while (l <= lf && livre) {
                                int col = ci;
                                while (col <= cf && livre) {
                                    if (tabuleiroNavios[l][col] != '~') {
                                        livre = false;
                                    }
                                    col = col + 1;
                                }
                                l = l + 1;
                            }

                            c = c + 1;
                        }

                        if (livre) {
                            c = colunaInicial;
                            while (c <= colunaFinal) {
                                tabuleiroNavios[linhaInicial][c] = tipoNavio;
                                tabuleiroIds[linhaInicial][c] = indiceNavio;
                                c = c + 1;
                            }
                            colocado = true;
                        }
                    }
                } else {
                    int linhaFinal = linhaInicial + tamanhoNavio - 1;

                    if (linhaFinal < tamanho) {
                        boolean livre = true;
                        int l = linhaInicial;
                        while (l <= linhaFinal && livre) {

                            int li = l - 1;
                            int lf = l + 1;
                            int ci = colunaInicial - 1;
                            int cf = colunaInicial + 1;

                            if (li < 0) {
                                li = 0;
                            }
                            if (lf >= tamanho) {
                                lf = tamanho - 1;
                            }
                            if (ci < 0) {
                                ci = 0;
                            }
                            if (cf >= tamanho) {
                                cf = tamanho - 1;
                            }

                            int linhaViz = li;
                            while (linhaViz <= lf && livre) {
                                int col = ci;
                                while (col <= cf && livre) {
                                    if (tabuleiroNavios[linhaViz][col] != '~') {
                                        livre = false;
                                    }
                                    col = col + 1;
                                }
                                linhaViz = linhaViz + 1;
                            }

                            l = l + 1;
                        }

                        if (livre) {
                            l = linhaInicial;
                            while (l <= linhaFinal) {
                                tabuleiroNavios[l][colunaInicial] = tipoNavio;
                                tabuleiroIds[l][colunaInicial] = indiceNavio;
                                l = l + 1;
                            }
                            colocado = true;
                        }
                    }
                }
            }

            indiceNavio = indiceNavio + 1;
        }
    }

    public static void imprimirTabuleiro(char[][] tabuleiro, int tamanho) {
        System.out.print("  ");
        int c = 0;
        while (c < tamanho) {
            System.out.print(c + " ");
            c = c + 1;
        }
        System.out.println();

        int i = 0;
        while (i < tamanho) {
            System.out.print(i + " ");
            int j = 0;
            while (j < tamanho) {
                System.out.print(tabuleiro[i][j] + " ");
                j = j + 1;
            }
            System.out.println();
            i = i + 1;
        }
    }

    public static int lerCoordenada(Scanner entrada, String nome, int tamanho) {
        int valor = -1;
        boolean valido = false;

        while (!valido) {
            System.out.print("Digite a " + nome + " (0-" + (tamanho - 1) + "): ");

            if (entrada.hasNextInt()) {
                valor = entrada.nextInt();
                if (valor >= 0 && valor < tamanho) {
                    valido = true;
                } else {
                    System.out.println("Valor fora do intervalo!");
                }
            } else {
                System.out.println("Entrada inválida!");
                entrada.next();
            }
        }

        return valor;
    }

    public static void mostrarNomeNavio(char tipo) {
        switch (tipo) {
            case 'P':
                System.out.print("Porta-aviões");
                break;
            case 'C':
                System.out.print("Cruzador");
                break;
            case 'D':
                System.out.print("Destroyer");
                break;
            case 'S':
                System.out.print("Submarino");
                break;
            default:
                System.out.print("Navio");
        }
    }

    public static void imprimirResumoNavios(char[] tiposNavios, int[] celulasRestantes) {
        int i = 0;
        int portaT = 0, portaA = 0;
        int cruzT = 0, cruzA = 0;
        int destT = 0, destA = 0;
        int subT = 0, subA = 0;

        while (i < tiposNavios.length) {
            char tipo = tiposNavios[i];
            boolean afundou = (celulasRestantes[i] == 0);

            switch (tipo) {
                case 'P':
                    portaT = portaT + 1;
                    if (afundou) {
                        portaA = portaA + 1;
                    }
                    break;
                case 'C':
                    cruzT = cruzT + 1;
                    if (afundou) {
                        cruzA = cruzA + 1;
                    }
                    break;
                case 'D':
                    destT = destT + 1;
                    if (afundou) {
                        destA = destA + 1;
                    }
                    break;
                case 'S':
                    subT = subT + 1;
                    if (afundou) {
                        subA = subA + 1;
                    }
                    break;
            }

            i = i + 1;
        }

        System.out.println("\nResumo dos navios:");
        System.out.println("- Porta-aviões: " + portaA + "/" + portaT);
        System.out.println("- Cruzadores: "   + cruzA + "/" + cruzT);
        System.out.println("- Destroyers: "   + destA + "/" + destT);
        System.out.println("- Submarinos: "   + subA + "/" + subT);
    }
}
