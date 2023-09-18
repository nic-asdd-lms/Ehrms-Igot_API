package igot.ehrms.model.metricsApiResponse;

import java.util.List;

public class ResponseData {
    private String chartType;
    private String visualizationCode;
    private String chartFormat;
    private String drillDownChartId;
    private String filterKeys;
    private String customData;
    private String dates;
    private String filter;
    private List<String> innerWidget;
    private List<Data> data;
    private String comments;
    private String image;
    private String drillDashboard;
    private String colorScheme;
    
    public String getChartType() {
        return chartType;
    }
    public void setChartType(String chartType) {
        this.chartType = chartType;
    }
    public String getVisualizationCode() {
        return visualizationCode;
    }
    public void setVisualizationCode(String visualizationCode) {
        this.visualizationCode = visualizationCode;
    }
    public String getChartFormat() {
        return chartFormat;
    }
    public void setChartFormat(String chartFormat) {
        this.chartFormat = chartFormat;
    }
    public String getDrillDownChartId() {
        return drillDownChartId;
    }
    public void setDrillDownChartId(String drillDownChartId) {
        this.drillDownChartId = drillDownChartId;
    }
    public String getFilterKeys() {
        return filterKeys;
    }
    public void setFilterKeys(String filterKeys) {
        this.filterKeys = filterKeys;
    }
    public String getCustomData() {
        return customData;
    }
    public void setCustomData(String customData) {
        this.customData = customData;
    }
    public String getDates() {
        return dates;
    }
    public void setDates(String dates) {
        this.dates = dates;
    }
    public String getFilter() {
        return filter;
    }
    public void setFilter(String filter) {
        this.filter = filter;
    }
    public List<String> getInnerWidget() {
        return innerWidget;
    }
    public void setInnerWidget(List<String> innerWidget) {
        this.innerWidget = innerWidget;
    }
    public List<Data> getData() {
        return data;
    }
    public void setData(List<Data> data) {
        this.data = data;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getDrillDashboard() {
        return drillDashboard;
    }
    public void setDrillDashboard(String drillDashboard) {
        this.drillDashboard = drillDashboard;
    }
    public String getColorScheme() {
        return colorScheme;
    }
    public void setColorScheme(String colorScheme) {
        this.colorScheme = colorScheme;
    }
    
}



