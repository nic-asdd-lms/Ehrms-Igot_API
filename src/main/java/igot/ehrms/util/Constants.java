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
	public static final String AUTH_PATH = "/apis/igot/dashboard/authenticate";
	public static final String CREATE_USER_PATH = "/apis/igot/dashboard/user/create/*";
	
	private Constants() {
		throw new IllegalStateException("Utility class");
	}

}
