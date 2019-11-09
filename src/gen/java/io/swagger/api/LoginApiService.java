package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.ErrorCode;
import io.swagger.model.ErrorMessage;
import io.swagger.model.Login;
import io.swagger.model.ResponseForLogin;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public abstract class LoginApiService {
    public abstract Response loginUser(Login body,SecurityContext securityContext) throws NotFoundException;
}
