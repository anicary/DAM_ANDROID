package mx.edu.ittepic.dadm_proyectofinal;

/**
 * Created by UsuarioPrueba on 02/12/2017.
 */

public class raza {
    String nombrer,caracter,salud,utilidad,fotor;
    int idrazamascota;
    public raza(int idrazamascota,String nombrer,String caracter, String salud,String utilidad,String fotor){
        this.idrazamascota=idrazamascota;
        this.nombrer=nombrer;
        this.caracter=caracter;
        this.salud=salud;
        this.utilidad=utilidad;
        this.fotor=fotor;
    }
    public int getidrazamascota(){
        return idrazamascota;
    }
    public String getnombrer(){
        return nombrer;
    }
    public String getcaracter(){
        return caracter;
    }
    public String getsalud(){
        return salud;
    }
    public String getutilidad(){
        return utilidad;
    }
    public String getfotor(){
        return fotor;
    }

}
