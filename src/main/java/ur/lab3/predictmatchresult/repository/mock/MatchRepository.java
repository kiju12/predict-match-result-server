package ur.lab3.predictmatchresult.repository.mock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ur.lab3.predictmatchresult.domainobjects.models.TeamWar;
import ur.lab3.predictmatchresult.domainobjects.models.War;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<War, Long> {

    @Query("SELECT TW.war FROM TeamWar TW WHERE TW.team.id = :teamId")
    List<War> findMatchesIdsByTeamId(@Param("teamId") Long homeTeamId);

    @Query("SELECT TW.team.id FROM TeamWar TW WHERE TW.war.id = :warId AND TW.warType = 'HOME'")
    Long getHomeTeamIdOfMatch(@Param("warId") Long matchId);

    @Query("SELECT TW.team.id FROM TeamWar TW WHERE TW.war.id = :warId AND TW.warType = 'AWAY'")
    Long getAwayTeamIdOfMatch(@Param("warId") Long matchId);
}
