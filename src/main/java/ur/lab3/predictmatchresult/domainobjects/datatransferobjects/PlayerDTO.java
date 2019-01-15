package ur.lab3.predictmatchresult.domainobjects.datatransferobjects;

import ur.lab3.predictmatchresult.domainobjects.models.Position;

public class PlayerDTO {
    private String firstname;
    private String lastname;
    private Integer rating;
    private Position position;

    public PlayerDTO(String firstname, String lastname, Integer rating, Position position) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.rating = rating;
        this.position = position;
    }

    public PlayerDTO() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
