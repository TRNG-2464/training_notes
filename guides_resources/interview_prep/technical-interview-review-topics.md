# Technical Interview Prep

The following is a list of topics covered in this training program, which you will be expected to understand, describe and provide practical examples for during a technical interview. You should also be comfortable writing code, or recognizing the structure of code which relates to any of these topics.

Note: This is a fairly comprehensive list, but it may not include the entire breadth of exact terms discussed in the program.

## AI Fundamentals & AI-Assisted Development

* AI / Machine Learning basics
* Supervised vs. unsupervised learning
* Large Language Models (GPT, BERT, Claude, Llama, Copilot, Codeium)
* LLM use cases and cost/token considerations
* Responsible AI (bias, limitations, security considerations)
* Prompt injection prevention
* Input validation and sanitization / strengthening internal prompts
* PII masking
* Prompt Engineering fundamentals (instruction, completion, dialogue, chain-of-thought)
* Zero-shot and few-shot prompting
* Chain-of-thought prompting for reasoning and problem solving
* Constraints, role/persona prompting
* Human-in-the-loop validation of AI output

## Linux & Developer Tooling

* Fundamentals of Linux OS
* Basic Linux commands
* Shell scripting fundamentals
* Source control management concepts (VCS, CVCS, DVCS)
* Git fundamentals
* Initializing a repository
* Git commit, branch, merge, push, pull
* Pushing to a remote repository
* .gitignore

## Agile & Software Development Practices

* SDLC Foundations
* Waterfall vs Agile methodologies
* Agile manifesto/core beliefs 
* Scrum (Scrum ceremonies & Scrum Artifacts)
* Kanban
* Project boards, story pointing, and burndown charts

## Java Fundamentals & OOP

* Data types, variables, and operators (logical, mathematical, comparison)
* Primitive data types, wrapper classes, and casting
* Reference variables and value 
* Control flow / conditional statements and loops
* Arrays (common methods & properties, iterations with loops)
* Classes vs. objects
* Stack and heap memory
* Class members (fields, constructors, methods)
* Access modifiers and non-access modifiers
* Static members
* Encapsulation
* Inheritance
* Abstraction (abstract classes & interfaces)
* Polymorphism (overloading, overriding, & covariant types)
* Object class, equals() and hashCode()
* Garbage collection
* Strings (String basics, String methods & properties, String pool, StringBuilder, and StringBuffer)
* Methods: declaration, syntax, parameters, return types, invocation, visibility, and scope
* Packages and imports
* Debugging fundamentals

## Exception Handling, I/O & Logging

* Exceptions vs. errors and exception hierarchy
* Handling exceptions, checked vs. unchecked exceptions
* Creating custom exceptions
* Reading the stack trace
* Reading from console (Scanner)
* File I/O (FileInputStream / FileReader / FileWriter)
* Serialization
* Logging fundamentals (Logback), logging levels

## Java Collections, Generics & Functional Programming

* Collection API overview (List, Set, Queue) 
* Map interfaces
* ArrayList and LinkedList
* HashSet and TreeSet
* ArrayDeque and PriorityQueue
* Iterators, Iterable, Comparable, and Comparator interfaces
* Generics
* Implementing a custom List
* Lambdas and functional interfaces
* Optional class
* Stream API
* Big O notation (time and space complexity)
* Sorting and searching algorithms
* Greedy algorithms

## Concurrency & Multithreading

* Thread class and Runnable interface
* Thread lifecycle and states
* Multithreading and synchronization
* Deadlock and Livelock
* Producer-consumer problem

## Design Patterns

* What is a design pattern
* Creational: Singleton
* Creational: Factory
* Creational: Builder
* Behavioral: Observer pattern
* Data Access Object (DAO) pattern

## Other Java APIs & Build Tools

* Reflection API fundamentals
* DateTime API fundamentals
* Maven, lifecycle, central repository, and Project Object Model (POM)

## Java Testing & Code Quality

* Unit testing vs. integration testing
* Test-Driven Development (TDD) overview
* JUnit
* Arrange-Act-Assert pattern
* Assertion types
* Line vs. branch coverage
* Mockito and mocking fundamentals
* Stubs & mocks

## SQL, Database & NoSQL Fundamentals

