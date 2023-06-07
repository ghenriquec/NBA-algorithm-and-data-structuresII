import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
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

    public int getAltura() {
        return altura;
    }
}

class ResultadoPesquisa {
    boolean encontrado;
    int posicao;

    ResultadoPesquisa(boolean encontrado, int posicao) {
        this.encontrado = encontrado;
        this.posicao = posicao;
    }
}

class TabelaHash {
  private final ArrayList<LinkedList<Jogador>> tabela;
  private final int TAMANHO_TABELA = 37;
  public int numComparacoes;

  TabelaHash() {
      tabela = new ArrayList<>(TAMANHO_TABELA);
      for (int i = 0; i < TAMANHO_TABELA; i++) {
          tabela.add(new LinkedList<>());
      }
  }

  private int funcaoHash(int altura) {
      return altura % TAMANHO_TABELA;
  }

  void inserir(Jogador jogador) {
      int pos = funcaoHash(jogador.getAltura());
      tabela.get(pos).add(jogador);
  }

  ResultadoPesquisa pesquisar(String nome) {
    for (int i = 0; i < tabela.size(); i++) {
        LinkedList<Jogador> lista = tabela.get(i);
        for (Jogador jogador : lista) {
            numComparacoes++;
            if (jogador.getNome().equals(nome)) {
                return new ResultadoPesquisa(true, i);
            }
        }
    }
    return new ResultadoPesquisa(false, -1);
}
}

public class Main {
  private static final String FIM = "FIM";
  private ArrayList<Jogador> jogadores = new ArrayList<>();

  public static void main(String[] args) throws IOException {
      Main aplicacao = new Main();
      aplicacao.carregarJogadores("/tmp/jogadores.txt");

      TabelaHash tabelaHash = new TabelaHash();
      Scanner scanner = new Scanner(System.in);
      inserirJogadores(scanner, tabelaHash, aplicacao);

      long startTime = System.currentTimeMillis();
      pesquisarJogadores(scanner, tabelaHash);
      long endTime = System.currentTimeMillis();
      scanner.close();

      gerarArquivoLog(endTime - startTime, tabelaHash.numComparacoes);
  }

  private static void inserirJogadores(Scanner scanner, TabelaHash tabelaHash, Main aplicacao) {
      String entrada;
      do {
          entrada = scanner.nextLine();
          if (!entrada.equals(FIM)) {
              int id = Integer.parseInt(entrada);
              Jogador jogador = aplicacao.jogadores.get(id);
              tabelaHash.inserir(jogador);
          }
      } while (!entrada.equals(FIM));
  }

  private static void pesquisarJogadores(Scanner scanner, TabelaHash tabelaHash) {
    while (scanner.hasNextLine()) {
        String entrada = scanner.nextLine();
        if (!entrada.equals(FIM)) {
            ResultadoPesquisa resultado = tabelaHash.pesquisar(entrada);
            if (resultado.encontrado) {
                System.out.println(resultado.posicao + " SIM");
            } else {
                System.out.println("NAO");
            }
        }
    }
}

  private static void gerarArquivoLog(long tempo, int numComparacoes) throws IOException {
      try (FileWriter writer = new FileWriter("793938_hashSeparado.txt")) {
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
      String universidade = campos.length > 4 ? campos[4] : "nao informado";
      int anoNascimento = Integer.parseInt(campos[5]);
      String cidadeNascimento = campos.length > 6 ? campos[6] : "nao informado";
      String estadoNascimento = campos.length > 7 ? campos[7] : "nao informado";
      return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
  }
}