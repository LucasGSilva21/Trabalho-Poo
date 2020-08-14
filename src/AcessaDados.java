import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import atendentes.*;
import pacientes.*;

/**
 * Classe Abstrata responsavel pela leitura e escrita nos arquivos de texto
 */
public abstract class AcessaDados {

  /**
   * Metodo estático responsavel por ler os Atendentes do arquivo de texto
   * 
   * @return Array de Atendentes
   */
  public static ArrayList<Atendente> lerAtendentes() {
    try (BufferedReader arq = new BufferedReader(new FileReader("dadosEntrada.txt"))) {
      String linha = arq.readLine();
      String[] campos = linha.split(" ");

      int experientes = Integer.parseInt(campos[0]);
      int novatos = Integer.parseInt(campos[1]);
      ArrayList<Atendente> atendentes = new ArrayList<Atendente>();

      for (int i = 0; i < experientes; i++) {
        atendentes.add(new AtendenteExperiente());
      }
      for (int i = 0; i < novatos; i++) {
        atendentes.add(new AtendenteNovato());
      }
      return atendentes;
    } catch (FileNotFoundException e) {
      System.out.println("Não foi possível ler o arquivo ");
      return null;
    } catch (IOException e) {
      System.out.println("Falha na leitura do arquivo ");
      return null;
    }
  }

  /**
   * Metodo estático responsavel por ler os Pacientes Normais do arquivo de texto
   * 
   * @return Array de Pacientes Normais
   */
  public static ArrayList<PacienteNormal> lerPacientesNormal() {
    try (BufferedReader arq = new BufferedReader(new FileReader("dadosEntrada.txt"))) {
      String linha = arq.readLine();
      linha = arq.readLine();
      ArrayList<PacienteNormal> pacientes = new ArrayList<PacienteNormal>();
      while (linha != null) {
        String[] campos = linha.split(" ");
        if (campos[2].equals("normal")) {
          String[] tempoInteiro = campos[3].split(":");
          PacienteNormal paciente_normal = new PacienteNormal(campos[0], LocalTime.of(Integer.parseInt(tempoInteiro[0]),
              Integer.parseInt(tempoInteiro[1]), Integer.parseInt(tempoInteiro[2])), Integer.parseInt(campos[1]));
          pacientes.add(paciente_normal);
        }
        linha = arq.readLine();
      }
      return pacientes;
    } catch (FileNotFoundException e) {
      System.out.println("Não foi possível ler o arquivo ");
      return null;
    } catch (IOException e) {
      System.out.println("Falha na leitura do arquivo ");
      return null;
    }
  }

  /**
   * Metodo estático responsavel por ler os Pacientes Preferenciais do arquivo de
   * texto
   * 
   * @return Array de Pacientes Preferenciais
   */
  public static ArrayList<PacientePreferencial> lerPacientesPreferencial() {
    try (BufferedReader arq = new BufferedReader(new FileReader("dadosEntrada.txt"))) {
      String linha = arq.readLine();
      linha = arq.readLine();
      ArrayList<PacientePreferencial> pacientes = new ArrayList<PacientePreferencial>();
      while (linha != null) {
        String[] campos = linha.split(" ");
        if (campos[2].equals("preferencial")) {
          String[] tempoInteiro = campos[3].split(":");
          PacientePreferencial paciente_preferencial = new PacientePreferencial(campos[0],
              LocalTime.of(Integer.parseInt(tempoInteiro[0]), Integer.parseInt(tempoInteiro[1]),
                  Integer.parseInt(tempoInteiro[2])),
              Integer.parseInt(campos[1]));
          pacientes.add(paciente_preferencial);
        }
        linha = arq.readLine();
      }
      return pacientes;
    } catch (FileNotFoundException e) {
      System.out.println("Não foi possível ler o arquivo ");
      return null;
    } catch (IOException e) {
      System.out.println("Falha na leitura do arquivo ");
      return null;
    }
  }

  /**
   * Metodo estático responsavel por limpar o arquivo de texto contendo as
   * estatisticas
   */
  public static void limparEstatisticas() {
    try (FileWriter arq = new FileWriter("estatisticas.txt")) {
      arq.write("Pacientes:\n");
    } catch (IOException e) {
      System.out.println("Falha ao limpar o arquivo");
    }
  }

  /**
   * Metodo estático responsavel por escrever as estatisticas de um Paciente no
   * arquivo de texto
   * 
   * @param paciente Paciente que será escrito no arquivo de texto
   */
  public static void gravarEstatisticasPaciente(Paciente paciente) {
    try (FileWriter arq = new FileWriter("estatisticas.txt", true)) {
      arq.write(paciente.toString() + "\tTempo de fila: "
          + paciente.getHorario_atendimento().minusHours(paciente.getHorario_chegada().getHour())
              .minusMinutes(paciente.getHorario_chegada().getMinute())
              .minusSeconds(paciente.getHorario_chegada().getSecond())
          + '\n');
    } catch (IOException e) {
      System.out.println("Falha ao salvar no arquivo");
    }
  }

  /**
   * Metodo estático responsavel por escrever as estatisticas dos Atendentes no
   * arquivo de texto
   * 
   * @param atendentes Array de Atendentes que serão escritos no arquivo de texto
   */
  public static void gravarEstatisticasAtendente(ArrayList<Atendente> atendentes) {
    gravarEstatisticasGrafico(atendentes);
    try (FileWriter arq = new FileWriter("estatisticas.txt", true)) {
      arq.write("\nAtendentes:\n");
      for (int i = 0; i < atendentes.size(); i++) {
        if (atendentes.get(i) instanceof AtendenteExperiente) {
          arq.write("Atendente " + (i + 1) + "\tTipo: Experiente\t" + atendentes.get(i).toString());
        } else {
          arq.write("Atendente " + (i + 1) + "\tTipo: Novato\t" + atendentes.get(i).toString());
        }
      }
    } catch (IOException e) {
      System.out.println("Falha ao salvar no arquivo");
    }
  }

  /**
   * Metodo estático responsavel por escrever as estatisticas no arquivo de texto
   * para o grafico
   * 
   * @param atendentes Array de Atendentes que serão escritos no arquivo de texto
   */
  private static void gravarEstatisticasGrafico(ArrayList<Atendente> atendentes) {
    try (FileWriter arq = new FileWriter("estatisticasGrafico.txt")) {
      for (int i = 0; i < atendentes.size(); i++) {
        if (atendentes.get(i) instanceof AtendenteExperiente) {
          arq.write((i + 1) + " Experiente " + atendentes.get(i).getNumero_atendimentos() + "\n");
        } else {
          arq.write((i + 1) + " Novato " + atendentes.get(i).getNumero_atendimentos() + "\n");
        }
      }
    } catch (IOException e) {
      System.out.println("Falha ao salvar no arquivo");
    }
  }

  /**
   * Metodo estático responsavel por escrever as estatisticas gerais da simulação
   * no arquivo de texto
   * 
   * @param tempoTotal Tempo total de Simulação
   */
  public static void gravarEstatisticasGerais(LocalTime tempoTotal, int numeroEventos) {
    try (FileWriter arq = new FileWriter("estatisticas.txt", true)) {
      arq.write("\nEstatisticas Gerais:\n");
      arq.write("Tempo simulado total: " + tempoTotal + "\n");
      arq.write("Número de eventos tratados: " + numeroEventos + "\n");
    } catch (IOException e) {
      System.out.println("Falha ao salvar no arquivo");
    }
  }
}