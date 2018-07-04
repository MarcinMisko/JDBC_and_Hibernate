package jdbc1.dao;

import jdbc1.model.Car;
import jdbc1.model.Skills;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillsDao {
    private Connection dbConnection;

    public SkillsDao(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Skills> readAllSkills() {
        String querySql = "select LEFT_FOOT_IN_PERCENT, RIGHT_FOOT_IN_PERCENT, HEAD_IN_PERCENT, DEFENSE_IN_PERCENT, ATTACK_IN_PERCENT, MIDFIELD_IN_PERCENT\n" + " from MYSHEMA.SKILLS";
        List<Skills> listOfSkills = new ArrayList<>();
        try {
            Statement connectionStatement = dbConnection.createStatement();
            ResultSet result = connectionStatement.executeQuery(querySql);

            while (result.next()) {
             int leftFootInPercent = result.getInt(1);
             int rightFootInPercent = result.getInt(2);
             int headInPercent = result.getInt(3);
             int defenseInPercent = result.getInt(4);
             int attackInPercent = result.getInt(5);
             int midfieldInPercent = result.getInt(6);

                Skills s = new Skills(leftFootInPercent, rightFootInPercent, headInPercent, defenseInPercent, attackInPercent, midfieldInPercent);

                listOfSkills.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfSkills;
    }
    // PREPARED STATEMENT
    public boolean insertSkillToDao(Skills skills){
        String insertPetQuery = "insert into MYSHEMA.SKILLS(LEFT_FOOT_IN_PERCENT, RIGHT_FOOT_IN_PERCENT, HEAD_IN_PERCENT, DEFENSE_IN_PERCENT, ATTACK_IN_PERCENT, MIDFIELD_IN_PERCENT) values (?,?,?,?,?,?)\n";

        try {PreparedStatement preparedStatement5 = dbConnection.prepareStatement(insertPetQuery);
            ResultSet resultSet = preparedStatement5.executeQuery(insertPetQuery);

            preparedStatement5.setInt(1,skills.getLeftFootInPercent());
            preparedStatement5.setInt(2,skills.getRightFootInPercent());
            preparedStatement5.setInt(3,skills.getHeadInPercent());
            preparedStatement5.setInt(4,skills.getDefenseInPercent());
            preparedStatement5.setInt(5,skills.getAttackInPercent());
            preparedStatement5.setInt(6,skills.getMidfieldInPercent());



            preparedStatement5.executeUpdate();

        }catch (SQLException k){
            k.printStackTrace();
        }

        return false;
    }
}
