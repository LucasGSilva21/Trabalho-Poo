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

  public Paciente getPaciente() {
    Paciente paciente;

    if (pacientesNormais.isEmpty() && pacientesPreferenciais.isEmpty()) {
      return null;
    } else if (pacientesPreferenciais.isEmpty()) {
      paciente = pacientesNormais.get(0);
      pacientesNormais.remove(0);
    } else {
      paciente = pacientesPreferenciais.get(0);
      pacientesPreferenciais.remove(0);
    }

    return paciente;
  }
}