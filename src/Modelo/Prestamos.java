package Modelo;
import java.util.Date;

public class Prestamos {
    private int idPrestamo;
    private int idUsuario;
    private int idDocumento;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private boolean devuelto;
    private double mora;

    public Prestamos() {
    }

    public Prestamos(int idPrestamo, int idUsuario, int idDocumento, Date fechaPrestamo, Date fechaDevolucion, boolean devuelto, double mora) {
        this.idPrestamo = idPrestamo;
        this.idUsuario = idUsuario;
        this.idDocumento = idDocumento;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
        this.mora = mora;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    public double getMora() {
        return mora;
    }

    public void setMora(double mora) {
        this.mora = mora;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
    }

    public void setFechaDevolucion(String fechaDevolucion) {
    }
}
