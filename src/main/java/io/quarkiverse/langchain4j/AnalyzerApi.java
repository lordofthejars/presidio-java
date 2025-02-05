package io.quarkiverse.langchain4j;

import io.quarkiverse.langchain4j.model.AnalyzeRequest;
import io.quarkiverse.langchain4j.model.RecognizerResultWithAnaysisExplanation;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;


/**
  * Presidio
  * <p>Context aware, pluggable and customizable PII anonymization service for text and images.</p>
  */
@Path("")
@RegisterRestClient( configKey="presidio-analyzer")
@ApplicationScoped
public interface AnalyzerApi {

     /**
     * Analyze Text
     *
     * Recognizes PII entities in a given text and returns their types, locations and score
     *
     * @param analyzeRequest 
     */
    @POST
    @Path("/analyze")
    List<RecognizerResultWithAnaysisExplanation> analyzePost(
        AnalyzeRequest analyzeRequest

    );

     /**
     * Healthcheck
     *
     */
    @GET
    @Path("/health")
    @Produces({"text/plain"})
    String healthGet(
        
    );

     /**
     * Get Recognizers
     *
     * Get the available PII recognizers for a given language
     *
     * @param language Two characters for the desired language in ISO_639-1 format
     */
    @GET
    @Path("/recognizers")

    List<String> recognizersGet(
        @QueryParam("language") String language
    );

     /**
     * Get supported entities
     *
     * Get the list of PII entities Presidio-Analyzer is capable of detecting
     *
     * @param language Two characters for the desired language in ISO_639-1 format
     */
    @GET
    @Path("/supportedentities")
    List<String> supportedentitiesGet(
        @QueryParam("language") String language
    );

}
