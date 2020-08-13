import pacientes.*;
import atendentes.*;
import eventos.*;
import java.time.*;
import java.util.ArrayList;

public class Simulacao {

  private static LocalTime tempoTotal;
  private ArrayList<Atendente> atendentes;
  private ArrayList<PacienteNormal> pacientesNormais;
  private ArrayList<PacientePreferencial> pacientesPreferenciais;
  private ArrayList<Evento> eventos;

  public Simulacao() {
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
        a.setOcupado(true);
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

  public void iniciaSimulacao() {
    Paciente auxPaciente;
    Atendente auxAtendente;

    while (true) {
      auxPaciente = this.getPaciente();
      auxAtendente = this.verificaAtendenteLivre();

      // verifica se tem pessoas na fila
      if (auxPaciente != null) {

        if (auxAtendente != null) {

        } else {

        }

      } else {
        // verifica se tem pessoas sendo atendidas
        if (atendimentosFinalizados()) {
          break;
        } else {

        }
      }

      /*
       * if(tem pessoa na fila){ if(verifica atendente livre){ adiciona evento
       * iniciaAtendimento }else{ espera - atualiza tempo de fila e executa os
       * atendimentos } }else{ if(todas atendentes tao desocupadas?){ *encerra } }
       */
    }

  }
}