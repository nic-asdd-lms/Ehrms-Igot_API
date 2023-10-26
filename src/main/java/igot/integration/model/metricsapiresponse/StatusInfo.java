package igot.integration.model.metricsapiresponse;

import org.springframework.http.HttpStatusCode;

public class StatusInfo {
    private HttpStatusCode statusCode;
    private String statusMessage;
    private String errorMessage;
    
    public HttpStatusCode getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }
    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}

