package com.example.Model;

import java.util.Objects;

public class Genoma {

    private int VacunaA;
    private int SueroB;
    private int TanqueOxigeno;

    public Genoma(String genoma){

        VacunaA = 0;
        SueroB = 0;
        TanqueOxigeno = 0;

        //

        char mediciona = ' ';
        short cantidadMedicina = 0;

        if (null == genoma || genoma.strip().isEmpty()){
            throw  new IllegalArgumentException("Error en el Genoma: " + (genoma == null ? "Null" : genoma));
        }

        genoma = genoma.strip().toUpperCase();

        if (genoma.length() % 2 == 0){

            for(int i = 0; i < genoma.length(); i+=2){

                mediciona = genoma.charAt(i);
                cantidadMedicina = Short.parseShort(Character.toString(genoma.charAt(i+1)));

                switch (mediciona){

                    case 'A': VacunaA += cantidadMedicina; break;
                    case 'B': SueroB += cantidadMedicina; break;
                    case 'O': TanqueOxigeno += cantidadMedicina; break;
                    default: break;

                }

            }

        }

    }

    public int getVacunaA() {
        return VacunaA;
    }

    public int getSueroB() {
        return SueroB;
    }

    public int getTanqueOxigeno() {
        return TanqueOxigeno;
    }

    @Override
    public String toString(){

        String mensaje = "";

        mensaje += "-".repeat(20);
        mensaje += "\n- > VacunaA: " + VacunaA + "\t\t\t |";
        mensaje += "\n- > SueroB: " + SueroB + "\t\t\t |";
        mensaje += "\n- > TanqueOxigeno: " + TanqueOxigeno + "\t\t\t |";
        mensaje += "\n-".repeat(20);

        return mensaje;
    }

    @Override
    public boolean equals (Object obj){

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Genoma genoma = (Genoma) obj;

        return VacunaA == genoma.VacunaA && SueroB == genoma.SueroB && TanqueOxigeno == genoma.TanqueOxigeno;

    }

    @Override
    public int hashCode() {
        
        return Objects.hash(VacunaA, SueroB, TanqueOxigeno);
        
    }

}
