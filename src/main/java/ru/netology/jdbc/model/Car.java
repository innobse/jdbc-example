package ru.netology.jdbc.model;

/**
 * Description
 *
 * @author bse71
 * Created on 23.07.2021
 * @since
 */
public class Car {

    private int id;
    private String name;
    private String model;

    public Car(int id, String name, String model) {
        this.id = id;
        this.name = name;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model=" + model +
                '}';
    }
}
