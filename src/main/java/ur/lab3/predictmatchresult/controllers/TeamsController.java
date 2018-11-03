package ur.lab3.predictmatchresult.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.FetchTeamData;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.MatchData;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.MatchesData;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.TeamData;
import ur.lab3.predictmatchresult.services.TeamsService;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamsController {

    private TeamsService teamsService;

    public TeamsController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @GetMapping("/availibleToPredict")
    public ResponseEntity<List<TeamData>> getTeamsAvailibleToPredict() {
        var dataOfTeamsThatAreAvailibleToPredict
                = teamsService.getDataOfTeamsThatAreAvailibleToPredict();

        ResponseEntity<List<TeamData>> response =
                new ResponseEntity<>(dataOfTeamsThatAreAvailibleToPredict, HttpStatus.OK);

        return response;
    }

    @GetMapping
    public ResponseEntity<List<TeamData>> getAllTeams() {
        return new ResponseEntity<>(teamsService.getAllTeams(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeamData> fetchTeam(@RequestBody FetchTeamData fetchTeamData) throws InterruptedException {
        Thread.sleep(5000L);
        return new ResponseEntity<>(
                new TeamData(
                        1L,
                        fetchTeamData.getTeamName(),
                        "no link in mock"
                ), HttpStatus.OK);
    }

    @PostMapping("/{teamId}/matches")
    public ResponseEntity<?> addMatchesToTeam(
            @PathVariable("teamId") Long teamId,
            @RequestBody MatchesData matches) {

        return ResponseEntity.ok(null);
    }

}
