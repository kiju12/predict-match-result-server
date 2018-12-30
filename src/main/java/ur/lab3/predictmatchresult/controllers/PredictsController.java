package ur.lab3.predictmatchresult.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.PredictResult;
import ur.lab3.predictmatchresult.services.PredictService;

@RestController
@RequestMapping("/api/predict")
public class PredictsController {

    private PredictService predictService;

    @Autowired
    public PredictsController(PredictService predictService) {
        this.predictService = predictService;
    }

    @GetMapping
    public ResponseEntity<PredictResult> whoWin(@RequestParam("homeTeamId") Long homeTeamId,
                                                @RequestParam("awayTeamId") Long awayTeamId) {
        return ResponseEntity.ok(new PredictResult(predictService.whoWin(homeTeamId, awayTeamId)));
    }
}
