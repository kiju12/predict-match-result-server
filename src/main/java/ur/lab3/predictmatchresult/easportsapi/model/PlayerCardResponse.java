package ur.lab3.predictmatchresult.easportsapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PlayerCardResponse {

    private Integer page;
    private Integer totalPages;
    private Integer totalResults;
    private Integer count;
    private List<PlayerCard> playerCards;

    public PlayerCardResponse() {
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonProperty("totalPages")
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @JsonProperty("totalResults")
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("items")
    public void setPlayerCards(List<PlayerCard> playerCards) {
        this.playerCards = playerCards;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getCount() {
        return count;
    }

    public List<PlayerCard> getPlayerCards() {
        if (playerCards == null)
            playerCards = new ArrayList<>();

        return playerCards;
    }
}
