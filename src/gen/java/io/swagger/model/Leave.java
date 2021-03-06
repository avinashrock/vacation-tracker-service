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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Leave
 */

public class Leave   {
  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("startDate")
  private Date startDate = null;

  @JsonProperty("endDate")
  private Date endDate = null;

  @JsonProperty("reason")
  private String reason = null;

  private Date convertDate(String dateStr) {
	Date date = null;
	try {
		date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return date;
  }
  
  public Leave userId(String userId) {
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

  public Leave startDate(String startDate) {
    this.startDate = convertDate(startDate);
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

  public void setStartDate(String startDate) {
    this.startDate = convertDate(startDate);
  }

  public Leave endDate(String endDate) {
    this.endDate = convertDate(endDate);
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

  public void setEndDate(String endDate) {
    this.endDate = convertDate(endDate);
  }

  public Leave reason(String reason) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Leave leave = (Leave) o;
    return Objects.equals(this.userId, leave.userId) &&
        Objects.equals(this.startDate, leave.startDate) &&
        Objects.equals(this.endDate, leave.endDate) &&
        Objects.equals(this.reason, leave.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, startDate, endDate, reason);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Leave {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
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

