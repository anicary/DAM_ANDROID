package mx.edu.ittepic.dadm_proyectofinal;

/**
 * Created by UsuarioPrueba on 30/11/2017.
 */

public class mascota {
    String nombre,edad,sexo,raza,tipo,foto,c,l;
    int idmascota;
    public mascota(int idmascota,String nombre, String edad, String sexo, String raza,String tipo,String foto){
        this.idmascota=idmascota;
        this.nombre=nombre;
        this.edad=edad;
        this.sexo=sexo;
        this.raza=raza;
        this.tipo=tipo;
        this.foto=foto;
    }
    public mascota(int idmascota,String nombre, String edad, String sexo, String raza,String tipo,String foto,String c,String l){
        this.idmascota=idmascota;
        this.nombre=nombre;
        this.edad=edad;
        this.sexo=sexo;
        this.raza=raza;
        this.tipo=tipo;
        this.foto=foto;
        this.c=c;
        this.l=l;
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
    public String gettipo(){
        return tipo;
    }
    public String getfoto(){
        return foto;
    }
    public String getCoraz(){
        return c;
    }
    public String getLike(){
        return l;
    }
    public void setCoraz(String c){
        this.c=c;
    }
    public void setLike(String l){
        this.l=l;
    }
}
