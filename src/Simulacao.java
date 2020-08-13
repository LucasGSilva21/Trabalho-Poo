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

  public Paciente getPaciente(LocalTime tempoAtual) {
    Paciente paciente;

    for (PacientePreferencial pacientePreferencial : pacientesPreferenciais) {
      if (pacientePreferencial.getHorario_chegada().compareTo(tempoAtual) >= 0) {
        paciente = pacientePreferencial;
        pacientesPreferenciais.remove(pacientePreferencial);
        return paciente;
      }
    }

    for (PacienteNormal pacienteNormal : pacientesNormais) {
      if (pacienteNormal.getHorario_chegada().compareTo(tempoAtual) >= 0) {
        paciente = pacienteNormal;
        pacientesNormais.remove(pacienteNormal);
        return paciente;
      }
    }

    return null;
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
    LocalTime menorTempo = LocalTime.of(00, 00, 00);

    // descobrir o menor tempo
    for (Evento evento : eventos) {
      // percorrer apenas os eventos iniciais
      if (evento.getClass() == EventoInicioAtendimento.class) {
        EventoInicioAtendimento eventoInicio = (EventoInicioAtendimento) evento;
        if (eventoInicio.getTempoAtendimento().compareTo(menorTempo) < 0) {
          // atualiza o menor tempo
        }
      }
    }

    // somar tempo total
    this.tempoTotal = this.tempoTotal.plusHours(menorTempo.getHour()).plusMinutes(menorTempo.getMinute())
        .plusSeconds(menorTempo.getSecond());

    // subtrair tempo de atendimento menor de todos os eventos iniciais
    for (Evento evento : eventos) {
      if (evento.getClass() == EventoInicioAtendimento.class) {
        EventoInicioAtendimento eventoInicio = (EventoInicioAtendimento) evento;

        LocalTime tempoAtendimento = eventoInicio.getTempoAtendimento();
        tempoAtendimento = tempoAtendimento.minusHours(menorTempo.getHour()).minusMinutes(menorTempo.getMinute())
            .minusSeconds(menorTempo.getSecond());

        eventoInicio.setTempoAtendimento(tempoAtendimento);
        // verifica se zerou
        if (eventoInicio.getTempoAtendimento().equals(LocalTime.of(0, 0))) {
          // adiciona um evento Fim no array
          // remove o evento inicio do array
          //
        }
      }
    }
  }

  public void iniciaSimulacao() {
    Paciente auxPaciente;
    Atendente auxAtendente;
    Evento auxEvento;

    while (true) {
      auxPaciente = this.getPaciente(tempoTotal);
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