import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class Jogador implements Comparable<Jogador> {
    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;
    private ArrayList<Jogador> listJogadores = new ArrayList<>();

    Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento,
            String estadoNascimento) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.anoNascimento = anoNascimento;
        this.nome = nome;
        this.universidade = universidade;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    Jogador() {

    }

    public void imprimirDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append(id).append(" ## ")
                .append(nome).append(" ## ")
                .append(altura).append(" ## ")
                .append(peso).append(" ## ")
                .append(anoNascimento).append(" ## ")
                .append(campoNaoInformado(universidade)).append(" ## ")
                .append(campoNaoInformado(cidadeNascimento)).append(" ## ")
                .append(campoNaoInformado(estadoNascimento))
                .append("]\n");
        MyIO.print(sb.toString());
    }

    private String campoNaoInformado(String campo) {
        return campo.isEmpty() ? "nao informado" : campo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }

    public void setAnoNascimento(int ano) {
        this.anoNascimento = ano;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setCidadeNascimento(String cidade) {
        this.cidadeNascimento = cidade;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setEstadoNascimento(String estado) {
        this.estadoNascimento = estado;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void ler(String nomeArquivo) {
        ArquivoTextoLeitura arquivo = new ArquivoTextoLeitura(nomeArquivo);
        arquivo.readLine();
        String entradaDados = arquivo.readLine();

        while (entradaDados != null && !entradaDados.equals("FIM")) {
            String[] valores = entradaDados.split(",", -1);
            int id = Integer.parseInt(valores[0]);

            if (id != 28) {
                listJogadores.add(preencherJogador(valores));
            }

            entradaDados = arquivo.readLine();
        }

        arquivo.fecharArquivo();
        escrever();
    }

    private Jogador preencherJogador(String[] valores) {
        Jogador jogador = new Jogador();
        jogador.setId(Integer.parseInt(valores[0]));
        jogador.setNome(valores[1]);
        jogador.setAltura(Integer.parseInt(valores[2]));
        jogador.setPeso(Integer.parseInt(valores[3]));
        jogador.setUniversidade(campoNaoInformado(valores[4]));
        jogador.setAnoNascimento(valores[5].equals("nao informado") ? 0 : Integer.parseInt(valores[5]));
        jogador.setCidadeNascimento(campoNaoInformado(valores[6]));
        jogador.setEstadoNascimento(campoNaoInformado(valores[7]));
        return jogador;
    }

    public void escrever() {
        int[] ids = new int[10];
        int idCount = 0;

        String linha = MyIO.readLine();
        while (!linha.isEmpty() && !linha.equals("FIM")) {
            if (idCount == ids.length) {
                ids = Arrays.copyOf(ids, ids.length * 2);
            }
            ids[idCount++] = Integer.parseInt(linha);
            linha = MyIO.readLine();
        }

        for (int i = 0; i < idCount; i++) {
            imprimirJogadorPorId(ids[i]);
        }
    }

    private void imprimirJogadorPorId(int jogadorId) {
        for (Jogador jogadorTemp : listJogadores) {
            if (jogadorTemp.getId() == jogadorId) {
                jogadorTemp.imprimirDados();
                break;
            }
        }
    }

    public Jogador clone() {
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }

    @Override
    public int compareTo(Jogador outro) {
        if (this.altura != outro.altura) {
            return Integer.compare(this.altura, outro.altura);
        }
        return this.nome.compareTo(outro.nome);
    }
}

class ArquivoTextoLeitura {
    private BufferedReader entrada;

    ArquivoTextoLeitura(String nomeArquivo) {
        try {
            entrada = new BufferedReader(new InputStreamReader(new
                    FileInputStream(nomeArquivo), "UTF-8"));
        } catch (UnsupportedEncodingException excecao) {
            System.out.println(excecao.getMessage());
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo nao encontrado");
        }
    }

    public String readLine() {
        String textoEntrada = null;
        try {
            textoEntrada = entrada.readLine();
        } catch (EOFException excecao) {
            textoEntrada = null;
        } catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
            textoEntrada = null;
        } finally {
            return textoEntrada;
        }
    }

    public void fecharArquivo() {
        try {
            entrada.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de leitura: " +
                    excecao);
        }
    }
}

class Main {

    public static void heapSort(Jogador[] jogadores, int[] comparacoes, int[] movimentacoes) {
        int n = jogadores.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(jogadores, n, i, comparacoes, movimentacoes);
        }

        for (int i = n - 1; i >= 0; i--) {
            movimentacoes[0]++;
            Jogador temp = jogadores[0];
            jogadores[0] = jogadores[i];
            jogadores[i] = temp;

            heapify(jogadores, i, 0, comparacoes, movimentacoes);
        }
    }

    public static void heapify(Jogador[] jogadores, int n, int i, int[] comparacoes, int[] movimentacoes) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n) {
            comparacoes[0]++;
            if (jogadores[left].compareTo(jogadores[largest]) > 0) {
                largest = left;
            }
        }

        if (right < n) {
            comparacoes[0]++;
            if (jogadores[right].compareTo(jogadores[largest]) > 0) {
                largest = right;
            }
        }

        if (largest != i) {
            movimentacoes[0]++;
            Jogador swap = jogadores[i];
            jogadores[i] = jogadores[largest];
            jogadores[largest] = swap;

            heapify(jogadores, n, largest, comparacoes, movimentacoes);
        }
    }
    public static void main(String[] args) {
        HashMap<Integer, Jogador> jogadoresMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("/tmp/jogadores.txt"))) {
            String linha;

            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(",", -1);
                int id = Integer.parseInt(valores[0]);
                if (!jogadoresMap.containsKey(id)) {
                    Jogador jogador = new Jogador(
                            id,
                            valores[1],
                            Integer.parseInt(valores[2]),
                            Integer.parseInt(valores[3]),
                            valores[4].isEmpty() ? "nao informado" : valores[4],
                            valores[5].isEmpty() ? 0 : Integer.parseInt(valores[5]),
                            valores[6].isEmpty() ? "nao informado" : valores[6],
                            valores[7].isEmpty() ? "nao informado" : valores[7]
                    );
                    jogadoresMap.put(id, jogador);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Jogador> jogadoresList = new ArrayList<>(jogadoresMap.values());

        HashSet<Integer> idsEntrada = new HashSet<>();
        String linha = MyIO.readLine();
        while (!linha.isEmpty() && !linha.equals("FIM")) {
            int id = Integer.parseInt(linha);
            idsEntrada.add(id);
            linha = MyIO.readLine();
        }

        ArrayList<Jogador> jogadoresPesquisados = new ArrayList<>();
        for (Jogador jogador : jogadoresList) {
            if (idsEntrada.contains(jogador.getId())) {
                jogadoresPesquisados.add(jogador);
            }
        }

        Jogador[] jogadores = jogadoresPesquisados.toArray(new Jogador[0]);
        int n = jogadores.length;

        int[] comparacoes = {0};
        int[] movimentacoes = {0};
        long inicio = System.currentTimeMillis();
        heapSort(jogadores, comparacoes, movimentacoes);
        long fim = System.currentTimeMillis();

        for (Jogador jogador : jogadores) {
            jogador.imprimirDados();
        }

        try (FileWriter fw = new FileWriter("1395175_heapsort.txt")) {
            fw.write("1395175\t" + (fim - inicio) + "ms\t" + comparacoes[0] + "\t" + movimentacoes[0] + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
