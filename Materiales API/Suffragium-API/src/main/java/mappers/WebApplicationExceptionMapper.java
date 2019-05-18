package mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Convertidor de Excepciones WebApplicationExceptionMapper a mensajes REST.
 *
 * El error se transforma bajo el siguiente modelo: Codigo de respuesta:
 * <code style="color: #c7254e; background-color: #f9f2f4;">Codigo de exepcion
 * de javax</code> Respuesta: La razon del error
 *
 *
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    /**
     * Generador de una respuesta a partir de una excepción
     * WebApplicationException
     *
     * @param exception excepión a convertir a una respuesta REST
     * @return La respuesta HTTP con el error.
     */
    @Override
    public Response toResponse(WebApplicationException exception) {

        return Response.status(exception.getResponse().getStatus())// Se recibe el status
                .entity(getInitCause(exception).getLocalizedMessage())// Se envía la causa
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();

    }

    private Throwable getInitCause(Throwable e) {
        if (e.getCause() != null) {
            return getInitCause(e.getCause());
        } else {
            return e;
        }

    }
}
