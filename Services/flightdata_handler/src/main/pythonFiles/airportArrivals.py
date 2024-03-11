from opensky_api import OpenSkyApi
from datetime import datetime, timedelta

airport = input()

api = OpenSkyApi('Jasvort', 'AeroTelcel')

# Current time
now = datetime.now()

# 130 days ago
date_130_days_ago = now - timedelta(days=130)

# Get the weekday of the current date
current_weekday = now.weekday()
# print("The current date is: ", now, " and the weekday is: ", current_weekday)

# Keep subtracting days until the weekday matches
while date_130_days_ago.weekday() != current_weekday:
    date_130_days_ago -= timedelta(days=1)

#print("The same day of the week as today but not less than 130 days ago is: ", date_130_days_ago, " and the weekday is: ", date_130_days_ago.weekday())

# Day before the 130 days ago
start_period = date_130_days_ago - timedelta(days=1)


#print("\nNow looking for all flights over ", airport, " Airport from: ", start_period, " to ", date_130_days_ago, "...\n")
data = api.get_arrivals_by_airport(airport, int(start_period.timestamp()), int(date_130_days_ago.timestamp()))

if(data == None or len(data) == 0):
    print("No_data")
else:
    for flight in data:
        print(flight)