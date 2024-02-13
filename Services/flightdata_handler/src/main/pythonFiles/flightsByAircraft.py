from opensky_api import OpenSkyApi
api = OpenSkyApi()

# ALL TIMESTAMPS HAVE TO BE IN Unix Time Stamp
# ALL ICAO24 CODES AS STRING

data = api.get_flights_by_aircraft("ICAO24", TIMESTAMP, TIMESTAMP)