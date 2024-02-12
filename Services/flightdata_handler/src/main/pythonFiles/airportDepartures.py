from opensky_api import OpenSkyApi
api = OpenSkyApi()

# ALL TIMESTAMPS HAVE TO BE IN Unix Time Stamp
# AIRPORT CODES ALL CAPS IN STRING

departures = api.get_departures_by_airport("AIRPORTCODE", TIMESTAMP, TIMESTAMP)