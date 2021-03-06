package entity;

import java.util.Set;

/**
 * Created by Aleksey on 07.12.2016.
 */
public class Speciality {

    private int id;

    private String name;
    private String description;

    private Set<Doctor> doctors;

    public Speciality() {
    }

    public Speciality(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return name;
    }
}
