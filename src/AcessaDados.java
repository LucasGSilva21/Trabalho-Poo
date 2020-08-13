import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import atendentes.*;

public class AcessaDados {

  public static ArrayList<Atendente> lerAtendentes() {
    try (BufferedReader arq = new BufferedReader(new FileReader("dadosEntrada.txt"))) {
      String linha = arq.readLine();
      String[] campos = linha.split(" ");

      int experientes = Integer.parseInt(campos[0]);
      int novatos = Integer.parseInt(campos[1]);
      ArrayList<Atendente> atendentes = new ArrayList<Atendente>();

      for (int i = 0; i < experientes; i++) {
        atendentes.add(new atendentes.AtendenteExperiente());
      }
      for (int i = 0; i < novatos; i++) {
        atendentes.add(new atendentes.AtendenteNovato());
      }

      System.out.println("Arquivo foi lido com sucesso!\n");
      return atendentes;
    } catch (FileNotFoundException e) {
      System.out.println("Não foi possível ler o arquivo ");
      return null;
    } catch (IOException e) {
      System.out.println("Falha na leitura do arquivo ");
      return null;
    }
  }
}