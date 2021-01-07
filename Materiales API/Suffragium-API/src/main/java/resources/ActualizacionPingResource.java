/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dtos.PingDTO;
import entities.*;
import exceptions.BusinessLogicException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import logic.PingLogic;
import logic.MaletaLogic;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Clase que implementa el recurso "authors/{id}/books".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("ping")
@Produces("application/json")
@Consumes(MediaType.TEXT_PLAIN)
@RequestScoped
public class ActualizacionPingResource {

    private static final Logger LOGGER = Logger.getLogger(ActualizacionPingResource.class.getName());

    @Inject
    private MaletaLogic maletaLogic;

    @Inject
    private PingLogic pingLogic;

    @GET
    @Path("ultimos")
    public List<PingDTO> getUltimos() {
        LOGGER.log(Level.INFO, "ActualizacionMaletaResource actualizarDireccion()");
        PingEntity ping = pingLogic.getLast(new Long(1));
        List<PingDTO> list = new ArrayList<>();
        LOGGER.log(Level.INFO, "ActualizacionMaletaResource actualizarDireccion()1");
        PingDTO detailDTO = new PingDTO();
        if (ping != null) {
            detailDTO.setHora(ping.getHora());
            detailDTO.setLatitud(ping.getLatitud());
            detailDTO.setLongitud(ping.getLongitud());
            detailDTO.setTipo(ping.getTipo());
            list.add(detailDTO);
        }
        double lat = Double.parseDouble(detailDTO.getLatitud());
        double lon = Double.parseDouble(detailDTO.getLongitud());
        ping = pingLogic.getLast(new Long(0));
        detailDTO = new PingDTO();
        if (ping != null) {
            detailDTO.setHora(ping.getHora());
            detailDTO.setLatitud(ping.getLatitud());
            detailDTO.setLongitud(ping.getLongitud());
            detailDTO.setTipo(ping.getTipo());
            list.add(detailDTO);
        }
        double distance = distance(lat, lon, Double.parseDouble(detailDTO.getLatitud()), Double.parseDouble(detailDTO.getLongitud()));
        MaletaEntity maleta =maletaLogic.getMaleta(new Long(1));
        if (distance > 20&&!maleta.isEnviado()) {
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS

            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ovelhodacaixa@gmail.com"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse("af.varon@uniandes.edu.co,pipevaronmaya@gmail.com")
                );
                message.setSubject("Alerta Maleta!");
                message.setText("Dear Mail Crawler,"
                        + "\n\n Please do not spam my email!");

                Transport.send(message);
                maleta.setEnviado(true);
                maletaLogic.updateMaleta(new Long(0), maleta);
                System.out.println("Done");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        LOGGER.log(Level.INFO, "EditorialResource getEditorial: output: {0}", detailDTO);
        LOGGER.log(Level.INFO, "Distancia! {0}", distance);
        return list;

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public boolean actualizar(@QueryParam("lat") String lat,
            @QueryParam("lon") String lon) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Datos lat: {0}", lat);
        LOGGER.log(Level.INFO, "Datos lon: {0}", lon);
        PingEntity ping3 = new PingEntity();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy-HH:mm");
        Date date = new Date();
        ping3.setHora(dateFormat.format(date));
        ping3.setTipo(2);
        lat = lat.substring(0, lat.length() - 6) + "." + lat.substring(lat.length() - 6, lat.length());
        lon = lon.substring(0, lon.length() - 6) + "." + lon.substring(lon.length() - 6, lon.length());
        ping3.setLatitud(lat);
        ping3.setLongitud(lon);

        MaletaEntity maleta = maletaLogic.getMaleta(new Long(1));
        ping3.setMaleta(maleta);
        PingDTO ping2 = new PingDTO(pingLogic.registrarPing(ping3));
        LOGGER.log(Level.INFO, "PingMaleta createPing: output: {0}", ping2);
        return true;
    }

    public static double distance(double lat1, double lng1,
            double lat2, double lng2) {
        double a = (lat1 - lat2) * distPerLat(lat1);
        double b = (lng1 - lng2) * distPerLng(lat1);
        return Math.sqrt(a * a + b * b);
    }

    private static double distPerLng(double lat) {
        return 0.0003121092 * Math.pow(lat, 4)
                + 0.0101182384 * Math.pow(lat, 3)
                - 17.2385140059 * lat * lat
                + 5.5485277537 * lat + 111301.967182595;
    }

    private static double distPerLat(double lat) {
        return -0.000000487305676 * Math.pow(lat, 4)
                - 0.0033668574 * Math.pow(lat, 3)
                + 0.4601181791 * lat * lat
                - 1.4558127346 * lat + 110579.25662316;
    }
}
