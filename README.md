# Ciklum-TUI-test

## This is my implementation of the test task for my potential customer (TUI).

### Introduction

Welcome everyone who will review results of my work.

During my implementation of the task I tried to use new approaches for each part.
However, I nevertheless considered some of them too redundant for solving the task and removed them from the current version.

You can add some comments where describe your ideas how to improve the quality of my implementation in the future..

### Description

The service performs a simple function of collecting information about the user's repositories.
This only has one endpoint (/{userName}), which takes the username as a path parameter and returns short 
information about all the repositories of the given user that are not forks, including branch information.

The full task description you can find [HERE](https://github.com/stanislav-belousov/ciklum-tui-test/blob/main/TUI_BackEndTask.pdf)

###### NOTE: implementation in current repository was done with Java as a base programing language.

### How to build and run application

Current application was created with Gradle, so you can build and run it with common commands. Such as:

``` 
./gradlew build
``` 

or

``` 
./gradlew bootRun
``` 

Note: Default port of the application is 80.

So, if you want to run application locally I recommend to use local profile, where application port was override to 8080:

``` 
./gradlew bootRun --args='--spring.profiles.active=local'
``` 

or specify any another port:

``` 
./gradlew bootRun --args='--server.port=8888'
``` 

### API Documentation

As a part of the task in this service configured Swagger (OpenAPI) documentation.
You can open it after running application and use the link:

```
http://localhost:8080/swagger-ui.html
```