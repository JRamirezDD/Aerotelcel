from opensky_api import OpenSkyApi
import time

airport = input()

api = OpenSkyApi('Jasvort', 'AeroTelcel')

now = int(time.time())

# Right now we are looking for all flights from 120
end_period = now - 130*24*60*60
start_period = end_period - 24*60*60

# try with now
# end_period = now
# start_period = end_period - 24*60*60

#print("\nNow looking for all flights over ", airport, " Airport from: ", time.ctime(start_period), " to ", time.ctime(end_period), "...\n")
data = api.get_arrivals_by_airport(airport, start_period, end_period)

if(data == None or len(data) == 0):
    print("No_data")
else:
    for flight in data:
        print(flight)