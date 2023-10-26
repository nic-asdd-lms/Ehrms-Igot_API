package igot.ehrms.ehrms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.qos.logback.classic.Logger;
import igot.ehrms.auth.JwtTokenUtil;
import igot.ehrms.model.ApiRequest;
import igot.ehrms.model.ApiResponse;
import igot.ehrms.user.UserModel;
import igot.ehrms.user.UserService;
import igot.ehrms.util.CommonUtil;
import igot.ehrms.util.Constants;

import org.json.simple.*;
import org.json.simple.parser.*;
import org.slf4j.LoggerFactory;

@Service
@SuppressWarnings("unchecked")
public class EhrmsServiceImpl implements EhrmsService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(EhrmsServiceImpl.class);

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Value("${response}")
    private String responseFile;

    @Value("${config}")
    private String config;

    @Value("${fields}")
    private String fieldMap;

    @Value("${user.search.path}")
    private String userSearchPath;

    @Value("${user.update.path}")
    private String userUpdatePath;

    @Value("${ehrms.org.id}")
    private String orgId;

    @Override
    public ApiResponse profileUpdate(Map<String, Object> request, UUID id, String token)
            throws IOException, ParseException {
        ApiResponse response = CommonUtil.createResponse(Constants.API_PROFILE_UPDATE);

        if (validOrgUser(id, token)) {

            try {
                Map<String, Object> requestData = (Map<String, Object>) request.get(Constants.REQUEST);

                if (!validateRequest(requestData)) {
                    response.setResponseCode(HttpStatus.BAD_REQUEST);
                    response.getParams().setStatus(Constants.FAILED);
                    response.getParams().setErr(Constants.MSG_INVALID_REQUEST);
                    return response;
                } else {
                    Map<String, Object> responseObj = searchUser(requestData);

                    if (responseObj != null) {
                        long count = (long) responseObj.get(Constants.COUNT);
                        // Map<String, Object> additionalProperties = (Map<String, Object>)
                        // content.get(0).get(Constants.ADDITIONAL_PROPERTIES);
                        // List<String> tags = (ArrayList<String>)
                        // additionalProperties.get(Constants.TAG);

                        if (count != 0) {

                            ArrayList<Map<String, Object>> content = (ArrayList<Map<String, Object>>) responseObj
                                    .get(Constants.CONTENT);
                            Map<String, Object> profileDetails = (Map<String, Object>) content.get(0)
                                    .get(Constants.PROFILE_DETAILS);

                            Map<String, Object> updateRequest = createRequest(requestData, profileDetails);
                            if (!updateRequest.containsKey(Constants.ERROR)) {
                                updateRequest.put(Constants.USER_ID, content.get(0).get(Constants.USER_ID));

                                ApiRequest requestBody = new ApiRequest();
                                requestBody.setRequest(updateRequest);

                                Map<String, Object> result = update(requestBody);
                                response.putAll(result);

                                logger.info("iGOT profile updated");
                            } else {
                                logger.error(updateRequest.get(Constants.ERROR).toString());
                                response.getParams().setStatus(Constants.FAILED);
                                response.getParams().setErr(updateRequest.get(Constants.ERROR).toString());
                                response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
                            }

                        } else {
                            logger.error(Constants.MSG_INVALID_EHRMS_USER);
                            response.getParams().setStatus(Constants.FAILED);
                            response.getParams().setErr(Constants.MSG_INVALID_EHRMS_USER);
                            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);

                        }
                    }
                }

            } catch (Exception e) {
                logger.error("ERROR: ", e);
                response.getParams().setStatus(Constants.FAILED);
                response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);

            }
        } else {
            logger.info("Unauthorized user");
            response.getParams().setStatus(Constants.FAILED);
            response.getParams().setErr(Constants.MSG_UNAUTHORIZED_USER);
            response.setResponseCode(HttpStatus.UNAUTHORIZED);

        }

        return response;

    }

    private Map<String, Object> createRequest(Map<String, Object> request, Map<String, Object> profileDetails)
            throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(fieldMap));
        JSONObject jsonObject = (JSONObject) obj;

        Map<String, Object> mappedRequest = new HashMap<>();
        for (Entry<String, Object> entry : request.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(Constants.EMAIL))
                if (jsonObject.get(entry.getKey()) != null)
                    mappedRequest.put(jsonObject.get(entry.getKey()).toString(), entry.getValue());
                else {
                    mappedRequest.put(Constants.ERROR, Constants.MSG_INVALID_FIELD + entry.getKey());
                    return mappedRequest;
                }

        }

        Map<String, Object> formattedFields = format(mappedRequest);
        Map<String, Object> requestFields = convertToJson(formattedFields);
        Map<String, Object> fields = new HashMap<>();
        fields.put(Constants.PROFILE_DETAILS, profileDetails);
        if (request.containsKey(Constants.FIRSTNAME))
            fields.put(Constants.FIRSTNAME, request.get(Constants.FIRSTNAME));

        Map<String, Object> mergedFields = merge(requestFields, fields);

        return mergedFields;
    }

    private Map<String, Object> format(Map<String, Object> mappedRequest)
            throws java.text.ParseException, FileNotFoundException, IOException, ParseException {

        Map<String, Object> formattedFields = new HashMap<>();

        for (Entry<String, Object> entry : mappedRequest.entrySet()) {
            if (entry.getKey().equals(Constants.LANGUAGES)) {
                formattedFields.put(entry.getKey(), (ArrayList<String>) entry.getValue());

            } else if (entry.getKey().equals(Constants.FIRSTNAME_FIELD_TO_CHANGE)) {
                formattedFields.put(Constants.FIRSTNAME, entry.getValue());
                formattedFields.put(Constants.PROFILE_DETAILS_FIRSTNAME, entry.getValue());

            } else if (entry.getKey().equals(Constants.QUALIFICATIONS)) {
                formattedFields.put(entry.getKey(), (ArrayList<Map<String, Object>>) entry.getValue());

            } else {
                formattedFields.put(entry.getKey(), entry.getValue());
            }
        }
        return formattedFields;
    }

    private Map<String, Object> merge(Map<String, Object> requestFields,
            Map<String, Object> profileDetails) {

        BiFunction<Object, Object, Object> remappingFunction = (value1, value2) -> {
            if (value1 instanceof Map && value2 instanceof Map) {
                // If both values are maps, recursively merge them
                Map<String, Object> mapValue1 = (Map<String, Object>) value1;
                Map<String, Object> mapValue2 = (Map<String, Object>) value2;
                mergeMaps(mapValue1, mapValue2);
                return mapValue1;
            } else {
                // If one of the values is not a map, take the value from the second map
                return value2;
            }
        };

        requestFields.forEach((key, value) -> profileDetails.merge(key, value, remappingFunction));

        return profileDetails;
    }

    public static void mergeMaps(Map<String, Object> target, Map<String, Object> source) {
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            String key = entry.getKey();
            Object sourceValue = entry.getValue();
            Object targetValue = target.get(key);

            if (sourceValue instanceof Map && targetValue instanceof Map) {
                // Recursively merge nested maps
                Map<String, Object> nestedTarget = (Map<String, Object>) targetValue;
                Map<String, Object> nestedSource = (Map<String, Object>) sourceValue;
                mergeMaps(nestedTarget, nestedSource);
            } else {
                // Replace or add the value from source to target
                target.put(key, sourceValue);
            }
        }
    }

    private Map<String, Object> convertToJson(Map<String, Object> mappedRequest) throws JsonProcessingException {

        Map<String, Object> requestFields = new HashMap<>();

        for (Entry<String, Object> entry : mappedRequest.entrySet()) {
            String[] keys = entry.getKey().split("\\.");
            Map<String, Object> currentMap = requestFields;

            for (int i = 0; i < keys.length - 1; i++) {
                currentMap = (Map<String, Object>) currentMap.computeIfAbsent(keys[i], k -> new HashMap<>());
            }
            currentMap.put(keys[keys.length - 1], entry.getValue());

        }

        return requestFields;
    }

    private Map<String, Object> update(ApiRequest requestData)
            throws FileNotFoundException, IOException, ParseException, InterruptedException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(config));
        JSONObject jsonObject = (JSONObject) obj;

        String reqBodyData = new ObjectMapper().writeValueAsString(requestData);

        String profileUpdateUrl = Constants.PORTAL_URL + jsonObject.get("url") + userUpdatePath;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(profileUpdateUrl))
                .header(Constants.CONTENT_TYPE, "application/json")
                .header(Constants.AUTHORIZATION, "Bearer " + jsonObject.get("metricsToken").toString())
                .method("PATCH", HttpRequest.BodyPublishers.ofString(reqBodyData))
                .build();

        HttpResponse<String> response = null;
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        obj = parser.parse(response.body());
        jsonObject = (JSONObject) obj;

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> responseMap = mapper.convertValue(jsonObject, Map.class);
        if (responseMap != null && "OK".equalsIgnoreCase((String) responseMap.get("responseCode"))) {
            Map<String, Object> map = (Map<String, Object>) responseMap.get("result");
            return map;
        }

        return null;
    }

    private Map<String, Object> searchUser(Map<String, Object> requestData)
            throws FileNotFoundException, IOException, ParseException, InterruptedException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(config));
        JSONObject jsonObject = (JSONObject) obj;

        String email = requestData.get(Constants.EMAIL).toString();

        ApiRequest requestObj = new ApiRequest();
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put(Constants.FILTERS, new HashMap<String, Object>() {
            {
                put(Constants.EMAIL, email);
                put(Constants.STATUS, "1");
            }
        });
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("request", reqMap);

        requestObj.setRequest(requestBody);

        String reqBodyData = new ObjectMapper().writeValueAsString(requestBody);
        String token = login(config);

        String userSearchUrl = Constants.PORTAL_URL + jsonObject.get("url") + userSearchPath;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(userSearchUrl))
                .header(Constants.CONTENT_TYPE, "application/json")
                .header(Constants.AUTHORIZATION, "Bearer " + jsonObject.get("metricsToken").toString())
                .header(Constants.X_AUTH_TOKEN, token)
                .method("POST", HttpRequest.BodyPublishers.ofString(reqBodyData))
                .build();

        HttpResponse<String> response = null;
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        obj = parser.parse(response.body());
        jsonObject = (JSONObject) obj;

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> responseMap = mapper.convertValue(jsonObject, Map.class);
        if (responseMap != null && "OK".equalsIgnoreCase((String) responseMap.get("responseCode"))) {
            Map<String, Object> map = (Map<String, Object>) responseMap.get("result");
            if (map.get("response") != null) {
                Map<String, Object> responseObj = (Map<String, Object>) map.get("response");
                return responseObj;
            }
        }

        return null;
    }

    public boolean validateRequest(Map<String, Object> requestBody) {
        if (!(ObjectUtils.isEmpty(requestBody.get(Constants.EMAIL))))
        // && !(ObjectUtils.isEmpty(requestBody.get(Constants.PROFILE_DETAILS))))
        {
            return true;
        } else {
            return false;
        }
    }

    public boolean validOrgUser(UUID id, String token) {
        Optional<UserModel> user = userService.getUserById(id);
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            return (!user.isEmpty() && user.get().getOrg().equalsIgnoreCase(orgId)
                    && jwtTokenUtil.validateUser(jwtToken, id));

        }
        return false;

    }

    static String login(String config) throws IOException, InterruptedException, ParseException {
        JSONParser parser = new JSONParser();
        logger.info("Reading file : " + config);
        FileReader reader = new FileReader(config);

        // Object obj = parser.parse(new FileReader(Constants.METADATA));
        Object obj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;

        String authUrl = Constants.PORTAL_URL + jsonObject.get("url") + Constants.LOGIN_PATH;

        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id", "lms");
        parameters.put("client_secret", jsonObject.get("password").toString());
        parameters.put("grant_type", "client_credentials");

        String form = parameters.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        // String reqBody = "client_id=lms&client_secret=" + jsonObject.get("password")
        // + "@&grant_type=client_credentials";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(authUrl))
                .header(Constants.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .header(Constants.AUTHORIZATION, "Bearer " + jsonObject.get("loginToken").toString())
                .POST(HttpRequest.BodyPublishers.ofString(form))
                // .method("POST", HttpRequest.BodyPublishers.ofString(reqBody))
                .build();

        HttpResponse<String> response = null;
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        logger.info("Authenticated");

        parser = new JSONParser();
        obj = parser.parse(response.body());
        jsonObject = (JSONObject) obj;
        String token = jsonObject.get("access_token").toString();

        return token;
    }

}
