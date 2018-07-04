package jdbc1.dao;

import jdbc1.model.Car;
import jdbc1.model.Footballer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private Connection dbConnection;

    public CarDao(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Car> readAllCars() {
        String querySql = "select BRAND, MODEL \n" + "from MYSHEMA.CAR";
        List<Car> listOfCars = new ArrayList<>();
        try {
            Statement connectionStatement = dbConnection.createStatement();
            ResultSet result = connectionStatement.executeQuery(querySql);

            while (result.next()) {
             String brand = result.getString(1);
             String model = result.getString(2);

                Car c = new Car(brand, model);

                listOfCars.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfCars;
    }
    // PREPARED STATEMENT
    public boolean insertCarToDao(Car car){
        String insertPetQuery = "insert into MYSHEMA.FOOTBALLER(NAME, SURNAME, SALARY, AGE) values (?,?,?,?)\n";

        try {PreparedStatement preparedStatement4 = dbConnection.prepareStatement(insertPetQuery);
            ResultSet resultSet = preparedStatement4.executeQuery(insertPetQuery);

            preparedStatement4.setString(1,car.getBrand());
            preparedStatement4.setString(2,car.getModel());


            preparedStatement4.executeUpdate();

        }catch (SQLException k){
            k.printStackTrace();
        }

        return false;
    }
}
