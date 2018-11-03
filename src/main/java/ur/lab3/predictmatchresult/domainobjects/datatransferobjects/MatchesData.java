package ur.lab3.predictmatchresult.domainobjects.datatransferobjects;

import java.util.List;

public class MatchesData {
    private List<MatchData> matches;

    public MatchesData() {
    }

    public List<MatchData> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchData> matches) {
        this.matches = matches;
    }
}
