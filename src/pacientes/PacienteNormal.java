package pacientes;

import java.time.LocalTime;

public class PacienteNormal extends Paciente {
  public PacienteNormal(String nome, LocalTime horario_chegada, int numero_vacinas) {
    super(nome, horario_chegada, numero_vacinas);
  }
}