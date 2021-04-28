# Instructions to start 

## Requirements
You will need: 
 - Docker installed
 - Docker compose installed 
 - The Git Repo in your local machine

## First step
Head to the `run_locally` folder in your machine inside the terminal 
make sure these PORTS are free [443,3000,8080,5008], if can not change them in the docker compose file in the `run_locally` folder
then run these commands:
 - `cd IC_Artifact`            
 - `sudo docker build -t jmeterimage:latest .`
 - `cd ..`
 - `cd FrontEnd`
 - ` docker build -t frontend:latest .`
 - `cd .. `           
 - `sudo docker-compose down`
 - `sudo docker-compose up -d`

## Second Step
head to Grafana on your browser at: 
localhost:3000
login with
username: admin
password: admin 

then head to data source and add the influxdb database as the datasource with 
the url -> localhost:443

then head to import and enter 5496 as the Grafana dashboard ID 
- choose influx as DB name 
- Enter jmeter as measurement name 
Then click import 

## Step Three
Now you should be able to see results in grafana, if you want to see them on the User interface you will need to replace the 
iframes in the ejs file found in (./FrontEnd/view/index.ejs) with the ones you have. simply click on a panel and then share, 
and then embed to access the iframes 

## Step Four
User interface can be located at Localhost:8080
