package pacientes;

import java.time.LocalTime;

public abstract class Paciente implements Comparable<Paciente> {
  private String nome;
  private LocalTime horario_chegada;
  private LocalTime horario_atendimento;
  private int numero_vacinas;

  public Paciente(String nome, LocalTime horario_chegada, int numero_vacinas) {
    this.nome = nome;
    this.horario_chegada = horario_chegada;
    this.numero_vacinas = numero_vacinas;
  }

  public LocalTime getHorario_chegada() {
    return horario_chegada;
  }

  public void setHorarioAtendimento(LocalTime horario_atendimento) {
    this.horario_atendimento = horario_atendimento;
  }

  @Override
  public String toString() {
    return "Nome: " + nome + "\tNúmero de Vacinas: " + numero_vacinas + "\nHorario de Chegada: " + horario_chegada
        + "\tHorario de Atendimento: " + horario_atendimento + "\n";
  }

  @Override
  public int compareTo(Paciente paciente2) {
    return this.getHorario_chegada().compareTo(paciente2.getHorario_chegada());
  }
}