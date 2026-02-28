package com.example.Model;

public class Civil extends Paciente {

    private final float porcentajeAvanceInfeccion = 0.1f;
    private final float porcentajeReduccionSalud = 0.15f;

    public Civil(int id, int nivelInfeccion, int nivelSalud, String genoma) {
        super(id, nivelInfeccion, nivelSalud, genoma);

        Prioridad = 3;

    }

    @Override
    public String cicloMutacion() {

        String resultado = "Ciclo de Mutación del Civil: \n";

        int nivelInfeccion = getNivelInfeccion();
        int nivelSalud = getNivelSalud();

        resultado += "Nivel de Infección: " + nivelInfeccion + "\n";
        resultado += "Nivel de Salud: " + nivelSalud + "\n";

        int avanceInfeccion = (int) (nivelInfeccion * porcentajeAvanceInfeccion);
        int reduccionSalud = (int) (nivelSalud * porcentajeReduccionSalud);

        NivelInfeccion += avanceInfeccion;
        NivelSalud -= reduccionSalud;

        resultado += "Nuevo Nivel de Infección: " + NivelInfeccion + "\n";
        resultado += "Nuevo Nivel de Salud: " + NivelSalud + "\n"; 

        return resultado;
    }
}
