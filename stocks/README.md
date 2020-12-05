# Company stock Rest

Simple project for inserting Company name and value of stock. And then getting it back depending on desired time stamp.

## Getting Started

Use import exiting projects into workspace eclipse feature.

## URLs

GET:

```
/stocks/{companyName}/at-time/{timestamp}
```
Example:

```
http://localhost:8080/stocks/IBM/at-time/2020-12-05T16:13:00.310542
```

PUT:

```
/stocks/{companyName}/value/{value}
```
Example:

```
http://localhost:8080/stocks/IBM/value/10
```

### Prerequisites

```
Eclipse IDE for Enterprise Java Developers Version: 2020-09 (4.17.0)
Maven 4.0.0
Spring boot 2.4.0
JavaSe 11
```

## Running the tests

Right click on project folder Run as -> JUnit test