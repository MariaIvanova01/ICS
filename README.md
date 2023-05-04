# ICS
The Image Classification Service (ICS) allows users to submit image URLs and get the linked image classified (tagged) based on their perceived content. To do this, the service relies on image recognition services online.

## How to build and run the project using Docker :

 1. Build a docker image with the following command(this is building the project) : 
 
 ```docker build -t imageclassifier:v1 .```
 
 2. Then run the image with:
 
  ```docker run --name imagecontainer imageclassifier:v1```
    
