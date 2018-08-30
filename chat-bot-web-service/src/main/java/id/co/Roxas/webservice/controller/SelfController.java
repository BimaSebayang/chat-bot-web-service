package id.co.Roxas.webservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.Roxas.webservice.api.reader.svc.ApiReader;
import id.co.Roxas.webservice.entity.CrmMstCategory;
import id.co.Roxas.webservice.entity.FmMstCities;
import id.co.Roxas.webservice.model.EasyMap;
import id.co.Roxas.webservice.model.ExtensionRequest;
import id.co.Roxas.webservice.model.ExtensionResult;

@RestController
@RequestMapping("/self")
public class SelfController extends ApiReader{

	@RequestMapping("/test")
	@GetMapping
	public ExtensionResult doGetSrnResult() {
		ExtensionResult er = new ExtensionResult();
		List<FmMstCities> fmMstCities = new ArrayList<>();
		try {
			fmMstCities = getListResultGetMethod("http://103.206.244.11:8084/fifgrouprest-api/fifappsdaily/v1/master/cities", 
					FmMstCities.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> value = new HashMap<>();
		for (FmMstCities fm : fmMstCities) {
			value.put(fm.getCityCode(),fm.getCity());
		}
		er.setAgent(false);
		er.setRepeat(false);
		er.setSuccess(true);
		er.setNext(true);
		er.setValue(value);
		return er;
	}
	
}
