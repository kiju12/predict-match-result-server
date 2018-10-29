package ur.lab3.predictmatchresult.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @GetMapping("/api/teamNames")
    public List<TeamNameAndId> getNames() {
        String team1 = "Legiunia";
        String team2 = "Real Madryyyd";
        String team3 = "hwdp";
        String team4 = "cycki";

        List<TeamNameAndId> list = new ArrayList<>();

        list.add(new TeamNameAndId(team1, 1L));
        list.add(new TeamNameAndId(team2, 2L));
        list.add(new TeamNameAndId(team3, 3L));

        return list;
    }


    private class TeamNameAndId {
        private String teamName;
        private Long id;

        public TeamNameAndId(String teamName, Long id) {
            this.teamName = teamName;
            this.id = id;
        }

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
