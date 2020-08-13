package eventos;

import atendentes.Atendente;

/**
 * Subclasse EventoFimAtendimento
 */
public class EventoFimAtendimento extends Evento {

  /**
   * Construtor da Subclasse EventoFimAtendimento
   * 
   * @param atendente Atendente responsavel pelo Evento
   */
  EventoFimAtendimento(Atendente atendente) {
    super(atendente);
    // desocupa o atendente
    getAtendente().setOcupado(false);
  }
}