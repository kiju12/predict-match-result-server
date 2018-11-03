package ur.lab3.predictmatchresult.domainobjects.datatransferobjects;

import java.util.Date;

public class MatchData {
    private Long oppositeTeamId;
    private int oppositeTeamGoals;
    private int teamGoals;
    private Date date;

    public MatchData() {
    }

    public Long getOppositeTeamId() {
        return oppositeTeamId;
    }

    public void setOppositeTeamId(Long oppositeTeamId) {
        this.oppositeTeamId = oppositeTeamId;
    }

    public int getOppositeTeamGoals() {
        return oppositeTeamGoals;
    }

    public void setOppositeTeamGoals(int oppositeTeamGoals) {
        this.oppositeTeamGoals = oppositeTeamGoals;
    }

    public int getTeamGoals() {
        return teamGoals;
    }

    public void setTeamGoals(int teamGoals) {
        this.teamGoals = teamGoals;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
