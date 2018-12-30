package ur.lab3.predictmatchresult.services;

import org.junit.Before;
import org.junit.Test;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.MatchData;
import ur.lab3.predictmatchresult.domainobjects.models.Team;
import ur.lab3.predictmatchresult.repository.mock.TeamRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TeamsServiceTest {

    private TeamsService teamsService;
    private TeamRepository teamRepository;

    @Before
    public void setUp() {
        teamsService = new TeamsService();
        teamRepository = mock(TeamRepository.class);
        teamsService.setTeamRepository(teamRepository);
    }

}