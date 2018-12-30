package ur.lab3.predictmatchresult.domainobjects.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class War {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer homeGoals;
    private Integer awayGoals;

    @OneToMany(mappedBy = "war", cascade = CascadeType.ALL)
    private List<TeamWar> teams;

    public War() {
    }

    public War(Integer homeGoals, Integer awayGoals, List<TeamWar> teams) {
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.teams = teams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }

    public List<TeamWar> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamWar> teams) {
        this.teams = teams;
    }

}
