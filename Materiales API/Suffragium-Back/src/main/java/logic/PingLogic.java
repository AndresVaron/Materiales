package logic;

import entities.PingEntity;
import exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import persistence.PingPersistence;

/**
 *
 * @author Equipo Suffragium
 */
@Stateless
public class PingLogic {

    private static final Logger LOGGER = Logger.getLogger(PingLogic.class.getName());

    @Inject
    private PingPersistence persistence;

    /**
     * Crea un ping en la persistencia.
     *
     * @param pingEntity La entidad que representa el ping a persistir.
     * @return La entidad de el ping luego de persistirla.
     * @throws exceptions.BusinessLogicException si ya existe un ping con el
     * mismo id
     */
    public PingEntity registrarPing(PingEntity pingEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de el ping");
        persistence.create(pingEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de el ping");
        return pingEntity;
    }

    /**
     *
     * Obtener todas los pings existentes en la base de datos.
     *
     * @return una lista de pings.
     */
    public List<PingEntity> getPings() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los pings");
        List<PingEntity> pings = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas los pings");
        return pings;
    }

    /**
     *
     * Obtener un ping por medio de su id.
     *
     * @param pingsId: id de el ping para ser buscada.
     * @return el ping solicitado por medio de su id.
     */
    public PingEntity getPing(Long pingsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el ping con el id = {0}", pingsId);
        PingEntity pingEntity = persistence.find(pingsId);
        if (pingEntity == null) {
            LOGGER.log(Level.SEVERE, "El ping con el id = {0} no existe", pingsId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el ping con el id = {0}", pingsId);
        return pingEntity;
    }

    /**
     *
     * Obtener un ping por medio de su id.
     *
     * @param maleta id de la maleta
     * @return el ping solicitado por medio de su id.
     */
    public PingEntity getLast(long maleta) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el ping");
        PingEntity pingEntity = persistence.findLast(maleta);
        if (pingEntity == null) {
            LOGGER.log(Level.SEVERE, "la maleta con el id = {0} no tiene pings", maleta);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el ultimo ping de la maleta con el id = {0}", maleta);
        return pingEntity;
    }

    /**
     *
     * Actualizar un ping.
     *
     * @param pingsId: id de el ping para buscarla en la base de datos.
     * @param pingEntity: ping con los cambios para ser actualizada, por ejemplo
     * el nombre.
     * @return el ping con los cambios actualizados en la base de datos.
     */
    public PingEntity updatePing(Long pingsId, PingEntity pingEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el ping con id = {0}", pingsId);
        PingEntity newEntity = persistence.update(pingEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el ping con id = {0}", pingEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un ping
     *
     * @param pingsId: id de el ping a borrar
     */
    public void deletePing(Long pingsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el ping con id= {0}", pingsId);
        persistence.delete(pingsId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el ping con id = {0}", pingsId);
    }
}
