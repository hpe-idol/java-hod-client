/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.hod.client.api.search;

import com.hp.autonomy.hod.client.api.authentication.AuthenticationToken;
import com.hp.autonomy.hod.client.error.HodErrorException;
import retrofit.http.Header;
import retrofit.http.Multipart;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.PartMap;
import retrofit.http.Path;

import java.util.Map;

public interface UpdateQueryProfileService {

    String URL = "/2/api/sync/queryprofile/{queryProfileName}/v1";

    /**
     * Update a query profile in HP Haven OnDemand for a specified query manipulation index using a token provided by a
     * {@link retrofit.RequestInterceptor}
     * @param name The name of the query profile
     * @param params Configuration parameters for the query profile
     * @return The name of the created query profile
     */
    @PUT(URL)
    @Multipart
    QueryProfileStatusResponse updateQueryProfile(
        @Path("queryProfileName") String name,
        @Part("query_manipulation_index") String queryManipulationIndex,
        @PartMap Map<String, Object> params
    ) throws HodErrorException;

    /**
     * Update a query profile in HP Haven OnDemand for a specified query manipulation index using the given token
     * @param token The token to use to authenticate the request
     * @param name The name of the query profile
     * @param params Configuration parameters for the query profile
     * @return The name of the created query profile
     */
    @PUT(URL)
    @Multipart
    QueryProfileStatusResponse updateQueryProfile(
        @Header("token") AuthenticationToken token,
        @Path("queryProfileName") String name,
        @Part("query_manipulation_index") String queryManipulationIndex,
        @PartMap Map<String, Object> params
    ) throws HodErrorException;
}
