import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Jogador {
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
}

class FilaCircular {
    private int primeiro, ultimo;
    private Jogador[] fila;
    private int tamanhoMaximo;

    public FilaCircular() {
        tamanhoMaximo = 5;
        fila = new Jogador[tamanhoMaximo];
        primeiro = ultimo = 0;
    }

    public void enfileirar(Jogador jogador) {
        if ((ultimo + 1) % tamanhoMaximo == primeiro) {
            desenfileirar();
        }

        fila[ultimo] = jogador;
        ultimo = (ultimo + 1) % tamanhoMaximo;
    }

    public Jogador desenfileirar() {
        Jogador jogador = null;

        if (primeiro != ultimo) {
            jogador = fila[primeiro];
            primeiro = (primeiro + 1) % tamanhoMaximo;
        }

        return jogador;
    }

    public void mostrar() {
        for (int i = primeiro; i != ultimo; i = (i + 1) % tamanhoMaximo) {
            System.out.println("[" + i + "] " + fila[i].getNome() + " ## " + fila[i].getAltura() + " ## " + fila[i].getPeso() + " ## " + fila[i].getAnoNascimento() + " ## " + fila[i].getUniversidade() + " ## " + fila[i].getCidadeNascimento() + " ## " + fila[i].getEstadoNascimento());
        }
    }

    public double obterMediaAltura() {
        double somaAlturas = 0;
        int contador = 0;

        for (int i = primeiro; i != ultimo; i = (i + 1) % tamanhoMaximo) {
            somaAlturas += fila[i].getAltura();
            contador++;
        }

        return contador > 0 ? somaAlturas / contador : 0;
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
    public static Jogador encontrarJogadorPorId(Jogador[] jogadores, int id){
        for(Jogador jogador : jogadores){
            if(jogador.getId() == id){
                return jogador;
            }
        }
        return null;
    }


    public static void main(String[] args) throws IOException {
        String[] jogadoresArquivo = lerArquivo("/tmp/jogadores.txt");
        Jogador[] todosJogadores = preencherJogadores(jogadoresArquivo);

        FilaCircular fila = new FilaCircular();

        Scanner scanner = new Scanner(System.in);
        String inputLine;
        while (scanner.hasNextLine() && !(inputLine = scanner.nextLine()).equals("FIM")) {
            int id = Integer.parseInt(inputLine);
            Jogador jogador = encontrarJogadorPorId(todosJogadores, id);
            if (jogador != null) {
                fila.enfileirar(jogador);
            }
        }

        int n = scanner.hasNextLine() ? Integer.parseInt(scanner.nextLine()) : 0;
        for (int i = 0; i < n; i++) {
            String[] comando = scanner.nextLine().split(" ");
            if (comando[0].equals("I")) {
                int id = Integer.parseInt(comando[1]);
                Jogador jogador = encontrarJogadorPorId(todosJogadores, id);
                if (jogador != null) {
                    fila.enfileirar(jogador);
                }
            } else if (comando[0].equals("R")) {
                Jogador jogador = fila.desenfileirar();
                if (jogador != null) {
                    System.out.println("(R) " + jogador.getNome());
                }
            }
        }

        fila.mostrar();
        scanner.close();
    }

    public static String[] lerArquivo(String nomeArquivo) {
        ArrayList<String> linhas = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas.toArray(new String[0]);
    }

    public static Jogador[] preencherJogadores(String[] jogadoresArquivo) {
        Jogador[] jogadores = new Jogador[jogadoresArquivo.length - 1];
        for (int i = 1; i < jogadoresArquivo.length; i++) {
            String[] campos = jogadoresArquivo[i].split(",");
            Jogador jogador = new Jogador(
                    Integer.parseInt(campos[0]),
                    campos[1],
                    Integer.parseInt(campos[2]),
                    Integer.parseInt(campos[3]),
                    campos.length > 4 ? campos[4] : "nao informado",
                    campos.length > 5 ? Integer.parseInt(campos[5]) : -1,
                    campos.length > 6 ? campos[6] : "nao informado",
                    campos.length > 7 ? campos[7] : "nao informado"
            );
            jogadores[i - 1] = jogador;
        }
        return jogadores;
    }

    public static Jogador getJogadorPorId(Jogador[] jogadores, int id) {
        Jogador jogadorEncontrado = null;
        for (Jogador jogador : jogadores) {
            if (jogador.getId() == id) {
                jogadorEncontrado = jogador;
                break;
            }
        }
        return jogadorEncontrado;
    }
}