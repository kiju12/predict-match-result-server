package ur.lab3.predictmatchresult.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.FetchedTeam;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.MatchData;
import ur.lab3.predictmatchresult.domainobjects.models.*;
import ur.lab3.predictmatchresult.easportsapi.exception.IncorrectTeamIdException;
import ur.lab3.predictmatchresult.repository.mock.MatchRepository;
import ur.lab3.predictmatchresult.repository.mock.PlayerRepository;
import ur.lab3.predictmatchresult.repository.mock.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamsService {

    private final static Formation DEFAULT_FORMATION = new Formation(4,3,3);

    private FifaUltimateTeamService fifaUltimateTeamService;
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public void fetchAllTeams(Formation formation) {
        List<Team> allTeamsWithFormation = fifaUltimateTeamService.getAllTeamsWithFormation(formation);

        for (Team team : allTeamsWithFormation) {
            try {
                saveOrUpdateTeam(team);
            } catch (IncorrectTeamIdException e) {
                continue;
            }
        }

    }

    public void fetchAllTeams() {
        List<Team> allTeamsWithFormation = fifaUltimateTeamService.getAllTeamsWithFormation(DEFAULT_FORMATION);

        for (Team team : allTeamsWithFormation) {
            try {
                saveOrUpdateTeam(team);
            } catch (IncorrectTeamIdException e) {
                continue;
            }
        }

    }

    public FetchedTeam saveTeam(Long fifaTeamId, Formation formation) throws IncorrectTeamIdException {
        Team team = fifaUltimateTeamService.getFirstSquad(fifaTeamId, formation);

        return saveOrUpdateTeam(team);
    }

    private FetchedTeam saveOrUpdateTeam(Team team) throws IncorrectTeamIdException {
        if (team != null) {
            if (teamRepository.existsById(team.getId()))
                teamRepository.deletePlayers(team.getId());

            for (Player player : team.getPlayers())
                player.setTeam(team);

            team = teamRepository.save(team);
        } else throw new IncorrectTeamIdException();

        return FetchedTeam.createFromTeam(team);
    }

    public void addMatches(Long homeTeamId, Long awayTeamId, List<MatchData> matches) throws IncorrectTeamIdException {
        Optional<Team> homeTeam = teamRepository.findById(homeTeamId);
        Optional<Team> awayTeam = teamRepository.findById(awayTeamId);

        if (homeTeam.isPresent() && awayTeam.isPresent()) {
            matches.stream().forEach(matchData -> {
                War war = new War(
                        matchData.getHomeGoals(),
                        matchData.getAwayGoals(),
                        new ArrayList<>());

                TeamWar matchAsHome = new TeamWar();
                matchAsHome.setWar(war);
                matchAsHome.setTeam(homeTeam.get());
                matchAsHome.setWarType(WarType.HOME);

                TeamWar matchAsAway = new TeamWar();
                matchAsAway.setWar(war);
                matchAsAway.setTeam(awayTeam.get());
                matchAsAway.setWarType(WarType.AWAY);

                war.getTeams().add(matchAsHome);
                war.getTeams().add(matchAsAway);
                matchRepository.save(war);
            });
        } else throw new IncorrectTeamIdException();

    }


//    public List<FetchedTeam> getDataOfTeamsThatAreAvailibleToPredict() {
//        return mockTeamsRepository.getTeamsWithMoreThanFiveMatches();
//    }

//    public List<FetchedTeam> getAllTeams() {
//        return mockTeamsRepository.getAllTeams();
//    }


    @Autowired
    public void setFifaUltimateTeamService(FifaUltimateTeamService fifaUltimateTeamService) {
        this.fifaUltimateTeamService = fifaUltimateTeamService;
    }

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    public boolean teamExists(Long teamId) {
        return teamRepository.existsById(teamId);
    }

    public Set<Player> getTeamPlayers(Long teamId) {
        return playerRepository.findPlayersByTeamId(teamId);
    }

    public List<War> getTeamMatchesIds(Long homeTeamId) {
        return matchRepository.findMatchesIdsByTeamId(homeTeamId);
    }


    public Formation getFormation(Long teamId) {
        String formation = teamRepository.getFormationOfTeam(teamId);
        return Formation.createFromRaw(formation);
    }

    public Long getHomeTeamIdOfMatch(Long matchId) {
        return matchRepository.getHomeTeamIdOfMatch(matchId);
    }

    public Long getAwayTeamIdOfMatch(Long matchId) {
        return matchRepository.getAwayTeamIdOfMatch(matchId);
    }
}
