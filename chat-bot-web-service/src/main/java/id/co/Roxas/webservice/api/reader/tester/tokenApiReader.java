package id.co.Roxas.webservice.api.reader.tester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RealmRepresentation;


public class tokenApiReader {
   
	public static void main(String[] args){
	  String username = "a37e129ebfee19f51e297ef14d39b8ad559653eab0f513fa73b65d2a93184ae966e092b372cd314d751af043c63b48a27f77551a61c6f6de7ffbd815abd9b145";	
	  String password = "07108c12bc2e0f5ca687cf7a1eb3816926138c946b717dd396299b8d3fb7a57e6a8a38f750109fa9767c125f90d5673c2d63fd24686239c5b9511d04a7d2ed58:tobtahcfif";
	  String clientId = "fifgroup-token";
	  String clientSecret = "261f1b80-7a18-438e-b9fa-2f9575c97e0b";
	  Keycloak keycloak = Keycloak.getInstance("http://testauthtoken.fifgroup.co.id:8380/auth",
			  "fifgroup", username, password, clientId, clientSecret);	
	  	
	  System.out.println("Maka tokennya adalah : " + keycloak.tokenManager().getAccessToken().getToken());
	  System.out.println("Tipe Token : " + keycloak.tokenManager().getAccessToken().getTokenType());
	  System.out.println("expire in  : " + keycloak.tokenManager().getAccessToken().getExpiresIn());
	  System.out.println("refresh expire in : " + keycloak.tokenManager().getAccessToken().getRefreshExpiresIn());
    }
}
