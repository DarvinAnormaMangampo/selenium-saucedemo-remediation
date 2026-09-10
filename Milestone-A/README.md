# **Milestone-A: Baseline Assessment**

Prepared by: Darvin Anorma Mangampo

GitHub: **[DarvinAnormaMangampo](https://github.com/DarvinAnormaMangampo)**

Assessment period: 5–7 September 2026

## **Purpose**

This package records the inherited project’s starting point and the diagnostic work completed in Milestone-A. It brings together the fixed baseline, the original host environment, the tagged-source review, one bounded compilation attempt, and the integrity checks recorded before and after that attempt. Together, these records distinguish conditions already present at the baseline from later repository changes.

## **Authorship and repository boundary**

I prepared this package from the repository state and technical records listed below. My Milestone-A work covers the baseline record, original host environment, tagged-source analysis, bounded compilation attempt in a disposable clone, repository and workbook integrity checks, and the accompanying documentation.

The application source and its original configuration are inherited, and I do not claim authorship of them. The signed baseline tag fixes that inherited state at commit `4929e146a856d3b9ca2f04f5fda2eb014f599aa4`. The first repository change after the baseline is the separately signed `.gitignore` commit identified in the traceability links.

## **Package contents**

- [Milestone-A Baseline Assessment](./Milestone-A%20Baseline%20Assessment.pdf) — the consolidated report, including its supporting screenshots.
- `README.md` — this overview, with links to the repository sources cited in the report.

## **Recorded outcome**

The bounded assessment did not reach test execution. Compilation stopped during dependency resolution before any test ran. Static source review separately identified configuration that could suppress failure propagation, a retry setting applied globally, code paths capable of writing to the tracked workbook, and a default runner that combined the POC, WebAPI and WebAPP suites.

These findings describe the inherited configuration. The recorded attempt did not establish that any of those behaviours occurred at runtime, and no application-level pass or fail result is claimed.

## **What the report includes**

The report covers the baseline identity, original host environment, inherited failure-propagation and retry settings, workbook access and write paths, default-runner composition, dependency-resolution outcome, test-execution decision, and repository integrity checks. It contains no results from executed manual cases, browser checks, functional automation or regression measurement, and it reports no application-level defect finding.

## **Result boundaries**

The compilation result comes from one `compileTestJava` attempt in a clean detached clone using the inherited build, an isolated AdoptOpenJDK `14.0.2+12` environment, and Gradle `6.6.1`. Dependency resolution failed before any test ran. The result does not establish a test-logic defect, browser result, website condition or application failure.

Matching workbook hashes show that the tracked file was byte-for-byte identical at the two recorded observation points. They do not establish that its contents are correct. Because no tests or listeners ran, the source-level workbook-write risk remains unresolved.

## **Traceability anchors**

- [Signed inherited baseline](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/tree/inherited-baseline-2026-09-05)
- [Inherited baseline commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/commit/4929e146a856d3b9ca2f04f5fda2eb014f599aa4)
- [Gradle failure propagation](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/blob/inherited-baseline-2026-09-05/build.gradle)
- [Retry analyser](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/blob/inherited-baseline-2026-09-05/src/test/java/com/buildListeners/TestNGRetry.java)
- [Tracked workbook write helper](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/blob/inherited-baseline-2026-09-05/src/test/java/com/buildSettings/ExcelEnvironment.java)
- [Default runner composition](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/blob/inherited-baseline-2026-09-05/src/test/resources/xml_runners/main_runner/TestNG-Runner.xml)
- [Signed tracking-policy commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/commit/47f56f01cf2434e20c598b971c660cbdbde4bd7d)

The inherited baseline resolves to commit `4929e146a856d3b9ca2f04f5fda2eb014f599aa4`. The SHA-256 value recorded for `src/test/resources/files/testdata.xlsx` is `9ECECF817507E4762A560DD1BFDA6C563B79648114B4121C1C54906CFDA07B9C`.

## **Repository documentation setup**

A separate signed commit changed only `.gitignore`. It added narrow exceptions for `.txt` and `.log` files under `public-evidence/logs` and `.png` files under `public-evidence/screenshots`, while generated build output, caches and bulk reports remained ignored. The commit did not publish this README or the consolidated report. It was pushed to `origin/master`; the remote commit matched the local commit, and GitHub reported its signature as verified.

The change affected repository tracking rules only. It did not modify source code, dependencies, runner configuration, test data or runtime results.
