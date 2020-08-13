package pacientes;

import java.time.LocalTime;

public class PacientePreferencial extends Paciente {
  public PacientePreferencial(String nome, LocalTime horario_chegada) {
    super(nome, horario_chegada);
  }
}