package io.swagger.api.impl.implementation;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import io.swagger.model.Login;
import io.swagger.model.ResponseForLogin;

public class LoginAuthentication {
	public ResponseForLogin  getUserBasicAttributes(Login loginData) {
		ResponseForLogin responseForLogin = new ResponseForLogin();
		LdapContext ctx= LdapUtil.getLdapContext(loginData);
        try {
        	SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String[] attrIDs = { 
                    "name",
                    "title",
                    "sAMAccountName",
                    "directReports"};
            constraints.setReturningAttributes(attrIDs);
            NamingEnumeration answer = ctx.search("OU=Users,OU=Bangalore,OU=Office Locations,dc=northamerica,dc=cerner,dc=net","sAMAccountName="
                    + "SK026590", constraints);
            if (answer.hasMore()) {
                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
                Attribute userName = attrs.get("name");
                responseForLogin.setName( (String) userName.get());
                Attribute designation = attrs.get("title");
                responseForLogin.setDesignation((String) designation.get());
                Attribute userId = attrs.get("sAMAccountName");
                responseForLogin.setUserId((String) userId.get());
                Attribute reportee = attrs.get("directReports");
                if(reportee == null) {
                responseForLogin.setReportee(false);
                }else {
                	responseForLogin.setReportee(true);
                }
                responseForLogin.setSuccess(true);

            }else{
                throw new Exception("Invalid User");
            }
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
        	try {
				ctx.close();
			} catch (NamingException e) {
				e.printStackTrace();
			}
        }
        return responseForLogin;
    }
	
	
	
}
