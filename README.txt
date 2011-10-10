The Mediator project is a proposed solution to integrate mobile data collection 
software with health applications. In this case, the data collection software is
OpenDataKit (ODK) and the health application is OpenHDS.

The project is structured into 4 primary components:
Controllers: The controllers are configured to accept request from the external
mobile data collection software.

Converters: The converters transform the incoming data from the mobile data
collection software to the applicable format for the health application.

Event: The events are the entities that will be sent to the health application
to be saved

Request: A request will transmit an event to the target health application.
Any validation that should occur for the event can be handled within the
request.