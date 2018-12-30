package ur.lab3.predictmatchresult.easportsapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class PlayerCard {
    private String id;
    private String firstname;
    private String lastname;
    private Integer rating;
    private String position;
    private Boolean isGoalkeeper;
    private String clubName;
    private String clubImageLink;

    public PlayerCard() {
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("firstName")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @JsonProperty("lastName")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonProperty("rating")
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @JsonProperty("position")
    public void setPosition(String position) {
        this.position = position;
    }

    @JsonProperty("isGK")
    public void setGoalkeeper(Boolean goalkeeper) {
        isGoalkeeper = goalkeeper;
    }

    @JsonProperty("club")
    public void setClubData(Map<String, Object> clubData) {
        this.clubName = (String) clubData.get("name");

        Map<String, Object> imageUrls = (Map<String, Object>) clubData.get("imageUrls");
        Map<String, String> light = (Map<String, String>) imageUrls.get("light");
        String mediumImageUrl = light.get("medium");

        this.clubImageLink = mediumImageUrl;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getRating() {
        return rating;
    }

    public String getPosition() {
        return position;
    }

    public Boolean getGoalkeeper() {
        return isGoalkeeper;
    }

    public String getClubName() {
        return clubName;
    }

    public String getClubImageLink() {
        return clubImageLink;
    }
}
