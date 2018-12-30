package ur.lab3.predictmatchresult.easportsapi;

import org.junit.Before;
import org.junit.Test;
import ur.lab3.predictmatchresult.easportsapi.model.PlayerCard;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class FifaUltimateTeamRepositoryTest {

    private FifaUltimateTeamRepository fifaUltimateTeamRepository;

    @Before
    public void setUp() {
        fifaUltimateTeamRepository = new FifaUltimateTeamRepository();
    }

    @Test
    public void getFifaCardsOfTeam_notExistingId_returnEmptyTeam() {
        List<PlayerCard> fifaCardsOfTeam = fifaUltimateTeamRepository.getFifaCardsOfTeam(239187239182739212L);
        assertTrue(fifaCardsOfTeam.isEmpty());

        List<PlayerCard> fifaCardsOfTeam1 = fifaUltimateTeamRepository.getFifaCardsOfTeam(25090L);
        assertTrue(fifaCardsOfTeam1.isEmpty());
    }

    @Test
    public void getFifaCardsOfTeam_nullId_returnEmtpyTeam() {
        List<PlayerCard> fifaCardsOfTeam = fifaUltimateTeamRepository.getFifaCardsOfTeam(null);
        assertTrue(fifaCardsOfTeam.isEmpty());
    }

    @Test
    public void getFifaCardsOfTeam_liverpoolTeamId_returnLiverpoolTeamWith88Cards() {
        Long teamId = 9L;
        List<PlayerCard> cards = fifaUltimateTeamRepository.getFifaCardsOfTeam(teamId);

        assertEquals(88, cards.size());
        cards.forEach(card -> {
            assertNotNull(card.getId());
            assertNotNull(card.getFirstname());
            assertNotNull(card.getLastname());
            assertNotNull(card.getRating());
            assertNotNull(card.getPosition());
            assertNotNull(card.getGoalkeeper());
            assertNotNull(card.getClubName());
            assertNotNull(card.getClubImageLink());
        });
    }

}