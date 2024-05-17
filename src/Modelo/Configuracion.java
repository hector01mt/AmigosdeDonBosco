package Modelo;

public class Configuracion {

    private int maxEjemplaresPrestamo;
    private double moraDiaria;

    public Configuracion() {
    }

    public Configuracion(int maxEjemplaresPrestamo, double moraDiaria) {
        this.maxEjemplaresPrestamo = maxEjemplaresPrestamo;
        this.moraDiaria = moraDiaria;
    }

    public int getMaxEjemplaresPrestamo() {
        return maxEjemplaresPrestamo;
    }

    public void setMaxEjemplaresPrestamo(int maxEjemplaresPrestamo) {
        this.maxEjemplaresPrestamo = maxEjemplaresPrestamo;
    }

    public double getMoraDiaria() {
        return moraDiaria;
    }

    public void setMoraDiaria(double moraDiaria) {
        this.moraDiaria = moraDiaria;
    }

}
