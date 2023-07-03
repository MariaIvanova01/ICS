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

#DataBase

The database will hold two types of records—images and tags, and another table will store the
relationship between them. An image may have multiple tags, and a tag may be present on
multiple images. Additionally, categorisation services provide the confidence that a tag is
accurate; this information would be useful to store as well.
The image schema can be defined in several ways, with valuable information being:

• The URL to the image

• The time the image was uploaded and analysed

• The service or services used to analyse the image

• The size of the image (width and height)

The label schema should contain the human-readable name of the label.
Once the database has been defined and created , it needs to be integrated with
the existing /images endpoint:

• When a never-seen-before image is submitted for categorisation, its details
and tags should be stored into the database prior to being returned to the user

• When a user submits an image that has been submitted before, the details
can be fetched directly from the database, skipping the sending of requests to
the services (and saving bandwidth and cost)

Additional endpoints:

Endpoint | HTTP Method | Details |
--------------|--------|---------|
/images/:id | GET | Retrieve a specific image using its ID. If no such image, return a 404 error|
/images | GET | Retrieve a list of all images |
| - | - |It should be possible to supply a list of tags as a parameters. When this parameter is set, the response should only contain images with these tags

# User Interface

The service’s home page allows the submission of an image for analysis. 

![image](https://github.com/MariaIvanova01/ICS/assets/81368587/2a22f30c-81a6-467c-9c2a-05efaf60fa75)

The page will feature an input field to enter a URL into, and a button to submit it for analysis. A validation of whether the submitted string is a valid URL can be done through the page’s logic to reduce the number of requests that get sent to the back-end. When the URL is submitted, the user interface informs the user the request is being processed. If the back-end returns an error, the interface again informs the user. The only other control on the page is a link on the top right that directs the user to the gallery page. 

Once the user submits an image and it is successfully analysed, they are taken to the single image view page. This page would also be reachable from the gallery page after clicking on any image shown. 


![image](https://github.com/MariaIvanova01/ICS/assets/81368587/2bb825d6-16b6-4b83-b425-ca227c86f8f1)

The page shows the image on the left (applying scaling if necessary). The tags are shown on the right. Hovering over a tag with the mouse reveals the confidence in the categorisation. Tags are clickable—they link to the gallery page with a search for the specific tag. The page also shows the date and time the image was uploaded at. The navigation on the top links to the home page and the gallery.

The gallery page allows users to view all the images that have been uploaded before in a grid. Upon clicking on any of the images, the user is taken to the single image view for the given picture. 


![image](https://github.com/MariaIvanova01/ICS/assets/81368587/2b99430c-0168-4594-86a8-7a2e5747ecf6)


## How to build and run the project using Docker :

 1. Build a docker image with the following command(this is building the project) : 
 
 ```docker build -t imageclassifier:v1 .```
 
 2. Then run the image with:
 
  ```docker run --name imagecontainer imageclassifier:v1```
    
