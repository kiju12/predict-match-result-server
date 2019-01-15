package ur.lab3.predictmatchresult.domainobjects.datatransferobjects;

import java.util.List;

public class TeamDTO {
    private Long id;
    private String name;
    private String imageLink;
    private List<PlayerDTO> players;
    private String formation;

    public TeamDTO() {
    }

    public TeamDTO(Long id, String name, String imageLink, List<PlayerDTO> players, String formation) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.players = players;
        this.formation = formation;
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

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }
}
