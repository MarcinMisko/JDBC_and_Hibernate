package jdbc1.dao;

import jdbc1.model.Footballer;
import jdbc1.model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FootballerDao {
    private Connection dbConnection;

    public FootballerDao(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Footballer> readAllFootballers() {
        String querySql = "select ID, NAME, SURNAME, SALARY, AGE \n" + "from MYSHEMA.FOOTBALLER";
        List<Footballer> listOfFootballers = new ArrayList<>();
        try {
            Statement connectionStatement = dbConnection.createStatement();
            ResultSet result = connectionStatement.executeQuery(querySql);

            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String surname = result.getString(3);
                int salary = result.getInt(4);
                int age = result.getInt(5);

                Footballer f = new Footballer(name,surname,salary,age);
                f.setId(id);
                listOfFootballers.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfFootballers;
    }
    // PREPARED STATEMENT
    public boolean insertFootballerToDao(Footballer footballer){
        String insertPetQuery = "insert into MYSHEMA.FOOTBALLER(NAME, SURNAME, SALARY, AGE) values (?,?,?,?)\n";

        try {PreparedStatement preparedStatement3 = dbConnection.prepareStatement(insertPetQuery);
            ResultSet resultSet = preparedStatement3.executeQuery(insertPetQuery);

            preparedStatement3.setString(1,footballer.getName());
            preparedStatement3.setString(2,footballer.getSurname());
            preparedStatement3.setInt(3,footballer.getSalary());
            preparedStatement3.setInt(4,footballer.getAge());

            preparedStatement3.executeUpdate();

        }catch (SQLException k){
            k.printStackTrace();
        }

        return false;
    }
}
