package eventos;

public class EventoInicioAtendimento extends Evento {

  public EventoInicioAtendimento() {

  }

  public void setAtendenteOcupado() {
    // atualiza ar
  }

  public void setHoraAtendimentoPaciente() {
    // atualiza a hora de atendimento do paciente, para calcular o tmepo de espera
    // na fila
  }

  public void eventoVerificaFicha() {
    // pega o tempo gasto com a verificação da ficha
  }

  public void eventoAplicaVacina() {
    // pega o tempo gasto com a aplicação da vacina
  }
}