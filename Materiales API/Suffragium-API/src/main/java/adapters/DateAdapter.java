
package adapters;

import resources.ActualizacionMaletaResource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Clase adaptador que formatea las fechas en formato serialiable utilizando la
 * convención: yyyy-MM-dd. Ej: 2018-02-12
 *
 * @author ISIS2603
 */
public class DateAdapter extends XmlAdapter<String, Date> {
    
      private static final Logger LOGGER = Logger.getLogger(ActualizacionMaletaResource.class.getName());

    /**
     * Thread safe {@link DateFormat}.
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT_TL = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    @Override
    public Date unmarshal(String v) throws Exception {
        LOGGER.log(Level.INFO, "input date "+v);
        return DATE_FORMAT_TL.get().parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        if (v == null) {
            return null;
        }
        return DATE_FORMAT_TL.get().format(v);
    }
}
