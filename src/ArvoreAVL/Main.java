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

class NoAVL {
    Jogador jogador;
    int altura;
    NoAVL esq, dir;

    NoAVL(Jogador jogador) {
        this.jogador = jogador;
        this.altura = 1;
        this.esq = this.dir = null;
    }
}

class ArvoreAVL {
    private NoAVL raiz;
    public int numComparacoes;

    int altura(NoAVL N) {
        if (N == null)
            return 0;
        return N.altura;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esq;
        NoAVL T2 = x.dir;
        x.dir = y;
        y.esq = T2;
        y.altura = max(altura(y.esq), altura(y.dir)) + 1;
        x.altura = max(altura(x.esq), altura(x.dir)) + 1;
        return x;
    }

    NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.dir;
        NoAVL T2 = y.esq;
        y.esq = x;
        x.dir = T2;
        x.altura = max(altura(x.esq), altura(x.dir)) + 1;
        y.altura = max(altura(y.esq), altura(y.dir)) + 1;
        return y;
    }

    int getBalance(NoAVL N) {
        if (N == null)
            return 0;
        return altura(N.esq) - altura(N.dir);
    }

    NoAVL inserir(NoAVL no, Jogador jogador) {
        if (no == null)
            return (new NoAVL(jogador));
        if (jogador.getNome().compareTo(no.jogador.getNome()) < 0)
            no.esq = inserir(no.esq, jogador);
        else if (jogador.getNome().compareTo(no.jogador.getNome()) > 0)
            no.dir = inserir(no.dir, jogador);
        else
            return no;

        no.altura = 1 + max(altura(no.esq), altura(no.dir));
        int balance = getBalance(no);

        if (balance > 1 && jogador.getNome().compareTo(no.esq.jogador.getNome()) < 0)
            return rotacaoDireita(no);

        if (balance < -1 && jogador.getNome().compareTo(no.dir.jogador.getNome()) > 0)
            return rotacaoEsquerda(no);

        if (balance > 1 && jogador.getNome().compareTo(no.esq.jogador.getNome()) > 0) {
            no.esq = rotacaoEsquerda(no.esq);
            return rotacaoDireita(no);
        }

        if (balance < -1 && jogador.getNome().compareTo(no.dir.jogador.getNome()) < 0) {
            no.dir = rotacaoDireita(no.dir);
            return rotacaoEsquerda(no);
        }
        return no;
    }

    void inserir(Jogador jogador) {
        raiz = inserir(raiz, jogador);
    }

    boolean pesquisar(String nome) {
        return pesquisar(raiz, nome);
    }

    private boolean pesquisar(NoAVL no, String nome) {
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

        ArvoreAVL arvore = new ArvoreAVL();
        Scanner scanner = new Scanner(System.in);
        inserirJogadores(scanner, arvore, aplicacao);

        long startTime = System.currentTimeMillis();
        pesquisarJogadores(scanner, arvore);
        long endTime = System.currentTimeMillis();
        scanner.close();

        gerarArquivoLog(endTime - startTime, arvore.numComparacoes);
    }

    private static void inserirJogadores(Scanner scanner, ArvoreAVL arvore, Main aplicacao) {
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

    private static void pesquisarJogadores(Scanner scanner, ArvoreAVL arvore) {
        while (scanner.hasNextLine()) {
            String entrada = scanner.nextLine();
            if (!entrada.equals(FIM)) {
                boolean encontrado = arvore.pesquisar(entrada);
                System.out.println(encontrado ? " SIM" : " NAO");
            }
        }
    }

    private static void gerarArquivoLog(long tempo, int numComparacoes) throws IOException {
        try (FileWriter writer = new FileWriter("793938_arvoreAVL.txt")) {
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
