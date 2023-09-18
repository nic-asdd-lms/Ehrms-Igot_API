package igot.ehrms.model.metricsApiResponse;

import java.util.List;

public class Response {
    private String orgId;
    private String orgName;
    private List<MetricsResponse> data;

    public Response(String sbOrgId, String orgName, List<MetricsResponse> data) {
        this.setOrgId(sbOrgId);
        this.setOrgName(orgName);
        this.setData(data);
    }
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public List<MetricsResponse> getData() {
        return data;
    }
    public void setData(List<MetricsResponse> data) {
        this.data = data;
    }

}
