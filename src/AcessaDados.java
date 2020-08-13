import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import atendentes.*;
import pacientes.*;

public abstract class AcessaDados {

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

  public static void limparEstatisticas() {
    try (FileWriter arq = new FileWriter("estatisticas.txt")) {
      arq.write("Pacientes:\n");
    } catch (IOException e) {
      System.out.println("Falha ao limpar o arquivo");
    }
  }

  public static void gravarEstatisticasPaciente(Paciente paciente) {
    try (FileWriter arq = new FileWriter("estatisticas.txt", true)) {
      arq.write(paciente.toString());
    } catch (IOException e) {
      System.out.println("Falha ao salvar no arquivo");
    }
  }

  public static void gravarEstatisticasAtendente(ArrayList<Atendente> atendentes) {
    try (FileWriter arq = new FileWriter("estatisticas.txt", true)) {
      arq.write("\nAtendentes:\n");
      for (int i = 0; i < atendentes.size(); i++) {
        if (atendentes.get(i).getClass() == AtendenteExperiente.class) {
          arq.write("Atendente " + (i + 1) + "\tTipo: Experiente\t" + atendentes.get(i).toString());
        } else {
          arq.write("Atendente " + (i + 1) + "\tTipo: Novato\t" + atendentes.get(i).toString());

        }
      }
    } catch (IOException e) {
      System.out.println("Falha ao salvar no arquivo");
    }
  }
}