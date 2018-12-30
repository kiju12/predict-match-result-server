package ur.lab3.predictmatchresult.easportsapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LeagueResponse {

    @JsonProperty("leagues")
    private List<League> leagues;

    public LeagueResponse() {
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }
}
