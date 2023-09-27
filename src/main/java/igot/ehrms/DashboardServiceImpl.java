package igot.ehrms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import org.json.simple.*;
import org.json.simple.parser.*;
import org.slf4j.LoggerFactory;
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
private static final Logger logger = (Logger) LoggerFactory.getLogger(DashboardServiceImpl.class);

    @Autowired
    UserService userService;

    @Value("${response}")
    private String responseFile;

    @Override
    public MetricsApiFinalResponse getOrgMetrics(UUID id, String parentOrgId) throws IOException, ParseException {
        MetricsApiFinalResponse response = CommonUtil.createDefaultResponse(Constants.API_EHRMS_DASHBOARD);
        try {

            if (validOrgUser(id, parentOrgId)) {

                JSONParser parser = new JSONParser();

                // Object obj = parser.parse(new FileReader(Constants.RESPONSE_FILE_PATH));
                Object obj = parser.parse(new FileReader(responseFile));
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
        } catch (FileNotFoundException e) {
            logger.error("ERROR: File '"+responseFile+"' not found ",  e);
            response.getParams().setErrmsg("Metrics not generated");
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        } catch (Exception e) {
            logger.error("ERROR: ",  e);
            response.getParams().setErrmsg(e.toString());
            response.setResponseCode(HttpStatus.BAD_REQUEST);
            return response;
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
