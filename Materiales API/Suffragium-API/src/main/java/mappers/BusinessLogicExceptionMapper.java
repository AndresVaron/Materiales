package mappers;

import exceptions.BusinessLogicException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Convertidor de Excepciones BusinessLogicException a mensajes REST.
 *
 * El error se transforma bajo el siguiente modelo:
 * Codigo de respuesta: <code style="color: #c7254e; background-color: #f9f2f4;">412 Precodition Failed</code>
 * Respuesta: La razon del error
 * 
 *
 * 
 */
@Provider
public class BusinessLogicExceptionMapper implements ExceptionMapper<BusinessLogicException> {

    /**
     * Generador de una respuesta a partir de una excepción
     *
     * @param exception excepión a convertir a una respuesta REST
     * @return La respuesta HTTP con el error.
     */
    @Override
    public Response toResponse(BusinessLogicException exception) {
        // retorna una respuesta
        return Response.status(Response.Status.PRECONDITION_FAILED)
                .entity(getInitCause(exception).getLocalizedMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build(); //To change body of generated methods, choose Tools | Templates.
    }

    private Throwable getInitCause(Throwable e) {
        if (e.getCause() != null) {
            return getInitCause(e.getCause());
        } else {
            return e;
        }
    }

}
