package mx.edu.ittepic.practicau1_5_activitys_anacarolinamondragonrangel;
public class Lista {
    private int id;
    private String nombre;
    private String descripcion;
    private String imagen;
    public Lista(int id, String nombre,String descripcion, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion=descripcion;
        this.imagen = imagen;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String titulo) {
        this.nombre = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String titulo) {
        this.descripcion = titulo;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    @Override
    public String toString() {
        return "mx.edu.ittepic.practicau1_5_activitys_anacarolinamondragonrangel.Lista [id=" + id + ", nombre=" + nombre + ", descripcion="+descripcion+", imagen=" + imagen + "]";
    }
}
