import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Jogador {
    private final int id, altura, peso, anoNascimento;
    private final String nome, universidade, cidadeNascimento, estadoNascimento;

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

    public String getNome() {
        return nome;
    }
}

class No {
    public Jogador jogador; 
    public No esq, dir;

    public No(Jogador jogador) {
        this.jogador = jogador;
        this.esq = this.dir = null;
    }
}

class ArvoreBinaria {
    private No raiz;
    public int numComparacoes;

    public void inserir(Jogador jogador) {
        raiz = inserir(raiz, jogador);
    }

    private No inserir(No no, Jogador jogador) {
        if (no == null) {
            return new No(jogador);
        }
        if (jogador.getNome().compareTo(no.jogador.getNome()) < 0) {
            no.esq = inserir(no.esq, jogador);
        } else if (jogador.getNome().compareTo(no.jogador.getNome()) > 0) {
            no.dir = inserir(no.dir, jogador);
        }
        return no;
    }

    public boolean pesquisar(String nome) {
        return pesquisar(raiz, nome);
    }

    private boolean pesquisar(No no, String nome) {
        if (no == null) {
            return false;
        }
        numComparacoes++; 
        System.out.printf("%s ", no.jogador.getNome());
        if (nome.equals(no.jogador.getNome())) {
            return true;
        }
        if (nome.compareTo(no.jogador.getNome()) < 0) {
            return pesquisar(no.esq, nome);
        } 
        return pesquisar(no.dir, nome);
    }
}

public class Main {
    private static final String FIM = "FIM";
    private ArrayList<Jogador> jogadores = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Main aplicacao = new Main();
        aplicacao.carregarJogadores("/tmp/jogadores.txt");

        ArvoreBinaria arvore = new ArvoreBinaria();
        Scanner scanner = new Scanner(System.in);
        inserirJogadores(scanner, arvore, aplicacao);

        long startTime = System.currentTimeMillis();
        pesquisarJogadores(scanner, arvore);
        long endTime = System.currentTimeMillis();
        scanner.close();

        gerarArquivoLog(endTime - startTime, arvore.numComparacoes);
    }

    private static void inserirJogadores(Scanner scanner, ArvoreBinaria arvore, Main aplicacao) {
        String entrada;
        do {
            entrada = scanner.nextLine();
            if (!entrada.equals(FIM)) {
                int id = Integer.parseInt(entrada);
                Jogador jogador = aplicacao.jogadores.get(id);
                arvore.inserir(jogador);
            }
        } while (!entrada.equals(FIM));
    }

    private static void pesquisarJogadores(Scanner scanner, ArvoreBinaria arvore) {
        while (scanner.hasNextLine()) {
            String entrada = scanner.nextLine();
            if (!entrada.equals(FIM)) {
                boolean encontrado = arvore.pesquisar(entrada);
                System.out.println(encontrado ? " SIM" : " NAO");
            }
        }
    }

    private static void gerarArquivoLog(long tempo, int numComparacoes) throws IOException {
        try (FileWriter writer = new FileWriter("793938_arvoreBinaria.txt")) {
            writer.write("793938\t" + tempo + "\t" + numComparacoes);
        }
    }

    private void carregarJogadores(String arquivo) throws IOException {
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            leitor.readLine();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(",", -1);
                jogadores.add(criarJogador(campos));
            }
        }
    }

    private Jogador criarJogador(String[] campos) {
        int id = Integer.parseInt(campos[0]);
        String nome = campos[1];
        int altura = Integer.parseInt(campos[2]);
        int peso = Integer.parseInt(campos[3]);
        String universidade = campos.length > 4 ? campos[4] : "";
        int anoNascimento = Integer.parseInt(campos[5]);
        String cidadeNascimento = campos.length > 6 ? campos[6] : "";
        String estadoNascimento = campos.length > 7 ? campos[7] : "";
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }
}
