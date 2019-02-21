# README

## About

The Twin Cities Metro Transit bot allows Telegram users to obtain real time tracking information for metro transit stops, routes associated with a stop, as well as the directions of any route in the Twin Cities. 

To locate and use the bot, you must first have access to Telegram with an account. Once you have access, simply follow the link below and click on the start bot button.

https://t.me/TCMetroTransitBot

## User Commands

There are three main commands that Telegram users can utilize:

1. /departures
  - Input: Metro Transit Stop ID located on Metro Transit sign
  - Return Value: All future times of arrival for desired Stop ID as well as the Route Number and Direction

2. /directions
  - Input: Route Number
  - Return Value: Directions of travel for the desired route number. In the form NORTHBOUND, SOUTHBOUND, EASTBOUND, WESTBOUND
  
3. /stops
  - Input: Route Number and Direction (North, South, East, West)
  - Return Value: List of all stops associated with the input route number and direction


Test Values:
- /departures 11167
- /directions 65
- /stops 65 North


## Code Structure of Project
There are three main packages for this project.
1. TwinCitiesTransitSchema :
  There are 4 response schemas for the Metro Transit, so within this package there corresponds classes for 
  each of the schema to allow for object creation of the returned json.
  
2. http_request_tools :
  Handles all requests to the Metro Transit API as well as the Telegram API. The URLs are stored and build using this package's URL creator class.

3. commands :
  This package is broken up into two packages, one for telegram commands and one for bus/user commands. When a user enters a command, the CommandProcessor determines which user command was entered. Based on the information received, a request url is built and request made of Metro Transit API using the http_request_tools package. The returned json is formatted and a message is returned to the user.



TODO/FUTURE IMPLENTATION:

- setup database for saving times to notify users inside of telegram commands

- Add Javadocs to generate documentation of bus bot


### Difficulties
- Cannot cast Object[] to T[], which was essential for parsing the json into a
  formatted string response.

- cannot have a static abstract method

- BusCommand abstract class was originally designed to have an implemented execute method, and
  leave the formatResponse and formatRequest method implementation up to the commands
  that extend the class. However I did not want to have to instantiate a command
  object to format the responses and requests. Additionally, BusCommand's execute requires the
  use of generics, and as stated previously Object Arrays cannot be cast to generic arrays.

### Notes

- StringBuffer was used over StringBuilder because it is thread safe
