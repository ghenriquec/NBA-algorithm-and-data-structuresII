import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
        sb.append("## ")
                .append(id).append(" ## ")
                .append(nome).append(" ## ")
                .append(altura).append(" ## ")
                .append(peso).append(" ## ")
                .append(anoNascimento).append(" ## ")
                .append(campoNaoInformado(universidade)).append(" ## ")
                .append(campoNaoInformado(cidadeNascimento)).append(" ## ")
                .append(campoNaoInformado(estadoNascimento))
                .append(" ##\n");
        MyIO.print(sb.toString());
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

    public static ArrayList<Jogador> ler(String nomeArquivo) {
        ArrayList<Jogador> listJogadores = new ArrayList<>();
        ArquivoTextoLeitura arquivo = new ArquivoTextoLeitura(nomeArquivo);
        arquivo.readLine();
        String entradaDados = arquivo.readLine();

        while (entradaDados != null && !entradaDados.equals("FIM")) {
            String[] valores = entradaDados.split(",", -1);
            int id = Integer.parseInt(valores[0]);

            listJogadores.add(preencherJogador(valores));
            entradaDados = arquivo.readLine();
        }

        arquivo.fecharArquivo();
        return listJogadores;
    }

    private static Jogador preencherJogador(String[] valores) {
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

    private static String campoNaoInformado(String campo) {
        return campo.isEmpty() ? "nao informado" : campo;
    }

    public Jogador clone() {
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }
}

class Pilha {
    private ArrayList<Jogador> pilha;

    public Pilha() {
        pilha = new ArrayList<>();
    }

    public void empilhar(Jogador jogador) {
        pilha.add(jogador);
    }

    public Jogador desempilhar() {
        if (!pilha.isEmpty()) {
            Jogador jogador = pilha.get(pilha.size() - 1);
            pilha.remove(pilha.size() - 1);
            return jogador;
        } else {
            throw new RuntimeException("Pilha vazia");
        }
    }

    public void mostrar() {
        for (int i = 0; i < pilha.size(); i++) {
            MyIO.print("[" + i + "] ");
            pilha.get(i).imprimirDados();
        }
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
    public static void main(String[] args) {
        ArrayList<Jogador> jogadores = Jogador.ler("/tmp/jogadores.txt");
        Pilha pilha = new Pilha();

        String linha = MyIO.readLine();
        while (!linha.isEmpty() && !linha.equals("FIM")) {
            int id = Integer.parseInt(linha);
            for (Jogador jogador : jogadores) {
                if (jogador.getId() == id) {
                    pilha.empilhar(jogador);
                    break;
                }
            }
            linha = MyIO.readLine();
        }

        int n = Integer.parseInt(MyIO.readLine());
        for (int i = 0; i < n; i++) {
            String[] comandoEId = MyIO.readLine().split(" ");
            String comando = comandoEId[0];
            if (comando.equals("I")) {
                int id = Integer.parseInt(comandoEId[1]);
                for (Jogador jogador : jogadores) {
                    if (jogador.getId() == id) {
                        pilha.empilhar(jogador);
                        break;
                    }
                }
            } else if (comando.equals("R")) {
                Jogador jogadorRemovido = pilha.desempilhar();
                MyIO.println("(R) " + jogadorRemovido.getNome());
            }
        }
        pilha.mostrar();
    }
}

