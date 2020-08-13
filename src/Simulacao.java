import pacientes.*;
import atendentes.*;
import eventos.*;
import java.time.*;
import java.util.ArrayList;

public class Simulacao {

  private LocalTime tempoTotal;
  private ArrayList<Atendente> atendentes;
  private ArrayList<PacienteNormal> pacientesNormais;
  private ArrayList<PacientePreferencial> pacientesPreferenciais;
  private ArrayList<Evento> eventos;

  public Simulacao() {
    this.tempoTotal = LocalTime.of(00, 00, 00);
    ArrayList<Atendente> atendentes = AcessaDados.lerAtendentes();
    ArrayList<PacienteNormal> pacientesNormais = AcessaDados.lerPacientesNormal();
    ArrayList<PacientePreferencial> pacientesPreferenciais = AcessaDados.lerPacientesPreferencial();
    ArrayList<Evento> eventos = new ArrayList<Evento>();
  }

  public Paciente getPaciente() {
    Paciente paciente;

    if (pacientesNormais.isEmpty() && pacientesPreferenciais.isEmpty()) {
      return null;
    } else if (pacientesPreferenciais.isEmpty()) {
      paciente = pacientesNormais.get(0);
      pacientesNormais.remove(0);
    } else {
      paciente = pacientesPreferenciais.get(0);
      pacientesPreferenciais.remove(0);
    }

    return paciente;
  }

  public void adicionaEvento(Evento novoEvento) {
    this.eventos.add(novoEvento);
  }

  public Atendente verificaAtendenteLivre() {
    Atendente atendente = null;

    for (Atendente a : atendentes) {
      if (a.getOcupado()) {
        atendente = a;
      }
    }

    return atendente;
  }

  public boolean atendimentosFinalizados() {
    boolean encerra = true;

    for (Atendente atendente : atendentes) {
      if (atendente.getOcupado()) {
        encerra = false;
      }
    }

    return encerra;
  }

  public void atendePaciente() {

  }

  public void iniciaSimulacao() {
    Paciente auxPaciente;
    Atendente auxAtendente;
    Evento auxEvento;

    while (true) {
      auxPaciente = this.getPaciente();
      auxAtendente = this.verificaAtendenteLivre();

      // verifica se tem pessoas na fila
      if (auxPaciente != null) {
        // verifica se tem atendente disponivel
        if (auxAtendente != null) {
          auxEvento = new EventoInicioAtendimento(auxAtendente, auxPaciente, this.tempoTotal);
          this.adicionaEvento(auxEvento);
        } else {
          // executa logica de subtrair tempo atendimento e adicionar tempo na fila
          atendePaciente();
        }
      } else {
        // verifica se tem pessoas sendo atendidas
        if (atendimentosFinalizados()) {
          break;
        } else {
          atendePaciente();
        }
      }
    }
  }

}