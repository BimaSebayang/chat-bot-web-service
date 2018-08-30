package id.co.Roxas.webservice.api.reader.svc;

import java.util.ArrayList;
import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import id.co.Roxas.webservice.entity.CrmMstCategory;

public class ApiReader {
	
	  private final String username = "a37e129ebfee19f51e297ef14d39b8ad559653eab0f513fa73b65d2a93184ae966e092b372cd314d751af043c63b48a27f77551a61c6f6de7ffbd815abd9b145";	
	  private final String password = "07108c12bc2e0f5ca687cf7a1eb3816926138c946b717dd396299b8d3fb7a57e6a8a38f750109fa9767c125f90d5673c2d63fd24686239c5b9511d04a7d2ed58:tobtahcfif";
	  private final String clientId = "fifgroup-token";
	  private final String clientSecret = "261f1b80-7a18-438e-b9fa-2f9575c97e0b";
   
//	  public static <T,K> Map<T, K> mapJsonToHashMapObject(Object source)
//				throws Exception {
//			
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			String json = mapper.writeValueAsString(source);
//			
//			return mapper.readValue(json,new TypeReference<HashMap<T, K>>() {});
//		}
	  
	  public <T> List<T> getListResultGetMethod(String url,Class<T> clazz) throws Exception{
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, String> paramHeader = new LinkedMultiValueMap<>();
			paramHeader.add("Authorization", "Bearer " + readKeyCloakTokenNumber(username, password, clientId, clientSecret));
			HttpEntity httpEntity = new HttpEntity(paramHeader);
			ResponseEntity<String> entity = restTemplate.exchange(url,
					HttpMethod.GET,httpEntity,String.class);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			TypeFactory t = TypeFactory.defaultInstance();
			List<T> list = mapper.readValue(entity.getBody(),
					t.constructCollectionType(ArrayList.class, clazz));
			return  list;
		}
		
	  
		public String readKeyCloakTokenNumber(String userName, String password, String clientId, String clientSecret) {
			  Keycloak keycloak = Keycloak.getInstance("http://testauthtoken.fifgroup.co.id:8380/auth",
					  "fifgroup", userName, password, clientId, clientSecret);	
			  	
			  System.err.println("Maka tokennya adalah : " + keycloak.tokenManager().getAccessToken().getToken());
			  System.err.println("Tipe Token : " + keycloak.tokenManager().getAccessToken().getTokenType());
			  System.err.println("expire in  : " + keycloak.tokenManager().getAccessToken().getExpiresIn());
			  System.err.println("refresh expire in : " + keycloak.tokenManager().getAccessToken().getRefreshExpiresIn());
			
			  return keycloak.tokenManager().getAccessToken().getToken();
		}
	
	
	
}
