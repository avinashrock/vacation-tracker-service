package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;

import io.swagger.model.ErrorCode;
import io.swagger.model.ErrorMessage;
import io.swagger.model.Leave;
import io.swagger.model.LeaveStatus;
import io.swagger.model.Success;
import java.util.List;
import io.swagger.api.NotFoundException;
import io.swagger.api.impl.implementation.LeaveApplication;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public class LeaveApiServiceImpl extends LeaveApiService {
    @Override
    public Response applyLeave(Leave body, SecurityContext securityContext) throws NotFoundException, SQLException, ParseException {
    	LeaveApplication leaveApplication = new LeaveApplication();
        Success success = leaveApplication.applyLeave(body);
        return Response.ok().entity(success).build();
    }
    @Override
    public Response updateLeaveStatus( @NotNull BigDecimal leaveId,  @NotNull BigDecimal status,  @NotNull String response, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response viewLeaveStatus( @NotNull String userId,SecurityContext securityContext) throws NotFoundException, SQLException {
    	LeaveApplication leaveApplication = new LeaveApplication();
    	List<LeaveStatus> status = leaveApplication.viewLeaveStatus(userId);
        return Response.ok().entity(status).build();
    }
	
}
