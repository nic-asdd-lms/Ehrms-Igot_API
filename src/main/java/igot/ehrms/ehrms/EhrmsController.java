package igot.ehrms.ehrms;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igot.ehrms.model.ApiResponse;
import igot.ehrms.util.Constants;

@RestController
@RequestMapping("/ehrmsservice/apis/igot")
public class EhrmsController {

    @Autowired
    EhrmsService ehrmsService;


    @PostMapping(value = "/user/update")
    public ResponseEntity<?> profileUpdate(@RequestBody Map<String, Object> request,@RequestHeader("id") UUID id, @RequestHeader(Constants.AUTHORIZATION) String token) throws Exception {

        ApiResponse response = ehrmsService.profileUpdate(request,id,token);
		return new ResponseEntity<>(response, response.getResponseCode());
    }

    

}
