package com.revature.database.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.revature.database.models.Employee;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl_mongo implements EmployeeDAO {

    /*
     * The following example uses the built-in POJO support by passing
     * the Model as the Generic type to the MongoCollection object.
     * See the Comments in Main.java and the Employee.java class for more
     * details.
     *
     * A Manual, more simple alternative would require that we build our
     * documents manually using our Employee Object data, then pass that
     * data to the database.
     *
     * The Manual alternative would look something like this:
     * public static boolean insertEmployee(MongoCollection<Document> collection) {
     *      Document newEmployee = new Document("emp_name", "Jordan Smith")
     *          .append("emp_title", "Software Engineer")
     *          .append("emp_salary", 85000.00);
     *      InsertOneResult result = collection.insertOne(newEmployee);
     *
     *      return result.wasAcknowledged();
     * }
     *
     * You may favor this more simple approach when building your own projects, since
     * the built-in CodecRegistry requires you to either follow some specific conventions
     * or import and use other annotations and classes.
     */
    private final MongoCollection<Employee> collection;

    public EmployeeDAOImpl_mongo(MongoCollection<Employee> collection) {
        this.collection = collection;
    }

    @Override
    public Employee selectEmployeeByName(String name) {
        Employee search = collection.find(Filters.eq("emp_name", name)).first();

        if (search == null)
            return null;

        return search;
    }

    @Override
    public List<Employee> selectAllEmployees() {
        List<Employee> allEmployees = new ArrayList<Employee>();
        FindIterable<Employee> iter = collection.find();

        for (Employee emp : iter) {
            allEmployees.add(emp);
        }

        return allEmployees;
    }

    @Override
    public Boolean insertIntoEmployees(Employee emp) {
        InsertOneResult result = collection.insertOne(emp);

        /*
         * We could also check this boolean to confirm insertion
         * then perform other steps before returning from the method
         * such as logging, checking the mongo generated id, etc...
         */
        return result.wasAcknowledged();
    }

    @Override
    public Boolean updateEmployeeByName(String name, Employee emp) {
        Document updatedEmp = new Document("emp_name", emp.getEmp_name())
                .append("emp_title", emp.getEmp_title())
                .append("emp_salary", emp.getEmp_salary());

        // The updateOne & updateMany methods only take a single update operation
        // use Updates.combine() to change multiple fields in a single update
        UpdateResult result = collection.updateOne(
                Filters.eq("emp_name", name),
                Updates.combine(
                        Updates.set("emp_name", emp.getEmp_name()),
                        Updates.set("emp_salary", emp.getEmp_salary()),
                        Updates.set("emp_title", emp.getEmp_salary())
                )
        );

        // Was at least one record updated?
        return result.getMatchedCount() > 0;
    }

    @Override
    public Boolean deleteEmployeeByName(String name) {
        /*
         * Before deleting - it might be better to search
         * your database to see how many rows would be affected
         * by the delete operation.
         *
         *
         * Just keep in mind, this type of business logic would
         * be better suited to a 'Services' class
         */
        DeleteResult result = collection.deleteOne(Filters.eq("emp_name", name));

        return result.getDeletedCount() > 0;
    }
}
