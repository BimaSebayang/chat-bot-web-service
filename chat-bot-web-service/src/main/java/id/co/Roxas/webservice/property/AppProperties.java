package id.co.Roxas.webservice.property;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Configuration
@PropertySource(value= {"classpath:application.properties"})
@Service
public class AppProperties {
    
	Properties prop = new Properties();
	
	@Value("${server.port}")
	String servicePort;

	@Value("${app.form.id}")
	String formId;
	
	@Value("${local.server.port}")
	String localServerPort;
	
	@Value("${tomcat.server.port}")
	String tomcatServerPort;
	
    public String getTomcatServerPort() {
		return tomcatServerPort;
	}


	public void setTomcatServerPort(String tomcatServerPort) {
		this.tomcatServerPort = tomcatServerPort;
	}


	public String getLocalServerPort() {
		return localServerPort;
	}


	public void setLocalServerPort(String localServerPort) {
		this.localServerPort = localServerPort;
	}


	public String getServicePort() {
		return servicePort;
	}


	public void setServicePort(String servicePort) {
		this.servicePort = servicePort;
	}


	public String getFormId() {
		return formId;
	}


	public void setFormId(String formId) {
		this.formId = formId;
	}


	public void setProp(){
    	InputStream is = null;
    	
    	try {
			is = new FileInputStream("\\chat-bot-web-service\\src\\main\\resources\\config.properties");
				prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

	
}
