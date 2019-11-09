package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;
import io.swagger.model.ErrorCode;
import io.swagger.model.ErrorMessage;
import io.swagger.model.Login;
import io.swagger.model.ResponseForLogin;
import io.swagger.api.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
import io.swagger.api.impl.implementation.LoginAuthentication;


public class LoginApiServiceImpl extends LoginApiService {
	@Override
	public Response loginUser(Login body, SecurityContext securityContext) throws NotFoundException {
		LoginAuthentication loginAuthentication = new LoginAuthentication();
		ResponseForLogin responseForLogin = loginAuthentication.getUserBasicAttributes(body);
		return Response.ok().entity(responseForLogin).build();

	}
}
