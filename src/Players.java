import java.util.ArrayList;
import MyIo.OutText;

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
                .append(id)
                .append(" ## ")
                .append(nome)
                .append(" ## ")
                .append(altura)
                .append(" ## ")
                .append(peso)
                .append(" ## ")
                .append(anoNascimento)
                .append(" ## ");
        if (universidade.isEmpty()) {
            sb.append("nao informado");
        } else {
            sb.append(universidade);
        }
        sb.append(" ## ");
        if (cidadeNascimento.isEmpty()) {
            sb.append("nao informado");
        } else {
            sb.append(cidadeNascimento);
        }
        sb.append(" ## ");
        if (estadoNascimento.isEmpty()) {
            sb.append("nao informado");
        } else {
            sb.append(estadoNascimento);
        }
        sb.append("]\n");
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
    public void ler() {
        String entradaDados = MyIO.readLine();
        while (!entradaDados.equals("FIM")) {
            String[] valores = entradaDados.split(",", -1);
            Jogador jogador = new Jogador();
            jogador.setId(Integer.parseInt(valores[0]));
            jogador.setNome(valores[1]);
            jogador.setAltura(Integer.parseInt(valores[2]));
            jogador.setPeso(Integer.parseInt(valores[3]));
            jogador.setUniversidade(valores[4].isEmpty() ? "nao informado" : valores[4]);
            jogador.setAnoNascimento(valores[5].equals("nao informado") ? 0 : Integer.parseInt(valores[5]));
            jogador.setCidadeNascimento(valores[6].isEmpty() ? "nao informado" : valores[6]);
            jogador.setEstadoNascimento(valores[7].isEmpty() ? "nao informado" : valores[7]);
            listJogadores.add(jogador);
            entradaDados = MyIO.readLine();
        }
        escrever();
    }

    public void escrever() {
        int idCount = Integer.parseInt(MyIO.readLine());

        for (int i = 0; i < idCount; i++) {
            String linha = MyIO.readLine();
            if (linha.equals("FIM")) {
                break;
            }
            int jogadorId = Integer.parseInt(linha);
            for (Jogador jogadorTemp : listJogadores) {
                if (jogadorTemp.getId() == jogadorId) {
                    jogadorTemp.imprimirDados();
                    break;
                }
            }
        }
    }
    public Jogador clone() {
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }
}
public class Players {
    public static void main(String[] args) {
        Jogador newJogador = new Jogador();
        newJogador.ler();
    }
}
