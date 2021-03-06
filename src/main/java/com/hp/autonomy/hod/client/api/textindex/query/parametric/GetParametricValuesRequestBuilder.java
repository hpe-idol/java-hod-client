/*
 * Copyright 2015-2016 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.hod.client.api.textindex.query.parametric;

import com.hp.autonomy.hod.client.api.resource.ResourceName;
import com.hp.autonomy.hod.client.util.MultiMap;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * Helper class for building up optional parameters for the Get Parametric Values API. The default value for all parameters
 * is null. Null parameters will not be sent to HP Haven OnDemand
 */
@Data
@Accessors(chain = true)
public class GetParametricValuesRequestBuilder {

    private Number minScore;

    /**
     * @param text The value of the text parameter
     */
    private String text;

    /**
     * @param fieldText The value of the field_text parameter
     */
    private String fieldText;

    /**
     * @param maxValues The value of the max_values parameter
     */
    private Integer maxValues;

    /**
     * @param documentCount The value of the document_count parameter
     */
    private Boolean documentCount;

    /**
     * @param totalValues The value of the total_values parameter
     */
    private Boolean totalValues;

    /**
     * @param nestFieldResults The value of the nest_field_results parameter
     */
    private Boolean nestFieldResults;

    /**
     * @param Sort The value of the sort parameter
     */
    private ParametricSort sort;

    /**
     * @param queryProfile The value of the query_profile parameter
     */
    private ResourceName queryProfile;

    /**
     * @param datePeriod The value of the date_period parameter
     */
    private DatePeriod datePeriod;

    private String securityInfo;

    /**
     * @param minScore Sets the value of minScore
     */
    public GetParametricValuesRequestBuilder setMinScore(final Double minScore) {
        this.minScore = minScore;

        return this;
    }

    /**
     * @param minScore Sets the value of minScore
     */
    public GetParametricValuesRequestBuilder setMinScore(final Integer minScore) {
        this.minScore = minScore;

        return this;
    }

    /**
     * @return A map of query parameters suitable for use with {@link GetParametricValuesBackend}. get is NOT supported on
     * the resulting map
     */
    Map<String, Object> build() {
        final Map<String, Object> map = new MultiMap<>();
        map.put("text", text);
        map.put("field_text", fieldText);
        map.put("max_values", maxValues);
        map.put("min_score", minScore);
        map.put("document_count", documentCount);
        map.put("sort", sort);
        map.put("query_profile", queryProfile);
        map.put("security_info", securityInfo);
        map.put("total_values", totalValues);
        map.put("nest_field_results", nestFieldResults);
        map.put("date_period", datePeriod);
        return map;
    }


}
