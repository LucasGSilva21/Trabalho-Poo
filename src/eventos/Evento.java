package eventos;

import atendentes.Atendente;

/**
 * Classe Abstrata Pai Evento
 */
public abstract class Evento {
  /**
   * Atendente responsavel pelo Evento
   */
  private Atendente atendente;

  /**
   * Número total de eventos instanciados
   */
  private static int numeroEventos;

  /**
   * Construtor da Classe Pai Evento
   * 
   * @param atendente Atendente responsavel pelo Evento
   */
  public Evento(Atendente atendente) {
    this.atendente = atendente;
    numeroEventos++;
  }

  /**
   * Metodo responsavel por retornar o Atendente responsavel pelo Evento
   * 
   * @return Atendente responsavel pelo Evento
   */
  public Atendente getAtendente() {
    return atendente;
  }

  /**
   * Metodo responsavel por retornar o número total de eventos instanciados
   * 
   * @return Número total de eventos instanciados
   */
  public static int getNumeroEventos() {
    return numeroEventos;
  }
}