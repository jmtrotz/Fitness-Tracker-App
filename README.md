# Fitness Tracker
Android application that will interface with a project my friend Kyle Rosales
(https://github.com/kjrosales) is working on. He is handling the server-side
and working on APIs for user authentication and to store/retrieve workout logs.

### Features in Progress
- For cardio workouts: log time and distance. Weight used is optional (in case
- you wore a weight vest, or jogged with dumbbells, or something)
- For weight lifting: log weight used, number of sets, and number of repetitions
- Two account modes: normal and "off the grid" mode
- Off the grid mode stores all data locally on the device, including your account
information. **WARNING:** wiping data for the application will permanently delete
your account and all workout logs. Your account and logs will NOT be recoverable.
- Normal mode uses Kyle's API to create/authenticate user accounts. Workout logs
will be stored locally on the device so they can be accessed offline, and backed
up to the cloud so they can be accessed in the future if the application data is
deleted
- Screen to search previous workouts
- Screen with general info about the app

### "Nice to Have" Features
These features aren't being worked on yet, but I would like to add them eventually:
- BMI calculator
- Calorie calculator
- Rest timer
- View graphs of progress over time
- Option to require pin/pattern/password/fingerprint to access the app
- GPS distance tracking when going on a jog/bike ride
- If the device is rooted: when the rest timer is done and this app isn't in the
- foreground, kill Reddit, TikTok, or whatever is distracting you 

### "On Hold" Features
- Custom splash screen (seems like Material3 creates one for you?)