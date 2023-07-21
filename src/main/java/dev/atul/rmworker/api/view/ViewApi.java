package dev.atul.rmworker.api.view;

import dev.atul.rmworker.dto.RMEvent;
import dev.atul.rmworker.dto.ViewRMResponse;
import org.springframework.stereotype.Service;

@Service
public class ViewApi {
    public ViewRMResponse sendRMEvent(RMEvent rmEvent){
        int random = (int) Math.round(Math.random());
        //int randomSleep = (int) ((Math.random() * (2000 - 1000)) + 1000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(random == 0){
           ViewRMResponse response = new ViewRMResponse();
           response.setResponse("ERROR");
           response.setError("VIEW API ERROR");
           return response;
        }
        else{
            ViewRMResponse response = new ViewRMResponse();
            response.setResponse("SUCCESS");
            response.setError("");
            return response;
        }

    }

}
