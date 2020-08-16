package com.cinema.sravs.domain;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"title",
"release_date"
})
public class Result {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("id")
    public String getId() {
    return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
    this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
    return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
    this.title = title;
    }

    @JsonProperty("release_date")
    public String getReleaseDate() {
    return releaseDate;
    }

    @JsonProperty("release_date")
    public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


}