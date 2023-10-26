package igot.integration.dashboard;

import java.io.IOException;
import java.util.UUID;

import org.json.simple.parser.ParseException;

import igot.integration.model.metricsapiresponse.MetricsApiFinalResponse;

public interface DashboardService {
    
    

    MetricsApiFinalResponse getOrgMetrics(UUID id, String orgId, String token) throws IOException, ParseException;
}
