package entity;


/**
 * Created by Aleksey on 08.12.2016.
 */
public class Inspection {

    private int id;
    private Doctor doctor;
    private Client client;
    private Diagnosis diagnosis;
    private HealingPlan healingPlan;

    private String description;
    private String date;


    public Inspection() {
    }

    public Inspection(Doctor doctor, Client client, Diagnosis diagnosis, HealingPlan healingPlan, String description, String date) {
        this.doctor = doctor;
        this.client = client;
        this.diagnosis = diagnosis;
        this.healingPlan = healingPlan;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public HealingPlan getHealingPlan() {
        return healingPlan;
    }

    public void setHealingPlan(HealingPlan healingPlan) {
        this.healingPlan = healingPlan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
