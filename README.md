Conceptual Report

Question 1

By default, a new instance of a JAX-RS resource class is created for each request. Due to multiple
requests being handled by different instances of resource classes simultaneously, shared in-memory data
structures without any thread-safety measures are highly susceptible to corruption due to being
manipulated or accessed at the same time. To mitigate such issues and ensure thread safety, the
application utilises concurrent collections such as ConcurrentHashMap to store data, which can safely be
manipulated by multiple threads.

Question 2

Providing Hypermedia (HATEOAS) is a hallmark of advanced RESTful design because it renders the API
discoverable and self-descriptive. Instead of relying on documentation and hardcoded endpoints that must
be updated to the client manually, with hypermedia links, client developers can dynamically navigate the
API by following URIs embedded directly within the server's responses. This detaches the client
implementation from the server's specific routing structure, allowing the backend of the server to change
without breaking existing client applications.

Question 3

Returning only IDs minimises the initial size of the response, thereby reducing the network bandwidth
required. However, if the client requires details of specific rooms later, this will result in a higher network
bandwidth and latency due to the client sending more requests in the future to acquire data for individual
IDs. In contrast, returning full room objects increases the initial payload size but significantly reduces the
total number of HTTP requests required. Since the data shared with the smart campas api is smaller,
returning full room objects was chosen as the best approach.

Question 4

The DELETE operation is idempotent. The roomResource contains an if condition to check whether the
room exists. If a client mistakenly sends the same DELETE request for a specific room multiple times, the
initial request successfully removes the room and modifies the database. Any subsequent identical
requests will encounter a state where the resource is already absent, resulting in a “Room not found”
response without causing further modifications or side effects. The data remains unchanged after the first
successful deletion. Due to the DELETE operation deleting a room with a specific ID, the operation is
idempotent regardless of the if condition.

Question 5

The @Consumes annotation imposes a strict constraint that the media type must be JSON. If a
client attempts to send an unsupported data format, the JAX-RS block the request before the
code in the resource method is executed to prevent any damage. The clients will receive an
HTTP 415 Unsupported Media Type response.

Question 6

Utilising @QueryParam is superior for filtering because it treats filter criteria as optional add-ons to the
current URL path. In contrast, embedding the filter directly into the URL path defines the URL as a
distinct resource, leading to the creation of new URL paths for every filter combination. The query
parameter approach prevents URI bloat and offers better flexibility, especially when implementing
multiple, combinable search parameters that would otherwise require convoluted and rigid path routing
setups.

Question 7

The Sub-Resource Locator pattern enhances API architecture by dividing route handling to multiple
classes instead of centralising all logic for deeply nested URL paths into a single controller class. With a
Sub-Resource Locator pattern, the parent resource securely routes the handling of specific sub-paths to
dedicated sub-resource classes to be handled elsewhere. This separation reduces the code complexity,
improves readability, and simplifies unit testing, making the overall codebase significantly easier to
maintain and scale.

Question 8

HTTP 422 is semantically superior in this specific context because the standard 404 is a more general
error that usually indicates the endpoint URL doesn't exist. Unlike 404, 422 indicates that the data and
format sent by the client are correct, but the code failed to process the data due to incompatibilities with
the code's logic. In the smart campas api codebase, the error is thrown because the resource logic requires
a valid room ID. Error 422 is specifically designed to indicate the issues in processing instructions
(Unprocessable Entity).

Question 9

Exposing internal Java stack traces can lead to cybersecurity vulnerabilities due to an attacker's
ability to read and analyse these stack traces to map the application's underlying architecture,
including specific framework versions, database technologies, internal package naming
conventions, and file system paths. They can use this information to detect vulnerabilities in the
system and plan an attack that can bypass security measures.

Question 10

Using JAX-RS filters for cross-cutting concerns, such as logging, is considerably more advantageous than
manually inserting logger statements into every statement, primarily because it centralises loggin in a
single, dedicated class. When logging is implemented through a filter, every incoming request and
outgoing response is intercepted and recorded automatically at the framework level, without requiring any
change to the resource classes themselves. Manually embedding logger statements introduces significant
code duplication, increases the risk of inconsistent or missing log entries, and creates a higher probability
of error due to longer, more complex code as the API scales. Furthermore, if the logging format is to be
updated, a filter-based approach requires changes in only one place, while a manual approach would
require updates across every resource method in the entire codebase.