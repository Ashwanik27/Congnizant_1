# Upgrade Plan: JUnitExample (20260629161702)

- **Generated**: 2026-06-29
- **HEAD Branch**: main
- **HEAD Commit ID**: N/A (files not yet committed)

## Available Tools

**JDKs**
- JDK 17.0.16: C:\Users\Lenovo\.jdk\jdk-17.0.16\bin (available)
- JDK 21.0.8: C:\Users\Lenovo\.jdk\jdk-21.0.8(1)\bin (latest LTS, target version)
- JDK 23.0.1: C:\Program Files\Java\jdk-23\bin (current active)
- JDK 24.0.1: C:\Users\Lenovo\.jdks\openjdk-24.0.1\bin (available)

**Build Tools**
- Maven 3.9.12: C:\Users\Lenovo\.maven\maven-3.9.12\bin (compatible with Java 21)

## Guidelines

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260629161702
- Run tests before and after the upgrade: true

## Upgrade Goals

- **Java**: 11 (default/current project) → **21** (latest LTS)

## Technology Stack

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
|-----------------------|---------|-----------------|------------------|
| Java | 11 (default, unspecified) | 21 | User requested upgrade to latest LTS |
| Maven | 3.9.12 | 3.9.0 | Maven 3.9+ recommended for Java 21 |
| JUnit | 4.13.2 | 4.13.2 | Compatible with Java 21 |
| maven-compiler-plugin | (default 3.8.1) | 3.11.0+ | Recommended for better Java 21 support |
| maven-surefire-plugin | (default 2.22.2) | 3.0.0+ | Recommended for Java 17+ module system support |

## Derived Upgrades

- **maven-compiler-plugin** → 3.11.0 (or later): Recommended for explicit Java 21 support and improved toolchain configuration
- **maven-surefire-plugin** → 3.1.2 (or later): Recommended for Java 17+ with better module system support and test isolation

## Impact Analysis

### Subsection: Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
|------|-----------|---------|--------|--------|--------|
| pom.xml | java.version (property) | (not set) | add | 21 | Configure project to target Java 21 |
| pom.xml | maven-compiler-plugin | 3.8.1 (default) | add/configure | 3.11.0 | Explicit plugin configuration for Java 21; needed to ensure proper compilation |
| pom.xml | maven-surefire-plugin | 2.22.2 (default) | add/configure | 3.1.2 | Explicit plugin configuration for test execution on Java 21 |

### Subsection: Source Code Changes

| File | Location | Current | Required Change | Reason |
|------|----------|---------|----------------|--------|
| CalculatorTest.java | (review) | JUnit 4 tests | Verify compatibility with Java 21 (no changes expected) | JUnit 4.13.2 is compatible with Java 21 |
| Calculator.java | (review) | Java source code | Verify no reflection/internal API usage that breaks on Java 21 | Java 21 has strong encapsulation of internal APIs |

### Subsection: Configuration Changes

No configuration file changes required (application.properties not present).

### Subsection: CI/CD Changes

No CI/CD files detected (no Dockerfile, workflows, or pipeline files in the project).

### Subsection: Risks & Warnings

- **Internal API usage**: If any code uses internal Java APIs (e.g., `sun.misc.*`, `sun.reflect.*`, `jdk.internal.*`), compilation/runtime failures may occur on Java 21. **Mitigation**: Code review and rewrite to use public APIs; if unavoidable, document as a limitation.
- **Test isolation on Java 21**: Java 21 has improved module system support. Tests may fail if they rely on reflection into classes from different modules. **Mitigation**: Run full test suite and fix any failures by updating test code to use proper reflection APIs.

## Upgrade Steps

- **Step 1: Setup Environment**
  - **Rationale**: Verify that Java 21 JDK and Maven are available for the upgrade process
  - **Changes to Make**: Install Java 21 if needed (already available at C:\Users\Lenovo\.jdk\jdk-21.0.8(1)\bin)
  - **Verification**: Command: `java -version` using JDK 21 path. Expected Result: Java 21.0.8 confirmed

- **Step 2: Setup Baseline**
  - **Rationale**: Establish baseline test pass rate with current Java version before making changes, to compare against post-upgrade results and detect regressions
  - **Changes to Make**: Run tests with Maven using current JDK version (default system Java)
  - **Verification**: Command: `mvn clean test-compile && mvn clean test`. Expected Result: Compilation SUCCESS, all tests PASS. Document test pass rate as baseline.

- **Step 3: Add Java Version Configuration**
  - **Rationale**: Configure pom.xml to explicitly target Java 21, ensuring consistent compilation and test execution across environments
  - **Changes to Make**: 
    - Add `<properties><java.version>21</java.version></properties>` to pom.xml
    - Add explicit `maven-compiler-plugin` (version 3.11.0) configuration with source and target 21
    - Add explicit `maven-surefire-plugin` (version 3.1.2) configuration for test execution
  - **Verification**: Command: `mvn -version` to confirm Maven, then `mvn clean test-compile -q`. Expected Result: Compilation SUCCESS using Java 21 configuration

- **Step 4: Verify Compilation with Java 21**
  - **Rationale**: Ensure all source code and test code compiles cleanly with Java 21 compiler and configuration
  - **Changes to Make**: None (configuration from Step 3)
  - **Verification**: Command: `mvn clean test-compile -q` with Java 21 JDK. Expected Result: Compilation SUCCESS for both main and test classes

- **Step 5: Final Validation**
  - **Rationale**: Execute full test suite with Java 21 to confirm all tests pass and no runtime regressions exist. Fix any failures to achieve 100% pass rate.
  - **Changes to Make**: Fix any test failures discovered during execution (apply minimal code changes to test or source files as needed)
  - **Verification**: Command: `mvn clean test -q` with Java 21 JDK. Expected Result: All tests PASS (100% pass rate). If failures occur, iteratively fix and re-run until all tests pass.
  - **CVE Validation**: Run `mvn dependency:list -DexcludeTransitive=true` to extract direct dependencies, then validate for CVEs. Upgrade any vulnerable dependency versions.

---

## Summary

This plan upgrades a simple Maven-based JUnit test project from an unspecified Java version (default system Java, currently 23) to Java 21 LTS. The upgrade involves:

1. Configuring the pom.xml with explicit Java 21 target version property
2. Adding/configuring maven-compiler-plugin and maven-surefire-plugin for Java 21
3. Verifying compilation and test execution with Java 21
4. Ensuring no regressions in the existing JUnit 4 test suite

The project has minimal dependencies (only JUnit 4.13.2) and no complex framework dependencies, making this a straightforward upgrade. All test code should compile and pass without modification on Java 21.
