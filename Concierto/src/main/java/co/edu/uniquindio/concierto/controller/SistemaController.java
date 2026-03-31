package co.edu.uniquindio.concierto.controller;

public class SistemaController {
    private static SistemaController instans;
    private SistemaController () {

    }
    public static SistemaController getInstance() {
        if (instans == null) {
            instans = new SistemaController();

        }
        return instans;
    }
}
