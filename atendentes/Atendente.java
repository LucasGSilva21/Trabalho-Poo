package atendentes;

public abstract class Atendente {
  private String nome;
  private boolean ocupado;
  final private int tempo_vacina;
  final private int tempo_ficha;

  public Atendente(String nome, int tempo_ficha, int tempo_vacina) {
    this.nome = nome;
    this.ocupado = false;
    this.tempo_ficha = tempo_ficha;
    this.tempo_vacina = tempo_vacina;
  }

  public void setOcupado(boolean ocupado) {
    this.ocupado = ocupado;
  }

  public int verificaFicha() {
    return tempo_ficha;
  };

  public int aplicaVacina() {
    return tempo_vacina;
  }

  @Override
  public String toString() {
    return "Nome: " + this.nome;
  }
}