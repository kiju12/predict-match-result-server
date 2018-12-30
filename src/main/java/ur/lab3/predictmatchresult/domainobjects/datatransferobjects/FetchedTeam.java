package ur.lab3.predictmatchresult.domainobjects.datatransferobjects;

import ur.lab3.predictmatchresult.domainobjects.models.Team;

import javax.persistence.FetchType;

public class FetchedTeam {
    private Long id;
    private String name;
    private String linkToImage;

    public FetchedTeam(Long id, String name, String linkToImage) {
        this.id = id;
        this.name = name;
        this.linkToImage = linkToImage;
    }

    public FetchedTeam() {
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

    public String getLinkToImage() {
        return linkToImage;
    }

    public void setLinkToImage(String linkToImage) {
        this.linkToImage = linkToImage;
    }

    public static FetchedTeam createFromTeam(Team team) {
        return new FetchedTeam(team.getId(), team.getName(), team.getImageLink());
    }
}
