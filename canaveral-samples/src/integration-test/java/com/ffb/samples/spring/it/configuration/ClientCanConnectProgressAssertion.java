package com.ffb.samples.spring.it.configuration;

import com.ffb.canaveral2.core.runtime.ProgressAssertion;
import com.ffb.canaveral2.core.runtime.RunnerContext;
import com.ffb.samples.spring.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class ClientCanConnectProgressAssertion implements ProgressAssertion {

    private static final Logger log = LoggerFactory.getLogger(ClientCanConnectProgressAssertion.class);

    @Override
    public boolean canProceed(RunnerContext runnerContext) {
        String luckyNumber = runnerContext.getApplicationProvider().getProperty("com.ffb.luckyNumber", "0");
        log.info("Application properties are available. Lucky number is {}.", luckyNumber);

        log.info("Making a call to application.");
        RestClient client = (RestClient) runnerContext.getTestBean(RestClient.class, Collections.emptySet());
        return client.askGoogle("any").getStatus() == 200;
    }
}
