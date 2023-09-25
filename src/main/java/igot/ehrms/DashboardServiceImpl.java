package igot.ehrms;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.apache.commons.lang3.StringUtils;

import igot.ehrms.model.ApiResponseParams;
import igot.ehrms.model.metricsApiResponse.MetricsApiFinalResponse;
import igot.ehrms.model.metricsApiResponse.Response;
import igot.ehrms.user.UserModel;
import igot.ehrms.user.UserService;
import igot.ehrms.util.CommonUtil;
import igot.ehrms.util.Constants;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    UserService userService;

    @Override
    public MetricsApiFinalResponse getOrgMetrics(UUID id, String parentOrgId) throws IOException, ParseException {
        try {

            MetricsApiFinalResponse response = CommonUtil.createDefaultResponse(Constants.API_EHRMS_DASHBOARD);
            if (validOrgUser(id, parentOrgId)) {

                JSONParser parser = new JSONParser();

                Object obj = parser.parse(new FileReader(Constants.RESPONSE_FILE_PATH));
                JSONObject jsonObject = (JSONObject) obj;

                String errMsg = validateRequest(jsonObject, parentOrgId);
                if (!StringUtils.isEmpty(errMsg)) {
                    response.getParams().setErrmsg(errMsg);
                    response.setResponseCode(HttpStatus.BAD_REQUEST);
                    return response;
                }

                JSONArray orgs = (JSONArray) jsonObject.get(parentOrgId);
                List<Response> resultArray = new ArrayList<Response>(orgs);

                response.setResult(resultArray);
            } else {
                ApiResponseParams params = new ApiResponseParams();
                params.setErrmsg("Invalid credentials for " + parentOrgId);

                response.setResponseCode(HttpStatus.FORBIDDEN);
                response.setParams(params);

            }
            return response;
        } catch (Exception e) {
            return null;
        }

    }

    private boolean validOrgUser(UUID id, String orgId) {
        Optional<UserModel> user = userService.getUserById(id);

        return (!user.isEmpty() && user.get().getOrg().equalsIgnoreCase(orgId));
        
    }

    private String validateRequest(JSONObject jsonObject, String parentOrgId) {
        StringBuilder strBuilder = new StringBuilder();

        if (!jsonObject.containsKey(parentOrgId)) {
            strBuilder.append("Analytics data not generated for organisations under given Dept. ID");

        }
        return strBuilder.toString();
    }
}
