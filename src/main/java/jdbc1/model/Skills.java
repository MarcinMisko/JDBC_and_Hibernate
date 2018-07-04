package jdbc1.model;

public class Skills {
    int leftFootInPercent;
    int rightFootInPercent;
    int headInPercent;
    int defenseInPercent;
    int attackInPercent;
    int midfieldInPercent;

    public Skills(int leftFootInPercent, int rightFootInPercent, int headInPercent, int defenseInPercent, int attackInPercent, int midfieldInPercent) {
        this.leftFootInPercent = leftFootInPercent;
        this.rightFootInPercent = rightFootInPercent;
        this.headInPercent = headInPercent;
        this.defenseInPercent = defenseInPercent;
        this.attackInPercent = attackInPercent;
        this.midfieldInPercent = midfieldInPercent;
    }

    public int getLeftFootInPercent() {
        return leftFootInPercent;
    }

    public void setLeftFootInPercent(int leftFootInPercent) {
        this.leftFootInPercent = leftFootInPercent;
    }

    public int getRightFootInPercent() {
        return rightFootInPercent;
    }

    public void setRightFootInPercent(int rightFootInPercent) {
        this.rightFootInPercent = rightFootInPercent;
    }

    public int getHeadInPercent() {
        return headInPercent;
    }

    public void setHeadInPercent(int headInPercent) {
        this.headInPercent = headInPercent;
    }

    public int getDefenseInPercent() {
        return defenseInPercent;
    }

    public void setDefenseInPercent(int defenseInPercent) {
        this.defenseInPercent = defenseInPercent;
    }

    public int getAttackInPercent() {
        return attackInPercent;
    }

    public void setAttackInPercent(int attackInPercent) {
        this.attackInPercent = attackInPercent;
    }

    public int getMidfieldInPercent() {
        return midfieldInPercent;
    }

    public void setMidfieldInPercent(int midfieldInPercent) {
        this.midfieldInPercent = midfieldInPercent;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "leftFootInPercent=" + leftFootInPercent +
                ", rightFootInPercent=" + rightFootInPercent +
                ", headInPercent=" + headInPercent +
                ", defenseInPercent=" + defenseInPercent +
                ", attackInPercent=" + attackInPercent +
                ", midfieldInPercent=" + midfieldInPercent +
                '}';
    }
}
