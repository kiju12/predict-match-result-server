package ur.lab3.predictmatchresult.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ur.lab3.predictmatchresult.domainobjects.models.*;
import ur.lab3.predictmatchresult.easportsapi.exception.IncorrectTeamIdException;
import ur.lab3.predictmatchresult.services.predict.*;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PredictService {

    private TeamsService teamsService;

    public MatchResult whoWin(Long homeTeamId, Long awayTeamId) {
        if (homeTeamId == null || awayTeamId == null) throw new IncorrectTeamIdException();
        if (homeTeamId == awayTeamId) throw new IncorrectTeamIdException();
        boolean homeTeamNotExists = !teamsService.teamExists(homeTeamId);
        boolean awayTeamNotExsits = !teamsService.teamExists(awayTeamId);

        if (homeTeamNotExists || awayTeamNotExsits) throw new IncorrectTeamIdException();

        MrPredictor mrPredictor = new MrPredictor(
                getTeamRating(homeTeamId),
                getTeamRating(awayTeamId),
                getWarsDataOfTwoTeams(homeTeamId, awayTeamId)
        );

        try {
            return mrPredictor.predict();
        } catch (Exception e) {
            return MatchResult.I_DONT_KNOW;
        }
    }

    private List<WarData> getWarsDataOfTwoTeams(Long homeTeamId, Long awayTeamId) {
        List<WarData> warData = new ArrayList<>();

        teamsService.getTeamMatchesIds(homeTeamId)
                .stream()
                .forEach(match -> warData.add(getWarData(match)));

        teamsService.getTeamMatchesIds(awayTeamId)
                .stream()
                .forEach(match -> warData.add(getWarData(match)));

        return warData;
    }

    private WarData getWarData(War match) {
        Long matchHomeTeamId = teamsService.getHomeTeamIdOfMatch(match.getId());
        Long matchAwayTeamId = teamsService.getAwayTeamIdOfMatch(match.getId());

        Integer homeGoals = match.getHomeGoals();
        Integer awayGoals = match.getAwayGoals();
        MatchResult result = null;
        if (homeGoals > awayGoals) result = MatchResult.HOME;
        if (homeGoals < awayGoals) result = MatchResult.AWAY;
        if (homeGoals.equals(awayGoals)) result = MatchResult.DRAW;

        return new WarData(
                getTeamRating(matchHomeTeamId),
                getTeamRating(matchAwayTeamId),
                result);
    }

    private TeamRating getTeamRating(Long teamId) {
        TeamRating teamRating = new TeamRating();
        Set<Player> teamPlayers = teamsService.getTeamPlayers(teamId);
        Player goalKeeper = teamPlayers
                .stream()
                .filter(player -> player.getPosition().equals(Position.GOALKEEPER))
                .findFirst().get();
        teamRating.setGoalKeeperRating(getPlayerRating(goalKeeper));

        Formation formation = teamsService.getFormation(teamId);

        Set<Player> defencePlayers = teamPlayers.stream()
                .filter(player -> player.getPosition().equals(Position.DEFENCE))
                .collect(Collectors.toSet());
        teamRating.setDefenseRating(
                getPositionRating(defencePlayers, formation.getNumerOfDefensivePlayers())
        );

        Set<Player> helpPlayers = teamPlayers.stream()
                .filter(player -> player.getPosition().equals(Position.HELP))
                .collect(Collectors.toSet());
        teamRating.setHelpRating(
                getPositionRating(helpPlayers, formation.getNumerOfHelpPlayers())
        );

        Set<Player> attackPlayers = teamPlayers.stream()
                .filter(player -> player.getPosition().equals(Position.ATTACK))
                .collect(Collectors.toSet());
        teamRating.setAttackRating(
                getPositionRating(attackPlayers, formation.getNumerOfAttackPlayers())
        );

        return teamRating;
    }

    private Rating getPositionRating(Set<Player> players, Integer numberOfPlayersOnPosition) {
        if (players == null)
            return getDefaultRatingForPosition(numberOfPlayersOnPosition);
        else if (players.size() < numberOfPlayersOnPosition) {
            int numerOfDefaultRatings = numberOfPlayersOnPosition - players.size();

            int actualDefault = 60 * numerOfDefaultRatings;
            int minDefault = 1 * numerOfDefaultRatings;
            int maxDefault = 100 * numerOfDefaultRatings;

            for (Player player : players) {
                actualDefault += player.getRating();
                minDefault += 1;
                maxDefault += 100;
            }

            return new Rating(actualDefault, minDefault, maxDefault);
        } else {
            int actual = 0;
            int min = 1* players.size();
            int max = 100 * players.size();

            for (Player player : players) {
                actual += player.getRating();
            }

            return new Rating(actual, min, max);
        }

    }

    private Rating getDefaultRatingForPosition(Integer numberOfPlayers) {
        return new Rating(60 * numberOfPlayers, 1 * numberOfPlayers, 100 * numberOfPlayers);
    }

    private Rating getPlayerRating(Player player) {
        if (player == null) return new Rating(60, 1, 100);
        else return new Rating(player.getRating(), 1, 100);
    }


    @Autowired
    private void setTeamsService(TeamsService teamsService) { this.teamsService = teamsService; }

}
