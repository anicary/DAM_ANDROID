package mx.edu.ittepic.dadm_u4_anacarolinamondragonrangel_pbd;

/**
 * Created by mario on 11/11/2017.
 */

public class Cliente {
    private int idcliente;
    private String nombre;
    private String domicilio;
    private  String colonia;
    public Cliente(int idcliente, String nombre, String domicilio,String colonia) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.colonia = colonia;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String  getDomicilio()
    {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public  String getColonia(){
        return colonia;
    }
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
}
