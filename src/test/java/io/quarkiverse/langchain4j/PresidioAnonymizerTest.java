package io.quarkiverse.langchain4j;

import io.quarkiverse.langchain4j.model.*;
import io.quarkus.test.junit.QuarkusTest;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class PresidioAnonymizerTest {


    @RestClient
    AnonymizerApi anonymizerApi;

    @RestClient
    AnalyzerApi analyzerApi;

    @Test
    public void shouldAnonymizeData() {

        String text = "hello world, my name is Jane Doe. My number is: 034453334";

        AnalyzeRequest analyzeRequest = new AnalyzeRequest();
        analyzeRequest.language("en");
        analyzeRequest.text(text);

        List<RecognizerResultWithAnaysisExplanation> recognizerResults = analyzerApi.analyzePost(analyzeRequest);

        AnonymizeRequestAnonymizersValue REPLACE = new AnonymizeRequestAnonymizersValue();
        REPLACE.setType("replace");
        REPLACE.setNewValue("ANONYMIZED");

        AnonymizeRequestAnonymizersValue PHONE = new AnonymizeRequestAnonymizersValue();
        PHONE.setType("mask");
        PHONE.setMaskingChar("*");
        PHONE.setCharsToMask(4);
        PHONE.setFromEnd(true);

        AnonymizeRequest anonymizeRequest = new AnonymizeRequest();

        anonymizeRequest.setText(text);

        anonymizeRequest.putAnonymizersItem("DEFAULT", REPLACE);
        anonymizeRequest.putAnonymizersItem("PHONE_NUMBER", PHONE);

        recognizerResults
                .stream()
                .map(r -> {
                    RecognizerResult recognizerResult = new RecognizerResult();
                    recognizerResult.setEnd(r.getEnd());
                    recognizerResult.setEntityType(r.getEntityType());
                    recognizerResult.setStart(r.getStart());
                    recognizerResult.setScore(r.getScore());

                    return recognizerResult;
                })
                .forEach(anonymizeRequest::addAnalyzerResultsItem);

        AnonymizeResponse anonymizeResponse = anonymizerApi.anonymizePost(anonymizeRequest);

        assertThat(anonymizeResponse.getText())
                .isEqualTo("hello world, my name is ANONYMIZED. My number is: 03445****");
        assertThat(anonymizeResponse.getItems()).hasSize(2);

    }
    
}
