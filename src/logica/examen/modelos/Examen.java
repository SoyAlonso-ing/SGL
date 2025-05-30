/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen.modelos;

import java.util.Date;
import logica.entidad.modelos.EntidadRelacionada;
import logica.persona.modelos.Persona;
import logica.usuario.modelos.Usuario;

/**
 *
 * @author Kris
 */
public class Examen {
    
    protected Long Id;
    protected Date Fecha;
    protected boolean Aprobado;
    protected EntidadRelacionada Entidad;
    protected Persona Persona;
    protected Usuario Examinador;
    protected String Tipo;

    public Examen(Long Id, Date Fecha, boolean Aprobado, EntidadRelacionada Entidad, Persona Persona, Usuario Examinador,String Tipo) {
        this.Id = Id;
        this.Fecha = Fecha;
        this.Aprobado = Aprobado;
        this.Tipo = Tipo;
        this.Entidad = Entidad;
        this.Examinador=Examinador;
        this.Persona=Persona;
        
    }
    
    public Examen( Date Fecha, boolean Aprobado, EntidadRelacionada Entidad, Persona Persona, Usuario Examinador,String Tipo) {
        this.Fecha = Fecha;
        this.Aprobado = Aprobado;
        this.Tipo = Tipo;
        this.Entidad = Entidad;
        this.Examinador=Examinador;
        this.Persona=Persona;
        
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public boolean isAprobado() {
        return Aprobado;
    }

    public void setAprobado(boolean Aprobado) {
        this.Aprobado = Aprobado;
    }

    public EntidadRelacionada getEntidad() {
        return Entidad;
    }

    public void setEntidad(EntidadRelacionada Entidad) {
        this.Entidad = Entidad;
    }

    public Persona getPersona() {
        return Persona;
    }

    public void setPersona(Persona Persona) {
        this.Persona = Persona;
    }

    public Usuario getExaminador() {
        return Examinador;
    }

    public void setExaminador(Usuario Examinador) {
        this.Examinador = Examinador;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
    @Override
    public String toString()
    {
         return "Id: "+ Id +"\n"+
                "Fecha: "+ Fecha +"\n"+
                "Aprobado: "+ Aprobado +"\n"+
                "Tipo"+Tipo+"\n"+
                "Entidad: " + Entidad.getNombre() +"\n"+
                "Persona: " + Persona.getNombre() +"\n"+
                "Examinador: " + Examinador.getNombre()+"\n";
    }
}
