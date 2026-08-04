package com.revature.database;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.database.dao.EmployeeDAO;
import com.revature.database.dao.EmployeeDAOImpl_mongo;
import com.revature.database.dao.EmployeeDAOImpl_pgsql;
import com.revature.database.models.Employee;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.sql.SQLException;

import static com.revature.database.util.ConnectionUtil.*;
import static com.revature.database.util.MongoConnectionManager.getMongoClient;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {
    public static void main(String[] args) {
//        postgresConnectionSanityTest();
        jdbcDaoImpl();
//        getMongoClient();
//        mongoDaoImpl();
    }

    // Make sure to test your connection before you get started!
    public static void postgresConnectionSanityTest() {
        // This acts as a sanity test to make sure our connection is working
        try {
            System.out.println("Properties");
            getConnection_prop();
            System.out.println("Env Var");
            getConnection_env();
            System.out.println("Connection Strings - unsafe method");
            getConnection();

            System.out.println("Connection was successful");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection was NOT sucessful");
        }
    }

    public static void jdbcDaoImpl() {
        EmployeeDAO eDao = new EmployeeDAOImpl_pgsql();

//        System.out.println("SELECT BY NAME");
//        System.out.println( eDao.selectEmployeeByName("Joseph") );

        System.out.println("SELECT ALL");
        for (Employee e : eDao.selectAllEmployees() ) {
            System.out.println(e);
        }

//        System.out.println("INSERTION");
//        Employee insert = new Employee(null, "From Java", "Developer", 150000.00);
//        System.out.println( eDao.insertIntoEmployees(insert) );
//
//        System.out.println("UPDATION");
//        // Typically when we update a record - we query the database first for the ID of the record (and to check it exists)
//        System.out.println("SELECT BY NAME");
//        Employee old = eDao.selectEmployeeByName("From Java");
//
//        Employee update = new Employee(null, "New Name", "New Title", 125000.00);
//        System.out.println( eDao.updateEmployeeByName(old.getEmp_name(), update) );
//
//        System.out.println("DELETION");
//        System.out.println( eDao.deleteEmployeeByName("New Name") );
    }

    public static  void mongoDaoImpl() {
        MongoClient client = getMongoClient();

        /*
         * By Default 'getCollection' returns MongoCollection<Document>
         * It is acceptable to use Documents and build each document
         * in your DAO Implementation (see comment in EmployDAOImpl_mongo)
         *
         * However, to leverage our model class more directly, we must
         * perform 2 steps:
         *  1. Inform the 'CodecRegistry' that we want to build codecs
         *      at runtime. A 'Codec' acts as a look-up table for Mongo
         *      to understand how to build Documents from Java Types
         *      we can inform the registry to build our potential Pojos
         *      on the fly with the code below
         *  2. Pass 'Employee.class' to the 'getCollection' method call
         *      which tells the registry which Pojo to use
         */

        // Create a provider (fromProviders(PojoCodecProvider.builder().automatic(true).build())
        // this provider will generate our POJO codec at runtime.
        CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        // Create our database using the registry provider above
        MongoDatabase database = client.getDatabase("trng-2464")
                .withCodecRegistry(pojoCodecRegistry);

        // Reference our collection - pass the Model class (our Pojo) so that
        // our CodecRegistry can tell Mongo how to create this Document during runtime
        // Note: in our example, we only have a single collection (table)
        // In your own projects, you will need multiple collections - you
        // can create multiple collections from a single MongoClient
        MongoCollection<Employee> employeeMongoCollection =
                database.getCollection("employees", Employee.class);

        EmployeeDAO eDao = new EmployeeDAOImpl_mongo(employeeMongoCollection);
        System.out.println("SELECT BY NAME");
        System.out.println( eDao.selectEmployeeByName("Joseph") );

//        System.out.println("SELECT ALL");
//        for (Employee e : eDao.selectAllEmployees() ) {
//            System.out.println(e);
//        }
//
//        System.out.println("INSERTION");
//        Employee insert = new Employee(null, "From Java", "Developer", 150000.00);
//        System.out.println( eDao.insertIntoEmployees(insert) );
//
//        System.out.println("UPDATION");
//        // Typically when we update a record - we query the database first for the ID of the record (and to check it exists)
//        System.out.println("SELECT BY NAME");
//        Employee old = eDao.selectEmployeeByName("From Java");
//
//        Employee update = new Employee(null, "New Name", "New Title", 125000.00);
//        System.out.println( eDao.updateEmployeeByName(old.getEmp_name(), update) );
//
//        System.out.println("DELETION");
//        System.out.println( eDao.deleteEmployeeByName("New Name") );

    }
}
