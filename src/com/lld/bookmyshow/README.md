# Design BookMyShow like movie booking app

1. Users should be able to search movies by movie name. For this design, let's go for 
prefix matching by name.
2. There are multiple theatre and each theatre has multiple halls / screens.
3. Same movie can be shown to multiple screens concurrently
4. Multiple experience like 2D, 3D, IMAX 2D, ICE, Dolby etc. are supported for a movie.
Also assume a single screen can support multiple experience but for a given show, it will
support only one type of experience.
5. Pricing information will vary depending on the experience
6. A user can book upto 10 seats for a movie
7. Seat selection ideally should be as much as collocated as possible for a booking, but
we can skip that algorithm for this problem.
8. Handle concurrent booking scenario for the same seat.