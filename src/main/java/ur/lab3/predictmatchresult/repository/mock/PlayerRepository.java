package ur.lab3.predictmatchresult.repository.mock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ur.lab3.predictmatchresult.domainobjects.models.Player;

import java.util.Set;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    @Query("SELECT P FROM Player P WHERE P.team.id = :teamId")
    Set<Player> findPlayersByTeamId(@Param("teamId") Long teamId);
}
