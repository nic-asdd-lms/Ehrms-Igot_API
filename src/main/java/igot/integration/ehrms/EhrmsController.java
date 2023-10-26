package igot.integration.ehrms;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igot.integration.log.LogModel;
import igot.integration.log.LogService;
import igot.integration.model.ApiResponse;
import igot.integration.util.Constants;

@RestController
@RequestMapping("/ehrmsservice/apis/igot")
public class EhrmsController {

    @Autowired
    EhrmsService ehrmsService;

    @Autowired
    LogService logService;

    @Value("${ehrms.org.id}")
    private String orgId;


    @PostMapping(value = "/user/update")
    public ResponseEntity<?> profileUpdate(@RequestBody Map<String, Object> request,@RequestHeader("id") UUID id, @RequestHeader(Constants.AUTHORIZATION) String token) throws Exception {

        LogModel logModel = new LogModel(id, orgId, "profileUpdate", LocalDateTime.now());
        logService.createLog(logModel);

        ApiResponse response = ehrmsService.profileUpdate(request,id,token);
		return new ResponseEntity<>(response, response.getResponseCode());
    }

    

}
