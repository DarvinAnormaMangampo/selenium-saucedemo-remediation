# Milestone-F Test Automation

Prepared by Darvin Anorma Mangampo

Work period: 26 September to 3 October 2026

## Purpose and result

Milestone-E documented 19 manual SauceDemo variations on Firefox. I developed six independent Selenium workflows from its executed cases in the maintained TestNG lane. The set follows the common shopping flow and two visible error paths. It demonstrates script development within a defined scope. It did not set employer coverage priorities or change the inherited AutomationPractice scripts.

This lean scope kept the work focused on developing and verifying six workflows without expanding it into full-site automation.

The written Milestone-E outcomes became assertions in the F tests. I ran the scripts locally and reviewed the final browser images against the expected page states.

The verified Chrome run on 30 September 2026 (`20260930-210141-857`) passed all six F tests. No F test failed or was skipped in that run. A separate readiness test also passed, and the Gradle command exited `0`. An earlier diagnostic run deliberately failed all six F tests to check failure reporting. Its result is described under M-F-A3 and in the evidence section.

## Work completed

| Activity | Work performed |
| --- | --- |
| M-F-A1 | Documented the source baseline and protected workbook hashes. A Chrome review identified candidate controls and E case links. |
| M-F-A2 | Added a dedicated `milestoneFAutomation` task and nine Java classes in `src/maintainedTest/java/saucedemo/`. The tests use the existing Selenium and TestNG dependencies. |
| M-F-A3 | Checked failure reporting with deliberately wrong temporary expectations. The task exited `1` when all six tests failed. The failure screenshots were saved during the diagnostic run. I then removed the temporary changes before the final passing run. |
| M-F-A4 | I ran the full Milestone-F suite with Chrome maximized and a fresh ChromeDriver for each test. The six scripts passed with retries off. |
| M-F-A5 | Compared test names and results with the Milestone-E records. I checked the source files and protected workbooks again, then reviewed the ten checkpoint screenshots from the final passing run. |
| M-F-A6 | I completed the Milestone-F report and README. I pushed the technical commit and added the PDF with its supporting evidence to the repository. |

## Six automated workflows

| F test | Milestone-E result | Main check |
| --- | --- | --- |
| AUT-F01 | TC01 / RES-001 | Standard sign-in reaches Products and shows inventory. |
| AUT-F02 | TC02 / RES-002 | The locked-out account stays on sign-in with its error message. |
| AUT-F03 | TC05 / RES-008 and TC06 / RES-009 | Two products appear in Cart, then removal on Cart and Products leaves Cart empty. |
| AUT-F04 | TC07 / RES-010 | A blank First Name shows the required-field error and stays on the information page. |
| AUT-F05 | TC04 / RES-006 | Low-to-high is selected. Each next displayed price is the same or higher. |
| AUT-F06 | TC08 / RES-014 and RES-016 | Cart and Overview agree on the two products. Displayed totals reconcile. Finish reaches completion, where the PDF control is visible. No receipt is generated. |

I kept assertions in the test classes. Page classes hold the screen interactions. Each method starts a fresh browser. `BaseUiTest` saves a failed page before cleanup and leaves the original failure visible in the result. The timestamped Gradle output folder separates each run. Legacy Excel listeners, retry code and notification tools were not added to this lane.

One F03 development run timed out after a Chrome password warning was seen. F03 passed after the browser preference was adjusted for the test session. That observation was not documented as a SauceDemo defect. The later M-F-A3 six-failure run was an intentional check of reporting, not a routine passing run.

## Evidence in this package

The links below are relative to this README. They open the same files when the Milestone-F folder is viewed locally or on GitHub.

- [Milestone-F Test Automation report](./Milestone-F%20Test%20Automation.pdf) gives the result and its limits.
- [Suite summary](./Evidence/suite-summary.png) and [CheckoutTests detail](./Evidence/checkout-tests.png) are figures from the final passing Gradle HTML report.
- [Verified passing run log](./Execution%20Results/verified-run-20260930-210141-857/verified-run.log) and the four XML files in its folder show one passing readiness test, six passing F tests and exit code `0`. The [ten successful browser checkpoints](./Execution%20Results/verified-run-20260930-210141-857/screenshots/milestoneFAutomation/successful-tests/) are grouped by AUT-F01 through AUT-F06. Chrome was maximized for this run.
- [Deliberate failure diagnostic log](./Execution%20Results/diagnostic-20260930-122320-911/failure-diagnostic.log) and the three XML files in its folder show six induced failures and exit code `1`. Its [six failure screenshots](./Execution%20Results/diagnostic-20260930-122320-911/screenshots/milestoneFAutomation/failed-tests/) show the page state captured for each test before Chrome closed. This earlier run was made before the maximized-window change. The failures were caused by temporary wrong expectations, not by SauceDemo defects.
- [tested-source-sha256.md](./Evidence/tested-source-sha256.md): The checksum file identifies the eleven Milestone-F source and configuration files used in the verified passing run. It also includes the hashes of the two protected workbooks.

Gradle writes compiled classes and temporary files to `build/`. The full folder stayed in the local checkout, where `build/timestamped-runs/` still holds the complete HTML reports. For the repository, I copied the logs and every F test XML result from both runs to `Milestone-F/Execution Results/`. That location also contains the browser screenshots listed above. The prepared logs and XML files omit local path prefixes.

The verified passing run used Java 21 and Gradle 8.14.5 with Selenium 4.49.0 and TestNG 7.12.0. The same invocation reported Chrome `154.0.8037.58` and ChromeDriver `154.0.8037.92`. Gradle's CDP, SLF4J and deprecation messages were warnings. The Gradle JUnit-style XML shows zero failures and zero skipped tests. The source was still uncommitted at the time of the run, so the base commit alone does not identify the tested files. The checksum record does.

## Scope and conclusion

These six workflows cover common SauceDemo interactions drawn from Milestone-E's 19 manual variations. These results do not cover the full SauceDemo site or the inherited AutomationPractice suite. The other sort direction and missing checkout fields were outside this suite. F06 checked the PDF control but did not generate or inspect a receipt. One passing full run does not establish repeat-run stability or results in other browsers.

My earlier Selenium work involved maintaining and enhancing scripts. In Milestone-F, I developed new tests inside the established maintained lane and checked their results. I did not create the inherited framework or set employer coverage priorities. This work did not include client sign-off or a release decision.
