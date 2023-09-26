package igot.ehrms;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.UUID;

import igot.ehrms.log.LogModel;
import igot.ehrms.log.LogService;
import igot.ehrms.model.metricsApiResponse.MetricsApiFinalResponse;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igot.ehrms.user.UserService;

@RestController
@RequestMapping("/apis/igot/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @Autowired
    LogService logService;

    @Autowired
    UserService userService;

    @GetMapping("/analytics/{parentMapId}")
    public ResponseEntity<MetricsApiFinalResponse> getMetrics(@PathVariable("parentMapId") String orgId,
            @RequestHeader("id") UUID id) throws IOException, ParseException {
        LogModel logModel = new LogModel(id, orgId, "getMetrics", LocalDateTime.now());
        logService.createLog(logModel);

        MetricsApiFinalResponse response = dashboardService.getOrgMetrics(id, orgId);
        return new ResponseEntity<>(response, response.getResponseCode());

    }

}
