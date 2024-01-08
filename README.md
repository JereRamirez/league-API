# League API

## Overview

The League API is a Spring Boot application that allows you to import football league data from
the [football-data.org](http://www.football-data.org/) API into a MySQL database. The API provides endpoints to retrieve
information about competitions, teams, players, and coaches.

## Getting Started

### Prerequisites

- Java 11 or later
- MySQL database
- [football-data.org](http://www.football-data.org/) API key (sign up [here](http://www.football-data.org/documentation/api))

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/league-api.git
   cd league-api
   ```

2. Set up the MySQL database. Create a database named `league_db` and configure the database connection in
   the `application.properties` file.

3. (Pending - currently key hardcoded) Obtain a football-data.org API key and update the `application.properties` file with your
   API key:

   ```properties
   football-data.api-key=your-api-key
   ```

4. Build and run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

   The application will be accessible at [http://localhost:8080](http://localhost:8080).

## Importing a League

To import a league, use the `importLeague` endpoint by providing a `leagueCode`:

```http
GET /api/importer/{leagueCode}
```

This endpoint fetches competition details and teams from football-data.org and imports them into the MySQL database.

## Endpoints

### 1. Get Team Members

Retrieve players and coaches from teams participating in a given league:

```http
GET /api/v1/players/{leagueCode}?teamName={teamName}
```

- `leagueCode` (required): Code of the league.
- `teamName` (optional): Name of the team to filter players.

### 2. Get Team

Retrieve information about a specific team, optionally resolving members (players or coaches):

```http
GET /api/v1/teams/{teamName}?resolveMembers={true|false}
```

- `teamName` (required): Name of the team.
- `resolveMembers` (optional): Set to `true` to include player or coach information.

## Notes

- If no players are available in team squads, only coaches are imported.

## Pending

- Tests
- Docker
- If teams/player already exists in DB when importing league, then only update relationship without importing entities again
- Limit frequency to the requests performed with a free-token

## Overview

The League API application is designed to manage and provide information about football leagues, teams, players, and coaches.
The application utilizes a Spring Boot backend with a MySQL database to store and retrieve data.

## Technology Stack

#### Spring Boot

Spring Boot is chosen as the primary framework for building the application due to its simplicity, convention over
configuration, and comprehensive ecosystem. It provides robust support for building RESTful APIs and integrates seamlessly with
various libraries.

#### MySQL Database

MySQL is selected as the relational database management system to store structured data efficiently. It is widely used, stable,
and has good community support. The relational nature of the data (teams, players, competitions) makes MySQL a suitable choice.

#### JPA (Java Persistence API)

JPA is used for object-relational mapping, simplifying the interaction between Java objects and the database. This choice
enhances data manipulation and persistence, allowing the application to work with entities like Team, Player, Competition, and
Coach as Java objects.

#### Lombok

Lombok is used to reduce boilerplate code in Java classes. With annotations like @Getter, @Setter, and @Builder, it enhances
code readability and maintainability. This is especially beneficial in entities and DTOs where repetitive code can be minimized.

### Project Structure

The project follows a modular structure, separating concerns into different packages. This promotes maintainability and
readability. The controllers, services, models, and repositories are organized logically, making it easier to navigate and
understand the codebase.

#### RESTful Endpoints

RESTful endpoints are created using Spring MVC to expose APIs for data manipulation and retrieval. This architectural style is
chosen for its simplicity, statelessness, and compatibility with a wide range of clients.




