
package com.TestPackage;

import java.util.*;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "category",
    "name",
    "photoUrls",
    "tags",
    "status"
})
public class Input {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("name")
    private String name;
    @JsonProperty("photoUrls")
    private List<String> photoUrls = new ArrayList<String>();
    @JsonProperty("tags")
    private List<Tag> tags = new ArrayList<Tag>();
    @JsonProperty("status")
    private String status;

    @JsonProperty("id")
    public Long getid () {
        return id;
    }

    @JsonProperty("id")
    public void setid (Long id){
        this.id = id;
    }

    public Input withid (int id){
        this.name = name;
        return this;
    }

    @JsonProperty("category")
        public Category getCategory () {
            return category;
        }

        @JsonProperty("category")
        public void setCategory (Category category){
            this.category = category;
        }

        public Input withCategory (Category category){
            this.category = category;
            return this;
        }

        @JsonProperty("name")
        public String getName () {
            return name;
        }

        @JsonProperty("name")
        public void setName (String name){
            this.name = name;
        }

        public Input withName (String name){
            this.name = name;
            return this;
        }

        @JsonProperty("photoUrls")
        public List<String> getPhotoUrls () {
            return photoUrls;
        }

        @JsonProperty("photoUrls")
        public void setPhotoUrls (List < String > photoUrls) {
            this.photoUrls = photoUrls;
        }

        public Input withPhotoUrls (List < String > photoUrls) {
            this.photoUrls = photoUrls;
            return this;
        }

        @JsonProperty("tags")
        public List<Tag> getTags () {
            return tags;
        }

        @JsonProperty("tags")
        public void setTags (List < Tag > tags) {
            this.tags = tags;
        }

        public Input withTags (List < Tag > tags) {
            this.tags = tags;
            return this;
        }

        @JsonProperty("status")
        public String getStatus () {
            return status;
        }

        @JsonProperty("status")
        public void setStatus (String status){
            this.status = status;
        }

        public Input withStatus (String status){
            this.status = status;
            return this;
        }








}
