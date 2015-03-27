/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.iod.client.api.queryprofile;

import com.hp.autonomy.iod.client.AbstractIodClientIntegrationTest;
import com.hp.autonomy.iod.client.Endpoint;
import com.hp.autonomy.iod.client.error.IodErrorException;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CreateDeleteQueryProfileServiceITCase extends AbstractIodClientIntegrationTest {

    private CreateQueryProfileService createQueryProfileService;
    private DeleteQueryProfileService deleteQueryProfileService;
    private Endpoint endpoint;

    @Before
    public void setUp() {
        super.setUp(endpoint);

        createQueryProfileService = getRestAdapter().create(CreateQueryProfileService.class);
        deleteQueryProfileService = getRestAdapter().create(DeleteQueryProfileService.class);
    }

    @Parameterized.Parameters
    public static Collection<Endpoint> endPoints() {
        return Arrays.asList(
                Endpoint.DEVELOPMENT
        );
    }

    public CreateDeleteQueryProfileServiceITCase(final Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    @Test
    public void testCreateDeleteQueryProfile() throws IodErrorException, InterruptedException {
        final String profileName = "iod_java_client_query_profile_test";

        final ArrayList<String> categories = new ArrayList<>();
        categories.add("Promotions");


        final QueryProfilePromotions promotions = new QueryProfilePromotions.Builder()
                .setEnabled(true)
                .setEveryPage(false)
                .setIdentified(false)
                .setCategories(categories)
                .build();

        final QueryProfile queryProfile = new QueryProfile.Builder()
                .setQueryManipulationIndex(getQueryManipulationIndex())
                .setPromotions(promotions)
                .build();

        final CreateDeleteQueryProfileResponse createResponse = createQueryProfileService.createQueryProfile(endpoint.getApiKey(), profileName, queryProfile);
        final CreateDeleteQueryProfileResponse deleteResponse = deleteQueryProfileService.deleteQueryProfile(endpoint.getApiKey(), profileName);

        assertThat(createResponse.getMessage(), is(notNullValue()));
        assertThat(createResponse.getQueryProfile(), is(profileName));
        assertThat(deleteResponse.getMessage(), is(notNullValue()));
        assertThat(deleteResponse.getQueryProfile(), is(profileName));
    }

}