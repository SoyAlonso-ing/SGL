/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.persona.modelos;

import java.util.Date;

/**
 *
 * @author Angel Hernandez
 */
public class Conductor extends Persona{
    private Long IdLicencia;

    public Conductor(Long Id, String Nombre, String Apellidos, String CI, Date FechaNacimiento, String Direccion, String Telefono, String Correo, String Foto,Long IdLicencia) {
        super(Id, Nombre, Apellidos, CI, FechaNacimiento, Direccion, Telefono, Correo, Foto);
        this.IdLicencia=IdLicencia;
    }
    
     public Conductor(String Nombre, String Apellidos, Long IdLicencia, String CI, String Foto){
         super(Nombre,Apellidos,CI,Foto);
        this.IdLicencia=IdLicencia;
    }
     
     public Conductor(String CI){
         super(CI);
     }
     
     public Conductor(String nombre, String apellidos, String CI, long idLicencia){
         super(nombre,apellidos,CI);
         this.IdLicencia=idLicencia;
     }
    

    public Long getIdLicencia() {
        return IdLicencia;
    }
    
    
    @Override
    public String toString()
    {
        return "Id: "+ Id + "\n"+
                "Nombre: "+ Nombre + "\n"+
                "Apellidos: "+ Apellidos + "\n"+
                "CI: "+ CI + "\n"+
                "FechaNacimiento: "+ FechaNacimiento + "\n"+
                "Direccion: "+ Direccion + "\n"+
                "Telefono: "+ Telefono + "\n"+
                "Correo: "+ Correo + "\n"+
                "Foto: "+ Foto + "\n"+
                "IdLicencia: "+ IdLicencia + "\n";
    }
}
