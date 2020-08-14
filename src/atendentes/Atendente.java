package atendentes;

/**
 * Classe Abstrata Pai Atendente
 */
public abstract class Atendente {
  /**
   * Booleano que indica se o Atendente se encontra ocupado
   */
  private boolean ocupado;

  /**
   * Número de Atendimentos feitos pelo Atendente
   */
  private int numero_atendimentos;

  /**
   * Tempo de duração em minutos da aplicação da vacina
   */
  final private int tempo_vacina;

  /**
   * Tempo de duração em minutos da checagem da ficha do Paciente
   */
  final protected int tempo_ficha;

  /**
   * Construtor da Classe Pai Atendente
   * 
   * @param tempo_ficha  Tempo de duração em minutos da checagem da ficha do
   *                     Paciente
   * @param tempo_vacina Tempo de duração em minutos da vacinação
   */
  public Atendente(int tempo_ficha, int tempo_vacina) {
    this.ocupado = false;
    this.tempo_ficha = tempo_ficha;
    this.tempo_vacina = tempo_vacina;
  }

  /**
   * Metodo responsavel pela modificação do estado de ocupado do Atendente
   * 
   * @param ocupado Booleano que indica se o Atendente se encontra ocupado
   */
  public void setOcupado(boolean ocupado) {
    this.ocupado = ocupado;
  }

  /**
   * Metodo responsavel por retornar o estado de ocupado do Atendente
   * 
   * @return Booleano que indica se o Atendente se encontra ocupado
   */
  public boolean getOcupado() {
    return this.ocupado;
  }

  /**
   * Metodo responsavel pela verificação da ficha do Paciente por um Atendente
   */
  public abstract int verificaFicha();

  /**
   * Metodo responsavel pela aplicação da vacina por um Atendente
   * 
   * @return Tempo em minutos de duração da aplicação da vacina
   */
  public int aplicaVacina() {
    return tempo_vacina;
  }

  /**
   * Metodo responsavel pela contabilização de atendimentos de cada Atendente
   */
  public void adicionaAtendimento() {
    this.numero_atendimentos++;
  }

  /**
   * Metodo responsavel pelo retorno do objeto Atendente em formato de String,
   * utilizado para salvar as estatisticas no arquivo de texto
   * 
   * @return Objeto Atendente formatado em String
   */
  @Override
  public String toString() {
    return "Número de Atendimentos: " + numero_atendimentos + "\n";
  }
}