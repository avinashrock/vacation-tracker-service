package io.swagger.api.impl.implementation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import io.swagger.model.Success;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ LeaveApplication.class })

public class LeaveApplicationTest {
	LeaveApplication leaveApplication;
	DBUtil db;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	@Before
	public void setUp() throws Exception {
		db = PowerMockito.mock(DBUtil.class);
		PowerMockito.whenNew(DBUtil.class).withNoArguments().thenReturn(db);

		con = PowerMockito.mock(Connection.class);
		PowerMockito.when(DBUtil.getConnection()).thenReturn(con);

		pstmt = PowerMockito.mock(PreparedStatement.class);
		PowerMockito.when(con.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
		PowerMockito.when(pstmt.executeUpdate()).thenReturn(1);

		rs = PowerMockito.mock(ResultSet.class);
		PowerMockito.when(pstmt.executeQuery()).thenReturn(rs);
		PowerMockito.when(rs.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);

		PowerMockito.when(rs.getInt(Matchers.anyInt())).thenReturn(1);
		PowerMockito.when(rs.getString(Matchers.anyInt())).thenReturn("hello");
		PowerMockito.when(rs.getDate(Matchers.anyInt())).thenReturn(new java.sql.Date(0));

		
	}

	@Test
	public void testApplyLeave() throws SQLException, ParseException {
		Mockito.verify(pstmt,times(2)).setString(Matchers.anyInt(), Matchers.anyString());
		Mockito.verify(pstmt,times(2)).setDate(Matchers.anyInt(), Matchers.any(java.sql.Date.class));
	
		leaveApplication = new LeaveApplication();
		Success success = leaveApplication.applyLeave(null);
		assertEquals(true,success);
		}
	@Test
	public void testGetSystemDateTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		assertEquals("2018-08-24 13:51:07.735",timestamp);
	}

}
