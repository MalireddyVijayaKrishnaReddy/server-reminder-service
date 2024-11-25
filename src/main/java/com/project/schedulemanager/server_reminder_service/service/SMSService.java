package com.project.schedulemanager.server_reminder_service.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SMSService {
    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.messaging-service-sid}")
    private String messagingServiceSid;

@Bean
    private RestTemplate restTemplate() {
    return new RestTemplate(getClientHttpRequestFactory());
}
    public String sendMessage(String message, String number) {
        String url = "https://api.twilio.com/2010-04-01/Accounts/accountSid/Messages.json";


        // Set up headers with basic authentication
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(accountSid, authToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        // Set up form data
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("To", number);
        formData.add("MessagingServiceSid", messagingServiceSid);
        formData.add("Body", message);

        // Create request with headers and form data
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        // Send POST request
        ResponseEntity<String> response = restTemplate().exchange(
                url, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return "Message sent successfully: " + response.getBody();
        } else {
            return "Failed to send message: " + response.getBody();
        }
    }


    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        return factory;
    }


}
