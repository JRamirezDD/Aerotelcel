from opensky_api import OpenSkyApi
import time

# takes input of airport code from java class
airport = input()

api = OpenSkyApi('Jasvort', 'AeroTelcel')

now = int(time.time())
print("Now: ", now)

# apparently it works w 1.1 and not 1, who knows
start_of_yesterday = now - 1.1*24*60*60
print("Start of Yesterday: ", start_of_yesterday)
print("Start of Yesterday: ", time.ctime(start_of_yesterday))

end_of_yesterday = start_of_yesterday - 24*60*60
print("End of Yesterday: ", end_of_yesterday)
print("End of Yesterday: ", time.ctime(end_of_yesterday))

data = api.get_arrivals_by_airport(airport, 1517184000, 1517270400)

for flight in data:
    print(flight)
    print("\n")





