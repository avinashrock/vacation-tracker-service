package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

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

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.servlet.ServletException;
import javax.validation.constraints.*;

public abstract class LeaveApiService {
    public abstract Response applyLeave(Leave body,SecurityContext securityContext) throws NotFoundException, SQLException, ParseException;
    public abstract Response updateLeaveStatus( @NotNull BigDecimal leaveId, @NotNull BigDecimal status, @NotNull String response,SecurityContext securityContext) throws NotFoundException;
    public abstract Response viewLeaveStatus( @NotNull String userId,SecurityContext securityContext) throws NotFoundException, SQLException;
}
