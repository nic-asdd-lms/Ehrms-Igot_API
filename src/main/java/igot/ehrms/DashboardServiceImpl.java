package igot.ehrms;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.apache.commons.lang3.StringUtils;


import igot.ehrms.model.metricsApiResponse.MetricsApiFinalResponse;
import igot.ehrms.model.metricsApiResponse.Response;
import igot.ehrms.util.CommonUtil;
import igot.ehrms.util.Constants;

@Service
public class DashboardServiceImpl implements DashboardService {

    

    @Override
    public MetricsApiFinalResponse getOrgMetrics(String parentOrgId) throws IOException, ParseException {
        MetricsApiFinalResponse response = CommonUtil.createDefaultResponse(Constants.API_EHRMS_DASHBOARD);

        try {

            JSONParser parser = new JSONParser();

            Object obj = parser.parse(new FileReader(Constants.RESPONSE_FILE_PATH));
            JSONObject jsonObject = (JSONObject) obj;
            
            String errMsg = validateRequest(jsonObject,parentOrgId);
			if (!StringUtils.isEmpty(errMsg)) {
				response.getParams().setErrmsg(errMsg);
				response.setResponseCode(HttpStatus.BAD_REQUEST);
				return response;
			}
            
            JSONArray orgs = (JSONArray) jsonObject.get(parentOrgId);
            List<Response> resultArray = new ArrayList<Response>(orgs);
            
            response.setResult(resultArray);

            return response;
        } catch (Exception e) {
            return null;
        }

    }

    private String validateRequest(JSONObject jsonObject, String parentOrgId) {
        StringBuilder strBuilder = new StringBuilder();

        if(!jsonObject.containsKey(parentOrgId)){
            strBuilder.append("Analytics data not generated for organisations under given Dept. ID");
			
        }
        return strBuilder.toString();
    }
}
