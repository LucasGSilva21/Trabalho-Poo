import pacientes.*;
import atendentes.*;
import java.time.*;
import java.util.ArrayList;

public class Simulacao {

  private static LocalTime tempoTotal;
  private ArrayList<Atendente> atendentes;
  private ArrayList<PacienteNormal> pacientesNormais;
  private ArrayList<PacientePreferencial> pacientesPreferenciais;

  public Simulacao() {
    ArrayList<Atendente> atendentes = AcessaDados.lerAtendentes();
    ArrayList<PacienteNormal> pacientesNormais = AcessaDados.lerPacientesNormal();
    ArrayList<PacientePreferencial> pacientesPreferenciais = AcessaDados.lerPacientesPreferencial();
  }

  public void getPaciente() {
    // Paciente novoPaciente;

    // if fila prioritaria
    // novoPaciente =
    // else fila normal
    // novoPaciente =

    // ...

    // return novoPaciente;
  }
}