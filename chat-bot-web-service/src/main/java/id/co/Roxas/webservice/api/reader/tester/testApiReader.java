package id.co.Roxas.webservice.api.reader.tester;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.keycloak.admin.client.Keycloak;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import id.co.Roxas.webservice.entity.CrmMstCategory;

public class testApiReader {

	  private final static String username = "a37e129ebfee19f51e297ef14d39b8ad559653eab0f513fa73b65d2a93184ae966e092b372cd314d751af043c63b48a27f77551a61c6f6de7ffbd815abd9b145";	
	  private final static String password = "07108c12bc2e0f5ca687cf7a1eb3816926138c946b717dd396299b8d3fb7a57e6a8a38f750109fa9767c125f90d5673c2d63fd24686239c5b9511d04a7d2ed58:tobtahcfif";
	  private final static String clientId = "fifgroup-token";
	  private final static String clientSecret = "261f1b80-7a18-438e-b9fa-2f9575c97e0b";
	
//		ResponseEntity<CrmMstCategory[]> entity = restTemplate.exchange("http://103.206.244.11:8084/fifgrouprest-api/crm/v1/mstcategories",
//		HttpMethod.GET,httpEntity, CrmMstCategory[].class);
//		   MediaType contentType = entity.getHeaders().getContentType();
//		   HttpStatus statusCode = entity.getStatusCode();
//		   TypeFactory _t = TypeFactory.defaultInstance();
//		   //System.err.println(entity.getBody()[0].getCategoryId());
//		   for (int i = 0; i < entity.getBody().length; i++) {
//			System.out.println("categori id : " + entity.getBody()[i].getCategoryId() + 
//					" category parent id : " + entity.getBody()[i].getCategoryParentId());
//		   }
	  
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> paramHeader = new LinkedMultiValueMap<>();
		paramHeader.add("Authorization", "Bearer " + readKeyCloakTokenNumber(username, password, clientId, clientSecret));
		HttpEntity httpEntity = new HttpEntity(paramHeader);
		List<CrmMstCategory> mstCategories = new ArrayList<>();
		try {
			mstCategories = getListResult("http://103.206.244.11:8084/fifgrouprest-api/crm/v1/mstcategories", "GET",
					CrmMstCategory.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for (CrmMstCategory crmMstCategory : mstCategories) {
			System.out.println(crmMstCategory.getCategoryId());
		}
	}
	
	public static <T> List<T> getListResult(String url, String Method, Class<T> clazz) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> paramHeader = new LinkedMultiValueMap<>();
		paramHeader.add("Authorization", "Bearer " + readKeyCloakTokenNumber(username, password, clientId, clientSecret));
		HttpEntity httpEntity = new HttpEntity(paramHeader);
		ResponseEntity<String> entity = restTemplate.exchange("http://103.206.244.11:8084/fifgrouprest-api/fifappsdaily/v1/master/cities",
				HttpMethod.GET,httpEntity,String.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		TypeFactory t = TypeFactory.defaultInstance();
		System.err.println(entity.getBody());
		List<T> list = mapper.readValue(entity.getBody(),
				t.constructCollectionType(ArrayList.class, clazz));
		return  list;
	}
	
	public static String readKeyCloakTokenNumber(String userName, String password, String clientId, String clientSecret) {
		  Keycloak keycloak = Keycloak.getInstance("http://testauthtoken.fifgroup.co.id:8380/auth",
				  "fifgroup", userName, password, clientId, clientSecret);	
		  	
		  System.err.println("Maka tokennya adalah : " + keycloak.tokenManager().getAccessToken().getToken());
		  System.err.println("Tipe Token : " + keycloak.tokenManager().getAccessToken().getTokenType());
		  System.err.println("expire in  : " + keycloak.tokenManager().getAccessToken().getExpiresIn());
		  System.err.println("refresh expire in : " + keycloak.tokenManager().getAccessToken().getRefreshExpiresIn());
		
		  return keycloak.tokenManager().getAccessToken().getToken();
	}
	
	

}
