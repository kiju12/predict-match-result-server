package ur.lab3.predictmatchresult.easportsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class League {

    @JsonProperty("idList")
    private Long[] clubIds;

    public League() {
    }

    public Long[] getClubIds() {
        return clubIds;
    }

    public void setClubIds(Long[] clubIds) {
        this.clubIds = clubIds;
    }
}
