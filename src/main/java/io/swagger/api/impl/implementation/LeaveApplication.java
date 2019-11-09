package io.swagger.api.impl.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import io.swagger.model.Leave;
import io.swagger.model.LeaveStatus;
import io.swagger.model.Login;
import io.swagger.model.Success;


public class LeaveApplication  {
	public Timestamp getSystemDateTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}
public boolean checkDateRange(Leave userData) throws SQLException {
		Connection con = null;
		ResultSet res = null;
		PreparedStatement pst = null;
		FetchQueries queryFetcher1 = new FetchQueries();
		String sqldate = queryFetcher1.fetchQuery("validateDateRange");
		try {
		con = DBUtil.getConnection();
		pst = con.prepareStatement(sqldate);
		pst.setDate(1, new Date(userData.getStartDate().getTime()));
		pst.setDate(2, new Date (userData.getEndDate().getTime()));
		pst.setString(3,userData.getUserId());
		res = pst.executeQuery();
		while(res.next()) {
			return true;
		}
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		finally {
//			res.close();
			pst.close();
			con.close();  
			
		}
		return false;
	}
	public Success applyLeave(Leave userData) throws SQLException, ParseException {
		Success success = new Success();
		Connection con = null;
		FetchQueries queryFetcher2 = new FetchQueries();
		String sql = queryFetcher2.fetchQuery("insertLeaveInformation");
		con = DBUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		System.out.println(userData.getEndDate());
		try {
			System.out.println("connection established");
			if(checkDateRange(userData) == true) {
				System.out.println("select different start and end dates");
				success.setSuccess(false);
			}
			else {
			pstmt.setString(1,userData.getUserId());
			pstmt.setDate(2, new Date(userData.getStartDate().getTime()));
			pstmt.setDate(3, new Date(userData.getEndDate().getTime()));
			pstmt.setString(4, userData.getReason());
			pstmt.setTimestamp(5,getSystemDateTime());
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("inserted successfully");
				success.setSuccess(true);
			}else {
				success.setSuccess(false);
			}
		}
	} catch (SQLException e) {
			
			System.err.println(e.getMessage());
		}
		finally {
			pstmt.close();
			con.close();
		}
		return success;
		
	}
	public ArrayList<Object> getUserId(String userId) {
		Login loginData = new Login();
		loginData.setUsername("AP062035");
		loginData.setPassword("React2018@");
		LdapContext ctx= LdapUtil.getLdapContext(loginData);
		ArrayList<Object> userIds = null;
        try {
        	SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String[] attrIDs = { "distinguishedName",
                    "manager",
                    "directReports",
                    "sAMAccountName"};
            constraints.setReturningAttributes(attrIDs);
            NamingEnumeration answer = ctx.search("OU=Users,OU=Bangalore,OU=Office Locations,dc=northamerica,dc=cerner,dc=net", "sAMAccountName="
                    + "SK026590", constraints);
            if (answer.hasMore()) {
                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
                Attribute attr = attrs.get("directReports");
                ArrayList<Object> list = new ArrayList<>();
                userIds = new ArrayList<>();
                for(int i=0;i<attr.size();i++) {
                	list.add(attr.get(i));
                }
                for(int i=0;i<list.size();i++) {
                	NamingEnumeration answer1 = ctx.search("OU=Users,OU=Bangalore,OU=Office Locations,dc=northamerica,dc=cerner,dc=net", "distinguishedName="+list.get(i).toString().replaceFirst("\\\\","\\\\5C") , constraints);
                	if (answer1.hasMore()) {
                    	Attributes attrs1 = ((SearchResult) answer1.next()).getAttributes();
                    	userIds.add(attrs1.get("sAMAccountName").get());
                    	//System.out.println(attrs1.get("sAMAccountName").toString());
                    }
                }
            }
            else{
                throw new Exception("Invalid User");
            }
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userIds;
    }
	
	public ArrayList<LeaveStatus> getLeaveRecords(ArrayList<Object> value) throws SQLException{
		ArrayList<LeaveStatus> leaveApplicants = new ArrayList<LeaveStatus>();
		Connection con = null;
		ResultSet rs = null;
		FetchQueries queryFetcher3 = new FetchQueries();
		String sql = queryFetcher3.fetchQuery("viewLeaveDetails");
		con = DBUtil.getConnection();
		try {
			for(Object associateID: value) {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, (String) associateID);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					LeaveStatus obj = new LeaveStatus();
					obj.setLeaveId(rs.getInt(1));
					obj.setUserId(rs.getString(2));
					obj.setStartDate(rs.getDate(3));
					obj.setEndDate(rs.getDate(4));
					obj.setReason(rs.getString(5));
					obj.setStatus(rs.getInt(6));
					leaveApplicants.add(obj);
				}
				pstmt.close();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			rs.close();
			con.close();
			
		}
		return leaveApplicants;
	}
	

	public List<LeaveStatus> viewLeaveStatus(String userId) throws SQLException {
		ArrayList<Object> list1 = getUserId(userId);
		ArrayList<LeaveStatus> listOfLeaveApplicants = getLeaveRecords(list1);
		return listOfLeaveApplicants;
		}
	
}

