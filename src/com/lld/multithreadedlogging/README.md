Design a logging system:

1. The system supports multiple log level like error, debug, warning, info etc.
2. Multiple log mediums are supported like file based logs, network logs, db logs etc.
3. Multiple producers can produce to the queue simultaneously.
4. Irrespective of the rate of production, the system should consume messages as fast
as possible from the queue and flush them to the log medium.
5. A single applicationAwareMessage can get published to multiple medium.
6. 