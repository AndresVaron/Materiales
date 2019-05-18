/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dtos.PingDTO;
import entities.PingEntity;
import exceptions.BusinessLogicException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import logic.PingLogic;

/**
 * Clase que implementa el recurso "authors/{id}/books".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("celular")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ActualizacionCelularResource {

    private static final Logger LOGGER = Logger.getLogger(ActualizacionCelularResource.class.getName());

    @Inject
    private PingLogic pingLogic;

    @POST
    public PingDTO createSBook(PingDTO ping) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CelularResource createPing: input: {0}", ping);
        PingEntity ping3 = ping.toEntity();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy-HH:mm");
        Date date = new Date();
        ping3.setHora(dateFormat.format(date));
        PingDTO ping2 = new PingDTO(pingLogic.registrarPing(ping3));
        LOGGER.log(Level.INFO, "BookResource createPing: output: {0}", ping2);
        return ping2;
    }
}
