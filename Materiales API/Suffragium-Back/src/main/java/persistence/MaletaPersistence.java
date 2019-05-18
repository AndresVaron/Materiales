/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package persistence;

import entities.MaletaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Maleta. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author ISIS2603
 */
@Stateless
public class MaletaPersistence {

    private static final Logger LOGGER = Logger.getLogger(MaletaPersistence.class.getName());

    @PersistenceContext(unitName = "BookStorePU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param maletaEntity objeto maleta que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public MaletaEntity create(MaletaEntity maletaEntity) {
        LOGGER.log(Level.INFO, "Creando una maleta nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la maleta en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(maletaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una maleta nueva");
        return maletaEntity;
    }
	
	/**
     * Devuelve todas las maletaes de la base de datos.
     *
     * @return una lista con todas las maletaes que encuentre en la base de
     * datos, "select u from MaletaEntity u" es como un "select * from
     * MaletaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<MaletaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las maletaes");
        // Se crea un query para buscar todas las maletaes en la base de datos.
        TypedQuery query = em.createQuery("select u from MaletaEntity u", MaletaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de maletaes.
        return query.getResultList();
    }
	
    /**
     * Busca si hay alguna maleta con el id que se envía de argumento
     *
     * @param maletasId: id correspondiente a la maleta buscada.
     * @return una maleta.
     */
    public MaletaEntity find(Long maletasId) {
        LOGGER.log(Level.INFO, "Consultando maleta con id={0}", maletasId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from MaletaEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(MaletaEntity.class, maletasId);
    }

	 /**
     * Actualiza una maleta.
     *
     * @param maletaEntity: la maleta que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una maleta con los cambios aplicados.
     */
    public MaletaEntity update(MaletaEntity maletaEntity) {
        LOGGER.log(Level.INFO, "Actualizando maleta con id = {0}", maletaEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la maleta con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la maleta con id = {0}", maletaEntity.getId());
        return em.merge(maletaEntity);
    }
	
    /**
     *
     * Borra una maleta de la base de datos recibiendo como argumento el id
     * de la maleta
     *
     * @param maletasId: id correspondiente a la maleta a borrar.
     */
    public void delete(Long maletasId) {
        LOGGER.log(Level.INFO, "Borrando maleta con id = {0}", maletasId);
        // Se hace uso de mismo método que esta explicado en public MaletaEntity find(Long id) para obtener la maleta a borrar.
        MaletaEntity entity = em.find(MaletaEntity.class, maletasId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from MaletaEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la maleta con id = {0}", maletasId);
    }
}
