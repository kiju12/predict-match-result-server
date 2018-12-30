package ur.lab3.predictmatchresult.domainobjects.datatransferobjects;

import ur.lab3.predictmatchresult.domainobjects.models.Formation;

public class FetchTeamData {

    private Long fifaId;
    private Formation formation;

    public FetchTeamData() {
    }

    public Long getFifaId() {
        return fifaId;
    }

    public void setFifaId(Long fifaId) {
        this.fifaId = fifaId;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}

