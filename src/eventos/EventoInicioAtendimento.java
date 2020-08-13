package eventos;

import java.time.LocalTime;
import atendentes.*;
import pacientes.*;

public class EventoInicioAtendimento extends Evento {

  private LocalTime tempoTotal;
  private LocalTime tempoAtendimento;
  private Atendente atendente;
  private Paciente paciente;

  public EventoInicioAtendimento(Atendente atendente, Paciente paciente) {
    int tempo = atendente.verificaFicha() + (atendente.aplicaVacina() * paciente.getNumero_vacinas());

    this.tempoTotal = LocalTime.of(00, tempo, 00);
    this.tempoAtendimento = LocalTime.of(00, tempo, 00);
    this.atendente = atendente;
    this.paciente = paciente;

    this.atendente.setOcupado(true);
  }

  public void setHoraAtendimentoPaciente() {
    // atualiza a hora de atendimento do paciente, para calcular o tmepo de espera
    // na fila
  }
}