package entity;

import java.util.Set;

/**
 * Created by Aleksey on 08.12.2016.
 */
public class Diagnosis {

    private int id;

    private String name;
    private String description;

    private Set<Inspection> inspections;

    public Diagnosis() {
    }

    public Diagnosis(String name, String description) {
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

    public Set<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(Set<Inspection> inspections) {
        this.inspections = inspections;
    }

    @Override
    public String toString() {
        return name;
    }
}
