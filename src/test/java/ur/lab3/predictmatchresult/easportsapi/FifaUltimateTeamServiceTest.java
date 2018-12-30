package ur.lab3.predictmatchresult.easportsapi;

import org.junit.Before;
import org.junit.Test;
import ur.lab3.predictmatchresult.domainobjects.models.Formation;
import ur.lab3.predictmatchresult.domainobjects.models.Player;
import ur.lab3.predictmatchresult.domainobjects.models.Position;
import ur.lab3.predictmatchresult.domainobjects.models.Team;
import ur.lab3.predictmatchresult.services.FifaUltimateTeamService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;

public class FifaUltimateTeamServiceTest {

    private FifaUltimateTeamService service;
    private FifaUltimateTeamRepository repository;

    @Before
    public void setUp() {
        service = new FifaUltimateTeamService();
        repository = spy(FifaUltimateTeamRepository.class);
        service.setFifaUltimateTeamRepository(repository);
    }

    @Test
    public void resolvePosition_incorrectPositions_unknown() {
        Position incorrectPosition = service.resolvePosition("incorrectPosition");
        assertEquals(Position.UNKNOWN, incorrectPosition);
    }

    @Test
    public void resolvePosition_correctPositions_correctPosition() {
        List<String> att = Arrays.asList("LN", "ŚN", "PN", "N", "ŚPO");
//        List<String> att = Arrays.asList("LF", "CF", "RF", "ST", "CAM");
        List<String> help = Arrays.asList("LS", "LP", "ŚP", "PP", "PS");
//        List<String> help = Arrays.asList("LW", "LM", "CM", "RM", "RW");
        List<String> def = Arrays.asList("CLS", "LO", "ŚO", "PO", "CPS");
//        List<String> def = Arrays.asList("CDM", "LWB", "LB", "CB", "RB", "RWB", "GK");

        for (String attackRawPosition : att)
            assertEqualsAttack(service.resolvePosition(attackRawPosition));

        for (String helpRawPosition : help)
            assertEqualsHelp(service.resolvePosition(helpRawPosition));

        for (String defRawPosition : def)
            assertEqualsDef(service.resolvePosition(defRawPosition));

        assertEquals(Position.GOALKEEPER, service.resolvePosition("BR"));
    }

    private void assertEqualsAttack(Position pos) {
        assertEquals(Position.ATTACK, pos);
    }

    private void assertEqualsHelp(Position pos) {
        assertEquals(Position.HELP, pos);
    }

    private void assertEqualsDef(Position pos) {
        assertEquals(Position.DEFENCE, pos);
    }

    @Test
    public void getFirstSquad_liverpoolId_getCorrectTeamData() {
        Team team = service.getFirstSquad(9L, new Formation(4, 4, 2));

        int defensive = 0;
        int help = 0;
        int attackers = 0;
        int goalkeepers = 0;
        int unknown = 0;

        assertEquals(11, team.getPlayers().size());

        for (Player p : team.getPlayers()) {
            assertNotNull(p.getId());
            assertNotNull(p.getRating());
            assertNotNull(p.getPosition());
            assertNotNull(p.getFirstname());
            assertNotNull(p.getLastname());

            switch (p.getPosition()) {
                case ATTACK:
                    attackers++; break;
                case DEFENCE:
                    defensive++; break;
                case HELP:
                    help++; break;
                case UNKNOWN:
                    unknown++;
            }

            if (p.getGoalkeeper())
                goalkeepers++;
        }

        assertEquals("4-4-2", team.getFormation());
        assertEquals(5, defensive);
        assertEquals(4, help);
        assertEquals(2, attackers);
        assertEquals(1, goalkeepers);
        assertEquals(0, unknown);

        assertEquals("Liverpool", team.getName());
        assertEquals("https://www.easports.com/fifa/ultimate-team/web-app/content/7D49A6B1-760B-4491-B10C-167FBC81D58A/2019/fut/items/images/mobile/clubs/normal/9.png",
                team.getImageLink());

    }
}