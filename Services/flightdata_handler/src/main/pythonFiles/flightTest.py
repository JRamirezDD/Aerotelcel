import json

flight = {
    'baro_altitude': 1866.9,
    'callsign': 'ACA1074 ',
    'category': 0,
    'geo_altitude': 1744.98,
    'icao24': 'c067ae',
    'last_contact': 1710277994,
    'latitude': 45.5636,
    'longitude': -73.8167,
    'on_ground': False,
    'origin_country': 'Canada',
    'position_source': 0,
    'sensors': None,
    'spi': False,
    'squawk': '2311',
    'time_position': 1710277994,
    'true_track': 68.39,
    'velocity': 117.31,
    'vertical_rate': 0}


flight_json = json.dumps(flight, indent=4)
import json

flight = {
    'baro_altitude': 1866.9,
    'callsign': 'ACA1074 ',
    'category': 0,
    'geo_altitude': 1744.98,
    'icao24': 'c067ae',
    'last_contact': 1710277994,
    'latitude': 45.5636,
    'longitude': -73.8167,
    'on_ground': False,
    'origin_country': 'Canada',
    'position_source': 0,
    'sensors': None,
    'spi': False,
    'squawk': '2311',
    'time_position': 1710277994,
    'true_track': 68.39,
    'velocity': 117.31,
    'vertical_rate': 0
}

flight_json = json.dumps(flight, indent=4)
flight_json = flight_json.replace("\"", "'")
print (flight_json)
