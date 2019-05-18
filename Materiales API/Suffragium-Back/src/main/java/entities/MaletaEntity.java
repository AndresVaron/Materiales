/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author af.varon
 */
@Entity
public class MaletaEntity extends BaseEntity implements Serializable {

    private boolean prendido;

    private boolean enviado;

    @OneToMany(mappedBy = "maleta")
    private List<PingEntity> pings = new ArrayList<PingEntity>();

    /**
     * @return the prendido
     */
    public boolean getPrendido() {
        return isPrendido();
    }

    /**
     * @param prendido the prendido of the maleta
     */
    public void setPrendido(boolean prendido) {
        this.prendido = prendido;
    }

    /**
     * Devuelve los libros de la editorial.
     *
     * @return Lista de entidades de Libro.
     */
    public List<PingEntity> getPings() {
        return pings;
    }

    /**
     * Modifica los libros de la editorial.
     *
     * @param pings Los nuevos libros.
     */
    public void setPings(List<PingEntity> pings) {
        this.pings = pings;
    }

    /**
     * @return the prendido
     */
    public boolean isPrendido() {
        return prendido;
    }

    /**
     * @return the enviado
     */
    public boolean isEnviado() {
        return enviado;
    }

    /**
     * @param enviado the enviado to set
     */
    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }
}
