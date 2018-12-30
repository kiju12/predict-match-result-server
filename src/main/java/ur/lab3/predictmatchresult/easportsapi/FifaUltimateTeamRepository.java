package ur.lab3.predictmatchresult.easportsapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import ur.lab3.predictmatchresult.easportsapi.model.LeagueResponse;
import ur.lab3.predictmatchresult.easportsapi.model.PlayerCard;
import ur.lab3.predictmatchresult.easportsapi.model.PlayerCardResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Repository
public class FifaUltimateTeamRepository {

    private final static String URL1 = "https://www.easports.com/pl/fifa/ultimate-team/api/fut/item?jsonParamObject=%7B%22page%22:";
    private final static String URL2 = ",%22club%22:%22";
    private final static String URL3 = "%22,%22position%22:%22GK,LF,CF,RF,ST,LW,LM,CAM,CDM,CM,RM,RW,LWB,LB,CB,RB,RWB%22%7D";

    private final static String ALL_CLUB_IDS_URL = "https://www.easports.com/pl/fifa/ultimate-team/api/fut/display";

    public List<PlayerCard> getFifaCardsOfTeam(Long teamId) {
        int page = 1;
        URI uri;
        try {
            uri = getUri(teamId, page);
        } catch (URISyntaxException e) {
            return Collections.emptyList();
        }

        List<PlayerCard> playerCards = new ArrayList<>();


        RestTemplate restTemplate = new RestTemplate();
        PlayerCardResponse firstPageObjects = null;
        try {
            firstPageObjects = restTemplate.getForObject(uri, PlayerCardResponse.class);
        } catch (HttpServerErrorException ex) {
            return Collections.EMPTY_LIST;
        }

        firstPageObjects.getPlayerCards().stream().forEach(card -> playerCards.add(card));

        int totalPages = firstPageObjects.getTotalPages();

        for (int i = page + 1; i <= totalPages; i++) {
            PlayerCardResponse forObject = null;
            try {
                forObject = restTemplate.getForObject(getUri(teamId, i), PlayerCardResponse.class);
            } catch (URISyntaxException e) {
                return Collections.emptyList();
            }
            forObject.getPlayerCards().forEach(card -> playerCards.add(card));
        }

        return playerCards;
    }

    private URI getUri(Long teamId, int page) throws URISyntaxException {
        return new URI(URL1 + page + URL2 + teamId + URL3);
    }

    public List<Long> getAllClubIds() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        TypeReference<LeagueResponse> typeReference = new TypeReference<LeagueResponse>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/all-leagues.json");

        LeagueResponse leagues = null;
        try {
            leagues = mapper.readValue(inputStream,typeReference);
        } catch (IOException e){
            System.out.println("Unable to fetch clubs ids: " + e.getMessage());
            return new ArrayList<>();
        }

        List<Long> ids = new ArrayList<>();
        leagues.getLeagues().stream().forEach(league -> ids.addAll(Arrays.asList(league.getClubIds())));
        return ids;
    }

}
