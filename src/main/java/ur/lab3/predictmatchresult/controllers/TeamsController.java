package ur.lab3.predictmatchresult.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.*;
import ur.lab3.predictmatchresult.domainobjects.models.Formation;
import ur.lab3.predictmatchresult.easportsapi.exception.IncorrectTeamIdException;
import ur.lab3.predictmatchresult.services.TeamsService;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamsController {

    private TeamsService teamsService;

    public TeamsController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

//    @GetMapping
//    public void fetchAll() { teamsService.fetchAllTeams(); }

    @PostMapping
    public ResponseEntity<FetchedTeam> fetchTeam(@RequestBody FetchTeamData fetchTeamData) {
        try {
            FetchedTeam fetchedTeam =
                    teamsService.saveTeam(
                            fetchTeamData.getFifaId(),
                            fetchTeamData.getFormation()
                    );
            return new ResponseEntity<>(fetchedTeam, HttpStatus.CREATED);
        } catch (IncorrectTeamIdException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/matches")
    public ResponseEntity<?> addMatchesToTeams(@RequestBody MatchesData matchesData) {
        try {
            teamsService.addMatches(matchesData.getHomeTeamId(), matchesData.getAwayTeamId(), matchesData.getMatches());
        } catch (IncorrectTeamIdException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping
    public ResponseEntity<List<TeamDTO>> getTeamsAvailibleToPredict() {
        return ResponseEntity.ok(teamsService.getTeamsAvailibleToPredict());
    }

    @GetMapping("/names")
    public ResponseEntity<List<TeamNameDTO>> getTeamNames() {
        return ResponseEntity.ok(teamsService.getTeamNames());
    }








//
//    @GetMapping("/availibleToPredict")
//    public ResponseEntity<List<FetchedTeam>> getTeamsAvailibleToPredict() {
//        var dataOfTeamsThatAreAvailibleToPredict
//                = teamsService.getDataOfTeamsThatAreAvailibleToPredict();
//
//        ResponseEntity<List<FetchedTeam>> response =
//                new ResponseEntity<>(dataOfTeamsThatAreAvailibleToPredict, HttpStatus.OK);
//
//        return response;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<FetchedTeam>> getAllTeams() {
//        return new ResponseEntity<>(teamsService.getAllTeams(), HttpStatus.OK);
//    }
//
//    @PostMapping("/{teamId}/matches")
//    public ResponseEntity<?> addMatchesToTeam(
//            @PathVariable("teamId") Long teamId,
//            @RequestBody MatchesData matches) {
//
//        return ResponseEntity.ok(null);
//    }

}
