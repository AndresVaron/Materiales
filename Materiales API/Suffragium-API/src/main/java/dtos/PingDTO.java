/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import adapters.DateAdapter;
import entities.PingEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Equipo Suffragium
 */
public class PingDTO implements Serializable {

    private String hora;
    private String latitud;
    private String longitud;
    private Integer tipo;

    public PingDTO() {

    }

    public PingDTO(String hora, String lat, String longitu, Integer tipo) {
        this.hora = hora;
        this.longitud = longitu;
        this.latitud = lat;
        this.tipo = tipo;
    }

    public PingDTO(PingEntity registrarPing) {
        if (registrarPing != null) {
            this.hora = registrarPing.getHora();
            this.latitud = registrarPing.getLatitud();
            this.longitud = registrarPing.getLongitud();
            this.tipo = registrarPing.getTipo();
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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

    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del del premio.
     */
    public PingEntity toEntity() {
        PingEntity prizeEntity = new PingEntity();
        prizeEntity.setLatitud(this.latitud);
        prizeEntity.setLongitud(this.longitud);
        prizeEntity.setTipo(this.tipo);
        prizeEntity.setHora(this.hora);
        return prizeEntity;
    }

}
