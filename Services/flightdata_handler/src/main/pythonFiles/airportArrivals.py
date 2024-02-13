from opensky_api import OpenSkyApi
api = OpenSkyApi()

# ALL TIMESTAMPS HAVE TO BE IN Unix Time Stamp
# AIRPORT CODES ALL CAPS IN STRING - IN ICAO FORMAT (4 LETTERS)

arrivals = api.get_arrivals_by_airport("AIRPORTCODE", TIMESTAMP, TIMESTAMP)