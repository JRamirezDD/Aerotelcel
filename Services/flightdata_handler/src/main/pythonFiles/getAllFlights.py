# Description: script to get all active flights

from opensky_api import OpenSkyApi

api = OpenSkyApi('Jasvort', 'AeroTelcel')

states = api.get_states()

for s in states.states:
    print(s)