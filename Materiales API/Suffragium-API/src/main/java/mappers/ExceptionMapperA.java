package mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Convertidor de Excepciones Exception a mensajes REST.
 *
 * El error se transforma bajo el siguiente modelo:
 * Codigo de respuesta: <code style="color: #c7254e; background-color: #f9f2f4;">401 Unauthorized</code>
 * Respuesta: La razon del error
 * 
 * 
 */
@Provider

public class ExceptionMapperA implements ExceptionMapper<Exception> {

    /**
     * Generador de una respuesta a partir de una excepción
     *
     * @param e excepión a convertir a una respuesta REST
     * @return La respuesta HTTP con el error.
     */
    @Override
    public Response toResponse(Exception e) {

        return Response.status(Response.Status.UNAUTHORIZED.getStatusCode())
                .entity(getInitCause(e).getMessage())
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
