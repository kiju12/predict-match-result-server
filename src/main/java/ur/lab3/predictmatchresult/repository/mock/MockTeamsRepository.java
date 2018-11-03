package ur.lab3.predictmatchresult.repository.mock;

import org.springframework.stereotype.Repository;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.TeamData;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MockTeamsRepository {

    public List<TeamData> getTeamsWithMoreThanFiveMatches() {
        var astonVilla = new TeamData();
        astonVilla.setId(1L);
        astonVilla.setLinkToImage("https://www.easports.com/fifa/ultimate-team/web-app/content/7D49A6B1-760B-4491-B10C-167FBC81D58A/2019/fut/items/images/mobile/clubs/dark/2.png");
        astonVilla.setName("ASTON VILLA");

        var lyon = new TeamData();
        lyon.setId(2L);
        lyon.setName("OLYMPIC LYON");
        lyon.setLinkToImage("https://www.easports.com/fifa/ultimate-team/web-app/content/7D49A6B1-760B-4491-B10C-167FBC81D58A/2019/fut/items/images/mobile/clubs/dark/66.png");

        var hansa = new TeamData();
        hansa.setId(3L);
        hansa.setName("FC HANSA");
        hansa.setLinkToImage("https://www.easports.com/fifa/ultimate-team/web-app/content/7D49A6B1-760B-4491-B10C-167FBC81D58A/2019/fut/items/images/mobile/clubs/dark/27.png");

        var borussia = new TeamData();
        borussia.setId(4L);
        borussia.setName("BORUSSIA DORTMUND");
        borussia.setLinkToImage("https://www.easports.com/fifa/ultimate-team/web-app/content/7D49A6B1-760B-4491-B10C-167FBC81D58A/2019/fut/items/images/mobile/clubs/dark/22.png");

        ArrayList<TeamData> mockTeams = new ArrayList<>();
        mockTeams.add(astonVilla);
        mockTeams.add(lyon);
        mockTeams.add(hansa);
        mockTeams.add(borussia);

        return mockTeams;
    }

    public List<TeamData> getAllTeams() {
        return getTeamsWithMoreThanFiveMatches();
    }
}
