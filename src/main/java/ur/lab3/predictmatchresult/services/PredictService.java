package ur.lab3.predictmatchresult.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ur.lab3.predictmatchresult.domainobjects.models.WhoWinMatchPredictResult;
import ur.lab3.predictmatchresult.repository.mock.MockTeamsRepository;

// Klasa wykorzystywana przez PredictController
@Service
public class PredictService {

    private MockTeamsRepository mockTeamsRepository;

    // Dependency Injection (wstrzykiwanie zależności)
    @Autowired // Obiekt zostaje utworzony i przypisany do pola automatycznie dzieki tej adnotacji
    public PredictService(MockTeamsRepository mockTeamsRepository) {
        this.mockTeamsRepository = mockTeamsRepository;
    }

    // Metoda zwraca randomowy wynik, dopóki nie zostanie zaimplementowany algorytm
    public WhoWinMatchPredictResult whoWin(Long homeTeamId, Long awayTeamId) {
        double random = Math.random();

        if (random < 0.25) return WhoWinMatchPredictResult.AWAY;
        else if (random >= 0.25 && random < 0.5) return WhoWinMatchPredictResult.HOME;
        else if (random >= 0.5 && random < 0.75) return WhoWinMatchPredictResult.DRAW;
        else return WhoWinMatchPredictResult.I_DONT_KNOW;
    }
}
