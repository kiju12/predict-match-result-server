package ur.lab3.predictmatchresult.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ur.lab3.predictmatchresult.domainobjects.models.Formation;
import ur.lab3.predictmatchresult.domainobjects.models.Player;
import ur.lab3.predictmatchresult.domainobjects.models.Position;
import ur.lab3.predictmatchresult.domainobjects.models.Team;
import ur.lab3.predictmatchresult.easportsapi.FifaUltimateTeamRepository;
import ur.lab3.predictmatchresult.easportsapi.model.PlayerCard;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FifaUltimateTeamService {

    private FifaUltimateTeamRepository fifaUltimateTeamRepository;

    public List<Team> getAllTeamsWithFormation(Formation formation) {
        List<Long> allClubIds = fifaUltimateTeamRepository.getAllClubIds();
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < allClubIds.size(); i++) {
            Team firstSquad = null;
            try {
                 firstSquad = getFirstSquad(allClubIds.get(i), formation);
            } catch (Exception x) {
                continue;
            }
            System.out.println("Pobrano " + (i+1) + " na " + allClubIds.size());
            teams.add(firstSquad);
        }

        return teams;
    }

    public Team getFirstSquad(Long teamId, Formation formation) {
        if (teamId == null || formation == null)
            return null;

        List<PlayerCard> fifaCardsOfTeam = fifaUltimateTeamRepository.getFifaCardsOfTeam(teamId);

        List<PlayerCard> normalPlayers =
                fifaCardsOfTeam.stream().filter(card -> !card.getGoalkeeper()).collect(Collectors.toList());
        List<PlayerCard> goalkeepers =
                fifaCardsOfTeam.stream().filter(card -> card.getGoalkeeper()).collect(Collectors.toList());

        if (fifaCardsOfTeam.isEmpty()) return null;

        Team team = new Team();
        team.setName(fifaCardsOfTeam.get(0).getClubName());
        team.setImageLink(fifaCardsOfTeam.get(0).getClubImageLink());

        Set<Player> players = new HashSet<>();
        normalPlayers.forEach(card -> players.add(convertCardToPlayer(card)));

        Set<Player> goalkeeperSET = new HashSet<>();
        goalkeepers.forEach(card -> goalkeeperSET.add(convertCardToPlayer(card)));

        List<Player> attackers = sortByRating(filterToListByPosition(players, Position.ATTACK));
        List<Player> helpers = sortByRating(filterToListByPosition(players, Position.HELP));
        List<Player> defencers = sortByRating(filterToListByPosition(players, Position.DEFENCE));
        List<Player> goalkeepersDistinct = sortByRating(new ArrayList<>(goalkeeperSET));

        Set<Player> theBestAttackers = getFirstPlayers(formation.getNumerOfAttackPlayers(), attackers, Position.ATTACK);
        Set<Player> theBestHelpers = getFirstPlayers(formation.getNumerOfHelpPlayers(), helpers, Position.HELP);
        Set<Player> theBestDefencers = getFirstPlayers(formation.getNumerOfDefensivePlayers(), defencers, Position.DEFENCE);
        Optional<Player> potTheBestGoalKeeper = goalkeepersDistinct.stream().findFirst();

        Player theBestGoalKeeper = null;
        if (potTheBestGoalKeeper.isPresent()) {
            theBestGoalKeeper = potTheBestGoalKeeper.get();
        } else {
            theBestGoalKeeper = new Player();
            theBestGoalKeeper.setGoalkeeper(true);
            theBestGoalKeeper.setRating(60);
            theBestGoalKeeper.setPosition(Position.GOALKEEPER);
            theBestGoalKeeper.setId(UUID.randomUUID().toString());
            theBestGoalKeeper.setFirstname(UUID.randomUUID().toString());
            theBestGoalKeeper.setLastname(UUID.randomUUID().toString());
        }


        HashSet<Player> squad = new HashSet<>();
        squad.addAll(theBestAttackers);
        squad.addAll(theBestHelpers);
        squad.addAll(theBestDefencers);
        squad.add(theBestGoalKeeper);

        team.setPlayers(squad);
        team.setId(teamId);
        team.setFormation(formation.getNumerOfDefensivePlayers() + "-"
                + formation.getNumerOfHelpPlayers() + "-"+ formation.getNumerOfAttackPlayers());
        return team;
    }

    private Set<Player> getFirstPlayers(Integer numerOfAttackPlayers, List<Player> attackers, Position position) {
        if (attackers.size() >= numerOfAttackPlayers)
            return new HashSet<>(attackers.subList(0, numerOfAttackPlayers));
        else {
            HashSet<Player> players = new HashSet<>(attackers);

            int size = players.size();
            int howMuchPlayersWeHaveToMake = numerOfAttackPlayers - size;

            for (int i = 0; i < howMuchPlayersWeHaveToMake; i++)
                players.add(createRandomPlayer(position));

            return players;
        }
    }

    private Player createRandomPlayer(Position position) {
        Player player = new Player();
        player.setId(UUID.randomUUID().toString());
        player.setFirstname(UUID.randomUUID().toString());
        player.setLastname(UUID.randomUUID().toString());
        player.setPosition(position);
        player.setRating(60);
        player.setGoalkeeper(false);
        return player;
    }

    private List<Player> sortByRating(List<Player> players) {
        if (players.isEmpty()) return players;

        return players.stream().sorted((player1, player2) -> {
            if (player1.getRating() > player2.getRating())
                return -1;
            else if (player1.getRating() < player2.getRating())
                return 1;
            else return 0;
        }).collect(Collectors.toList());
    }

    public List<Player> filterToListByPosition(Set<Player> players, Position pos) {
        if (players.isEmpty()) return Collections.emptyList();

        return players.stream()
                .filter(player -> pos.equals(player.getPosition()))
                .collect(Collectors.toList());
    }

    private Player convertCardToPlayer(PlayerCard card) {
        Player player = new Player();
        player.setFirstname(card.getFirstname());
        player.setLastname(card.getLastname());
        player.setId(card.getId());
        player.setPosition(resolvePosition(card.getPosition()));
        player.setRating(card.getRating());
        player.setGoalkeeper(card.getGoalkeeper());
        return player;
    }

    public Position resolvePosition(String rawPosition) {
        List<String> att = Arrays.asList("LN", "ŚN", "PN", "N", "ŚPO");
//        List<String> att = Arrays.asList("LF", "CF", "RF", "ST", "CAM");
        List<String> help = Arrays.asList("LS", "LP", "ŚP", "PP", "PS");
//        List<String> help = Arrays.asList("LW", "LM", "CM", "RM", "RW");
        List<String> def = Arrays.asList("CLS", "LO", "ŚO", "PO", "CPS");
//        List<String> def = Arrays.asList("CDM", "LWB", "LB", "CB", "RB", "RWB", "GK");

        if (att.contains(rawPosition))
            return Position.ATTACK;

        if (help.contains(rawPosition))
            return Position.HELP;

        if (def.contains(rawPosition))
            return Position.DEFENCE;

        if (rawPosition.equals("BR"))
            return Position.GOALKEEPER;

        return Position.UNKNOWN;
    }

    @Autowired
    public void setFifaUltimateTeamRepository(FifaUltimateTeamRepository fifaUltimateTeamRepository) {
        this.fifaUltimateTeamRepository = fifaUltimateTeamRepository;
    }
}
