package uv.mx.t4is.AgendaBd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import  https.t4is_uv_mx.agendabd.BuscarAgendaResponse.Tarea;

import https.t4is_uv_mx.agendabd.*;



@Endpoint
public class AgendaEndPoint {

    @Autowired
    private Itarea itarea; 
    
    @PayloadRoot(namespace = "https://t4is.uv.mx/AgendaBd", localPart = "AgregarRequest")
    @ResponsePayload
    public AgregarResponse saludar(@RequestPayload AgregarRequest nombre){
        AgregarResponse respuesta = new AgregarResponse();
        respuesta.setRespuesta("Tarea: " + nombre.getNombre() + " agregada");
        Tareas t = new Tareas();
        t.setNombre(nombre.getNombre());
        t.setDescripcion(nombre.getDescripcion());
        t.setFecha(nombre.getFechaDdMmAa());
        itarea.save(t);
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/AgendaBd", localPart = "BuscarAgendaRequest")
    @ResponsePayload
    public BuscarAgendaResponse buscar(){
        BuscarAgendaResponse buscarAgendaResponse = new BuscarAgendaResponse();
        List<Tareas> listtareas = (List<Tareas>) itarea.findAll();
        Tarea saludo = new Tarea();
        for(int i=0;i<listtareas.size();i++){
            saludo = new Tarea();
            saludo.setId(listtareas.get(i).getId());
            saludo.setNombre(listtareas.get(i).getNombre());
            buscarAgendaResponse.getTarea().add(saludo);
        }
        return buscarAgendaResponse;
    }


    @PayloadRoot(namespace = "https://t4is.uv.mx/AgendaBd", localPart="ModificarAgendaRequest")
    @ResponsePayload
    public ModificarTareaResponse modificar(@RequestPayload ModificarTareaRequest peticion){
        ModificarTareaResponse respuesta = new ModificarTareaResponse();
        Tareas e = new Tareas();

        e.setNombre(peticion.getNombre());
        e.setId(peticion.getId());
        e.setDescripcion(peticion.getDescripcion());
        e.setFecha(peticion.getFechaDdMmAa());
        itarea.save(e);
        respuesta.setRespuesta(true);
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/AgendaBd", localPart="BorrarTareaRequest")
    @ResponsePayload
    public BorrarTareaResponse modificar(@RequestPayload BorrarTareaRequest peticion){
        BorrarTareaResponse respuesta = new BorrarTareaResponse();
        itarea.deleteById(peticion.getId());
        respuesta.setRespuesta(true);
        return respuesta;
    }

}