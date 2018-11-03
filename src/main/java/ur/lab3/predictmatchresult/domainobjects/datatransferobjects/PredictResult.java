package ur.lab3.predictmatchresult.domainobjects.datatransferobjects;

import ur.lab3.predictmatchresult.domainobjects.models.WhoWinMatchPredictResult;

public class PredictResult {
    private WhoWinMatchPredictResult result;

    public PredictResult() {
        result = WhoWinMatchPredictResult.I_DONT_KNOW;
    }

    public PredictResult(WhoWinMatchPredictResult result) {
        this.result = result;
    }

    public WhoWinMatchPredictResult getResult() {
        return result;
    }

    public void setResult(WhoWinMatchPredictResult result) {
        this.result = result;
    }
}
