/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Equipo Suffragium
 */
@Entity
public class PingEntity extends BaseEntity implements Serializable {

    private String longitud;

    private String latitud;

    private String hora;
    
    private Integer tipo; //1 Celular //2 Maleta

    @ManyToOne
    private MaletaEntity maleta;

    /**
     * @return the longitud
     */
    public String getLongitud() {
        return longitud;
    }

    
    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the latitud
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * Devuelve la maleta a la que pertenece el libro.
     *
     * @return Una entidad de maleta.
     */
    public MaletaEntity getMaleta() {
        return maleta;
    }

    /**
     * Modifica la maleta a la que pertenece el libro.
     *
     * @param maletaEntity La nueva maleta.
     */
    public void setMaleta(MaletaEntity maletaEntity) {
        this.maleta = maletaEntity;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the tipo
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

}
