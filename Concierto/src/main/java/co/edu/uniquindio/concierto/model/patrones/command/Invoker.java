package co.edu.uniquindio.concierto.model.patrones.command;

import java.util.List;

public class Invoker {
    private List<Command> historial;

    public void ejecutar(Command command) {
        command.execute();
        historial.add(command);
    }

    public List<Command> getHistorial() {
        return historial;
    }
}
