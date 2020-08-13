package eventos;

import java.time.LocalTime;
import atendentes.*;
import pacientes.*;

/**
 * Subclasse EventoInicioAtendimento
 */
public class EventoInicioAtendimento extends Evento {

  private LocalTime tempoTotal;

  /**
   * Tempo de Atendimento
   */
  private LocalTime tempoAtendimento;

  /**
   * Paciente a ser atendido no Evento
   */
  private Paciente paciente;

  /**
   * Construtor da Subclasse EventoInicioAtendimento
   * 
   * @param atendente  Atendente responsavel pelo Evento
   * @param paciente   Paciente a ser atendido no Evento
   * @param tempoTotal
   */
  public EventoInicioAtendimento(Atendente atendente, Paciente paciente, LocalTime tempoTotal) {
    super(atendente);
    int tempo = atendente.verificaFicha() + (atendente.aplicaVacina() * paciente.getNumero_vacinas());

    this.tempoTotal = LocalTime.of(00, tempo, 00);
    this.tempoAtendimento = LocalTime.of(00, tempo, 00);
    this.paciente = paciente;

    this.atendente.setOcupado(true);
    this.paciente.setHorarioAtendimento(tempoTotal);
  }

  /**
   * Metodo responsavel pela definição do tempo de atendimento
   * 
   * @param tempo Tempo de Atendimento
   */
  public void setTempoAtendimento(LocalTime tempo) {
    this.tempoAtendimento = tempo;
  }

  /**
   * Metodo responsavel por retornar o tempo de atendimento
   * 
   * @return Tempo de Atendimento
   */
  public LocalTime getTempoAtendimento() {
    return this.tempoAtendimento;
  }
}