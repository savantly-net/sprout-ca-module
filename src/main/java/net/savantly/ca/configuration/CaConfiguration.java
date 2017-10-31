package net.savantly.ca.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ca")
public class CaConfiguration {
	
	String username;
	String password;
	String host = "http://localhost:8081";
	Map<String, String> serviceUrls = new HashMap<>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Map<String, String> getServiceUrls() {
		return serviceUrls;
	}
	public void setServiceUrls(Map<String, String> serviceUrls) {
		this.serviceUrls = serviceUrls;
	}
	
}
