package ur.lab3.predictmatchresult.domainobjects.datatransferobjects;

import ur.lab3.predictmatchresult.domainobjects.models.MatchResult;

public class PredictResult {
    private MatchResult result;

    public PredictResult() {
//        result = MatchResult.I_DONT_KNOW;
    }

    public PredictResult(MatchResult result) {
        this.result = result;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }
}
