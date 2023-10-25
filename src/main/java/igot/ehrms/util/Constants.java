package igot.ehrms.util;



public class Constants {
	
	public static final String FILTERS = "filters";
	public static final String START_DATE = "startDate";
	public static final String END_DATE = "endDate";
	


	//	Dashboard API

	public static final String DASHBOARD_ID = "dashboardId";
	public static final String VISUALIZATION_TYPE = "visualizationType";
	public static final String VISUALIZATION_CODE = "visualizationCode";
	public static final String QUERY_TYPE = "queryType";
	public static final String MDO_FILTER = "mdofilter";
	public static final String MODULE_LEVEL = "moduleLevel";
	public static final String AGGREGATION_FACTORS = "aggregationFactors";
	public static final String REQUEST_DATE = "requestDate";
	public static final String REQUEST_INFO = "RequestInfo";
	public static final String HEADERS = "headers";
	public static final String TENANT_ID = "tenantId";
	public static final String AGGREGATION_REQUEST_DTO = "aggregationRequestDto";
	
	public static final String API_EHRMS_DASHBOARD = "api.ehrms.dashboard";
	public static final String API_PROFILE_UPDATE = "api.profile.update";
	
	public static final String TABLE_LOG = "ehrms_log";
	
	public static final String AUTH_PATH = "/ehrmsservice/apis/igot/dashboard/authenticate";
	public static final String CREATE_USER_PATH = "/ehrmsservice/apis/igot/dashboard/user/create/*";
	public static final String UPDATE_USER_PATH = "/ehrmsservice/apis/igot/user/update";
	public static final String SERVICE_PATH = "/ehrmsservice";
	public static final String METRICS_PATH = "/ehrmsservice/apis/igot/dashboard/analytics/*";
	public static final String ENROLMENTS_PATH = "/ehrmsservice/apis/igot/enrolments";
	public static final String USER_READ_PATH = "/api/user/v1/read/";
	public static final String LOGIN_PATH = "/auth/realms/sunbird/protocol/openid-connect/token";
	public static final String ACTUATOR_PATH = "/actuator/env";
	
	public static final String PORTAL_URL = "https://portal.";
	public static final String[] DEFINED_ENDPOINTS={ACTUATOR_PATH,ENROLMENTS_PATH,CREATE_USER_PATH.substring(0, CREATE_USER_PATH.length()-2), AUTH_PATH, METRICS_PATH.substring(0, METRICS_PATH.length()-2), UPDATE_USER_PATH};
	
	public static final String REQUEST = "request";
	public static final String USER_ID = "userId";
	public static final String FAILED = "Failed";
	public static final String PROFILE_DETAILS = "profileDetails";
	public static final String ADDITIONAL_PROPERTIES = "additionalProperties";
	public static final String EMAIL = "email";
	public static final String LANGUAGES = "profileDetails.personalDetails.knownLanguages";
	public static final String FIRSTNAME_FIELD_TO_CHANGE = "[\"firstName\",\"profileDetails.personalDetails.firstname\"]";
	public static final String FIRSTNAME = "firstName";
	public static final String PROFILE_DETAILS_FIRSTNAME = "profileDetails.personalDetails.firstname";
	public static final String QUALIFICATIONS = "profileDetails.academics";
	public static final String QUALIFICATION_NAME = "profileDetails.academics.nameOfQualification";
	public static final String INSTITUTE = "profileDetails.academics.nameOfInstitute";
	public static final String STATUS = "status";
	public static final String CONTENT = "content";
	public static final String TAG = "tag";
	public static final String EHRMS_TAG = "eHRMS";
	
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String APPLICATION_JSON = "application/json";
	public static final String AUTHORIZATION = "authorization";
	public static final String COUNT = "count";
	public static final String X_AUTH_TOKEN = "x-authenticated-user-token";
	
	public static final String MSG_INVALID_EHRMS_USER = "Not an iGOT user";
	public static final String MSG_UNAUTHORIZED_USER = "Unauthorized";
	public static final String MSG_INVALID_REQUEST = "Invalid request: Email ID not provided";
	
	private Constants() {
		throw new IllegalStateException("Utility class");
	}

}
