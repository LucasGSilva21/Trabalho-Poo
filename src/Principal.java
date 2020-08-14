/**
 * Classe Principal responsavel pela incialização da Simulação
 */
public class Principal {
  public static void main(final String[] args) {
    System.out.println("Bem-vindo");

    final Simulacao simulacao = new Simulacao();
    System.out.println("Executando Simulação...");
    simulacao.iniciaSimulacao();
    System.out.println("Simulação Finalizada.");
  }
}