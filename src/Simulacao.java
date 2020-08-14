import pacientes.*;
import atendentes.*;
import eventos.*;
import java.time.*;
import java.util.ArrayList;

/**
 * Classe responsavel por toda lógica da simulação
 */
public class Simulacao {

  /**
   * Tempo total da simulação
   */
  private LocalTime tempoTotal;

  /**
   * Array de atendentes
   */
  private ArrayList<Atendente> atendentes;

  /**
   * Array de Pacientes Normais
   */
  private ArrayList<PacienteNormal> pacientesNormais;

  /**
   * Array de Pacientes Preferenciais
   */
  private ArrayList<PacientePreferencial> pacientesPreferenciais;

  /**
   * Array de Eventos
   */
  private ArrayList<Evento> eventos;

  /**
   * Construtor da Classe Simulação
   */
  public Simulacao() {
    this.tempoTotal = LocalTime.of(00, 00, 00);
    this.atendentes = AcessaDados.lerAtendentes();
    this.pacientesNormais = AcessaDados.lerPacientesNormal();
    this.pacientesPreferenciais = AcessaDados.lerPacientesPreferencial();
    this.eventos = new ArrayList<Evento>();

    AcessaDados.limparEstatisticas();
  }

  /**
   * Metodo responsavel por retornar
   * 
   * @return Paciente da fila
   */
  public LocalTime getTempoTotal() {
    return this.tempoTotal;
  }

  /**
   * Metodo responsavel por buscar e retornar um Paciente da fila
   * 
   * @param tempoAtual Tempo atual na simulação
   * @return Paciente da fila
   */
  private Paciente getPaciente(LocalTime tempoAtual) {
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

  /**
   * Metodo responsavel por adicionar um Evento na fila de eventos
   * 
   * @param novoEvento Evento a ser adicionado na fila
   */
  private void adicionaEvento(Evento novoEvento) {
    this.eventos.add(novoEvento);
  }

  /**
   * Metodo responsavel por buscar e retornar um Atendente disponivel
   * 
   * @return Atendente disponivel
   */
  private Atendente verificaAtendenteLivre() {
    Atendente atendente = null;

    for (Atendente a : atendentes) {
      if (!a.getOcupado()) {
        atendente = a;
      }
    }

    return atendente;
  }

  /**
   * Metodo responsavel por verificar se os atendimentos foram finalizados
   * 
   * @return Booleando indicando se o atendimento foi finalizado
   */
  private boolean atendimentosFinalizados() {
    boolean encerra = true;

    for (Atendente atendente : atendentes) {
      if (atendente.getOcupado()) {
        encerra = false;
      }
    }

    return encerra;
  }

  /**
   * Metodo responsavel por inciar o atendimento de um Paciente
   */
  private void atendePaciente() {
    LocalTime menorTempo = LocalTime.of(23, 59, 59);

    // descobre o menor tempo
    for (Evento evento : eventos) {
      if (evento instanceof EventoInicioAtendimento) {
        EventoInicioAtendimento eventoInicio = (EventoInicioAtendimento) evento;
        if (eventoInicio.getTempoAtendimento().compareTo(menorTempo) < 0) {
          menorTempo = eventoInicio.getTempoAtendimento();
        }
      }
    }

    // atualiza tempo total
    this.tempoTotal = this.tempoTotal.plusHours(menorTempo.getHour()).plusMinutes(menorTempo.getMinute())
        .plusSeconds(menorTempo.getSecond());

    // atualiza tempo de atendimento
    for (int i = 0; i < eventos.size(); i++) {
      if (eventos.get(i).getClass() == EventoInicioAtendimento.class) {
        EventoInicioAtendimento eventoInicio = (EventoInicioAtendimento) eventos.get(i);

        LocalTime tempoAtendimento = eventoInicio.getTempoAtendimento();
        tempoAtendimento = tempoAtendimento.minusHours(menorTempo.getHour()).minusMinutes(menorTempo.getMinute())
            .minusSeconds(menorTempo.getSecond());

        eventoInicio.setTempoAtendimento(tempoAtendimento);
        // verifica se zerou o tempo de atendimento
        if (eventoInicio.getTempoAtendimento().equals(LocalTime.of(0, 0))) {
          // adiciona um evento Fim no array
          Evento novoEventoFim = new EventoFimAtendimento(eventoInicio.getAtendente());
          adicionaEvento(novoEventoFim);
          // remove o evento inicio do array
          eventos.remove(eventos.get(i));
        }
      }
    }
  }

  /**
   * Metodo responsavel por verificar se a Fila de Pacientes esta vazia
   * 
   * @return Booleando indicando se a fila esta vazia
   */
  private boolean verificaFilaVazia() {
    return pacientesNormais.isEmpty() && pacientesPreferenciais.isEmpty();
  }

  /**
   * Metodo responsavel por iniciar a simulação
   */
  public void iniciaSimulacao() {
    Paciente auxPaciente;
    Atendente auxAtendente;
    Evento auxEvento;

    boolean finalizado = false;

    while (!finalizado) {
      // verifica se tem pacientes sendo atendidos
      if (atendimentosFinalizados() && verificaFilaVazia()) {
        finalizado = true;
      } else {
        // verifica se tem pacientes na fila
        if (!verificaFilaVazia()) {
          // verifica se tem atendente disponivel
          auxPaciente = this.getPaciente(tempoTotal);
          auxAtendente = this.verificaAtendenteLivre();

          if (auxAtendente != null) {
            auxEvento = new EventoInicioAtendimento(auxAtendente, auxPaciente, this.tempoTotal);
            this.adicionaEvento(auxEvento);
            auxAtendente.adicionaAtendimento();
            auxPaciente.setHorarioAtendimento(auxPaciente.getHorario_chegada().plusHours(tempoTotal.getHour())
                .plusMinutes(tempoTotal.getMinute()).plusSeconds(tempoTotal.getSecond()));
            AcessaDados.gravarEstatisticasPaciente(auxPaciente);
          } else {
            // executa os atendimentos até liberar um atendente
            atendePaciente();
          }
        } else {
          // finaliza os atendimentos restantes
          atendePaciente();
        }
      }
    }
    // grava as estatísticas da simulação
    AcessaDados.gravarEstatisticasAtendente(atendentes);
    AcessaDados.gravarEstatisticasGerais(tempoTotal, Evento.getNumeroEventos());
  }
}