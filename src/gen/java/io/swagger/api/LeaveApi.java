package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.LeaveApiService;
import io.swagger.api.factories.LeaveApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;

import io.swagger.model.ErrorCode;
import io.swagger.model.ErrorMessage;
import io.swagger.model.Leave;
import io.swagger.model.LeaveStatus;
import io.swagger.model.Success;
import io.swagger.model.Login;

import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/leave")


@io.swagger.annotations.Api(description = "the leave API")

public class LeaveApi  {
   private final LeaveApiService delegate;

   public LeaveApi(@Context ServletConfig servletContext) {
      LeaveApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("LeaveApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (LeaveApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = LeaveApiServiceFactory.getLeaveApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Enables user to apply for leave", notes = "", response = Success.class, tags={ "leave", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Success.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid details provided", response = ErrorMessage.class) })
    public Response applyLeave(@ApiParam(value = "Contains leave related details which are to be saved" ,required=true) Leave body
,@Context SecurityContext securityContext)
    throws NotFoundException, SQLException, ParseException {
        return delegate.applyLeave(body,securityContext);
    }
    @PUT
    @Path("/updateLeaveStatus")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update the leave status for an applicant", notes = "", response = Success.class, tags={ "leave", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Succefully updated leave status", response = Success.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "leaveId does not exist", response = ErrorMessage.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error", response = ErrorMessage.class) })
    public Response updateLeaveStatus(@ApiParam(value = "id of the user whose status needs to be updated",required=true) @QueryParam("leaveId") BigDecimal leaveId
,@ApiParam(value = "Status of the corresponding associate's leave",required=true) @QueryParam("status") BigDecimal status
,@ApiParam(value = "Manager's response to the corresponding leave",required=true) @QueryParam("response") String response
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateLeaveStatus(leaveId,status,response,securityContext);
    }
    @GET
    @Path("/viewLeaveStatus")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "View the leave applicants", notes = "", response = LeaveStatus.class, tags={ "leave", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = LeaveStatus.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Something went wrong", response = ErrorMessage.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized Client Error", response = ErrorCode.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = ErrorMessage.class) })
    public Response viewLeaveStatus(@ApiParam(value = "Current userId for filtering",required=true) @QueryParam("userId") String userId
,@Context SecurityContext securityContext)
    throws NotFoundException, SQLException {
        return delegate.viewLeaveStatus(userId,securityContext);
    }
}
