package ur.lab3.predictmatchresult.services.predict;

import ur.lab3.predictmatchresult.domainobjects.models.MatchResult;

public class WarData {

    private TeamRating homeTeamRating;
    private TeamRating awayTeamRating;
    private MatchResult whoWin;

    public WarData(TeamRating homeTeamRating, TeamRating awayTeamRating, MatchResult whoWin) {
        this.homeTeamRating = homeTeamRating;
        this.awayTeamRating = awayTeamRating;
        this.whoWin = whoWin;
    }

    public WarData() {
    }

    public TeamRating getHomeTeamRating() {
        return homeTeamRating;
    }

    public void setHomeTeamRating(TeamRating homeTeamRating) {
        this.homeTeamRating = homeTeamRating;
    }

    public TeamRating getAwayTeamRating() {
        return awayTeamRating;
    }

    public void setAwayTeamRating(TeamRating awayTeamRating) {
        this.awayTeamRating = awayTeamRating;
    }

    public MatchResult getWhoWin() {
        return whoWin;
    }

    public void setWhoWin(MatchResult whoWin) {
        this.whoWin = whoWin;
    }
}
