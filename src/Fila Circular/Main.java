import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.print(sb.toString());
    }

		public void imprimir(int posicao) {
			System.out.printf("[%d]", posicao);
			imprimir();
		}

    private String campoNaoInformado(String campo) {
        return campo.isEmpty() ? "nao informado" : campo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public String getUniversidade() {
        return universidade;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }
}

class Fila {
	private int primeiro, ultimo;
	private Jogador[] fila;
	private int tamanhoMaximo;

	public Fila() {
			tamanhoMaximo = 6;
			fila = new Jogador[tamanhoMaximo];
			primeiro = ultimo = 0;
	}

	public Fila(int tamanhoMaximo) {
			this.tamanhoMaximo = tamanhoMaximo;
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
			if (primeiro == ultimo) {
					System.out.println("Erro: Fila vazia.");
					return null;
			}
			Jogador jogadorRemovido = fila[primeiro];
			primeiro = (primeiro + 1) % tamanhoMaximo;
			return jogadorRemovido;
	}

	public void mostrar() {
    int i = primeiro;
    int posicao = 1;
    while (i != ultimo) {
        fila[i].imprimir(posicao++);
        i = (i + 1) % tamanhoMaximo;
    }
}
	public boolean isVazia() {
			return primeiro == ultimo;
	}

	public int tamanho() {
			return (ultimo - primeiro + tamanhoMaximo) % tamanhoMaximo;
	}

	public double calcularMediaAlturas() {
			double somaAlturas = 0;
			int quantidade = 0;
			int i = primeiro;
			while (i != ultimo) {
					somaAlturas += fila[i].getAltura();
					quantidade++;
					i = (i + 1) % tamanhoMaximo;
			}
			return somaAlturas / quantidade;
	}
}

public class Main {
	private ArrayList<Jogador> jogadores = new ArrayList<>();

	public static void main(String[] args) throws IOException {
			Main aplicacao = new Main();
			aplicacao.carregarJogadores("C:\\Users\\ghenr\\Development\\PilhaNBA\\src\\tmp\\jogadores.txt");

			Fila fila = new Fila();

			Scanner scanner = new Scanner(System.in);
			String entrada;
			do {
					entrada = scanner.nextLine();
					if (!entrada.equals("FIM")) {
							int id = Integer.parseInt(entrada);
							Jogador jogador = aplicacao.jogadores.get(id);
							fila.enfileirar(jogador);
							System.out.printf("%d%n", Math.round(fila.calcularMediaAlturas()));
					}
			} while (!entrada.equals("FIM"));

			int n = Integer.parseInt(scanner.nextLine());

			for (int i = 0; i < n; i++) {
					String[] comando = scanner.nextLine().split(" ");
					if (comando[0].equals("I")) {
							int id = Integer.parseInt(comando[1]);
							Jogador jogador = aplicacao.jogadores.get(id);
							fila.enfileirar(jogador);
							System.out.printf("%d%n", Math.round(fila.calcularMediaAlturas()));
					} else if (comando[0].equals("R")) {
							Jogador jogadorRemovido = fila.desenfileirar();
							if (jogadorRemovido != null) {
									System.out.printf("(R) %s%n", jogadorRemovido.getNome());
							}
					}
			}
			fila.mostrar();
			scanner.close();
	}

	private void carregarJogadores(String arquivo) throws IOException {
			BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
			String linha;
			leitor.readLine();
			while ((linha = leitor.readLine()) != null) {
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
			leitor.close();
	}
}







