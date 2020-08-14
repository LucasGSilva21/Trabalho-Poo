package atendentes;

/**
 * Subclasse AtendenteExperiente
 */
public class AtendenteExperiente extends Atendente {

  /**
   * Construtor da subclasse AtendenteExperiente
   */
  public AtendenteExperiente() {
    super(2, 1);
  }

  /**
   * Metodo responsavel pela verificação da ficha do Paciente por um Atendente
   * 
   * @return Tempo em minutos de duração para checagem da ficha do Paciente
   */
  @Override
  public int verificaFicha() {
    return this.tempo_ficha;
  };
}