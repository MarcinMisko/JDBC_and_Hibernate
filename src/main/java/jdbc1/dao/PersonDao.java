package jdbc1.dao;

import jdbc1.model.Person;
import jdbc1.model.Pet;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDao {
    private Connection dbConnection;

    public PersonDao(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // SIMPLE STATEMENT:
    public List<Person> readAllPerson() {
        String querySql = "select ID, NAME, SURNAME, AGE \n" + "from MYSHEMA.PERSON"; // tworzymy zapytanie
        List<Person> personList = new ArrayList<>();                // tworzymy listę, do której wrzucimy osoby z zapytania
        try {                                                       // opakowujemy w try/catch aby program złapał ewentualny błąd
            Statement statement = dbConnection.createStatement();   // tworzymy polączenie statementu z bazą
            ResultSet result = statement.executeQuery(querySql);    // polecenie wykonania wstawienia danych

            while (result.next()) {                                 // dopuki kursor będzie widział następny rekord
                int id = result.getInt(1);                       // przypisujemy do zmiennych kolumny z tabeli SQL
                String name = result.getString(2);               // (przypisywanie po numerach kolumn jest bezpieczniejsze
                String surname = result.getString(3);            // ponieważ unikamy problemu związanego z tym,
                int age = result.getInt(4);                      // że ktoś tak samo nazwał kolumny

                Person p = new Person(name, surname, age);          // tworzymy nową osobę z parametrami zawartymi w konstruktorze (w okrągłych nawiasach)
                p.setId(id);                                        // ustawiamy ID za pomocą setera z klasy Person
                personList.add(p);                                  // dodajemy do wcześniej stworonej listy
            }

        } catch (SQLException e) {                                  // łapanie wyjątków
            e.printStackTrace();
        }
        return personList;                                          // zwracamy listę osób z bazy
    }

    // PREPARED STATEMENT:
    public boolean addPersonToDao(Person person) {
        String insertQuery = "insert into MYSHEMA.PERSON(NAME, SURNAME, AGE) values (?,?,?)\n";
        String insertPet = "insert into MYSHEMA.PET(KIND, NAME, AGE,OWNER_ID) values (?,?,?,?)\n";
        try {
            dbConnection.setAutoCommit(false);                //PO USTAWIENIU TEGO PARAMETRU NA ""FALSE TO MY ZATWIERDZAMY WYSYŁKĘ ZAPYTANIA DO BAZY
            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertQuery); // "podłączenie" stworzonego zapytania/polecenia
            preparedStatement.setString(1, person.getName());                               // deklarujemy co gdzie wstawić
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setInt(3, person.getAge());

            PreparedStatement preparedStatement1 = dbConnection.prepareStatement(insertPet);
            Pet pet = person.getPet();
            preparedStatement1.setString(1, pet.getKind());
            preparedStatement1.setString(2, pet.getName());
            preparedStatement1.setInt(3, pet.getAge());
            preparedStatement1.setInt(4, pet.getId());


            preparedStatement.executeUpdate();                       // polecenie wykonania wstawienia danych

            dbConnection.commit();                            // ZATWIERDZENIE WYSYŁKI ZAPYTANIA DO BAZY
            ResultSet keys = preparedStatement.getGeneratedKeys();   // pobieranie klucza
            // (równoważne zapytaniu: SELECT ID FROM PERSON WHERE ID = NOWO DODANE)
            if (keys.next()) {
                int id = keys.getInt(1);
                System.out.println("New added key: " + id);
                person.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                dbConnection.rollback();                      // COFA WYSYŁKĘ JEŻELI NP. DODAMY DRUGIE ZAPYTANIE I COŚ Z NIM BĘDZIE NIE TAK
            } catch (SQLException el) {
                el.printStackTrace();
            }
        }
        return false;
    }


    public List<Person> readAdults(Person person) {
        String adultQuerySql = "select ID, NAME, SURNAME, AGE \n" + "from MYSHEMA.PERSON where AGE > 18";
        List<Person> adultPersonsList = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.prepareStatement(adultQuerySql);
            ResultSet resultSet = stmt.executeQuery(adultQuerySql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);

                Person adult = new Person(name, surname, age);
                adult.setId(id);
                adultPersonsList.add(adult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adultPersonsList;
    }

    public List<Person> readPersonsWithPets() {
        List<Person> result = new ArrayList<>();
        String query = "select p.ID, p.NAME, p.SURNAME, p.AGE, pt.ID, pt.NAME, pt.AGE, pt.KIND\n" +
                "from PERSON p\n" +
                "left join PET pt on (pt.OWNER_ID = p.ID)";
        try {
            Person personFromDb;
            Statement statement = dbConnection.createStatement();
            Map<Integer, Person> idOverPerson = new HashMap<>();
            ResultSet dbResult = statement.executeQuery(query);
            int personId = dbResult.getInt(1);

            Pet petFromDb;
            if (!idOverPerson.containsKey(personId)) {
                personFromDb = new Person(dbResult.getString(1), dbResult.getString(2), dbResult.getInt(3), personId);

                idOverPerson.put(personId, personFromDb);

                petFromDb = new Pet(dbResult.getInt(5), dbResult.getString(6), dbResult.getString(7), dbResult.getInt(8));
                personFromDb.addPet(petFromDb);
            }else {
                personFromDb = idOverPerson.get(personId);
                petFromDb = new Pet(dbResult.getInt(5), dbResult.getString(6), dbResult.getString(7), dbResult.getInt(8));
                personFromDb.addPet(petFromDb);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
