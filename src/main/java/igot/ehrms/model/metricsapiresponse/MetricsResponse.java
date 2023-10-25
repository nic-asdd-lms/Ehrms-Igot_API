package igot.ehrms.model.metricsapiresponse;

public class MetricsResponse {
    private StatusInfo statusInfo;
    private ResponseData responseData;
    
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }
    public void setStatusInfo(StatusInfo statusInfo) {
        this.statusInfo = statusInfo;
    }
    public ResponseData getResponseData() {
        return responseData;
    }
    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}
