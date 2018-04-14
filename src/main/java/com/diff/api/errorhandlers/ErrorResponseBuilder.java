package com.diff.api.errorhandlers;

import javax.ws.rs.core.Response;

public class ErrorResponseBuilder {

    public static Response buildErrorResponseWithMessage(Response.Status status, String message){
        return Response.status(status)
                .entity(new ErrorMessage(status.getStatusCode(), message))
                .build();
    }
}
