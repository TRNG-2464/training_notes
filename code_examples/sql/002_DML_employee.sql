/*
 * DML - Data Manipulation Language
 * DML clauses are your CRUD operations.
 * (C)reate: INSERT
 * (R)ead: SELECT
 * (U)pdate: UPDATE
 * (D)elete: DELETE
 * 
 * Adding information: INSERT
 * 	syntax:
 * 		INSERT INTO (<schema_name>.)<table_name> VALUES (<value_1> ...);
 */
INSERT INTO examples.employees VALUES (1000,'Joseph','CEO',null);
INSERT INTO examples.employees VALUES (1001,'Bin','Developer',120000.00);
INSERT INTO examples.employees VALUES (1002,'Dan','Tester',123000.00);
INSERT INTO examples.employees VALUES (1003,'Emmanuel','Developer',168000.00);
INSERT INTO examples.employees VALUES (1004,'Jacob','Tester',195000.00);
INSERT INTO examples.employees VALUES (1005,'Jessica','Developer',199000.00);
INSERT INTO examples.employees VALUES (1006,'Steve','Developer',97000.00);
INSERT INTO examples.employees VALUES (1007,'Maggie','Tester',88000.00);
INSERT INTO examples.employees VALUES (1008,'Quincy','Developer',86000.00);
INSERT INTO examples.employees VALUES (1009,'Leslie','Tester',151000.00);
INSERT INTO examples.employees VALUES (1010,'Bill','Developer',114000.00);

/*
 * Read information: SELECT
 *  syntax:
 *		SELECT <columns> FROM (<schema_name>).<table_name> [Optional 'WHERE' clauses]
 *
 * An asterisk (*) is a Wildcard indicating all columns
 */
SELECT * FROM examples.employees;
SELECT emp_id, emp_name, emp_title, emp_salary FROM examples.employees; -- equivalent to above

/*
 * The 'WHERE' clause is an optional statement that you can add to your
 * UPDATE, DELETE and SELECT operations which allow you to specify the
 * modification/removal/search function you perform. Note that if we do
 * not apply the where clause to our statement, we will effect all records
 * within a particular table.
 */
-- UPDATE examples.employees SET emp_salary=100000; -- UPDATE ALL ROWS TO have 100000 as the NEW salary
UPDATE examples.employees SET emp_salary=100000 WHERE emp_id=1000; -- ONLY UPDATE FIRST record


/*
 * DELETE - DML Command that allows you to delete records from a table without
 * manipulating the structure of the table in its entirety.
 * 
 * Cannot Restart/continue identity
 * 
 * The delete will remove records from our table one-row-at-a-time
 * If you need to 'clear' or 'reset' an entire table, it is best to use TRUNCATE
 *
 * Syntax:
 * DELETE FROM <table_name> [WHERE...]
 */
-- DELETE FROM examples.employees; -- This will Delete all rows from this table

DELETE FROM examples.EMPLOYEES WHERE EMP_ID=1010;

SELECT * FROM examples.employees;