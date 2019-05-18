package persistence;

import entities.PingEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Ping. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author ISIS2603
 */
@Stateless
public class PingPersistence {

    private static final Logger LOGGER = Logger.getLogger(PingPersistence.class.getName());

    @PersistenceContext(unitName = "BookStorePU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param pingEntity objeto libro que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PingEntity create(PingEntity pingEntity) {
        LOGGER.log(Level.INFO, "Creando un libro nuevo");
        em.persist(pingEntity);
        LOGGER.log(Level.INFO, "Libro creado");
        return pingEntity;
    }

    /**
     * Devuelve todos loslibros de la base de datos.
     *
     * @return una lista con todos los libros que encuentre en la base de datos,
     * "select u from PingEntity u" es como un "select * from PingEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<PingEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los libros");
        Query q = em.createQuery("select u from PingEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay algun lubro con el id que se envía de argumento
     *
     * @param pingsId: id correspondiente al libro buscado.
     * @return un libro.
     */
    public PingEntity find(Long pingsId) {
        LOGGER.log(Level.INFO, "Consultando el libro con id={0}", pingsId);
        return em.find(PingEntity.class, pingsId);
    }

    /**
     * Actualiza un libro.
     *
     * @param pingEntity: el libro que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un libro con los cambios aplicados.
     */
    public PingEntity update(PingEntity pingEntity) {
        LOGGER.log(Level.INFO, "Actualizando el libro con id={0}", pingEntity.getId());
        return em.merge(pingEntity);
    }

    /**
     *
     * Borra un libro de la base de datos recibiendo como argumento el id del
     * libro
     *
     * @param pingsId: id correspondiente al libro a borrar.
     */
    public void delete(Long pingsId) {
        LOGGER.log(Level.INFO, "Borrando el libro con id={0}", pingsId);
        PingEntity pingEntity = em.find(PingEntity.class, pingsId);
        em.remove(pingEntity);
    }

    /**
     * Busca si hay alguna maleta con el nombre que se envía de argumento
     *
     * @param maleta i de la maleta
     * @return null si no existe ninguna maleta con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public PingEntity findLast(long maleta) {
        LOGGER.log(Level.INFO, "Consultando ping de la maleta {0} ", maleta);
        PingEntity result = null;
        if (maleta != 0) {
            LOGGER.log(Level.INFO, "Consultando ping de la maleta {0} 1", maleta);
            // Se crea un query para buscar maletaes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
            TypedQuery query = em.createQuery("Select e From PingEntity e where e.tipo = 2 and e.maleta.id = :malet", PingEntity.class);
            // Se remplaza el placeholder ":name" con el valor del argumento 
            // Se invoca el query se obtiene la lista resultado
            query = query.setParameter("malet", maleta);
            List<PingEntity> ping = query.getResultList();
            if (ping == null) {
                result = null;
            } else if (ping.isEmpty()) {
                result = null;
            } else {
                String max = "01/01/00-00:00";
                for (PingEntity res : ping) {
                    if (max.compareTo(res.getHora()) < 0) {
                        result = res;
                        max = res.getHora();
                    }
                }
            }
        } else {
            LOGGER.log(Level.INFO, "Consultando ping de la maleta {0} 2", maleta);
            // Se crea un query para buscar maletaes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
            TypedQuery query = em.createQuery("Select e From PingEntity e where e.tipo = 1", PingEntity.class);
            // Se remplaza el placeholder ":name" con el valor del argumento 
            // Se invoca el query se obtiene la lista resultado
            List<PingEntity> ping = query.getResultList();
            if (ping == null) {
                result = null;
            } else if (ping.isEmpty()) {
                result = null;
            } else {
                String max = "01/01/00-00:00";
                for (PingEntity res : ping) {
                    if (max.compareTo(res.getHora()) < 0) {
                        result = res;
                        max = res.getHora();
                    }
                }
            }
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar maleta por nombre ", maleta);
        return result;
    }
}
