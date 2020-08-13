/**
 * Classe Principal responsavel pela incialização da Simulação
 */
public class Principal {
  public static void main(final String[] args) {
    System.out.println("Bem-vindo");

    final Simulacao simulacao = new Simulacao();
    simulacao.iniciaSimulacao();

    System.out.println(simulacao.getTempoTotal());
  }
}