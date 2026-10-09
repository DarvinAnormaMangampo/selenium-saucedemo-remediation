# SauceDemo Testing in an inherited Selenium Repository

## Project context

This fork keeps [Kamil Nowocin's AutomationPractice project](https://github.com/kamil-nowocin/Test_Automation-automationpractice). My earlier Java Selenium work involved maintaining and improving existing automation. I could not use client applications in a public portfolio, so I chose this older public repository as a starting point. Its tests targeted AutomationPractice, but that website was not a usable target for this work. I kept the repository's history and used SauceDemo for new manual checks and automated workflows.

I addressed build and execution issues in the inherited repository. I updated its existing Gradle configuration and wrapper, which are shared project files, and added a separate `maintainedTest` source set for SauceDemo. The new lane runs through that updated build but uses its own test classes and dependencies. Selected manual SauceDemo results became the basis for the new Selenium scripts. This work involved maintaining an existing automation project as well as developing new tests. The original AutomationPractice suite remains in the repository, but its tests and helpers were not used for the SauceDemo results.

## Results at a glance

| Milestone | Work and date | Outcome |
| --- | --- | --- |
| E | Prepared cases and test data, then manually checked 19 SauceDemo variations across nine case groups in Firefox on 22 September 2026. | All 19 passed on the first documented attempt. The execution workbook links each result to its expected outcome and evidence. |
| F | Developed six Selenium workflows from selected E outcomes and ran them in Chrome on 30 September 2026. | All six F tests passed in the verified run, along with the separate Milestone-D readiness method. Gradle returned exit code `0`, indicating successful command completion. |
| G | Repeated those six methods and the readiness method three times on 5 October 2026, using the same published repository commit. | The three commands produced 21 first-attempt passes. The XML has no failed or skipped methods, and none of the seven was missing. Neither protected workbook changed. |

Milestone-A examined the inherited repository. Gradle could not resolve the dependencies needed to compile its original tests, so none of those tests ran. The inherited setup also had retry code and an Excel listener that could write to a tracked workbook. In Milestone-B, I set up a separate TestNG lane without that retry code. This meant a later retry could not hide a failed first attempt. I made a test assertion fail on purpose and confirmed that Gradle reported the task as failed. Milestone-C made that maintained lane build with Java 21 and Gradle 8.14.5. Milestone-D added a Chrome check of the SauceDemo login page without signing in. Milestone-F and Milestone-G later used this maintained lane for their runs.

The Milestone-F workflows follow a short shopping path and include two visible error responses. They use selected Milestone-E outcomes rather than all 19 manual variations. An earlier Milestone-F diagnostic deliberately used wrong expectations. All six tests failed, the task exited `1`, and failure screenshots were saved. I removed those temporary changes before the verified passing run. The [F source checksum file](./Milestone-F/Evidence/tested-source-sha256.md) identifies the tested files because the source was uncommitted at run time. Milestone-G then used the [published F repository commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/commit/5a4a02378360805f8a2aed6ac22243d58e9eb6c3) for all three repeat runs. No method changed outcome in those runs.

## Run the maintained checks

The published Milestone-F and Milestone-G runs used Windows 11, Java 21, Chrome and the Gradle 8.14.5 wrapper. Set `JAVA_HOME` to a JDK 21 installation. From the repository root in PowerShell, run:

```powershell
.\gradlew.bat --init-script .\gradle\timestamped-build.init.gradle automationReadiness milestoneFAutomation --rerun-tasks --no-build-cache --no-daemon --console=plain
```

`automationReadiness` checks that the login page is ready without signing in. `milestoneFAutomation` selects AUT-F01 through AUT-F06 from the maintained source set. Both tasks fail if no test matches or an executed test fails. The forced-run flags prevent Gradle from reusing earlier task results. Selenium Manager resolves ChromeDriver. Gradle prints `OUTPUT_DIR` for the new timestamped folder. The JUnit-style XML in that folder lists which methods ran and their outcomes. Check the XML alongside the command's exit code. The inherited `test` task runs a different suite. Use the two named tasks above for the Milestone-F and Milestone-G checks.

## Milestone documents

| Milestone | What it shows | README | Report |
| --- | --- | --- | --- |
| A | Inherited baseline and execution findings | [README](./Milestone-A/README.md) | [PDF](./Milestone-A/Milestone-A%20Baseline%20Assessment.pdf) |
| B | Separate TestNG lane and failure reporting | [README](./Milestone-B/README.md) | [PDF](./Milestone-B/Milestone-B%20Automation%20Execution%20Integrity.pdf) |
| C | Build restoration for the maintained lane | [README](./Milestone-C/README.md) | [PDF](./Milestone-C/Milestone-C%20Build%20Restoration.pdf) |
| D | Chrome login-page readiness | [README](./Milestone-D/README.md) | [PDF](./Milestone-D/Milestone-D%20Automation%20Readiness.pdf) |
| E | Manual cases, results and evidence | [README](./Milestone-E/README.md) | [PDF](./Milestone-E/Milestone-E%20Manual%20Testing.pdf) |
| F | Six automated workflows and failure diagnostic | [README](./Milestone-F/README.md) | [PDF](./Milestone-F/Milestone-F%20Test%20Automation.pdf) |
| G | Three repeat runs and result analysis | [README](./Milestone-G/README.md) | [PDF](./Milestone-G/Milestone-G%20Repeat%20Runs%20and%20Result%20Analysis.pdf) |
| H | Fresh-clone verification and public access check | [README](./Milestone-H/README.md) | [PDF](./Milestone-H/Milestone-H%20Repository%20Publication%20and%20Test%20Evidence%20Verification.pdf) |

## Origin and contributions

The [baseline tag](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/tree/inherited-baseline-2026-09-05) preserves the inherited starting point. The [baseline-to-current comparison](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/compare/inherited-baseline-2026-09-05...master) shows this fork's changes. The original project remains credited to Kamil Nowocin under its [MIT licence](./LICENSE.md).

| Origin | What is in this repository |
| --- | --- |
| Inherited | AutomationPractice source and the original Gradle project. Its older tests were not used to produce the SauceDemo results. |
| Changed in this fork | I updated `.gitignore` to track approved evidence formats. I also updated the shared Gradle build and wrapper for the separate `maintainedTest` source set and its strict TestNG tasks. The original AutomationPractice test source was left in place. |
| Added in this fork | The Chrome readiness method and Milestone-E's manual cases, results and evidence. Milestone-F added SauceDemo tests with page classes. Its init script separates run output by timestamp. Milestone-G added the repeat-run evidence. The maintained Java source is under [`src/maintainedTest/java/`](./src/maintainedTest/java/). |

The inherited `.travis.yml` names the older `test` task, and `.travis/deploy.sh` points to an upstream report page. The maintained command above does not invoke either file.

## Coverage and limits

Milestone-E's cases used public SauceDemo information and visible page responses. For prices and totals, I compared values from the same manual run. Milestone-E manually generated and checked a PDF order receipt. Milestone-F's AUT-F06 stopped at the completion page and checked that its PDF control was visible. The test did not generate a receipt. Eleven of Milestone-E's variations were not included in Milestone-F's six workflows.

Milestone-E ran in Firefox. The Milestone-D, Milestone-F and Milestone-G automation evidence comes from Chrome on one Windows machine. Milestone-G's commands had gaps between their start times. They show matching first-attempt outcomes on 5 October 2026. These results cover the executed methods on those dates. They do not cover the whole SauceDemo site, a second automation browser or the inherited AutomationPractice suite. Client approval and release decisions were outside these activities.
