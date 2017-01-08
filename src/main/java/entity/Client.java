package entity;

import java.util.Set;

/**
 * Created by Aleksey on 20.11.2016.
 */
public class Client {

    private int id;

    private String name;
    private String birthday;
    private String passportData;
    private String address;

    private Set<Inspection> inspections;

    public Client() {
    }

    public Client(String name, String birthday, String passportData, String address) {
        this.name = name;
        this.birthday = birthday;
        this.passportData = passportData;
        this.address = address;
    }

    public Set<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(Set<Inspection> inspections) {
        this.inspections = inspections;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name;
    }
}
