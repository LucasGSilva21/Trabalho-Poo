package pacientes;

import java.time.LocalTime;

public class PacientePreferencial extends Paciente {
  public PacientePreferencial(String nome, LocalTime horario_chegada, int numero_vacinas) {
    super(nome, horario_chegada, numero_vacinas);
  }
}