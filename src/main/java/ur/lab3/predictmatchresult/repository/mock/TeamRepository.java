package ur.lab3.predictmatchresult.repository.mock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.TeamDTO;
import ur.lab3.predictmatchresult.domainobjects.datatransferobjects.TeamNameDTO;
import ur.lab3.predictmatchresult.domainobjects.models.Player;
import ur.lab3.predictmatchresult.domainobjects.models.Team;
import ur.lab3.predictmatchresult.domainobjects.models.TeamWar;
import ur.lab3.predictmatchresult.domainobjects.models.War;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Player P WHERE P.team.id = :teamId")
    void deletePlayers(@Param("teamId") Long teamId);

    boolean existsById(Long id);

    @Query("SELECT T.formation FROM Team T where T.id = :teamId")
    String getFormationOfTeam(@Param("teamId") Long teamId);

    @Query("SELECT T FROM Team T where T.wars.size > 0")
    List<Team> findAllByHasMatches();

    @Query("SELECT T.id as id, T.name as name FROM Team T")
    List<TeamNameDTO> findAllNames();


//    @Query("SELECT P FROM Player P WHERE P.team.id = :teamId")
//    Set<Player> findPlayersByTeamId(@Param("teamId") Long teamId);
//
//    @Query("SELECT TW FROM TeamWar TW WHERE TW.team.id = :teamId")
//    List<TeamWar> findMatchesByTeamIdOrderByMatchDate(@Param("teamId") Long teamId);
}
