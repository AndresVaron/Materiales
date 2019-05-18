/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.MaletaEntity;
import exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import persistence.MaletaPersistence;

/**
 *
 * @author Equipo Suffragium
 */
@Stateless
public class MaletaLogic {

    private static final Logger LOGGER = Logger.getLogger(MaletaLogic.class.getName());

    @Inject
    private MaletaPersistence persistence;

    /**
     * Crea un maleta en la persistencia.
     *
     * @param maletaEntity La entidad que representa el maleta a persistir.
     * @return La entidad de el maleta luego de persistirla.
     * @throws exceptions.BusinessLogicException si ya existe un maleta con el
     * mismo id
     */
    public MaletaEntity registrarMaleta(MaletaEntity maletaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de el maleta");
        persistence.create(maletaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de el maleta");
        return maletaEntity;
    }

    /**
     *
     * Obtener todas los maletas existentes en la base de datos.
     *
     * @return una lista de maletas.
     */
    public List<MaletaEntity> getMaletas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los maletas");
        List<MaletaEntity> maletas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas los maletas");
        return maletas;
    }

    /**
     *
     * Obtener un maleta por medio de su id.
     *
     * @param maletasId: id de el maleta para ser buscada.
     * @return el maleta solicitado por medio de su id.
     */
    public MaletaEntity getMaleta(Long maletasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el maleta con el id = {0}", maletasId);
        MaletaEntity maletaEntity = persistence.find(maletasId);
        if (maletaEntity == null) {
            LOGGER.log(Level.SEVERE, "El maleta con el id = {0} no existe", maletasId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el maleta con el id = {0}", maletasId);
        return maletaEntity;
    }

    /**
     *
     * Actualizar un maleta.
     *
     * @param maletasId: id de el maleta para buscarla en la base de datos.
     * @param maletaEntity: maleta con los cambios para ser actualizada, por
     * ejemplo el nombre.
     * @return el maleta con los cambios actualizados en la base de datos.
     */
    public MaletaEntity updateMaleta(Long maletasId, MaletaEntity maletaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el maleta con id = {0}", maletasId);
        MaletaEntity newEntity = persistence.update(maletaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el maleta con id = {0}", maletaEntity.getId());
        return newEntity;
    }

    /**
     *
     * Actualizar un maleta.
     *
     * @param maletasId: id de el maleta para buscarla en la base de datos.
     * @return el maleta con los cambios actualizados en la base de datos.
     */
    public MaletaEntity toggle(Long maletasId, boolean prendido) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el maleta con id = {0}", maletasId);

        MaletaEntity newEntity = getMaleta(maletasId);
        newEntity.setPrendido(prendido);
        persistence.update(newEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el maleta con id = {0}", newEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un maleta
     *
     * @param maletasId: id de el maleta a borrar
     */
    public void deleteMaleta(Long maletasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el maleta con id= {0}", maletasId);
        persistence.delete(maletasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el maleta con id = {0}", maletasId);
    }
}
