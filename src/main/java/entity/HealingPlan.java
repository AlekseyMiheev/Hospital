package entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Aleksey on 08.12.2016.
 */
public class HealingPlan {

    private int id;

    private Procedure procedure;
    private String advices;

    private Set<Inspection> inspections = new HashSet<Inspection>(0);

    public HealingPlan() {
    }

    public HealingPlan(Procedure procedure, String advices) {
        this.procedure = procedure;
        this.advices = advices;
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

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public String getAdvices() {
        return advices;
    }

    public void setAdvices(String advices) {
        this.advices = advices;
    }

    @Override
    public String toString() {
        return advices;
    }
}
