package util;

import entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksey on 02.01.2017.
 */
public abstract class Searcher {

    public static List<Client> searchClients(String search_info) {
        List<Client> searchResult = new ArrayList<>();
        if (search_info.length() > 0) {
            for (Client item : DBControl.clients) {
                if (item.getName().contains(search_info)) {
                    searchResult.add(item);
                }
            }
        } else {
            searchResult = DBControl.clients;
        }

        return searchResult;
    }

    public static List<Doctor> searchDoctors(String search_info) {
        List<Doctor> searchResult = new ArrayList<>();
        if (search_info.length() > 0) {
            for (Doctor item : DBControl.doctors) {
                if (item.getName().contains(search_info)) {
                    searchResult.add(item);
                }
            }
        } else {
            searchResult = DBControl.doctors;
        }

        return searchResult;
    }

    public static List<Speciality> searchSpecialities(String search_info) {
        List<Speciality> searchResult = new ArrayList<>();
        if (search_info.length() > 0) {
            for (Speciality item : DBControl.specialities) {
                if (item.getName().contains(search_info)) {
                    searchResult.add(item);
                }
            }
        } else {
            searchResult = DBControl.specialities;
        }

        return searchResult;
    }

    public static List<Procedure> searchProcedures(String search_info) {
        List<Procedure> searchResult = new ArrayList<>();
        if (search_info.length() > 0) {
            for (Procedure item : DBControl.procedures) {
                if (item.getName().contains(search_info)) {
                    searchResult.add(item);
                }
            }
        } else {
            searchResult = DBControl.procedures;
        }

        return searchResult;
    }

    public static List<Department> searchDepartments(String search_info) {
        List<Department> searchResult = new ArrayList<>();
        if (search_info.length() > 0) {
            for (Department item : DBControl.departments) {
                if (item.getName().contains(search_info)) {
                    searchResult.add(item);
                }
            }
        } else {
            searchResult = DBControl.departments;
        }

        return searchResult;
    }

    public static List<Diagnosis> searchDiagnoses(String search_info) {
        List<Diagnosis> searchResult = new ArrayList<>();
        if (search_info.length() > 0) {
            for (Diagnosis item : DBControl.diagnosises) {
                if (item.getName().contains(search_info)) {
                    searchResult.add(item);
                }
            }
        } else {
            searchResult = DBControl.diagnosises;
        }

        return searchResult;
    }

}
