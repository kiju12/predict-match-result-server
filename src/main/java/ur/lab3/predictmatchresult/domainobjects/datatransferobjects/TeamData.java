package ur.lab3.predictmatchresult.domainobjects.datatransferobjects;

public class TeamData {
    private Long id;
    private String name;
    private String linkToImage;

    public TeamData(Long id, String name, String linkToImage) {
        this.id = id;
        this.name = name;
        this.linkToImage = linkToImage;
    }

    public TeamData() {
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
}
