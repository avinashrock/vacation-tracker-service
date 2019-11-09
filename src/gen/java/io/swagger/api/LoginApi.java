package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.LoginApiService;
import io.swagger.api.factories.LoginApiServiceFactory;
import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;
import io.swagger.model.ErrorCode;
import io.swagger.model.ErrorMessage;
import io.swagger.model.Login;
import io.swagger.model.ResponseForLogin;
import io.swagger.api.NotFoundException;
import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/login")


@io.swagger.annotations.Api(description = "the login API")

public class LoginApi  {
   private final LoginApiService delegate;

   public LoginApi(@Context ServletConfig servletContext) {
      LoginApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("LoginApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (LoginApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = LoginApiServiceFactory.getLoginApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Authenticates the user to utilize the service", notes = "", response = ResponseForLogin.class, tags={ "login", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation.", response = ResponseForLogin.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = ErrorCode.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = ErrorMessage.class) })
    public Response loginUser(@ApiParam(value = "Contains user details to authenticate user" ,required=true) Login body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.loginUser(body,securityContext);
    }
}
