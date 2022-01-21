package com.student_info_manager.models;

/**
 * BasePerson - used as a base model for Admin, Teacher and Student.
 * Simplifies name and department handling for all the models
 */
public abstract class BasePerson {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String department;

    public BasePerson(String firstName, String middleName, String lastName, String department) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.department = department;
    }

    public String[] getName() {
        return new String[]{firstName, middleName, lastName};
    }

    public String getDepartment() {
        return department;
    }
}
