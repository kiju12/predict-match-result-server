package ur.lab3.predictmatchresult.domainobjects.datatransferobjects;

import java.util.List;

public class MatchesData {
    private Long homeTeamId;
    private Long awayTeamId;
    private List<MatchData> matches;

    public MatchesData() {
    }

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public List<MatchData> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchData> matches) {
        this.matches = matches;
    }
}
