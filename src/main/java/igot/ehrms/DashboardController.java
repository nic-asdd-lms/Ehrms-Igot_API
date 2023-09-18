package igot.ehrms;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import igot.ehrms.model.metricsApiResponse.MetricsApiFinalResponse;

@RestController
@RequestMapping("/apis/igot/analytics/")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("{parentMapId}")
	public ResponseEntity<MetricsApiFinalResponse> getMetrics(@PathVariable("parentMapId") String orgId) throws IOException, ParseException {
		MetricsApiFinalResponse response = dashboardService.getOrgMetrics(orgId);
        return new ResponseEntity<>(response, response.getResponseCode());
	}

    
    
}
