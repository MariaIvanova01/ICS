# ICS
The Image Classification Service (ICS) allows users to submit image URLs and get the linked image classified (tagged) based on their perceived content. To do this, the service relies on image recognition services online.

## How to work with the Dockerfile:

 1. Build the file with the following command: 
 
 ```docker build -t imageclassifier:v1 .```
 
 2. Then run with:
 
  ```docker run --name imagecontainer imageclassifier:v1```
    
