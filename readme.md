# fitnessAppServer

# MaxiPutz.github.io

### Quick view into the App

#### What can I do with this App? <br>
-> Compare the performance data of routes which you frequently use for your Workout. F.ex., if you run the same route every day, you might want to know your performance over time. You can graphically compare and analyse these data with this app.

#### What do I see on the homepage? <br>
-> All Workouts! <br>
![Screenshot 2023-03-29 at 20 46 23](https://user-images.githubusercontent.com/48091139/228637792-45201524-15bf-450a-9740-7a274dcdc662.jpg)

#### What happens if I click on a blue Workout-card/button?<br>
-> This Workout is shown then in Detail:<br>
![Screenshot 2023-03-29 at 20 38 16](https://user-images.githubusercontent.com/48091139/228636530-f73bcecd-3653-4533-84f7-d98ccf1c0422.jpg)

#### What's the use of the CREATE-button in the header bar?<br>
-> Select a frequent Workout segment (=part of the route) on the map.<br>
Ok, but how can I select my segment of interest from point A to point B?<br>
1. Click CREATE<br>
2. Drag&drop the pins on the map:<br>
2.1. green pin to the desired start point AND<br>
2.2. red pin to the end point (segment will appear green)<br>
3. Click the yellow LOAD button in the header bar to view the graphical comparison of multiple Workouts of one route:<br>
![Screenshot 2023-03-29 at 20 38 56](https://user-images.githubusercontent.com/48091139/228637416-43bb6465-c071-48af-be8f-f9895f89a904.jpg)

4. Load all runs with this segment and compare it with the mean data from<br>
![Screenshot 2023-03-29 at 20 39 35](https://user-images.githubusercontent.com/48091139/228637481-065573b2-8315-4e30-b065-c9eea4deefec.jpg)


## Demo 
if you like test the demo app you can klick here <br> 
https://maxiputz.github.io <br>

# Tutorial: Setting Up Not Another Fitness App with Strava API
![alt text](output3.64315625.gif)

# Step 1: Sign up for a Strava Account

If you haven't already, go to Strava registration page and sign up for a Strava account. Follow the instructions to create your account

# Step 2: Create an App on Strava

- After logging in to your Strava account, go to Strava API settings.
- Click on the "Create an App" button to create a new app.
- Fill in the following information for your app:
- Application Name: not-another-fitness
- Category: Visualizer
- Website: http://5.78.122.162:8080
- Authorization Callback Domain: 
5.78.122.162
Once you've filled in the details, click on the "Create" button to create your app.

# Step 3: Retrieve Client ID and Client Secret

After creating your app, you will be redirected to the "My API 
Application" page. On this page, you can find the following information:

- Category: The category you chose for your application (Visualizer in this case)
- Client ID: Your application ID
- Client Secret: Your client secret

Copy the Client ID and Client Secret from the "My API Application" page.


# Step 4: Configure Not Another Fitness App

- Register and login to the Not Another Fitness App
- Fill in the Client ID and Client Secret obtained from the previous steps.
- Click on Oauth and authorizate the api for the Not Another Fitness App

# Step 5: Sync Your Workouts and Have Fun with the App

- Initiate the workout sync process.
- Once the sync is complete, you will be able to view and analyze your workout data within the app.
- Explore the features and functionalities of the app to make the most out of your fitness data.
Now you're all set up with the Not Another Fitness app and Strava API integration. Enjoy tracking and analyzing your workouts!


## Ready to use
Vola you can use my fitness app
![Screenshot 2023-03-29 at 20 46 23](https://user-images.githubusercontent.com/48091139/228637792-45201524-15bf-450a-9740-7a274dcdc662.jpg)


