package igot.ehrms.model.metricsApiResponse;

import java.util.List;
import java.util.Map;

public class Data {
    private String headerName;
    private Double headerValue;
    private String headerSymbol;
    private String colorPaletteCode;
    private String colorPaletteId;
    private List<String> plots;
    private String insight;
    private Boolean isDecimal;
    private Map<String,Object> additionalProperties;
    
    public String getHeaderName() {
        return headerName;
    }
    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }
    public Double getHeaderValue() {
        return headerValue;
    }
    public void setHeaderValue(Double headerValue) {
        this.headerValue = headerValue;
    }
    public String getHeaderSymbol() {
        return headerSymbol;
    }
    public void setHeaderSymbol(String headerSymbol) {
        this.headerSymbol = headerSymbol;
    }
    public String getColorPaletteCode() {
        return colorPaletteCode;
    }
    public void setColorPaletteCode(String colorPaletteCode) {
        this.colorPaletteCode = colorPaletteCode;
    }
    public String getColorPaletteId() {
        return colorPaletteId;
    }
    public void setColorPaletteId(String colorPaletteId) {
        this.colorPaletteId = colorPaletteId;
    }
    public List<String> getPlots() {
        return plots;
    }
    public void setPlots(List<String> plots) {
        this.plots = plots;
    }
    public String getInsight() {
        return insight;
    }
    public void setInsight(String insight) {
        this.insight = insight;
    }
    public Boolean getIsDecimal() {
        return isDecimal;
    }
    public void setIsDecimal(Boolean isDecimal) {
        this.isDecimal = isDecimal;
    }
    public Map<String,Object> getAdditionalProperties() {
        return additionalProperties;
    }
    public void setAdditionalProperties(Map<String,Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
    
}

