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
   * Construtor da Classe Pai Evento
   * 
   * @param atendente Atendente responsavel pelo Evento
   */
  public Evento(Atendente atendente) {
    this.atendente = atendente;
  }

  /**
   * Metodo responsavel por retornar o Atendente responsavel pelo Evento
   * 
   * @return Atendente responsavel pelo Evento
   */
  public Atendente getAtendente() {
    return atendente;
  }
}