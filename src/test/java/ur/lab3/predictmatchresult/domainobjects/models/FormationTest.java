package ur.lab3.predictmatchresult.domainobjects.models;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FormationTest {

    @Test
    public void newFormation_incorrectFormation_throwException() {
        assertThrows(IllegalArgumentException.class, () -> new Formation(5, 5, 5));
    }

    @Test
    public void newFormation_correctFormation_newObject() {
        Formation formation = new Formation(4, 4, 2);
        assertEquals(2, formation.getNumerOfAttackPlayers().intValue());
        assertEquals(4, formation.getNumerOfHelpPlayers().intValue());
        assertEquals(4, formation.getNumerOfDefensivePlayers().intValue());
    }

}