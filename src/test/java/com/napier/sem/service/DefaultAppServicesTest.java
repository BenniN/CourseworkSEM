package com.napier.sem.service;

import com.napier.sem.datalayer.DataLayer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultAppServicesTest {

    private static DataLayer dataLayer;
    private static DefaultAppServices appServices;

    @BeforeAll
    public static void init() {
        dataLayer = Mockito.mock(DataLayer.class);
        appServices = new DefaultAppServices(dataLayer);
    }

    @Test
    public void testAllCountriesOrderedByLargestPopulationToSmallest() {
        Mockito.when(dataLayer.getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT)).thenReturn(Collections.emptyList());
        appServices.getAllCountriesOrderedByLargestPopulationToSmallest();
        Mockito.verify(dataLayer).getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT);
    }

}
