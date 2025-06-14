/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.recursos_compartidos.menus.controladores;

import com.jfoenix.controls.JFXRadioButton;
import gestor_interfaces.GestorEscenas;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.entidad.implementaciones.ServicioEntidad;
import logica.examen_conduccion.implementaciones.ServiciosExamenesConduccion;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.examen_conduccion.validaciones.ValidacionCrearExamenPractico;
import logica.examen_conduccion.validaciones.ValidacionCrearExamenTeorico;
import logica.examen_medico.implementaciones.ServiciosExamenesMedicos;
import logica.examen_medico.modelos.ExamenMedico;
import logica.examen_medico.validaciones.ValidacionCrearExamenMedico;
import logica.licencia.modelos.Licencia;
import logica.persona.implementaciones.ServicioPersona;
import logica.persona.modelos.Persona;
import logica.restricciones.implementacion.ServicioRestriccion;
import logica.usuario.implementaciones.ServicioUsuario;
import logica.usuario.modelos.Usuario;
import logica.validaciones_generales.ValidacionCampoVacio;
import logica.validaciones_generales.ValidacionCantidadCaracteresExacta;
import logica.validaciones_generales.ValidacionCarnet;
import logica.validaciones_generales.ValidacionComboBoxVacio;
import logica.validaciones_generales.ValidacionCompuesta;
import logica.validaciones_generales.ValidacionFecha;
import logica.validaciones_generales.ValidacionGrupoRadioButton;
import logica.validaciones_generales.ValidacionSoloLetras;
import logica.validaciones_generales.ValidacionSoloNumeros;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorRegistrarExamen {
    
    @FXML private RadioButton rbtMedico;
    
    @FXML private RadioButton rbtTeorico;
    
    @FXML private RadioButton rbtPractico;
    
    @FXML private ScrollPane scrpRestricciones;
    
    @FXML private Label lblRestricciones;
    
    @FXML private Button btnCancelar;
    
    @FXML private Button btnRegistrar;
    
    @FXML private TextField txfNombre;
    
    @FXML private TextField txfCarnet;
    
    @FXML private DatePicker dtFecha;
    
    @FXML private AnchorPane apnlContenedorRestricciones;
    
    @FXML private ToggleGroup rbtGrupoTipoExamen;
    
    @FXML private ComboBox<String> cmbNombreEntidad;
    
    @FXML private ComboBox<String> cmbNombreExaminador;
    
    @FXML private RadioButton rbtAprobado;
    
    public void initialize()
    {
        System.out.println("Controlador Registrar Examen Activado");
        visibilidadRestricciones(false);
        btnCancelar.setOnAction(e -> GestorEscenas.cerrar(btnCancelar));
        cmbNombreEntidad.setDisable(true);
        cmbNombreExaminador.setDisable(true);
        automaticoComboBox();
        
    }
    
    @FXML private void automaticoComboBox()
    {
        ArrayList<String> autoescuelas= new ArrayList<>();
        ArrayList<String> clinicas=new ArrayList<>();
        ArrayList<Usuario> examinadoresAutoescuela= new ArrayList<>();
        ArrayList<Usuario> examinadoresMedicos= new ArrayList<>();
        ArrayList<String> nombresExaminadoresAutoescuelas=new ArrayList<>();
        ArrayList<String> nombresExaminadoresMedicos=new ArrayList<>();
        ObjectProperty<RadioButton> seleccion= new SimpleObjectProperty<>();
        try {
           examinadoresAutoescuela=ServicioUsuario.obtenerUsuariosExamenesConduccion();
           examinadoresMedicos=ServicioUsuario.obtenerUsuariosExamenesMedicos();
           for(Usuario au: examinadoresAutoescuela)
           {
               nombresExaminadoresAutoescuelas.add(au.getNombre());
           }
           for(Usuario med: examinadoresMedicos)
           {
               nombresExaminadoresMedicos.add(med.getNombre());
           }
           autoescuelas=ServicioEntidad.obtenerNombresAutoescuelas();
           clinicas=ServicioEntidad.obtenerNombresClinicas();
        } catch (Exception e) {
            GestorEscenas.cargarError(btnCancelar.getScene().getWindow(), e);
        }
        
        rbtGrupoTipoExamen.selectedToggleProperty().addListener((obs,valorViejo,valorNuevo)->{
            if(valorNuevo!=null)
            {
                seleccion.set((RadioButton)valorNuevo);
            }
        });
        
        String[]nombresExaminadoresAuto=nombresExaminadoresAutoescuelas.toArray(new String[0]);
        String[]nombresExaminadoresMed=nombresExaminadoresMedicos.toArray(new String[0]);
        
        String[]nombresAutoescuelas=autoescuelas.toArray(new String[0]);
        String[]nombresClinicas=clinicas.toArray(new String[0]);
        seleccion.addListener((obs,valorViejo,valorNuevo)->{
           if(valorNuevo!= null){
               if(valorNuevo.getText().equalsIgnoreCase("Práctico") || valorNuevo.getText().equalsIgnoreCase("Teórico"))
               {
                   cmbNombreExaminador.getItems().clear();
                   cmbNombreExaminador.setDisable(false);
                   cmbNombreExaminador.getItems().addAll(nombresExaminadoresAuto);
                   
                   cmbNombreEntidad.getItems().clear();
                   cmbNombreEntidad.setDisable(false);
                   cmbNombreEntidad.getItems().addAll(nombresAutoescuelas);
               }
               else if(valorNuevo.getText().equalsIgnoreCase("Médico"))
               {
                   cmbNombreExaminador.getItems().clear();
                   cmbNombreExaminador.setDisable(false);
                   cmbNombreExaminador.getItems().addAll(nombresExaminadoresMed);
                   
                   cmbNombreEntidad.setDisable(false);
                   cmbNombreEntidad.getItems().clear();
                   cmbNombreEntidad.getItems().addAll(nombresClinicas);
               }
               
           }
       });
    }
    @FXML public void seleccionarTipoMedico()
    {
        visibilidadRestricciones(true);
        btnRegistrar.setText("Siguiente");
        try {
            GestorEscenas.generarRadioButtons(ServicioRestriccion.obtenerRestricciones(),scrpRestricciones);
        } catch (Exception ex) {
            GestorEscenas.cargarError(rbtMedico.getScene().getWindow(), ex);
        }
    }
    
    @FXML public void seleccionarTipoTeorico()
    {
        visibilidadRestricciones(false);
        btnRegistrar.setText("Registrar");
    }
    
    @FXML public void seleccionarTipoPractico()
    {
        visibilidadRestricciones(false);
        btnRegistrar.setText("Siguiente");
    }
    
    private void visibilidadRestricciones(boolean esVisible)
    {
        scrpRestricciones.setVisible(esVisible);
        lblRestricciones.setVisible(esVisible);
        scrpRestricciones.setManaged(esVisible);
        lblRestricciones.setManaged(esVisible);
    }
    
    @FXML private void botonRegistrar()
    {
        
        ValidacionCampoVacio validacionCampoVacio = new ValidacionCampoVacio();
        ValidacionSoloLetras validacionSoloLetras = new ValidacionSoloLetras();
        ValidacionSoloNumeros validacionSoloNumeros = new ValidacionSoloNumeros();
        
        ValidacionFecha validacionFecha = new ValidacionFecha();
        ValidacionCarnet validacionCarnet = new ValidacionCarnet();
        
        ValidacionCantidadCaracteresExacta validacionCantidadCaracteresExacta = new ValidacionCantidadCaracteresExacta(11);
        ValidacionGrupoRadioButton validacionGrupoBotones= new ValidacionGrupoRadioButton();
        ValidacionComboBoxVacio validacionComboBoxVacio= new ValidacionComboBoxVacio();
        
        
        ValidacionCompuesta campoNombre = new ValidacionCompuesta(validacionCampoVacio,validacionSoloLetras);
        ValidacionCompuesta campoCarnet = new ValidacionCompuesta(validacionCantidadCaracteresExacta,validacionSoloNumeros,validacionCarnet);

        
        
        try {

            campoNombre.validar(txfNombre.getText(), "nombre");
            campoCarnet.validar(txfCarnet.getText(), "carnet identidad");
            validacionFecha.validar(dtFecha.getValue(), "fecha examen");
            validacionGrupoBotones.validar(rbtGrupoTipoExamen, "opciones de tipo de examen");
            validacionComboBoxVacio.validar(cmbNombreEntidad, "nombre entidad");
            validacionComboBoxVacio.validar(cmbNombreExaminador, "nombre examinador");

            System.out.println("Datos Correctos");

            Usuario examinador = ServicioUsuario.obtenerUsuarioPorNombre(cmbNombreExaminador.getValue());
            if (rbtMedico.isSelected()) {      
                ExamenMedico examenMedico = new ExamenMedico(Date.from(dtFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        rbtAprobado.isSelected(),
                        ServicioEntidad.obtenerEntidadPorNombre(cmbNombreEntidad.getValue()),
                        ServicioPersona.obtenerPersonaPorCi(txfCarnet.getText()),
                        examinador,
                        obtenerRestricciones());
                
                
                if (ValidacionCrearExamenMedico.validarExamenMedico(txfNombre.getText(), txfCarnet.getText(),examinador )) {
                    //Crear Examen Medico y salir
                    ServiciosExamenesMedicos.crearExamenMedico(examenMedico);
                    GestorEscenas.cerrar(btnCancelar);
                    
                } else {
                    Window ventanaActual = rbtMedico.getScene().getWindow();
                    
                    Map<String,String> nombreApellidos = separarNombreApellidos(txfNombre.getText());
                    System.out.println(examenMedico.getRestricciones());
                    Persona persona = new Persona(nombreApellidos.get("nombre"), nombreApellidos.get("apellidos"), txfCarnet.getText());
                    GestorEscenas.cargarRegistrarPersona(ventanaActual, (Stage) rbtMedico.getScene().getWindow(),examenMedico,persona);
                }

            } else if (rbtTeorico.isSelected()) {
                ExamenConduccion examen;

                //Crear examen teorico
                examen = new ExamenConduccion(Date.from(dtFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        rbtAprobado.isSelected(),
                        ServicioEntidad.obtenerEntidadPorNombre(cmbNombreEntidad.getValue()),
                        ServicioPersona.obtenerPersonaPorCi(txfCarnet.getText()),
                        examinador, "Teorico");

                ExamenMedico examenMedico = ValidacionCrearExamenTeorico.validarCrearExamenTeorico(txfNombre.getText(),
                        txfCarnet.getText(), examinador);

                if (examen.getFecha().before(examenMedico.getFecha())) {
                    throw new Exception("No puede realizar el examen teorico antes del medico");
                }

                ServiciosExamenesConduccion.crearExamenTeorico(examen);

                GestorEscenas.cargarConfirmacion(btnRegistrar.getScene().getWindow(), "Se ha registrado con éxito");
                GestorEscenas.cerrar(btnRegistrar);
                
            } else if (rbtPractico.isSelected() && rbtAprobado.isSelected()) {
                //Examen Practico
                ExamenConduccion examen;
                examen = new ExamenConduccion(Date.from(dtFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())
                        , rbtAprobado.isSelected(), ServicioEntidad.obtenerEntidadPorNombre(cmbNombreEntidad.getValue())
                        , ServicioPersona.obtenerPersonaPorCi(txfCarnet.getText())
                        , examinador, "Practico");
                
                ExamenMedico examenMedico = ValidacionCrearExamenPractico.validarCrearExamenPractico(txfNombre.getText(),
                        txfCarnet.getText(), examinador);   
                
                if(examen.getFecha().before(examenMedico.getFecha()))
                        throw new Exception("No puede realizar el examen practico antes del medico");
                
                if(examen.getFecha().before(ValidacionCrearExamenPractico.revisarExamenTeoricoValido(
                        ServiciosExamenesConduccion.obtenerExamenesTeoricosPorCI(
                                txfCarnet.getText())).getFecha()))
                    throw new Exception("No puede realizar el examen practico antes del teorico");
                    
                
                
                if(rbtAprobado.isSelected())
                {
                    Window ventanaActual = rbtTeorico.getScene().getWindow();
                    
                    //Cargar Menu Crear Licencia
                    GestorEscenas.cargarRegistrarLicencia(ventanaActual,
                            (Stage) rbtMedico.getScene().getWindow(),
                            ValidacionCrearExamenPractico.revisarExamenMedicoValido(
                                    ServiciosExamenesMedicos.obtenerExamenesMedicoPorCI(txfCarnet.getText())),
                             examen
                    );
                }  
                else
                    ServiciosExamenesConduccion.crearExamenPractico(examen);
            }

        } catch (Exception ex) {
            GestorEscenas.cargarError(btnRegistrar.getScene().getWindow(), ex);
        }
         
    }

    private ArrayList<String> obtenerRestricciones() {
        ArrayList<String> restricciones = new ArrayList<>();

        try {
            // Obtener el contenido del ScrollPane
            Node content = scrpRestricciones.getContent();

            if (content instanceof AnchorPane) {
                AnchorPane anchorPane = (AnchorPane) content;

                // Buscar recursivamente en todos los nodos
                for (Node node : anchorPane.getChildren()) {
                    if (node instanceof VBox) {
                        VBox vbox = (VBox) node;

                        for (Node child : vbox.getChildren()) {
                            if (child instanceof JFXRadioButton) {
                                JFXRadioButton rb = (JFXRadioButton) child;
                                if (rb.isSelected()) {
                                    System.out.println("RadioButton seleccionado: " + rb.getText());
                                    restricciones.add(rb.getText().trim());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener restricciones: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Total restricciones encontradas: " + restricciones.size());
        return restricciones;
}
    
    
    public static Map<String, String> separarNombreApellidos(String nombreCompleto) {
    nombreCompleto = nombreCompleto.trim().replaceAll("\\s+", " ");
    String[] partes = nombreCompleto.split(" ");
        Map<String, String> resultado = new HashMap<>();

        if (partes.length == 0) {
            resultado.put("nombre", "");
            resultado.put("apellidos", "");
        } else if (partes.length <= 2) {
            resultado.put("nombre", partes[0]);
            resultado.put("apellidos", partes.length > 1 ? partes[1] : "");
        } else {
            // Para 3+ palabras
            int splitPoint = partes.length - 2; // Separa las últimas 2 palabras como apellidos


            String nombre = String.join(" ", Arrays.copyOfRange(partes, 0, splitPoint));
            String apellidos = String.join(" ", Arrays.copyOfRange(partes, splitPoint, partes.length));

            resultado.put("nombre", nombre);
            resultado.put("apellidos", apellidos);
        }

        return resultado;
    }
}
