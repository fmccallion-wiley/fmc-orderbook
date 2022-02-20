# SRE Orderbook application

Orderbook is a Java Spring Boot application used for training purposes.  It can be compiled using Maven;

## Building

```
$ mvn -Dmaven.test.skip=true clean package
```

It can also be compiled using the Maven container, see the [compose/Dockerfile.api](compose/Dockerfile.api), and the hub.docker.com image called maven:3.6.3-openjdk-11.

```
$ docker run -it --rm --name orderbook -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn -Dmaven.test.skip=true clean package
```

## Building with docker-compose

If you have Docker and docker-compose installed on your system then you can launch the application locally on your system using;

```
$ docker-compose -d up
```

This will launch the environment as it would in Kubernetes with the Database, API/Web UI, automated client.

Once launched the application will be available at;

```
http://localhost:8080
```

If for any reasons you are already running something on port 8080 then edit the [**docker-compose.yml**](docker-compose.yml) file and change the following code;

```
ports:
  - "8080:8080"
```

Change the first 8080 to an available port on your system.

# Using the application

The application has an API and a Web front end.  The URLs of interest will show up if you go to;

```
localhost:8080
```

# SRE Orderbook pipeline build

This is an example build job for the orderbook application pipeline.  This branch contains the pipeline and docker configuration for the application and database.

GitHub no longer accept usernames and passwords so a token must be created and used as the password in Jenkins.

**Steps to create the token;**
* Go to https://github.com/settings/tokens
* Click the Generate new token button
* In the Note box type in a use for the token, e.g., SRETraining
* Specify when you want the token to end.  For this training select 7 days
* Tick the box next to repo
* Tick the box next to user
* Click the Generate token button
* Copy the access token value shown and store it somewhere for the next task

**Steps to create a Jenkins pipeline job;**
* Go to https://jenkins.computerlab.online
* Click on
* In the Enter an item name box type in cXXXteamNN
* Where cXXX is the course number from your instructor
* Where NN is the team number assigned to you by your instructor
* Click the Pipeline task
* Click OK at the bottom of the screen

**Steps to configuring the Jenkins job;**
* Scroll down the configuration page to Pipeline section
* Or click the Pipeline tab
* Click on the Definition pull down and change to Pipeline script from SCM
* Click on the pull down for SCM and select Git
* In the Repository URL text box paste in the sre-orderbook repository
https://github.com/The-Software-Guild/sre-orderbook.git
* Click on the **Add** button
* Click  **Jenkins** option with the house icon
  * A new window opens
  * **Kind** remains as a **Username and password**
  * **Scope** remains as **Global (Jenkins, nodes, items, all child items, etc)**
  * **Username** type in your GitHub username
  * **Password** copy in your GitHub access token
  * **ID** type in your name so that you are able to identify your token
  * **Description** type in the same as what you typed into **ID**
  * Click **Add**
* Click on the pull down for Credentials and select your credential
* Leave the branch as master for the time being, we may change it during the course
* Leave the Script Path as Jenkinsfile
  - You only need change this if the file is in a different place, or name
* Click the Save button
* Click Build to start the build

**NOTE:** This job has a 10 minute polling set up to check GitHub
