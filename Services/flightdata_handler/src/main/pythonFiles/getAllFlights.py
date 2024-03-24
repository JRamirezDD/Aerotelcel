#
#    Title: Database File
#    Author: Ortega Mendoza, Javier
#    Date: 2024
#    Code version: 1.0
#    Availability: https://github.com/JRamirezDD/Aerotelcel
#

# Description: script to get all active flights

from opensky_api import OpenSkyApi

api = OpenSkyApi('Jasvort', 'AeroTelcel')

states = api.get_states()

for s in states.states:
    print(s)