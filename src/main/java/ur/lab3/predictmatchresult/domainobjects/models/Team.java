package ur.lab3.predictmatchresult.domainobjects.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Team {

    @Id
    private Long id;
    private String name;
    private String imageLink;
    private String formation;

    @OneToMany(mappedBy = "team", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Player> players;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<TeamWar> wars;
//    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
//    private List<War> wars;

    public Team() {
    }

    public List<TeamWar> getWars() {
        return wars;
    }

    public void setWars(List<TeamWar> wars) {
        this.wars = wars;
    }

    public Team(Long id, String name, String imageLink, String formation, Set<Player> players) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.formation = formation;
        this.players = players;
//        this.wars = wars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public Set<Player> getPlayers() {
        if (players == null)
            players = new HashSet<>();

        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

}
