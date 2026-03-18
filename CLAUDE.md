# Claude Instructions

You are to follow a strict test-driven development (TDD) workflow for all code changes. The process is as follows:

1. Write Tests First:

- Begin by writing comprehensive tests (unit, integration, or end-to-end) that define the expected behavior of the code.
- Use explicit input/output pairs and edge cases.
- Do not write any implementation code at this stage, even if the functionality does not yet exist.
- Confirm that you are only writing tests, not mock implementations.

2. Run and Confirm Failing Tests:

- Run the tests and confirm that they fail, as the implementation does not exist yet.
- Do not write or suggest any implementation code at this stage.

3. Commit the Tests:

Once the tests are complete and you are satisfied with their coverage, commit only the test code.

4. Write Implementation Code:

- Write the minimal implementation code required to make the tests pass.
- Do not modify the tests during this step.
- Iterate: If the tests do not pass, adjust the implementation and rerun the tests until all pass.
- Optionally, verify with independent subagents or reviewers that the implementation is not overfitting to the tests.

5. Commit the Implementation:

- Once all tests pass and you are satisfied with the implementation, commit the code changes.


**Guidelines:**

- Always keep tests and implementation changes in separate commits.
- Do not write any implementation code before the tests are written and committed.
- Do not modify the tests after they are committed, unless explicitly instructed to do so.
- Be explicit and transparent about each step, confirming completion before moving to the next.

**Your goal:** Incrementally build and verify code by iterating between writing tests and writing implementation, ensuring each change is easily verifiable and traceable.

## Test Code

When writing test code, please follow these guidelines:

- Use JUnit 5 for all test classes
- Name test methods using the pattern: `should<ExpectedBehavior>When<Condition>`
- Structure each test with the Arrange-Act-Assert pattern
- Use AssertJ for all assertions
- Avoid for loops and if statements in tests
- Avoid comments in the test code
- Name the class under test variable as cut
- Create a separate test class for each production class
- Follow a consistent setup pattern for all tests
- Use `@DisplayName` for more descriptive test names in reports
- Group related tests with `@Nested` classes
- Use parameterized tests for testing multiple scenarios
- Mock external dependencies with Mockito
- Use Java records for test data classes
- When constructing test data for existing domain classes, use the Builder and Object Mother pattern
- Use Java text blocks for larger JSON data
