# Micronaut Test Property Provider Example Project

This is only an extraction of much bigger project to show a problem when upgrading from Micronaut 3.10.1 to the latest
4.2.4 version.

## The Problem

There are two test classes in the project:

- `TestPropertyProviderConstructorTest` - dependencies are injected by constructor
- `TestPropertyProviderAnnotationTest` - dependencies are injected using annotated property

Both tests use Kafka test container and implements `TestPropertyProvider` to provide bootstrap servers URL to
Micronaut. It is a known fact that `TestPropertyProvider` does not work when the test uses constructor based dependency
injection and because of that `TestPropertyProviderConstructorTest` always fails.

But, and there is our problem, `TestPropertyProviderAnnotationTest` is ignored after upgrade to Micronaut 4.2.4.
While `TestPropertyProviderConstructorTest` is not ignored in Micronaut 4.x, and it seems like the constructor based DI
could be used as a workaround to _ignore issue_, but not when `TestPropertyProvider` is needed. Is there any other way
how to overcome this state in Micronaut 4?

## Steps to Reproduce

Branch _main_ uses Micronaut 3.10.1 and branch _upgrade_ uses Micronaut 4.2.4.

1. Execute test by `./gradlew clean test --info` and then _2 tests completed, 1 failed_.
2. Switch branch `git checkout upgrade` to use more recent Micronaut.
3. Execute the test again `./gradlew clean test` and then _2 tests completed, 1 failed, 1 skipped_.
