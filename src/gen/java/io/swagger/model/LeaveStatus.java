/*
 * Vacation Tracker
 * It is a service to handle Read,Write and Modify leave requests and track requests
 *
 * OpenAPI spec version: 1.0.0
 * Contact: p.avinash@cerner.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.*;

/**
 * LeaveStatus
 */

public class LeaveStatus   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("leaveId")
  private Integer leaveId =  null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("startDate")
  private Date startDate = null;

  @JsonProperty("endDate")
  private Date endDate = null;

  @JsonProperty("reason")
  private String reason = null;

  @JsonProperty("leaveStatus")
  private Integer leaveStatus =  null;

  public LeaveStatus name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @JsonProperty("name")
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LeaveStatus leaveId(int leaveId) {
    this.leaveId = leaveId;
    return this;
  }

  /**
   * Get leaveId
   * @return leaveId
   **/
  @JsonProperty("leaveId")
  @ApiModelProperty(value = "")
  public int getLeaveId() {
    return leaveId;
  }

  public void setLeaveId(int i) {
    this.leaveId = i;
  }

  public LeaveStatus userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
   **/
  @JsonProperty("userId")
  @ApiModelProperty(value = "")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public LeaveStatus startDate(Date startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
   **/
  @JsonProperty("startDate")
  @ApiModelProperty(value = "")
  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public LeaveStatus endDate(Date endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
   **/
  @JsonProperty("endDate")
  @ApiModelProperty(value = "")
  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public LeaveStatus reason(String reason) {
    this.reason = reason;
    return this;
  }

  /**
   * Get reason
   * @return reason
   **/
  @JsonProperty("reason")
  @ApiModelProperty(value = "")
  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public LeaveStatus status(int leaveStatus) {
    this.leaveStatus = leaveStatus;
    return this;
  }

  /**
   * Get status
   * @return status
   **/
  @JsonProperty("leaveStatus")
  @ApiModelProperty(value = "")
  public int getStatus() {
    return leaveStatus;
  }

  public void setStatus(int leaveStatus) {
    this.leaveStatus = leaveStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaveStatus leaveStatus = (LeaveStatus) o;
    return Objects.equals(this.name, leaveStatus.name) &&
        Objects.equals(this.leaveId, leaveStatus.leaveId) &&
        Objects.equals(this.userId, leaveStatus.userId) &&
        Objects.equals(this.startDate, leaveStatus.startDate) &&
        Objects.equals(this.endDate, leaveStatus.endDate) &&
        Objects.equals(this.reason, leaveStatus.reason) &&
        Objects.equals(this.leaveStatus, leaveStatus.leaveStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, leaveId, userId, startDate, endDate, reason, leaveStatus);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaveStatus {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    leaveId: ").append(toIndentedString(leaveId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    leaveStatus: ").append(toIndentedString(leaveStatus)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
