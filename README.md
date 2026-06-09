# TourEase

> A simple Java Swing application for managing tour packages, customers and bookings.

## Overview

This project is a desktop Java application (Swing) that provides UI frames for login, managing packages, customers and bookings. The entry point is `Start.java`.

## Prerequisites

- Java Development Kit (JDK) 8 or newer
- MySQL server (or compatible) for the application's database
- MySQL JDBC driver (`mysql-connector-java`) jar on the classpath

## Database setup

The project includes a SQL dump file: `toureasedatabase.sql`. Create the database and import the SQL:

```bash
# create database
mysql -u root -p -e "CREATE DATABASE tourease_db;"
# import dump
mysql -u root -p tourease_db < toureasedatabase.sql
```

Update the connection credentials in `repository/DatabaseConnection.java` if needed (username, password, host, port).

## Compile and run (command line)

1. Download the MySQL connector JAR (for example `mysql-connector-java-8.0.33.jar`) and place it in the project root or a `lib/` folder.
2. From the project root, compile all Java sources to an `out/` directory and include the connector on the classpath:

```bash
mkdir -p out
javac -d out -cp "lib/*:." $(find . -name "*.java")
```

3. Run the application, including the connector on the classpath:

```bash
java -cp "out:lib/*:." Start
```

Note: On Windows replace `:` with `;` in the classpath.

## Run in an IDE

- Import the project into IntelliJ or Eclipse as a plain Java project.
- Add the MySQL connector jar as a library dependency.
- Mark the project root as the source root if needed, then run `Start.main()`.

## Project structure

- `Start.java` — application entry point
- `toureasedatabase.sql` — SQL dump to create schema + seed data
- `entity/` — model classes (`Booking.java`, `Customer.java`, `TourPackage.java`, `User.java`)
- `frames/` — Swing UI frames (login, manage, book list, etc.)
- `interfaces/` — repository interfaces
- `repository/` — repository implementations and `DatabaseConnection.java`

## Notes

- `repository/DatabaseConnection.java` uses MySQL by default (`jdbc:mysql://localhost:3306/tourease_db`). Change the URL, user, and password as required before running.
- If you prefer, set up a Maven or Gradle build to manage the JDBC dependency automatically.

## Troubleshooting

- ClassNotFoundException for `com.mysql.cj.jdbc.Driver` means the connector jar is not on the classpath.
- Connection errors usually indicate wrong DB credentials or MySQL not running.

---

If you want, I can add a simple `build.sh` script and a `lib/` folder with instructions to download the connector automatically.
