package ru.tecon.integrationEISUOT.util.provider;

import com.google.gson.JsonParseException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Rest провайдер для обработки ошибик разбора json
 * @author Maksim Shchelkonogov
 */
@Provider
public final class GsonParseExceptionHandler implements ExceptionMapper<JsonParseException> {

    @Override
    public Response toResponse(JsonParseException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
    }
}
