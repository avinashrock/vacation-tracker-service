package io.swagger.api.impl.implementation;

import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import io.swagger.model.Login;

public class LdapUtil {
	public static LdapContext getLdapContext(Login loginData){
        LdapContext ctx = null;
        try{
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.SECURITY_AUTHENTICATION, "Simple");
            env.put(Context.SECURITY_PRINCIPAL, loginData.getUsername()+"@cerner.net");
            env.put(Context.SECURITY_CREDENTIALS,loginData.getPassword());
            env.put(Context.PROVIDER_URL, "ldap://corpdcberl01.northamerica.cerner.net:3268");
            ctx = new InitialLdapContext(env, null);
          
           
        }
        catch (AuthenticationNotSupportedException exception) {
			throw new WebApplicationException("UnAuthorized", Status.UNAUTHORIZED);
		}
		catch (AuthenticationException exception) {
			throw new WebApplicationException("UnAuthorized", Status.UNAUTHORIZED);
		}
		catch (NamingException exception) {
			throw new WebApplicationException("UnAuthorized", Status.UNAUTHORIZED);
		}
		catch (Exception exception) {
			throw new WebApplicationException("Internal Server Error", Status.INTERNAL_SERVER_ERROR);
		}	
        return ctx;
    }

	

}
