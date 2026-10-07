# Milestone-G Repeat Runs and Result Analysis

Prepared by Darvin Anorma Mangampo
GitHub: [DarvinAnormaMangampo](https://github.com/DarvinAnormaMangampo)
Run date: 5 October 2026 (UTC+08:00)

## Purpose and result

Milestone-F documented one full passing run of six SauceDemo methods. That run happened before the Milestone-F source was committed, so its checksum file identifies the tested code. Milestone-G used the published commit [`5a4a023`](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/commit/5a4a02378360805f8a2aed6ac22243d58e9eb6c3). I ran seven methods in each command: the six Milestone-F SauceDemo tests and the Milestone-D login-page readiness check. I wanted to see whether the same code would give the same first-attempt outcomes across three runs. One Milestone-F run could not answer that question.

All seven methods ran once per command and passed. The three commands produced **21 passing first-attempt executions**. There were no failed, skipped or missing methods. Each command exited `0`, meaning Gradle reported successful completion. The XML shows the actual test outcomes. Retry had already been off in the maintained lane since Milestone-B. Milestone-G did not change that setting or add test code.

## Repeat runs and regression testing

The [ISTQB Foundation syllabus](https://istqb.org/wp-content/uploads/2024/11/ISTQB_CTFL_Syllabus_v4.0.1.pdf) describes regression testing as checking whether a change caused adverse effects. In Milestone-G I compared first-attempt outcomes from one published suite. Chrome's version had changed since Milestone-F, but these commands did not isolate the effect of that change or verify an identified SauceDemo change. I therefore report them as repeat runs.

## At a glance

| Item | Recorded result |
| --- | --- |
| Published source | `5a4a02378360805f8a2aed6ac22243d58e9eb6c3` |
| Run scope | 7 methods per command, 21 executions across 3 commands |
| Actual result | 21 executed and passed, 0 failed, 0 skipped, 0 not run |
| Retries | 0. Each method appears once in each run's XML |
| Source changes during the runs | None. The worktree was clean before each command |
| Protected workbooks | Both SHA-256 values matched before and after every run |

## Run results

| Run | Start, 5 Oct (UTC+08:00) | Timestamped run ID | Chrome / driver | Executed | Passed | Failed | Skipped | Not run | Exit | Build time |
| --- | --- | --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | --- |
| 1 | 20:35:57 | `20261005-203603-404` | `154.0.8037.98` / `154.0.8037.92` | 7 | 7 | 0 | 0 | 0 | `0` | 1m 4s |
| 2 | 21:46:30 | `20261005-214636-568` | `154.0.8037.98` / `154.0.8037.92` | 7 | 7 | 0 | 0 | 0 | `0` | 1m 3s |
| 3 | 22:11:35 | `20261005-221141-448` | `154.0.8037.98` / `154.0.8037.92` | 7 | 7 | 0 | 0 | 0 | `0` | 1m 1s |

The build time is the Gradle duration. It is separate from the XML suite times.

| Method | Run 1 | Run 2 | Run 3 |
| --- | --- | --- | --- |
| Milestone-D readiness | Pass (1) | Pass (1) | Pass (1) |
| AUT-F01 standard sign-in | Pass (1) | Pass (1) | Pass (1) |
| AUT-F02 locked-out sign-in | Pass (1) | Pass (1) | Pass (1) |
| AUT-F03 cart changes | Pass (1) | Pass (1) | Pass (1) |
| AUT-F04 required first name | Pass (1) | Pass (1) | Pass (1) |
| AUT-F05 price sorting | Pass (1) | Pass (1) | Pass (1) |
| AUT-F06 two-item checkout | Pass (1) | Pass (1) | Pass (1) |

## Result analysis

The number in parentheses is the actual XML execution count. No test outcome varied across the three runs. No failure needed classification in Milestone-G. The earlier [Milestone-F evidence](../Milestone-F/README.md) includes a separate deliberate-failure run that checked failure reporting and browser image capture.

Run 2 started 1 hour 10 minutes 33 seconds after run 1. Run 3 started 25 minutes 5 seconds after run 2. I reviewed and saved each completed run before starting the next, so the runs were not back to back. All three original attempts remain in the run record. `CheckoutTests` reported XML suite times of 27.568, 6.855 and 27.605 seconds. These are XML test execution times, not measured SauceDemo response times. Both checkout methods still passed once in every run. I had set no performance target, and the XML does not show which checkout step took longer.

## Integrity check

Milestone-A found inherited code that could write to the tracked `testdata.xlsx`. The separate maintained lane excludes that listener. I checked both protected workbooks before run 1, after each run and again on 6 October. The full SHA-256 values were the same at every check:

| Workbook | SHA-256 |
| --- | --- |
| `testdata.xlsx` | `9ECECF817507E4762A560DD1BFDA6C563B79648114B4121C1C54906CFDA07B9C` |
| `backup-testdata.xlsx` | `A09FB992C49034F0465F5ECEA42628F1EA9BB6C7668F1523789B2FBD0F5366A2` |

## How the runs were made

The worktree was clean before each command and at the final check. All three used this Gradle command from the same commit:

```powershell
.\gradlew.bat --init-script .\gradle\timestamped-build.init.gradle automationReadiness milestoneFAutomation --rerun-tasks --no-build-cache --no-daemon --console=plain
```

The forced flags stopped Gradle from using cached or up-to-date test results. Each expected method appeared once in the Gradle JUnit-style XML. Run 3 piped its console output to a file, with the same Gradle tasks and flags. The runs used Windows 11 with Java 21. Gradle was version 8.14.5. Chrome had moved from `154.0.8037.58` in the verified Milestone-F run to `154.0.8037.98` here. The repeat runs also checked the suite in the newer Chrome version. All seven methods executed and passed there. These results do not isolate the effect of the Chrome version change or establish whether SauceDemo changed.

## Scope and limits

This result covers three Chrome commands on one Windows machine. The start-time gaps limit what can be said about consecutive operation. The live SauceDemo site was outside my control, and these runs do not show when any site change reached it. The remaining eleven Milestone-E variations were outside these automated repeat runs. I did not test another browser or the inherited AutomationPractice suite. No client sign-off or release decision was part of this activity.

## Relationship to earlier Milestones

The earlier milestones explain this narrow scope. Milestone-A identified an inherited retry setting and a workbook write path. Milestone-B set up a separate test lane without that retry code. Milestone-C restored its Java build. Milestone-D added the Chrome readiness check. Milestone-E supplied manual SauceDemo outcomes, which Milestone-F used for six automated workflows. Here, I repeated those published workflows and checked the resulting evidence.

## Work completed

| Activity | Work performed | Result |
| --- | --- | --- |
| M-G-A1 | I checked the published commit and the clean worktree. I also saved the tool versions and both workbook hashes before run 1. | The source and seven-method scope were fixed. The workbook hashes gave the later runs a baseline. |
| M-G-A2 | I ran the same forced Gradle command three times. Every command attempt and its timestamped output were retained. | Each run produced four XML files and exited `0`. |
| M-G-A3 | I compared the XML method results with the console output, then checked the workbook hashes after each run. | All 21 method executions passed once. No outcome varied. |
| M-G-A4 | I prepared focused logs and XML copies for the repository, with this report and README. | The evidence is grouped by run ID below. |

## Evidence files

The links below are relative to this README when it is in `Milestone-G/`. The three folders under `Milestone-G/Execution Results/` are `run-1-20261005-203603-404/`, `run-2-20261005-214636-568/` and `run-3-20261005-221141-448/`. Each run folder contains one focused log and the four XML files it produced. Each run saved ten successful browser checkpoint images in its local timestamped folder. The Milestone-G evidence uses the logs and XML to compare results across the runs. Milestone-F already provides browser checkpoint images for these six workflows, so they were not copied into the Milestone-G package. No Milestone-G test failed, so there was no failure image to include.

| Run | Log | Readiness XML | Authentication XML | Inventory and cart XML | Checkout XML |
| --- | --- | --- | --- | --- | --- |
| Run 1 | [repeat-run.log](./Execution%20Results/run-1-20261005-203603-404/repeat-run.log) | [readiness](./Execution%20Results/run-1-20261005-203603-404/TEST-readiness.SauceDemoReadinessSmokeTest.xml) | [authentication](./Execution%20Results/run-1-20261005-203603-404/TEST-saucedemo.tests.AuthenticationTests.xml) | [inventory and cart](./Execution%20Results/run-1-20261005-203603-404/TEST-saucedemo.tests.InventoryAndCartTests.xml) | [checkout](./Execution%20Results/run-1-20261005-203603-404/TEST-saucedemo.tests.CheckoutTests.xml) |
| Run 2 | [repeat-run.log](./Execution%20Results/run-2-20261005-214636-568/repeat-run.log) | [readiness](./Execution%20Results/run-2-20261005-214636-568/TEST-readiness.SauceDemoReadinessSmokeTest.xml) | [authentication](./Execution%20Results/run-2-20261005-214636-568/TEST-saucedemo.tests.AuthenticationTests.xml) | [inventory and cart](./Execution%20Results/run-2-20261005-214636-568/TEST-saucedemo.tests.InventoryAndCartTests.xml) | [checkout](./Execution%20Results/run-2-20261005-214636-568/TEST-saucedemo.tests.CheckoutTests.xml) |
| Run 3 | [repeat-run.log](./Execution%20Results/run-3-20261005-221141-448/repeat-run.log) | [readiness](./Execution%20Results/run-3-20261005-221141-448/TEST-readiness.SauceDemoReadinessSmokeTest.xml) | [authentication](./Execution%20Results/run-3-20261005-221141-448/TEST-saucedemo.tests.AuthenticationTests.xml) | [inventory and cart](./Execution%20Results/run-3-20261005-221141-448/TEST-saucedemo.tests.InventoryAndCartTests.xml) | [checkout](./Execution%20Results/run-3-20261005-221141-448/TEST-saucedemo.tests.CheckoutTests.xml) |

The [Milestone-G report](./Milestone-G%20Repeat%20Runs%20and%20Result%20Analysis.pdf) explains why I repeated the suite and how I assessed the results. Gradle also generated compiled classes and full HTML reports under `build/` in the local checkout. The generated build folder stays in the local checkout. The repository package contains the logs and every XML result from these runs. Those files show the commands and actual test outcomes needed here. The publication copies replace the local repository path and computer name with generic labels. I parsed the prepared XML again and confirmed its method names, counts, outcomes and times matched the retained originals.

## Conclusion

In my earlier employment, I maintained and executed Java Selenium scripts for smoke and regression testing. I also examined automation results and failure logs to report test status. In Milestone-G I used that experience to compare the same seven methods across three commands. With the recorded local setup, all seven methods passed on each of the three runs on 5 October. Three runs cannot rule out a later intermittent failure or establish long-term stability.
