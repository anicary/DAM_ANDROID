package mx.edu.ittepic.dadm_proyectofinal;

/**
 * Created by UsuarioPrueba on 30/11/2017.
 */

public class mascota {
    String nombre,edad,sexo,raza,foto;
    int idmascota;
    public mascota(int idmascota,String nombre, String edad, String sexo, String raza,String foto){
        this.idmascota=idmascota;
        this.nombre=nombre;
        this.edad=edad;
        this.sexo=sexo;
        this.raza=raza;
        this.foto=foto;
    }
    public int getidmascota(){
        return idmascota;
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
    public String getfoto(){
        return foto;
    }
}
