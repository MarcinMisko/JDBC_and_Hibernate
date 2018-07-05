package jdbc1.model;

import java.util.Set;

public class Person {
    private Pet pet;
    private String name;
    private String surname;
    private int age;
    private int id;
    private Set<Pet > pets;

    public Set<Pet> getPets() {
        return pets;
    }

    public void addPet(Pet petFromDb) {
        pets.add(pet);
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.id = -1;
    }

    public Person(String name, String surname, int age, int id) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
