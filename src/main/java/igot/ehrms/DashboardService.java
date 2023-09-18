package igot.ehrms;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import igot.ehrms.model.metricsApiResponse.MetricsApiFinalResponse;

public interface DashboardService {
    
    

    MetricsApiFinalResponse getOrgMetrics(String orgId) throws IOException, ParseException;
}
