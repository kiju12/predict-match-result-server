package ur.lab3.predictmatchresult.domainobjects.models;

public class Formation {

    private Integer numerOfDefensivePlayers;
    private Integer numerOfHelpPlayers;
    private Integer numerOfAttackPlayers;

    public Formation() {
    }

    public static Formation createFromRaw(String formation) {
        String[] split = formation.split("-");

        return new Formation(
                Integer.parseInt(split[0]),
                Integer.parseInt(split[1]),
                Integer.parseInt(split[2])
        );
    }

    public void setNumerOfDefensivePlayers(Integer numerOfDefensivePlayers) {
        this.numerOfDefensivePlayers = numerOfDefensivePlayers;
    }

    public void setNumerOfHelpPlayers(Integer numerOfHelpPlayers) {
        this.numerOfHelpPlayers = numerOfHelpPlayers;
    }

    public void setNumerOfAttackPlayers(Integer numerOfAttackPlayers) {
        this.numerOfAttackPlayers = numerOfAttackPlayers;
    }

    public Formation(Integer def, Integer help, Integer attack) {
        if (def + help + attack != 10) throw new IllegalArgumentException();

        this.numerOfDefensivePlayers = def;
        this.numerOfHelpPlayers = help;
        this.numerOfAttackPlayers = attack;
    }

    public Integer getNumerOfDefensivePlayers() {
        return numerOfDefensivePlayers;
    }

    public Integer getNumerOfHelpPlayers() {
        return numerOfHelpPlayers;
    }

    public Integer getNumerOfAttackPlayers() {
        return numerOfAttackPlayers;
    }
}
