Note:

The problem was approached as an OO design problem
(rather than as an API design problem for a Web-based application).
As such, request and response are modeled using regular classes.

Validation check has been mostly omitted.

Documentation has been omitted except in ReservationSystemClient.

Unit tests have been omitted.
Functional testing can be performed by running ReservationSystemClient.

====================================================================================================
Package Structure:

delivery.reservation

------ date_time
       ------ date
              ------ Day (enum)
              ------ CalendarDate
       ------ time
              ------ TimePeriod (enum)
              ------ Time
              ------ TimeSlot

------ schedule
       ------ Schedule
       ------ ScheduleTuple

------ reservation
       ------ Reservation

------ user
       ------ SupervisorInterface (interface)
       ------ Supervisor
       ------ Customer

------ request_response
       ------ request
              ------ RequestType (enum)
              ------ Request
       ------ response
              ------ ResponseType (enum)
              ------ Response

------ system
       ------ ReservationSystem

------ application
       ------ ReservationSystemClient (client program demonstrating the use of ReservationSystem)

====================================================================================================