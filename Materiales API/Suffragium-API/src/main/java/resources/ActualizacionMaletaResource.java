/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dtos.MaletaDTO;
import entities.MaletaEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import logic.MaletaLogic;

/**
 *
 * @author Equipo Suffragium
 */
@Path("maleta")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ActualizacionMaletaResource {

    private static final Logger LOGGER = Logger.getLogger(ActualizacionMaletaResource.class.getName());

    @Inject
    private MaletaLogic maletaLogic;

    @GET
    @Path("prender")
    public MaletaDTO prender() {
        LOGGER.log(Level.INFO, "ActualizacionMaletaResource actualizarDireccion()");
        maletaLogic.toggle(new Long(1), true);
        MaletaDTO detailDTO = new MaletaDTO(maletaLogic.getMaleta(new Long(1)).getPrendido());
        LOGGER.log(Level.INFO, "EditorialResource getEditorial: output: {0}", detailDTO);
        return detailDTO;
    }

    @GET
    @Path("apagar")
    public MaletaDTO apagar() {
        LOGGER.log(Level.INFO, "ActualizacionMaletaResource actualizarDireccion()");
        maletaLogic.toggle(new Long(1), false);
        MaletaDTO detailDTO = new MaletaDTO(maletaLogic.getMaleta(new Long(1)).getPrendido());
        LOGGER.log(Level.INFO, "EditorialResource getEditorial: output: {0}", detailDTO);
        return detailDTO;
    }

    @GET
    @Path("correo")
    public MaletaDTO correo() {
        LOGGER.log(Level.INFO, "ActualizacionMaletaResource actualizarDireccion()");
        MaletaEntity maleta = maletaLogic.getMaleta(new Long(1));
        maleta.setEnviado(false);
        maletaLogic.updateMaleta(new Long(1), maleta);
        LOGGER.log(Level.INFO, "EditorialResource getEditorial: output: {0}", maleta);
        return new MaletaDTO(maleta.getPrendido());
    }

    @GET
    @Path("prendido")
    public MaletaDTO getPrendido() {
        LOGGER.log(Level.INFO, "ActualizacionMaletaResource actualizarDireccion()");
        MaletaDTO detailDTO = new MaletaDTO(maletaLogic.getMaleta(new Long(1)).getPrendido());
        LOGGER.log(Level.INFO, "EditorialResource getEditorial: output: {0}", detailDTO);
        return detailDTO;

    }
}
