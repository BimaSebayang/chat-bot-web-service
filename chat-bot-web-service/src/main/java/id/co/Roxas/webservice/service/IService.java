package id.co.Roxas.webservice.service;

import id.co.Roxas.webservice.model.ExtensionRequest;
import id.co.Roxas.webservice.model.ExtensionResult;

public interface IService {
	
	ExtensionResult getSrnResult(ExtensionRequest extensionRequest);
	
	ExtensionResult getCustomerInfo(ExtensionRequest extensionRequest);
	
	ExtensionResult getProductInfo(ExtensionRequest extensionRequest);
	
	ExtensionResult getMessageBody(ExtensionRequest extensionRequest);
	
	ExtensionResult getQuickReplies(ExtensionRequest extensionRequest);
	
	ExtensionResult getForms(ExtensionRequest extensionRequest);
	
	ExtensionResult getButtons(ExtensionRequest extensionRequest);
}
