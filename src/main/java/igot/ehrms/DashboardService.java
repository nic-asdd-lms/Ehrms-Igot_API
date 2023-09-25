package igot.ehrms;

import java.io.IOException;
import java.util.UUID;

import org.json.simple.parser.ParseException;

import igot.ehrms.model.metricsApiResponse.MetricsApiFinalResponse;

public interface DashboardService {
    
    

    MetricsApiFinalResponse getOrgMetrics(UUID id, String orgId) throws IOException, ParseException;
}
