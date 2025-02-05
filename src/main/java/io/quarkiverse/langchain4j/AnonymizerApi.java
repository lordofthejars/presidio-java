package io.quarkiverse.langchain4j;


import io.quarkiverse.langchain4j.model.AnonymizeRequest;
import io.quarkiverse.langchain4j.model.AnonymizeResponse;
import io.quarkiverse.langchain4j.model.DeanonymizeRequest;
import io.quarkiverse.langchain4j.model.DeanonymizeResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;


import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;


/**
  * Presidio
  * <p>Context aware, pluggable and customizable PII anonymization service for text and images.</p>
  */
@Path("")
@RegisterRestClient( configKey="presidio-anonymizer")
@ApplicationScoped
public interface AnonymizerApi {

     /**
     * Anonymize Text
     *
     * @param anonymizeRequest 
     */
    @POST
    @Path("/anonymize")
    AnonymizeResponse anonymizePost(
        AnonymizeRequest anonymizeRequest);

     /**
     * Get supported anonymizers
     *
     */
    @GET
    @Path("/anonymizers")
    List<String> anonymizersGet();

     /**
     * Deanonymize Text
     *
     * @param deanonymizeRequest 
     */
    @POST
    @Path("/deanonymize")
    DeanonymizeResponse deanonymizePost(
        DeanonymizeRequest deanonymizeRequest);

     /**
     * Get supported deanonymizers
     *
     */
    @GET
    @Path("/deanonymizers")
    List<String> deanonymizersGet();

     /**
     * Healthcheck
     *
     */
    @GET
    @Path("/health")
    @Produces({"text/plain"})
    String healthGet();

}
