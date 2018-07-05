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
    public boolean insertPetToDao(Pet pet) {
        String insertPetQuery = "insert into MYSHEMA.PET(KIND, NAME, AGE,OWNER_ID) values (?,?,?,?)\n";

        try {
            PreparedStatement preparedStatement2 = dbConnection.prepareStatement(insertPetQuery);
            ResultSet resultSet = preparedStatement2.executeQuery(insertPetQuery);

            preparedStatement2.setString(1, pet.getKind());
            preparedStatement2.setString(2, pet.getName());
            preparedStatement2.setInt(3, pet.getAge());
            preparedStatement2.setInt(4, pet.getId());

            preparedStatement2.executeUpdate();

        } catch (SQLException k) {
            k.printStackTrace();
        }

        return false;
    }

    // ALTERNATYWNE DODANIE ZWIERZAKA
    public boolean savePerson(Pet pet2) {
        boolean result = false;

        if (Pet.ID_OF_NOT_PERSISTENT_PERSON != pet2.getId()) {
            System.out.println(String.format("This person has already been added to db: [%s]", pet2));
        } else {
            String insert = "" +
                    "INSERT INTO JDBC_SCHEMA.PET(KIND, NAME, AGE)\n" +
                    "VALUES (?, ?, ?)";

            try {
                PreparedStatement insertStatement = dbConnection.prepareStatement(insert, new String[]{"ID"});
                insertStatement.setString(1, pet2.getKind());
                insertStatement.setString(2, pet2.getName());
                insertStatement.setInt(3, pet2.getAge());

                int numberOfAddedRows = insertStatement.executeUpdate();
                if (1 == numberOfAddedRows) {
                    System.out.println("Person was added to db");
                    result = true;

                    ResultSet generatedId = insertStatement.getGeneratedKeys();
                    if (generatedId.next()) {
                        pet2.setId(generatedId.getInt(1));
                        System.out.println(String.format("Id for pets was set: [%s]", pet2));
                    } else {
                        System.out.println("Couldn't obtain generated key");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }
}
