package atendentes;

public abstract class Atendente {
  private boolean ocupado;
  private int numero_atendimentos;
  final private int tempo_vacina;
  final private int tempo_ficha;

  public Atendente(int tempo_ficha, int tempo_vacina) {
    this.ocupado = false;
    this.tempo_ficha = tempo_ficha;
    this.tempo_vacina = tempo_vacina;
  }

  public void setOcupado(boolean ocupado) {
    this.ocupado = ocupado;
  }

  public boolean getOcupado() {
    return this.ocupado;
  }

  public int verificaFicha() {
    return tempo_ficha;
  };

  public int aplicaVacina() {
    return tempo_vacina;
  }

  public void adicionaAtendimento() {
    this.numero_atendimentos++;
  }

  @Override
  public String toString() {
    return "Número de Atendimentos: " + numero_atendimentos;
  }
}