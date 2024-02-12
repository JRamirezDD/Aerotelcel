from opensky_api import OpenSkyApi
api = OpenSkyApi()

# ALL TIMESTAMPS HAVE TO BE IN Unix Time Stamp
# AIRPORT CODES ALL CAPS IN STRING

arrivals = api.get_arrivals_by_airport("AIRPORTCODE", TIMESTAMP, TIMESTAMP)