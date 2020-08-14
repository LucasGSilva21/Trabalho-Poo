package pacientes;

import java.time.LocalTime;

/**
 * Classe Abstrata Pai Paciente
 */
public abstract class Paciente implements Comparable<Paciente> {
  /**
   * Nome do Paciente
   */
  private String nome;

  /**
   * Horário de chegada do Paciente na fila
   */
  private LocalTime horario_chegada;

  /**
   * Horário em que o Paciente foi atendido
   */
  private LocalTime horario_atendimento;

  /**
   * Número de vacinas que o Paciente deseja tomar
   */
  private int numero_vacinas;

  /**
   * Construtor da Classe Pai Paciente
   * 
   * @param nome            Nome do Paciente
   * @param horario_chegada Horário de chegada do Paciente na fila
   * @param numero_vacinas  Número de vacinas que o Paciente deseja tomar
   */
  public Paciente(String nome, LocalTime horario_chegada, int numero_vacinas) {
    this.nome = nome;
    this.horario_chegada = horario_chegada;
    this.numero_vacinas = numero_vacinas;
  }

  /**
   * Metodo responsavel por retorna o horário de chegada do Paciente na fila
   * 
   * @return Horário de chegada do Paciente na fila
   */
  public LocalTime getHorario_chegada() {
    return horario_chegada;
  }

  /**
   * Metodo responsavel por retorna o número de vacinas que o Paciente deseja
   * tomar
   * 
   * @return Número de vacinas que o Paciente deseja tomar
   */
  public int getNumero_vacinas() {
    return numero_vacinas;
  }

  /**
   * Metodo responsavel por retorna o horário que o Paciente foi atendido
   * 
   * @return Horário em que o Paciente foi atendido
   */
  public LocalTime getHorario_atendimento() {
    return horario_atendimento;
  }

  /**
   * Metodo que define o horário em que o Paciente foi atendido
   * 
   * @param horario_atendimento Horário em que o Paciente foi atendido
   */
  public void setHorarioAtendimento(LocalTime horario_atendimento) {
    this.horario_atendimento = horario_atendimento;
  }

  /**
   * Metodo que define a estrategia de comparação entre objetos da classe
   * Paciente, usado na ordenação dos pacientes na fila
   * 
   * @param paciente2 Paciente que será comparado
   * 
   * @return Retorna 1 caso o horario de chegada seja maior, -1 caso seja menor e
   *         0 caso seja igual
   */
  @Override
  public int compareTo(Paciente paciente2) {
    return this.getHorario_chegada().compareTo(paciente2.getHorario_chegada());
  }

  /**
   * Metodo responsavel pelo retorno do objeto Paciente em formato de String,
   * utilizado para salvar as estatisticas no arquivo de texto
   * 
   * @return Objeto Paciente formatado em String
   */
  @Override
  public String toString() {
    return "Nome: " + nome + "\tNúmero de Vacinas: " + numero_vacinas + "\tHorario de Chegada: " + horario_chegada
        + "\tHorario de Atendimento: " + horario_atendimento;
  }
}