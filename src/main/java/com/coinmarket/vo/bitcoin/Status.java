package com.coinmarket.vo.bitcoin;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "timestamp",
        "error_code",
        "error_message",
        "elapsed",
        "credit_count",
        "notice"
})
public class Status {

    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("error_code")
    private Integer errorCode;
    @JsonProperty("error_message")
    private Object errorMessage;
    @JsonProperty("elapsed")
    private Integer elapsed;
    @JsonProperty("credit_count")
    private Integer creditCount;
    @JsonProperty("notice")
    private Object notice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("error_code")
    public Integer getErrorCode() {
        return errorCode;
    }

    @JsonProperty("error_code")
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("error_message")
    public Object getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("error_message")
    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    @JsonProperty("elapsed")
    public Integer getElapsed() {
        return elapsed;
    }

    @JsonProperty("elapsed")
    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    @JsonProperty("credit_count")
    public Integer getCreditCount() {
        return creditCount;
    }

    @JsonProperty("credit_count")
    public void setCreditCount(Integer creditCount) {
        this.creditCount = creditCount;
    }

    @JsonProperty("notice")
    public Object getNotice() {
        return notice;
    }

    @JsonProperty("notice")
    public void setNotice(Object notice) {
        this.notice = notice;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}