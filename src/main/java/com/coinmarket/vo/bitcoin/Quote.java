package com.coinmarket.vo.bitcoin;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "BOB"
})
public class Quote {

    @JsonProperty("BOB")
    private BOB bOB;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("BOB")
    public BOB getBOB() {
        return bOB;
    }

    @JsonProperty("BOB")
    public void setBOB(BOB bOB) {
        this.bOB = bOB;
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