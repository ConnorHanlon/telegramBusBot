# README
TODO:
- Re-adjust to command pattern
- Pull Webhook out of HandleRequest

- Fix generics for BusCommand and other commands
- Add bus commands
- Add commentary to commands on the user side of bot
- Remove classes no longer in use

- setup database for saving times to notify users inside of telegram commands

- Add Javadocs to generate documentation of bus bot

- redeploy new bus bot

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
