package ur.lab3.predictmatchresult.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.TeamData;
import ur.lab3.predictmatchresult.repository.mock.MockTeamsRepository;

import java.util.List;

@Service
public class TeamsService {

    private MockTeamsRepository mockTeamsRepository;

    @Autowired
    public TeamsService(MockTeamsRepository mockTeamsRepository) {
        this.mockTeamsRepository = mockTeamsRepository;
    }

    public List<TeamData> getDataOfTeamsThatAreAvailibleToPredict() {
        return mockTeamsRepository.getTeamsWithMoreThanFiveMatches();
    }

    public List<TeamData> getAllTeams() {
        return mockTeamsRepository.getAllTeams();
    }
}
