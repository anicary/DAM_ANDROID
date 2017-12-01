package mx.edu.ittepic.dadm_proyectofinal;

/**
 * Created by UsuarioPrueba on 30/11/2017.
 */

public class mascota {
    String nombre,edad,sexo,raza;
    public mascota(String nombre, String edad, String sexo, String raza){
        this.nombre=nombre;
        this.edad=edad;
        this.sexo=sexo;
        this.raza=raza;
    }
    public String getnombre(){
        return nombre;
    }
    public String getedad(){
        return edad;
    }
    public String getsexo(){
        return sexo;
    }
    public String getraza(){
        return raza;
    }
}
