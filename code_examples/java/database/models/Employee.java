package com.revature.database.models;

/*
 * As you may notice our Model class does not match the
 * expected naming conventions for Java classes. We
 * did this intentionally so that we can more easily
 * map this Model class to our CodecRegistry (see
 * mongoDaoImpl example in Main.java).
 *
 * Note, if we followed standard Java naming conventions
 * (i.e. empId | empName | etc...) we would need to match
 * fields to our Mongo document by using a '@BsonProperty'
 * annotation. Imported from:
 *      "org.bson.codecs.pojo.annotations.BsonProperty;"
 *
 * Example:
 * @BsonProperty("emp_name")
 * String empName;
 */
public class Employee {
    // Note: MongoDB ids are represented as the 'ObjectID' class
    // imported from: org.bson.types.ObjectId;
    // We aren't using the _id from Mongo, so this field will
    // effectively be ignored by mongo, but can still be used
    // with our PostgreSQL implementation
    private Integer emp_id;
    private String emp_name;
    private String emp_title;
    private Double emp_salary;

    public Employee() {
        super();
    }

    // Used by PostgreSQL DAO implementation - Ids from Relational DB is always an Integer
    public Employee(Integer emp_id, String emp_name, String emp_title, Double emp_salary) {
        super();
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_title = emp_title;
        this.emp_salary = emp_salary;
    }

    // Used by MongoDB DAO implementation - Ids from Mongo are not strictly Integer values...
    public Employee(String emp_name, String emp_title, Double emp_salary) {
        super();
        this.emp_name = emp_name;
        this.emp_title = emp_title;
        this.emp_salary = emp_salary;
    }

    public Integer getEmp_id() {
        return emp_id;
    }
    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }
    public String getEmp_name() {
        return emp_name;
    }
    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }
    public String getEmp_title() {
        return emp_title;
    }
    public void setEmp_title(String emp_title) {
        this.emp_title = emp_title;
    }
    public Double getEmp_salary() {
        return emp_salary;
    }
    public void setEmp_salary(Double emp_salary) {
        this.emp_salary = emp_salary;
    }
    @Override
    public String toString() {
        return "Employee [emp_id=" + emp_id +
                ", emp_name=" + emp_name +
                ", emp_title=" + emp_title +
                ", emp_salary="+ emp_salary + "]";
    }
}
