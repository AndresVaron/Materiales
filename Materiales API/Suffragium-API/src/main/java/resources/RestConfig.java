package resources;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

/**
 * Clase que indica que este proyecto web ofrece servicios REST. Adicionalmente,
 * esta clase define el prefijo por defecto de las rutas a los recursos.
 *
 * (non-Javadoc)
 *
 * @see javax.ws.rs.core.Application
 * @author Equipo Suffragium
 */
@ApplicationPath("/api")
public class RestConfig extends Application {
}
