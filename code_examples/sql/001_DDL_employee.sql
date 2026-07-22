-- single-line comments

/*
 * Multi-line comments in SQL
 */
-- Drop statement
DROP SCHEMA IF EXISTS examples CASCADE;

-- Create a new Schema
CREATE SCHEMA examples;
/*
 * When defining a table you can specify the schema using dot notation, as 
 * shown below:
 * 
 * CREATE TABLE <schema_name>.<table_name> (
 * 		<column_name> <column_datatype> [addl options...],
 * 		<column_name> <column_datatype> [addl options...],
 * 		<column_name> <column_datatype> [addl options...]
 * );
 */
CREATE TABLE examples.employees (
		emp_id INTEGER,
		emp_name VARCHAR(200),
		emp_title VARCHAR(100)
);

/*
 * ALTER - DDL command which enables you to make changes to the structure
 * of a schema or table. This can be used to add or remove columns, change
 * datatype of columns, etc...
 */
ALTER TABLE examples.employees
ADD COLUMN emp_salary DECIMAL -- Note: 'Decimal' and 'Numeric' are functionally the same type in PostgreSQL

/*
 * DROP - DDL command which will allow for the removal of a database entity in
 * its entirety. If we drop a table, we will lose all of the data within that
 * table, and will need to re-define it.
 */ 
DROP TABLE examples.EMPLOYEES;

/*
 * TRUNCATE - DDL command which allows you to remove all data within a table 
 * while retaining the structure of the table itself.
 */
--TRUNCATE examples.employees;

/*
 * RESTART IDENTITY allows you to reset any sequences (values that increase
 * over time) associated with your table. We will talk more about sequences
 * and triggers later...
 * 
 */
--TRUNCATE examples.employees RESTART IDENTITY;
--TRUNCATE examples.employees CONTINUE IDENTITY; -- default : do NOT reset sequences

