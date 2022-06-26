# Garage-SpringBoot

This repository contains Garage Application with Spring Boot,

### Table of Contents  
[Technologies](#technologies)<br>
[Dependencies](#dependencies)<br>
[Pre requirements](#requirements)<br>
[Download](#startingProject)<br>
[Run Tests](#runtests)<br>
[Installing](#installing)<br>
[Run Project](#run)<br>

<a name="technologies"/></a>
## Technologies
  * Java 11
  * Maven
  * Spring Boot
  * Lombok
  * H2 Database
  
<a name="dependencies"/></a>
## Dependencies
  * Lombok
  * H2 Database

<a name="requirements"/></a>
## Pre requirements
  * JDK 11. version
  * Maven 4.0.0 version
  * git (optional)

<a name="startingProject"/></a>
## Starting Project
  * You can download the project using powershell with command below. <br>
      `git clone https://github.com/selimsahin1/Garage-SpringBoot.git`<br>.

<a name="runtests"/></a>
## Run Tests
  * You can run test with command below.<br>
    `mvn clean compile test`<br>

<a name="installing"/></a>
## Installing
  * First of all, the command below is need to be run before running the project. <br>
    `mvn clean install`<br>

<a name="run"/></a>
## Run Project
  * The project will be run with command below. Server port is 8080.<br>
    `java -jar ./target/carfactory-0.0.1-SNAPSHOT.jar`<br>
    
<a name="endpoints"/></a>
## End Points
    
### Create Ticket

```
localhost:8080/ticket/create
```
JSON Request:

```json
{
    "color":"blue",
    "plate":"34MM0308",
    "vehicleType":"CAR"
}
```

JSON Response:

```
Allocated one slot.
```

### Leave

```
localhost:8080/ticket/leave
```
JSON Request:

```json
{
    "ticketId": 1
}
```

JSON Response:

```
1 slot is empty after removing ticket.
```

### Leave

```
localhost:8080/ticket/status
```

JSON Response:

```json
[
    {
        "id": 2,
        "slots": [
            1
        ],
        "plate": "34MM0308",
        "color": "blue",
        "vehicleType": "CAR",
        "status": "INGARAGE"
    },
    {
        "id": 3,
        "slots": [
            0
        ],
        "plate": "34MM0308",
        "color": "blue",
        "vehicleType": "CAR",
        "status": "INGARAGE"
    },
    {
        "id": 4,
        "slots": [
            2
        ],
        "plate": "34MM0308",
        "color": "blue",
        "vehicleType": "CAR",
        "status": "INGARAGE"
    }
]
```
    
