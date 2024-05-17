package Modelo;

public class Documentos {
    private int idDocumento;
    private String tipoDocumento;
    private String titulo;
    private String autor;
    private String editorial;
    private int anioPublicacion;
    private String ubicacionFisica;
    private int cantidadEjemplares;
    private int ejemplaresDisponibles;
    private int ejemplaresPrestados;

    public Documentos() {
    }

    public Documentos(int idDocumento, String tipoDocumento, String titulo, String autor, String editorial, int anioPublicacion, String ubicacionFisica, int cantidadEjemplares, int ejemplaresDisponibles, int ejemplaresPrestados) {
        this.idDocumento = idDocumento;
        this.tipoDocumento = tipoDocumento;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
        this.ubicacionFisica = ubicacionFisica;
        this.cantidadEjemplares = cantidadEjemplares;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getUbicacionFisica() {
        return ubicacionFisica;
    }

    public void setUbicacionFisica(String ubicacionFisica) {
        this.ubicacionFisica = ubicacionFisica;
    }

    public int getCantidadEjemplares() {
        return cantidadEjemplares;
    }

    public void setCantidadEjemplares(int cantidadEjemplares) {
        this.cantidadEjemplares = cantidadEjemplares;
    }

    public int getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public int getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(int ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

}
