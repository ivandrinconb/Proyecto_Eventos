package co.edu.uniquindio.concierto.model.patrones.command;

public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    // Ejecuta el comando actual
    public void executeCommand() {
        if (command != null) {
            command.execute();
        }
    }
}
