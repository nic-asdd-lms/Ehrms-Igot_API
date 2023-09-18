package igot.ehrms.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import igot.ehrms.model.ApiResponseParams;
import igot.ehrms.model.metricsApiResponse.MetricsApiFinalResponse;

public class CommonUtil {
    public static MetricsApiFinalResponse createDefaultResponse(String api) {
		MetricsApiFinalResponse response = new MetricsApiFinalResponse();
		response.setId(api);
		response.setVer("1.0");
		response.setParams(new ApiResponseParams());
		response.getParams().setStatus("success");
		response.setResponseCode(HttpStatus.OK);
		response.setTs(LocalDateTime.now().toString());
		response.setResult(null);
		return response;
	}
}
