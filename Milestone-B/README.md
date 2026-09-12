# Milestone-B: Automation Execution Integrity

Prepared by: Darvin Anorma Mangampo

GitHub: [DarvinAnormaMangampo](https://github.com/DarvinAnormaMangampo)

Assessment period: 11-12 September 2026 (UTC+08:00)

## Purpose

Milestone-B began from the published Milestone-A state. I added a separate `maintainedTest` lane for one non-browser TestNG probe and used it to check the result returned by Gradle. The first run used `integrityProbe=fail`. I kept the console output and XML result from that run, then ran the same source with `integrityProbe=pass`.

## Authorship and repository boundary

The inherited application source and original test setup are not my work. My Milestone-B technical implementation consists of `build.gradle` and `src/maintainedTest/java/integrity/ExecutionIntegrityProbe.java`. Both files are in technical commit `882a08a7fec606855baf556ddffd25fd6105dda5`.

Work began from the completed Milestone-A commit `d48d41dbc53f3e675089f537bc2c45bc9980deea`. The inherited baseline tag still points to `4929e146a856d3b9ca2f04f5fda2eb014f599aa4`.

## Package contents

- [Milestone-B Automation Execution Integrity](./Milestone-B%20Automation%20Execution%20Integrity.pdf) - the report and its supporting screenshots.
- `README.md` - this overview and the links used to trace the repository changes.

## Recorded outcome

The maintained source compiled through its own path. TestNG 7.3.0 was declared directly for this source set. Its task uses the maintained compiled classes and runtime classpath, then selects only `ExecutionIntegrityProbe`.

No inherited XML suite is configured for the task. The inherited retry transformer and listeners are outside its classpath. The lane also leaves out the inherited hooks and workbook helpers. It contains no browser code.

The first request ran one test and failed as intended. Gradle marked the task and build as failed, returning exit code 1. The passing request used the same source. It ran one test and returned exit code 0.

Each retained XML result contains one invocation. No retry was observed in those two runs.

The tracked workbook kept this SHA-256 value:

`9ECECF817507E4762A560DD1BFDA6C563B79648114B4121C1C54906CFDA07B9C`

The signed technical commit contains only the two source paths named above. At technical closure, local HEAD matched `origin/master`, and the worktree was clean. No generated paths were included in the commit.

## Limits

Results only pertain to the separate non-browser TestNG lane. This does not indicate if the inherited test source compiles. Inherited legacy XML suites were not addressed or fixed in this milestone, nor was the readiness of Selenium and browsers.

No application function was tested. The intentional failure came from the integrity probe, so it is not an application defect.

## Repository links

- [Milestone-B technical commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/commit/882a08a7fec606855baf556ddffd25fd6105dda5)
- [build.gradle at the Milestone-B commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/blob/882a08a7fec606855baf556ddffd25fd6105dda5/build.gradle)
- [ExecutionIntegrityProbe.java at the Milestone-B commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/blob/882a08a7fec606855baf556ddffd25fd6105dda5/src/maintainedTest/java/integrity/ExecutionIntegrityProbe.java)
- [Milestone-A starting commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/commit/d48d41dbc53f3e675089f537bc2c45bc9980deea)
- [Inherited baseline tag](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/releases/tag/inherited-baseline-2026-09-05)
