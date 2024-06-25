# Client for mockserver-server project
This is a demo project for representing a how to implement a MockServer in a service which calls a client.

### Ways to implement a MockServer:
There are multiple ways to implement MockServer, this project shows 2 example:
- **JavaApiIntegrationTest.kt:** Uses the client API to run or interact with MockServer programmatically.
- **JUnit5ExtensionIntegrationTests.kt:** Uses the MockServerExtension (JUnit5 Test Extension) to run or interact with MockServer.
- ... for more check: https://www.mock-server.com/mock_server/running_mock_server.html

### Build and Run
1. Run the application / integration-tests