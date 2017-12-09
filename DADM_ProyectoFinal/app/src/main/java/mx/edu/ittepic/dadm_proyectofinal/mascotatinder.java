package mx.edu.ittepic.dadm_proyectofinal;

import android.graphics.drawable.Drawable;

/**
 * Created by UsuarioPrueba on 30/11/2017.
 */

public class mascotatinder {
    String nombre,edad,sexo,raza,tipo,foto,c,l;
    int idmascota;
    Drawable cd,ld;
    public mascotatinder(int idmascota, String nombre, String edad, String sexo, String raza, String tipo, String foto){
        this.idmascota=idmascota;
        this.nombre=nombre;
        this.edad=edad;
        this.sexo=sexo;
        this.raza=raza;
        this.tipo=tipo;
        this.foto=foto;
    }
    public mascotatinder(int idmascota, String nombre, String edad, String sexo, String raza, String tipo, String foto, String c, String l, Drawable cd, Drawable ld){
        this.idmascota=idmascota;
        this.nombre=nombre;
        this.edad=edad;
        this.sexo=sexo;
        this.raza=raza;
        this.tipo=tipo;
        this.foto=foto;
        this.c=c;
        this.l=l;
        this.cd=cd;
        this.ld=ld;
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
    public Drawable obtenerfotoCora(){
        return cd;
    }
    public Drawable obtenerfotoLike(){
        return ld;
    }
    public void ponerImagenCora(Drawable cd){
        this.cd=cd;
    }
    public void ponerImagenLike(Drawable ld){
        this.ld=ld;
    }
}
