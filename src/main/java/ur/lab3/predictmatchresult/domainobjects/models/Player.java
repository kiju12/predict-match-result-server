package ur.lab3.predictmatchresult.domainobjects.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Player {

    @Id
    private String id;
    private Integer rating;
    @Enumerated(EnumType.STRING)
    private Position position;
    private String firstname;
    private String lastname;
    private Boolean isGoalkeeper;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public Player() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(firstname, player.firstname) &&
                Objects.equals(lastname, player.lastname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstname, lastname);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getGoalkeeper() {
        return isGoalkeeper;
    }

    public void setGoalkeeper(Boolean goalkeeper) {
        isGoalkeeper = goalkeeper;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
