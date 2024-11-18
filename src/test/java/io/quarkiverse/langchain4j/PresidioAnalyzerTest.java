package io.quarkiverse.langchain4j;

import io.quarkiverse.langchain4j.model.AnalyzeRequest;
import io.quarkiverse.langchain4j.model.RecognizerResultWithAnaysisExplanation;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class PresidioAnalyzerTest {

    @RestClient
    AnalyzerApi analyzerApi;

    @Test
    public void shouldAnalyzeText() {


        AnalyzeRequest analyzeRequest = new AnalyzeRequest();
        analyzeRequest.text("John Smith drivers license is AC432223");
        analyzeRequest.language("en");

        List<RecognizerResultWithAnaysisExplanation> recognizerResultWithAnaysisExplanations = analyzerApi.analyzePost(analyzeRequest);

        assertThat(recognizerResultWithAnaysisExplanations).hasSize(2);

        final RecognizerResultWithAnaysisExplanation personEntity = recognizerResultWithAnaysisExplanations.getFirst();
        assertThat(personEntity.getEntityType()).isEqualTo("PERSON");
        assertThat(personEntity.getStart()).isEqualTo(0);
        assertThat(personEntity.getEnd()).isEqualTo(10);

        final RecognizerResultWithAnaysisExplanation driverLicenseEntity = recognizerResultWithAnaysisExplanations.getLast();
        assertThat(driverLicenseEntity.getEntityType()).isEqualTo("US_DRIVER_LICENSE");
        assertThat(driverLicenseEntity.getStart()).isEqualTo(30);
        assertThat(driverLicenseEntity.getEnd()).isEqualTo(38);

    }

}
