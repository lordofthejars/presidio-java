package io.quarkiverse.langchain4j.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.ws.rs.QueryParam;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnonymizePost400Response  {

    private String error;

    /**
    * Get error
    * @return error
    **/
    @JsonProperty("error")
          @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getError() {
        return error;
    }

    /**
     * Set error
     **/
    public void setError(String error) {
        this.error = error;
    }

    public AnonymizePost400Response error(String error) {
        this.error = error;
        return this;
    }

    /**
     * Create a string representation of this pojo.
     **/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AnonymizePost400Response {\n");

        sb.append("    error: ").append(toIndentedString(error)).append("\n");
        
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private static String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AnonymizePost400ResponseQueryParam  {

        @QueryParam("error")
        private String error;

        /**
        * Get error
        * @return error
        **/
        @JsonProperty("error")
        public String getError() {
            return error;
        }

        /**
         * Set error
         **/
        public void setError(String error) {
            this.error = error;
        }

        public AnonymizePost400ResponseQueryParam error(String error) {
            this.error = error;
            return this;
        }

        /**
         * Create a string representation of this pojo.
         **/
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("class AnonymizePost400ResponseQueryParam {\n");

            sb.append("    error: ").append(toIndentedString(error)).append("\n");
            sb.append("}");
            return sb.toString();
        }

        /**
         * Convert the given object to string with each line indented by 4 spaces
         * (except the first line).
         */
        private static String toIndentedString(Object o) {
            if (o == null) {
                return "null";
            }
            return o.toString().replace("\n", "\n    ");
        }
    }
}