* What is a database & fundamentals of RDBMS
* SQL basics
* Database schema, table structure, and data modeling
* Entity-Relationship Diagrams (ERD) and normalization
* Primary key, foreign key, composite key, unique key
* Constraints, CHECK, DEFAULT, CASCADE, auto-incrementing
* Referential integrity
* SQL sublanguages: DDL, DML, DQL, DCL, TCL
* DDL: CREATE, DROP, TRUNCATE, defining schema
* DML: INSERT, UPDATE, DELETE
* DQL: queries, clauses, aggregate functions, subqueries
* Joins: inner, outer, left/right, equi/theta, cross joins, aliases
* Transactions and transaction properties
* ACID properties
* CRUD operations
* Indexes, views, sequences, triggers
* Stored procedures and user-defined functions
* SQL injection awareness
* NoSQL vs. SQL
* MongoDB and MongoDB architecture/concepts
* Document data model, collections, and databases
* BSON and ObjectId
* MongoDB CRUD operations and query syntax/operators

## JDBC & Database Connectivity

* JDBC architecture and interfaces
* Driver types and registration
* Setting up the database driver and utility class
* Statements, Prepared Statements, and Callable Statements
* Result Sets and navigating result set rows
* DAO concepts

## HTTP & REST API Fundamentals

* Introduction to HTTP and HTTP lifecycle
* HTTP methods and status codes
* HTTP headers and cookies
* REST concepts: resources, URLs, and endpoints
* Basic API development

## HTML, CSS & Wireframes

* Overview of HTML, document structure, and DOM
* HTML tags, elements, and attributes (inline vs. block)
* HTML forms: elements, input types, select/multi-select
* HTML5 validation and form submission
* Fundamentals of CSS and stylesheets (inline, internal, external)
* CSS properties and selectors (class, ID, sibling, advanced)
* Cascading nature of CSS and specificity
* Box model
* Flexbox and Grid
* Responsive web design and CSS variables
* Wireframes: purpose and types (low, mid, high fidelity)
* Wireframe design best practices

## JavaScript

* Variables, data types, and type coercion
* Variable scopes, let/const keywords
* Functions, arrow functions, default parameters
* Spread and rest operators
* Template literals
* this keyword, hoisting, strict mode
* Classes
* Errors
* DOM structure, selecting and manipulating elements, traversing the DOM
* Events and listeners, event bubbling/capturing
* JSON
* Promises, Fetch API, and Async/Await

## TypeScript

* JavaScript vs. TypeScript, why use TypeScript
* Basic and special types, object types, union types
* Type aliases and interfaces
* Arrays, tuples, and array generics
* Enums (numeric and string)
* Classes, casting, and basic generics
* Utility types
* Keyof, decorators, and functions
* As const, type guards
* Interface vs. type
* TSX and readonly interfaces
* TypeScript compiler and tsconfig basics

## React

* Single Page Applications overview
* React Fundamentals
* JSX and component basics
* React with TypeScript integration
* Event handling
* Lists and keys, props and state
* One-way data flow and lifting state
* Immutability
* Rendering and routing
* Higher-order components
* Hooks 
* Refs and Context
* Nested components
* Controlled vs. uncontrolled components
* API integration (fetch/axios), handling response data and errors
* Component testing with TypeScript (React Testing Library, Jest & Vitest)

## Spring Framework & Spring Boot

* Spring Framework Fundamentals (Dependency Injection, Beans, IoC)
* Types of dependency injection (XML-based and Java-based configuration)
* Inversion of Control and the Spring IoC container
* Bean lifecycle, scopes, definition, and instantiation
* Annotation-based configuration, component scanning, stereotype annotations
* Common Spring Boot starters and Spring Boot Dev Tools
* Spring Boot Actuator and built-in endpoints
* Spring MVC overview and architecture
* Controllers and @Controller, @RequestMapping and MVC annotations
* HTTP method annotations
* Request body handling and @RequestBody annotation
* Path variables and request parameters
* RESTful API development with @RestController
* Error handling and HTTP status codes with @ExceptionHandler
* Spring Data JPA relationship with Hibernate
* Spring Data repositories: JpaRepository vs. CrudRepository
* @RequestMapping and @ResponseBody
* SpEL Fundamentals (Property expressions)
* Transaction management with @Transactional

## Coding & Problem-Solving Practice

You will be expected to write code to solve short coding exercises (most likely in Java). This coding problem is used to assess your programming fundamentals, logical thinking, problem-solving abilities, code structure and ability to explain your approach. Coding problems may involve one or more of the following areas:
* String manipulation
* Arrays and Lists
* Frequency / duplicate entry identification
* Palindrome or similar string problems
* Basic searching and sorting
* Simple OOP-based problems
* Basic SQL exercises
* Explaining approach to training assignments/projects, debugging activities, and use of AI tools during development
