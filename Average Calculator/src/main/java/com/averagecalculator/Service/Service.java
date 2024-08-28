package com.averagecalculator.Service;

import com.averagecalculator.Repository.WindowStateRepository;
import com.averagecalculator.ResponseObject.ApiResponse;
import com.averagecalculator.ResponseObject.AuthResponse;
import com.averagecalculator.ResponseObject.Response;
import com.averagecalculator.ResponseObject.WindowState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    public Response getResponse(char c) {
        Response res=getOperations(c);
        if(res==null){
            return null;
        }
        return res;
    }

    @Autowired
    WindowStateRepository windowStateRepository;
    public String getToken(){

            String url = "http://20.244.56.144/test/auth";
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            String requestBody = "{\n" +
                    "    \"companyName\": \"glbajajgroup\",\n" +
                    "    \"clientID\": \"e334b8ee-cadf-40e9-9168-d0491060c32c\",\n" +
                    "    \"clientSecret\": \"kyJEjgMvwhPbKYZl\",\n" +
                    "    \"ownerName\": \"Somendra\",\n" +
                    "    \"ownerEmail\": \"somendra.singh2021@glbajajgroup.org\",\n" +
                    "    \"rollNo\": \"2105111530045\"\n" +
                    "}";

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<AuthResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, AuthResponse.class);
            AuthResponse authResponse = responseEntity.getBody();

            if (authResponse == null) {
                return null;
            }
            return authResponse.getAccess_token();
    }
    public Response getOperations(char c){
        String token=getToken();
        String url;
        switch (c){
            case 'e':
                url = "http://20.244.56.144/test/even";
                break;
            case 'r':
                url="http://20.244.56.144/test/rand";
                break;
            case'p':
                url="http://20.244.56.144/test/primes";
                break;
            case 'f':
                url="http://20.244.56.144/test/fibo";
                break;
            default: url="";
        }
        if(url.equals("")){
            return null;
        }
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, ApiResponse.class);
        ApiResponse response = responseEntity.getBody();
        if(response==null){
            return null;
        }
        Response res=new Response();
        WindowState ws=new WindowState();
        res.setNumbers(response.getNumbers());
        Optional<WindowState> lastWindowState = windowStateRepository.findTopByOrderByIdDesc();
        if (lastWindowState.isPresent()) {
            res.setWindowPrevState(lastWindowState.get().getState());
        } else {
            res.setWindowPrevState(new ArrayList<>());
        }
        res.setWindowCurrState(response.getNumbers());
        res.setAverage(Average(res.getWindowCurrState()));
        ws.setState(res.getWindowCurrState());
        windowStateRepository.save(ws);
        return res;
    }
    public double Average(List<Integer> windowCurrState){
        double sum=0;
        for (int i : windowCurrState) {
            sum+=i;
        }
        return sum/windowCurrState.size();
    }
}
