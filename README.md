airhacks-control
================

Attendee Management JavaFX Application with Convention over Configuration, IoC and Dependency Injection

The application was built entirely with Scene Builder with maintainability as the highest goal.

The application is used for maintaining the registrations of [Java EE Workshops](http://airhacks.com)
and uses the following design principles:
1. Convention Over Configuration: Presenters, Views, CSS and FXML files are named after a convention
2. Dependency Injection: Backed services / Business Logic are injected for better testability / simplicity
3. Generated Scene Builder code is encapsulated and separated from the business logic / presentation logic
4. Binding is used for communication between views and presenters, as well as, for input validation.
5. Maven is used as build tool.

Feedback / pull requests are highly appreciated. The ultimate goal: extraction of a minimalistic but
pragmatic and opinionated JavaFX framework / library.