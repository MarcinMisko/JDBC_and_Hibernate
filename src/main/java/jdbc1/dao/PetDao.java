package jdbc1.dao;

import jdbc1.model.Person;
import jdbc1.model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDao {
    private Connection dbConnection;

    public PetDao(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Pet> readAllPets() {
        String querySql = "select ID, KIND, SURNAME, AGE \n" + "from MYSHEMA.PET";
        List<Pet> listOfPets = new ArrayList<>();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet result = stmt.executeQuery(querySql);

            while (result.next()) {
                int id = result.getInt(1);
                String kind = result.getString(2);
                String name = result.getString(3);
                int age = result.getInt(4);

                Pet p = new Pet(kind, name, age);
                p.setId(id);
                listOfPets.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfPets;
    }
    // PREPARED STATEMENT
    public boolean insertPetToDao(Pet pet){
        String insertPetQuery = "insert into MYSHEMA.PET(KIND, NAME, AGE,OWNER_ID) values (?,?,?,?)\n";

        try {PreparedStatement preparedStatement2 = dbConnection.prepareStatement(insertPetQuery);
            ResultSet resultSet = preparedStatement2.executeQuery(insertPetQuery);

            preparedStatement2.setString(1,pet.getKind());
            preparedStatement2.setString(2,pet.getName());
            preparedStatement2.setInt(3,pet.getAge());
            preparedStatement2.setInt(4,pet.getId());

            preparedStatement2.executeUpdate();

        }catch (SQLException k){
            k.printStackTrace();
        }

        return false;
    }
}
