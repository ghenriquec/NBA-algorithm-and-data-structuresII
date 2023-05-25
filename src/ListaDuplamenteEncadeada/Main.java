import java.io.*;
import java.util.ArrayList;

class Jogador {
    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;

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

    public void imprimir() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ## ")
                .append(id).append(" ## ")
                .append(nome).append(" ## ")
                .append(altura).append(" ## ")
                .append(peso).append(" ## ")
                .append(anoNascimento).append(" ## ")
                .append(campoNaoInformado(universidade)).append(" ## ")
                .append(campoNaoInformado(cidadeNascimento)).append(" ## ")
                .append(campoNaoInformado(estadoNascimento))
                .append(" ##");
        System.out.println(sb.toString());
    }

    private String campoNaoInformado(String campo) {
        return campo.isEmpty() ? "nao informado" : campo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }
}

class Nodo {
    Jogador jogador;
    Nodo anterior;
    Nodo proximo;

    Nodo(Jogador jogador) {
        this.jogador = jogador;
        this.anterior = null;
        this.proximo = null;
    }
}

class ListaDuplamenteEncadeada {
    private Nodo inicio;
    private Nodo fim;

    public ListaDuplamenteEncadeada() {
        inicio = null;
        fim = null;
    }

    public void inserirInicio(Jogador jogador) {
        Nodo novoNodo = new Nodo(jogador);
        if (inicio == null) {
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            novoNodo.proximo = inicio;
            inicio.anterior = novoNodo;
            inicio = novoNodo;
        }
    }

    public void inserir(Jogador jogador, int posicao) {
        if (posicao < 0 || posicao > tamanho()) {
            System.out.println("Erro: Posição inválida.");
            return;
        }

        if (posicao == 0) {
            inserirInicio(jogador);
        } else if (posicao == tamanho()) {
            inserirFim(jogador);
        } else {
            Nodo novoNodo = new Nodo(jogador);
            Nodo nodoAtual = obterNodo(posicao);

            novoNodo.proximo = nodoAtual;
            novoNodo.anterior = nodoAtual.anterior;
            nodoAtual.anterior.proximo = novoNodo;
            nodoAtual.anterior = novoNodo;
        }
    }

    public void inserirFim(Jogador jogador) {
        Nodo novoNodo = new Nodo(jogador);
        if (inicio == null) {
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            novoNodo.anterior = fim;
            fim.proximo = novoNodo;
            fim = novoNodo;
        }
    }

    public Jogador removerInicio() {
        if (inicio == null) {
            System.out.println("Erro: Lista vazia.");
            return null;
        }

        Jogador jogadorRemovido = inicio.jogador;
        inicio = inicio.proximo;

        if (inicio == null) {
            fim = null;
        } else {
            inicio.anterior = null;
        }

        return jogadorRemovido;
    }

    public Jogador remover(int posicao) {
        if (posicao < 0 || posicao >= tamanho()) {
            System.out.println("Erro: Posição inválida.");
            return null;
        }

        if (posicao == 0) {
            return removerInicio();
        } else if (posicao == tamanho() - 1) {
            return removerFim();
        } else {
            Nodo nodoRemovido = obterNodo(posicao);
            Jogador jogadorRemovido = nodoRemovido.jogador;

            nodoRemovido.anterior.proximo = nodoRemovido.proximo;
            nodoRemovido.proximo.anterior = nodoRemovido.anterior;

            return jogadorRemovido;
        }
    }

    public Jogador removerFim() {
        if (inicio == null) {
            System.out.println("Erro: Lista vazia.");
            return null;
        }

        Jogador jogadorRemovido = fim.jogador;
        fim = fim.anterior;

        if (fim == null) {
            inicio = null;
        } else {
            fim.proximo = null;
        }

        return jogadorRemovido;
    }

    public void mostrar() {
        Nodo nodoAtual = inicio;
        int posicao = 0;

        while (nodoAtual != null) {
            System.out.print("[" + posicao + "]");
            nodoAtual.jogador.imprimir();
            posicao++;
            nodoAtual = nodoAtual.proximo;
        }
    }

    private int tamanho() {
        int tamanho = 0;
        Nodo nodoAtual = inicio;

        while (nodoAtual != null) {
            tamanho++;
            nodoAtual = nodoAtual.proximo;
        }

        return tamanho;
    }

    private Nodo obterNodo(int posicao) {
        Nodo nodoAtual = inicio;
        int i = 0;

        while (i < posicao) {
            nodoAtual = nodoAtual.proximo;
            i++;
        }

        return nodoAtual;
    }
}

public class Main {
    private static ArrayList<Jogador> jogadores = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Main aplicacao = new Main();
        aplicacao.carregarJogadores("/tmp/jogadores.txt");

        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        do {
            entrada = reader.readLine();
            if (!entrada.equals("FIM")) {
                int id = Integer.parseInt(entrada);
                Jogador jogador = aplicacao.jogadores.get(id);
                lista.inserirFim(jogador);
            }
        } while (!entrada.equals("FIM"));

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] comando = reader.readLine().split(" ");
            if (comando[0].equals("II")) {
                int id = Integer.parseInt(comando[1]);
                Jogador jogador = aplicacao.jogadores.get(id);
                lista.inserirInicio(jogador);
            } else if (comando[0].equals("I*")) {
                int id = Integer.parseInt(comando[2]);
                int posicao = Integer.parseInt(comando[1]);
                Jogador jogador = aplicacao.jogadores.get(id);
                lista.inserir(jogador, posicao);
            } else if (comando[0].equals("IF")) {
                int id = Integer.parseInt(comando[1]);
                Jogador jogador = aplicacao.jogadores.get(id);
                lista.inserirFim(jogador);
            } else if (comando[0].equals("RI")) {
                Jogador jogadorRemovido = lista.removerInicio();
                if (jogadorRemovido != null) {
                    System.out.println("(R) " + jogadorRemovido.getNome());
                }
            } else if (comando[0].equals("R*")) {
                int posicao = Integer.parseInt(comando[1]);
                Jogador jogadorRemovido = lista.remover(posicao);
                if (jogadorRemovido != null) {
                    System.out.println("(R) " + jogadorRemovido.getNome());
                }
            } else if (comando[0].equals("RF")) {
                Jogador jogadorRemovido = lista.removerFim();
                if (jogadorRemovido != null) {
                    System.out.println("(R) " + jogadorRemovido.getNome());
                }
            }
        }

        lista.mostrar();
        reader.close();
    }

    private void carregarJogadores(String arquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
        reader.readLine();
        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(",", -1);
            int id = Integer.parseInt(campos[0]);
            String nome = campos[1];
            int altura = Integer.parseInt(campos[2]);
            int peso = Integer.parseInt(campos[3]);
            String universidade = campos.length > 4 ? campos[4] : "";
            int anoNascimento = Integer.parseInt(campos[5]);
            String cidadeNascimento = campos.length > 6 ? campos[6] : "";
            String estadoNascimento = campos.length > 7 ? campos[7] : "";

            Jogador jogador = new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento,
                    estadoNascimento);
            jogadores.add(jogador);
        }
        reader.close();
    }
}
