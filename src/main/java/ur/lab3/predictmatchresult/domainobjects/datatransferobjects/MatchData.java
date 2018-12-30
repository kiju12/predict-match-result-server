package ur.lab3.predictmatchresult.domainobjects.datatransferobjects;

import java.util.Date;

public class MatchData {
    private Integer homeGoals;
    private Integer awayGoals;

    public MatchData(Integer homeGoals, Integer awayGoals, Date dateOfMatch) {
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public MatchData() {
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

}
