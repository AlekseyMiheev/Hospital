package util;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksey on 08.12.2016.
 */

public abstract class DBControl {

    public static Session session;
    public static List<Doctor> doctors = new ArrayList<Doctor>();
    public static List<Client> clients = new ArrayList<Client>();
    public static List<Department> departments = new ArrayList<Department>();
    public static List<Speciality> specialities = new ArrayList<Speciality>();
    public static List<WorkingTime> time = new ArrayList<WorkingTime>();
    public static List<Inspection> inspections = new ArrayList<Inspection>();
    public static List<Diagnosis> diagnosises = new ArrayList<Diagnosis>();
    public static List<Procedure> procedures = new ArrayList<Procedure>();
    public static List<HealingPlan> healingPlan = new ArrayList<HealingPlan>();

    public static void openConnection(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public static void putData(){
        session.beginTransaction();
        WorkingTime time = new WorkingTime("11-12");
        Department department = new Department("main.Main department", "Department description");
        Speciality speciality = new Speciality("main.Main speciality", "Speciality description");

        Doctor doctor = new Doctor("Doctor 1", speciality, department, time);

        Client client = new Client("Aleksey Miheev", "17.09.1996", "AA12345", "Kyiv");
        Diagnosis diagnosis = new Diagnosis("Diagnosis 1", "Some description");

        Procedure procedure = new Procedure("Procedure 1", "Some description", 50);

        HealingPlan healingPlan = new HealingPlan(procedure, "Some advices");

        Inspection inspection = new Inspection(doctor, client, diagnosis, healingPlan, "Some description", "12.12.2016");

        session.saveOrUpdate(time);
        session.saveOrUpdate(speciality);
        session.saveOrUpdate(department);
        session.saveOrUpdate(doctor);
        session.saveOrUpdate(client);
        session.saveOrUpdate(diagnosis);
        session.saveOrUpdate(procedure);
        session.saveOrUpdate(healingPlan);
        session.saveOrUpdate(inspection);

        doctor = new Doctor("Doctor 2", speciality, department, time);
        inspection = new Inspection(doctor, client, diagnosis, healingPlan, "Some description", "22.12.2016");

        session.saveOrUpdate(doctor);
        session.saveOrUpdate(inspection);

        session.getTransaction().commit();
    }

    public static void getData(){
        session.beginTransaction();

        doctors = session.createCriteria(Doctor.class).list();
        clients = session.createCriteria(Client.class).list();
        departments = session.createCriteria(Department.class).list();
        specialities = session.createCriteria(Speciality.class).list();
        time = session.createCriteria(WorkingTime.class).list();
        inspections = session.createCriteria(Inspection.class).list();
        diagnosises = session.createCriteria(Diagnosis.class).list();
        procedures = session.createCriteria(Procedure.class).list();
        healingPlan = session.createCriteria(HealingPlan.class).list();

        session.getTransaction().commit();
    }

    public static void closeConnection(){
        session.close();
        HibernateUtil.closeSessionFactory();
    }

    public static void addData(Object o){
        session.beginTransaction();
        session.saveOrUpdate(o);
        session.getTransaction().commit();
    }

    public static List<Inspection> getInspectionsByPerson(Client client){
        List<Inspection> personsInspections;
        personsInspections = session.createCriteria(Inspection.class).add(Restrictions.eq("client", client)).list();
        return personsInspections;
    }

    public static List<Inspection> getInspectionsByDoctor(Doctor doctor){
        List<Inspection> doctorsInspections;
        doctorsInspections = session.createCriteria(Inspection.class).add(Restrictions.eq("doctor", doctor)).list();
        return doctorsInspections;
    }

    public static List<Doctor> getDoctorsBySpeciality(Speciality speciality){
        List<Doctor> doctors;
        doctors = session.createCriteria(Doctor.class).add(Restrictions.eq("speciality", speciality)).list();
        return doctors;
    }

    public static List<Doctor> getDoctorsByDepartment(Department department){
        List<Doctor> doctors;
        doctors = session.createCriteria(Doctor.class).add(Restrictions.eq("department", department)).list();
        return doctors;
    }


    public static void deleteData(Object object){
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
    }

    public static void updateDoctors(){
        session.beginTransaction();
        doctors = session.createCriteria(Doctor.class).list();
        session.getTransaction().commit();
    }

    public static void updateClients(){
        session.beginTransaction();
        clients = session.createCriteria(Client.class).list();
        session.getTransaction().commit();
    }

    public static void updateDepartments(){
        session.beginTransaction();
        departments = session.createCriteria(Department.class).list();
        session.getTransaction().commit();
    }

    public static void updateSpecialities(){
        session.beginTransaction();
        specialities = session.createCriteria(Speciality.class).list();
        session.getTransaction().commit();
    }

    public static void updateInspections(){
        session.beginTransaction();
        inspections = session.createCriteria(Inspection.class).list();
        session.getTransaction().commit();
    }

    public static void updateTime(){
        session.beginTransaction();
        time = session.createCriteria(WorkingTime.class).list();
        session.getTransaction().commit();
    }

    public static void updateProcedures(){
        session.beginTransaction();
        procedures = session.createCriteria(Procedure.class).list();
        session.getTransaction().commit();
    }

    public static void updateDiagnoses(){
        session.beginTransaction();
        diagnosises = session.createCriteria(Diagnosis.class).list();
        session.getTransaction().commit();
    }

    public static void updatePlans(){
        session.beginTransaction();
        healingPlan = session.createCriteria(HealingPlan.class).list();
        session.getTransaction().commit();
    }

}
