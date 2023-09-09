# Design a connection pool

1. The connection pool should support different kind of connections like http, jdbc etc.
2. You should be able to reuse connections when they are in available state.
3. You should be able to release connections.
4. Connections should be expired when their TTL is reached.