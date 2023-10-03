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
	public static final String TABLE_LOG = "ehrms_log";
	public static final String AUTH_PATH = "/ehrmsservice/apis/igot/dashboard/authenticate";
	public static final String CREATE_USER_PATH = "/ehrmsservice/apis/igot/dashboard/user/create/*";
	public static final String SERVICE_PATH = "/ehrmsservice";
	public static final String METRICS_PATH = "/ehrmsservice/apis/igot/dashboard/analytics/*";
	public static final String PORTAL_URL = "portal.";
	public static final String[] DEFINED_ENDPOINTS={CREATE_USER_PATH.substring(0, CREATE_USER_PATH.length()-2), AUTH_PATH, METRICS_PATH.substring(0, METRICS_PATH.length()-2)};
	
	private Constants() {
		throw new IllegalStateException("Utility class");
	}

}
