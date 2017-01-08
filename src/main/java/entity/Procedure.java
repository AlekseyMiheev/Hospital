package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aleksey on 08.12.2016.
 */
public class Procedure {

    private int id;

    private String name;
    private String description;
    private double price;

    private Set<HealingPlan> healingPlans = new HashSet<HealingPlan>(0);

    public Procedure() {
    }

    public Procedure(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Set<HealingPlan> getHealingPlans() {
        return healingPlans;
    }

    public void setHealingPlans(Set<HealingPlan> healingPlans) {
        this.healingPlans = healingPlans;
    }

    @Override
    public String toString() {
        return name;
    }
}
