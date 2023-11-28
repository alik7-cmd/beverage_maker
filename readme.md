# Architecture Decision Record (ADR)
### Submitters

KH Rafiquel Islam (Alik) <br>
EngD trainee at Software Technology, 2023 <br>
Eindhoven University of Technology

## Change Log

State is one of: pending, approved, amended, deprecated.

Date is an ISO 8601 (YYYY-MM-DD) string.

Status: **Pending** <br>
Last Updated: 2023-11-27

## Referenced Use Case(s)

- [An requirement e-mail from professor Dr. Yanja Dajsuren.](https://www.google.com)
- [UML diagram of the initial architecture.]()

Initially we were asked to implement a software for the coffee machine. 

## Context

- Initially I thought to use the [Builder Pattern](https://refactoring.guru/design-patterns/builder) to create the BeverageOrder. Later I discarded the idea because in Kotlin it doesn't come with a lot of benefits and sometimes creates bottlenecks.
- Used Factory pattern to create the BeverageOrder wrapped inside BaseResponse.

## Proposed Design

- Implemented the Strategy pattern for [BeverageMaker](https://github.com/alik7-cmd/beverage_maker/blob/master/src/main/kotlin/common/maker/BeverageMaker.kt) such as create the set of objects (e.g: [CoffeeMaker](https://github.com/alik7-cmd/beverage_maker/blob/master/src/main/kotlin/coffee/CoffeeMaker.kt), [HotChocolateMaker](https://github.com/alik7-cmd/beverage_maker/blob/master/src/main/kotlin/chocolate/HotChocolateMaker.kt)) of this and makes them interchangeable inside original context object.
- Implemented the Strategy pattern for [Decorator](https://github.com/alik7-cmd/beverage_maker/blob/master/src/main/kotlin/common/decorator/Decorator.kt) such as create the set of objects (e.g: [CoffeeDecorator](https://github.com/alik7-cmd/beverage_maker/blob/master/src/main/kotlin/coffee/CoffeeDecorator.kt)) of this and makes them interchangeable inside original context object.
- Implemented the Factory pattern to create appropriate [BeverageMaker](https://github.com/alik7-cmd/beverage_maker/blob/master/src/main/kotlin/common/maker/BeverageMaker.kt)

## Considerations

Didn't use any Architecture like MVC or MVP. But might introduce one of these later period. Also thinking about how to pass the user input to the factory for different types of beverage.
Might create some other function of name prepareBeverage() with different parameter (Polymorphism). Also thinking about restructuring the packages.

## Decision

As per the design, The software is very open for any future requirements. 
We also have the support to sell other _**types**_ of beverages such as Ice-cream and Beers. 


## References
- [Don't Use The Builder Pattern in Kotlin.](https://backendhance.com/en/blog/2021/dont-use-builder-in-kotlin/)




