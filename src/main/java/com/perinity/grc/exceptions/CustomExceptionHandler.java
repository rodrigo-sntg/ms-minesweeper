package com.perinity.grc.exceptions;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<CustomException> {

    @ConfigProperty(name = "knowledgefactory.custom.error.msg.gameNotFound")
    String gameNotFound;

    @Override
    public Response toResponse(CustomException e) {

        if (e.getMessage().equalsIgnoreCase(gameNotFound)) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new ErrorMessage(e.getMessage(), false)).build();
        } else {

            return Response.status(Response.Status.BAD_REQUEST).
                    entity(new ErrorMessage(e.getMessage(), false)).build();
        }
    }
}