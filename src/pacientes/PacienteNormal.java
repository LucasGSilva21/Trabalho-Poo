package pacientes;

import java.time.LocalTime;

public class PacienteNormal extends Paciente {
  public PacienteNormal(String nome, LocalTime horario_chegada) {
    super(nome, horario_chegada);
  }
}