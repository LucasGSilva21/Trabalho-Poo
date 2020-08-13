package pacientes;

import java.time.LocalTime;

/**
 * Subclasse PacientePreferencial
 */
public class PacientePreferencial extends Paciente {
  /**
   * Construtor da subclasse PacientePreferencial
   * 
   * @param nome            Nome do Paciente
   * @param horario_chegada Horário de chegada do Paciente na fila
   * @param numero_vacinas  Número de vacinas que o Paciente deseja tomar
   */
  public PacientePreferencial(String nome, LocalTime horario_chegada, int numero_vacinas) {
    super(nome, horario_chegada, numero_vacinas);
  }
}