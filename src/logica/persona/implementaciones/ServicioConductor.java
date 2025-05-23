/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.persona.implementaciones;

import javafx.collections.ObservableList;
import logica.persona.consultas.ConsultasPersona;
import logica.persona.modelos.Conductor;

/**
 *
 * @author Angel Hernandez
 */
public class ServicioConductor{

    
    public static ObservableList<Conductor> ObtenerConductores() throws Exception {
        return ConsultasPersona.ObtenerConductoresConsulta();
    }
    
    public static Conductor ObtenerConductorPorId(long Id) throws Exception
    {
        return ConsultasPersona.ObtenerConductorPorIdConsulta(Id);
    }
    
     public static Conductor ObtenerConductorPorIdLicencia(long IdLicencia) throws Exception
    {
        return ConsultasPersona.ObtenerConductorPorIdLicenciaConsulta(IdLicencia);
    }
}
