package ur.lab3.predictmatchresult.services.predict;

import ur.lab3.predictmatchresult.domainobjects.models.MatchResult;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MrPredictor {

    private TeamRating homeTeamRating;
    private TeamRating awayTeamRating;
    private List<WarData> trainData;

    private ArrayList<Attribute> attributes;

    public MrPredictor(TeamRating homeTeamRating, TeamRating awayTeamRating, List<WarData> trainData) throws NoTrainSetException {
        if (trainData == null || trainData.isEmpty())
            throw new NoTrainSetException("Brak danych treningowych - drużyny nie zawierają meczy.");

        this.homeTeamRating = homeTeamRating;
        this.awayTeamRating = awayTeamRating;
        this.trainData = trainData;

        init();
    }

    private void init() {
        attributes = new ArrayList<>(7);
        attributes.add(new Attribute("FT_GOALKEEPER"));
        attributes.add(new Attribute("FT_DEFENCE"));
        attributes.add(new Attribute("FT_HELP"));
        attributes.add(new Attribute("FT_ATTACK"));
        attributes.add(new Attribute("ST_GOALKEEPER"));
        attributes.add(new Attribute("ST_DEFENCE"));
        attributes.add(new Attribute("ST_HELP"));
        attributes.add(new Attribute("ST_ATTACK"));
        attributes.add(new Attribute("WHO_WIN", Arrays.asList("FIRST", "SECOND", "DRAW")));
    }

    public MatchResult predict() throws Exception {
        Instances train = prepareTrainData();
        if (train.classIndex() == -1)
            train.setClassIndex(train.numAttributes() - 1);

        Instances test = preparePredictData();
        if (test.classIndex() == -1)
            test.setClassIndex(train.numAttributes() - 1);

        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.buildClassifier(train);

        double label = naiveBayes.classifyInstance(test.instance(0));
        test.instance(0).setClassValue(label);

        String predictResult = test.instance(0).stringValue(8);
        switch (predictResult) {
            case "FIRST":
                return MatchResult.HOME;
            case "SECOND":
                return MatchResult.AWAY;
            default:
                return MatchResult.DRAW;
        }
    }

    private Instances preparePredictData() {
        Instances data = new Instances("Matches", attributes, 0);
        data.add(new DenseInstance(1.0, createPredictRecord(homeTeamRating, awayTeamRating)));
        return data;
    }

    private Instances prepareTrainData() {
        Instances dataSet = new Instances("Matches", attributes, 0);

        List<double[]> instances = new ArrayList<>();
        trainData.forEach(warData -> {

            TeamRating homeTeamRating = warData.getHomeTeamRating();
            TeamRating awayTeamRating = warData.getAwayTeamRating();

            int resultIndexFirst;
            switch (warData.getWhoWin()) {
                case HOME:
                    resultIndexFirst = 0;
                    break;
                case AWAY:
                    resultIndexFirst = 1;
                    break;
                default:
                    resultIndexFirst = 2;
            }

            double[] trainSetRecord = createTrainSetRecord(
                    homeTeamRating,
                    awayTeamRating,
                    resultIndexFirst);

            instances.add(trainSetRecord);
        });

        instances.forEach(instance -> dataSet.add(new DenseInstance(1.0, instance)));
        return dataSet;
    }

    private double[] createTrainSetRecord(TeamRating firstTeamRating, TeamRating secondTeamRating, int resultIndex) {
        double[] record = new double[attributes.size()];

        record[0] = firstTeamRating.getGoalKeeperRating().getNormalizedForm();
        record[1] = firstTeamRating.getDefenseRating().getNormalizedForm();
        record[2] = firstTeamRating.getHelpRating().getNormalizedForm();
        record[3] = firstTeamRating.getAttackRating().getNormalizedForm();

        record[4] = secondTeamRating.getGoalKeeperRating().getNormalizedForm();
        record[5] = secondTeamRating.getDefenseRating().getNormalizedForm();
        record[6] = secondTeamRating.getHelpRating().getNormalizedForm();
        record[7] = secondTeamRating.getAttackRating().getNormalizedForm();
        record[8] = resultIndex;

        return record;
    }

    private double[] createPredictRecord(TeamRating firstTeamRating, TeamRating secondTeamRating) {
        double[] record = new double[attributes.size()];

        record[0] = firstTeamRating.getGoalKeeperRating().getNormalizedForm();
        record[1] = firstTeamRating.getDefenseRating().getNormalizedForm();
        record[2] = firstTeamRating.getHelpRating().getNormalizedForm();
        record[3] = firstTeamRating.getAttackRating().getNormalizedForm();

        record[4] = secondTeamRating.getGoalKeeperRating().getNormalizedForm();
        record[5] = secondTeamRating.getDefenseRating().getNormalizedForm();
        record[6] = secondTeamRating.getHelpRating().getNormalizedForm();
        record[7] = secondTeamRating.getAttackRating().getNormalizedForm();

        return record;
    }


}
