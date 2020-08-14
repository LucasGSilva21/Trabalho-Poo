package atendentes;

/**
 * Subclasse AtendenteNovato
 */
public class AtendenteNovato extends Atendente {

  final private int tempo_tirar_duvida;

  /**
   * Construtor da subclasse AtendenteNovato
   */
  public AtendenteNovato() {
    super(4, 2);
    this.tempo_tirar_duvida = 1;
  }

  /**
   * Metodo responsavel pela verificação da ficha do Paciente por um Atendente
   * 
   * @return Tempo em minutos de duração para checagem da ficha do Paciente
   */
  @Override
  public int verificaFicha() {
    return this.tempo_ficha + this.tempo_tirar_duvida;
  };
}