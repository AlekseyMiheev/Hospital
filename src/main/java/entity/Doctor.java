package entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Aleksey on 07.12.2016.
 */

public class Doctor {

    private int doctorId;

    private String name;

    private Speciality speciality;

    private Department department;

    private WorkingTime time;

    public Doctor() {
    }

    public Doctor(String name, Speciality speciality, Department department, WorkingTime time) {
        this.name = name;
        this.speciality = speciality;
        this.department = department;
        this.time = time;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkingTime getTime() {
        return time;
    }

    public void setTime(WorkingTime time) {
        this.time = time;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return name;
    }
}
