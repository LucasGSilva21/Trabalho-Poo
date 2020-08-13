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

  public void iniciaSimulacao() {

    while (true) {
      /*
       * if(tem pessoa na fila){ if(verifica atendente livre){ adiciona evento
       * iniciaAtendimento }else{ espera - atualiza tempo de fila e executa os
       * atendimentos } }else{ if(todas atendentes tao desocupadas?){ *encerra } }
       */
    }

  }
}