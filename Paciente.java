import java.sql.Time;

public abstract class Paciente {
  private String nome;
  private Time horario_chegada;
  private Time horario_atendimento;
  private boolean atendido;

  public Paciente(String nome, Time horario_chegada) {
    this.atendido = false;
    this.nome = nome;
    this.horario_chegada = horario_chegada;
  }

  public void setHorarioAtendimento(Time horario_atendimento) {
    this.horario_atendimento = horario_atendimento;
  }
}