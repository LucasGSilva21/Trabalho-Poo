package pacientes;

import java.time.LocalTime;

public abstract class Paciente {
  private String nome;
  private LocalTime horario_chegada;
  private LocalTime horario_atendimento;
  private boolean atendido;

  public Paciente(String nome, LocalTime horario_chegada) {
    this.atendido = false;
    this.nome = nome;
    this.horario_chegada = horario_chegada;
  }

  public void setHorarioAtendimento(LocalTime horario_atendimento) {
    this.horario_atendimento = horario_atendimento;
  }
}