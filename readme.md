The porject is built using Android Studio
Instructions: Clone the repo, and let the project build for the first time in Android Studio, where it will download all the neceassary files.

Then run the project on a phone or on an emulator.

The Android app consumes REST Api, so make sure the device has an active Internet connection.

The REST Api has been written by me using using Node.js and a Json based Database.

The Api has been deployed to Heroku, and is consumed using this heroku link : 

https://heroku-song.herokuapp.com/

The API has he following end-points : 

GET : /api/songs : to get all the songs in DB
POST : /api/songs : to add a new song
POST : /api/songs/rate : to add a new song rating
DELETE : api/songs/{id} : to delete a song using id

GET : /api/artists : to get all the artists in DB
POST : /api/artists : to add a new song
DELETE : /api/artists/{id} : to delete an artists using id

The REST Api source code is available in the following github repo : 

https://github.com/Luciferx86/heroku-song

