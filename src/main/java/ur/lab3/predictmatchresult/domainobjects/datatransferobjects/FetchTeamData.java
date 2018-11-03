package ur.lab3.predictmatchresult.domainobjects.datatransferobjects;

public class FetchTeamData {

    private String idFromEADatebase;
    private String teamName;

    public FetchTeamData() {
    }

    public FetchTeamData(String idFromEADatebase, String teamName) {
        this.idFromEADatebase = idFromEADatebase;
        this.teamName = teamName;
    }

    public String getIdFromEADatebase() {
        return idFromEADatebase;
    }

    public void setIdFromEADatebase(String idFromEADatebase) {
        this.idFromEADatebase = idFromEADatebase;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}

