package ur.lab3.predictmatchresult.services.predict;

import ur.lab3.predictmatchresult.domainobjects.models.Formation;

public class TeamRating {
    private Rating goalKeeperRating;
    private Rating defenseRating;
    private Rating helpRating;
    private Rating attackRating;

    public TeamRating(Rating goalKeeperRating, Rating defenseRating, Rating helpRating, Rating attackRating) {
        this.goalKeeperRating = goalKeeperRating;
        this.defenseRating = defenseRating;
        this.helpRating = helpRating;
        this.attackRating = attackRating;
    }

    public TeamRating() {
    }

    public Rating getGoalKeeperRating() {
        return goalKeeperRating;
    }

    public void setGoalKeeperRating(Rating goalKeeperRating) {
        this.goalKeeperRating = goalKeeperRating;
    }

    public Rating getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(Rating defenseRating) {
        this.defenseRating = defenseRating;
    }

    public Rating getHelpRating() {
        return helpRating;
    }

    public void setHelpRating(Rating helpRating) {
        this.helpRating = helpRating;
    }

    public Rating getAttackRating() {
        return attackRating;
    }

    public void setAttackRating(Rating attackRating) {
        this.attackRating = attackRating;
    }
}
