package pacientes;

import java.sql.Time;

public class PacientePreferencial extends Paciente {
  public PacientePreferencial(String nome, Time horario_chegada) {
    super(nome, horario_chegada);
  }
}