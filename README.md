# Leaflit
Arkansas Coding Academy Demo Day Project

This is the first Full Stack Application I made on my own

Purpose
  Leaflit was designed for anyone who wants to garden or struggles to grow plants. The user is asked for their zipcode, sunlight in their yard, 
and the amount of time they have to spend in their garden.

It will then return a list of plants from the Leaflit database to tell the user what they can grow in their own yard.

Each plant in the Leaflit data base has hardiness zone that it grows best in. Using AngularJS the code is calling an external API (from rapid API)
that will take the user's zipcode and convert it to a hardiness zone. Then the database will be filtered by the hardiness zone to return only plants
that grow within the users area. The list is then filtered down by the light requirements and the time the user is able to spend in their garden.

Currently there are about 90 plants in the Leaflit database and is designed for only the Continental United States. 

Another function of Leaflit is for users to be able to learn more about plants and using the random plant function the user can get a random plant 
from the leaflit database at the click of a button.

All CRUD functions are done through the Admin tab, currently there is no log in for Admin privileges but will ideally be added in the future. I don't
want any user to be able to edit the database so admin privileges would be neccessary before a public version on Leaflit could be launched.

Tech Stack
The back end was built with Java as a Maven Project using RESTful web services and the Jersey framework on the Eclipse IDE

The front end was built using HTML, CSS/Booatstrap 4, and AngularJS/JavaScript

The database was first built usiing MariaDB on HeidiSQL but was transferred to MySQL Workbench
