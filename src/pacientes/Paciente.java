package pacientes;

import java.time.LocalTime;

public abstract class Paciente {
  private String nome;
  private LocalTime horario_chegada;
  private LocalTime horario_atendimento;
  private boolean atendido;
  private int numero_vacinas;

  public Paciente(String nome, LocalTime horario_chegada, int numero_vacinas) {
    this.atendido = false;
    this.nome = nome;
    this.horario_chegada = horario_chegada;
    this.numero_vacinas = numero_vacinas;
  }

  public void setHorarioAtendimento(LocalTime horario_atendimento) {
    this.horario_atendimento = horario_atendimento;
  }
}