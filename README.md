# ICS
The Image Classification Service (ICS) allows users to submit image URLs and get the linked image classified (tagged) based on their perceived content. To do this, the service relies on image recognition services online.

The ICS implementation you will be building, will have the following features:

• A web-based user interface with three pages

o One to submit images for analysis

o One to view an image that has been analysed

o One to show a gallery of all uploaded images

• A web API that allows the UI to function, and CI to run tests, with endpoints
for submitting and retrieving images

• A back-end to connect to the recognition services, perform the analysis, store
the information into a database, and handle HTTP requests

• A CI pipeline that runs on every commit, preparing an artifact with the
solution


This is an overview of the service’s architecture:
![image](https://github.com/MariaIvanova01/ICS/assets/81368587/4be80295-4b0e-484f-b0d4-0ccf541b5c1a)


## How to build and run the project using Docker :

 1. Build a docker image with the following command(this is building the project) : 
 
 ```docker build -t imageclassifier:v1 .```
 
 2. Then run the image with:
 
  ```docker run --name imagecontainer imageclassifier:v1```
    
