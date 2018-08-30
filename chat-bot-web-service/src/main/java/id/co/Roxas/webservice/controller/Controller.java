package id.co.Roxas.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.Roxas.webservice.model.ExtensionRequest;
import id.co.Roxas.webservice.model.ExtensionResult;
import id.co.Roxas.webservice.property.AppProperties;
import id.co.Roxas.webservice.service.IService;

@RestController
public class Controller {

	@Autowired
	private AppProperties appProperties;

	@Autowired
	IService svcService;

	@RequestMapping("/forms")
	public String getStarted() {
		return "Hello Form, service port: " + appProperties.getServicePort();
	}

	@RequestMapping("/status/")
	@PostMapping
	public ExtensionResult doGetSrnResult(@RequestBody ExtensionRequest extensionRequest) {
		return svcService.getSrnResult(extensionRequest);
	}

	@RequestMapping("/customers")
	@PostMapping
	public ExtensionResult doQueryCustomerInfo(@RequestBody ExtensionRequest extensionRequest) {
		
		return svcService.getCustomerInfo(extensionRequest);
	}

	@RequestMapping("/productinfo")
	@PostMapping
	public ExtensionResult doQueryProductInfo(@RequestBody ExtensionRequest extensionRequest) {
		return svcService.getProductInfo(extensionRequest);
	}

	@RequestMapping("/messages")
	@PostMapping
	public ExtensionResult doGetMessages(@RequestBody ExtensionRequest extensionRequest) {
		return svcService.getMessageBody(extensionRequest);
	}

	@RequestMapping("/quickreplies")
	@PostMapping
	public ExtensionResult doBuildQuickReplies(@RequestBody ExtensionRequest extensionRequest) {
		return svcService.getQuickReplies(extensionRequest);
	}

	@RequestMapping("/form")
	@PostMapping
	public ExtensionResult doBuildForm(@RequestBody ExtensionRequest extensionRequest) {
		return svcService.getForms(extensionRequest);
	}

	@RequestMapping("/button")
	@PostMapping
	public ExtensionResult doBuildButton(@RequestBody ExtensionRequest extensionRequest) {
		return svcService.getButtons(extensionRequest);
	}

}
