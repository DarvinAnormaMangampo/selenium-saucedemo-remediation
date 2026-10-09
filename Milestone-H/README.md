# Milestone-H: Repository Publication and Test Evidence Verification

Prepared by Darvin Anorma Mangampo\
Assessment period: 7–9 October 2026 (UTC+08:00)

## Purpose and result

Work from Milestone-A through Milestone-G established a maintained SauceDemo test path in an inherited Selenium repository. Milestone-H checked whether the published [root README](../README.md) could guide a fresh public clone through that path. It also checked public access to the reports from Milestone-A through Milestone-G and several evidence files. This was a publication and verification activity. It added no test cases.

The fresh clone used commit [`885d9b91`](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/commit/885d9b91174111cf59aa990b61d7801f841a736a). On 9 October, its first test-command attempt ran the Milestone-D readiness method and AUT-F01 through AUT-F06. The four XML files show **seven executions and seven passes**, with no failures, errors, skips or missing methods. Each method ran once. Gradle returned exit code `0`, which means the command completed successfully. The XML shows the test outcomes. There was no later test attempt.

## What changed before the check

The Milestone-G publication ended at commit `3593870f`. On 8 October, the local and remote heads matched that commit. The maintained source and execution configuration showed no change from the published Milestone-F source used for Milestone-G. The protected workbook hashes also matched the earlier evidence.

The fresh-clone check used the root README published at commit 885d9b91. That version gave the maintained SauceDemo command and linked to Kamil Nowocin’s original AutomationPractice repository. The older test suite remains separate from the SauceDemo results.

## Fresh-clone execution

The clone was separate from the working checkout, though it ran on the same Windows 11 machine. It used Java 21, Gradle 8.14.5 and Chrome 154.0.8037.98. From the public root README, I ran:

```powershell
.\gradlew.bat --init-script .\gradle\timestamped-build.init.gradle automationReadiness milestoneFAutomation --rerun-tasks --no-build-cache --no-daemon --console=plain
```

The command printed `OUTPUT_DIR` for run `20261009-112238-516` and ended with `BUILD SUCCESSFUL in 1m 23s`. The exit code was captured immediately after Gradle finished. No inherited AutomationPractice test appeared in that run's XML. The clone's tracked files stayed clean. Both protected workbook SHA-256 values were unchanged before and after the command and matched the Milestone-F and Milestone-G values.

## Public evidence

The [short report](./Milestone-H%20Repository%20Publication%20and%20Test%20Evidence%20Verification.pdf) explains the source comparison and workbook check. The files below belong to the single Milestone-H fresh-clone command attempt.

| File | Use |
| --- | --- |
| [Focused console log](./Execution%20Results/first-attempt-20261009-112238-516/fresh-clone-verification.log) | Command, timestamped output folder, test output, final build line and captured exit code. |
| [Readiness XML](./Execution%20Results/first-attempt-20261009-112238-516/TEST-readiness.SauceDemoReadinessSmokeTest.xml) | One passing login-page readiness method. |
| [Authentication XML](./Execution%20Results/first-attempt-20261009-112238-516/TEST-saucedemo.tests.AuthenticationTests.xml) | AUT-F01 and AUT-F02. |
| [Checkout XML](./Execution%20Results/first-attempt-20261009-112238-516/TEST-saucedemo.tests.CheckoutTests.xml) | AUT-F04 and AUT-F06. |
| [Inventory and cart XML](./Execution%20Results/first-attempt-20261009-112238-516/TEST-saucedemo.tests.InventoryAndCartTests.xml) | AUT-F03 and AUT-F05. |

This log is a focused copy of the saved visible console output. The generated build folder was not copied into the Milestone-H evidence set. In the publication copies, `[repository]` replaces the local path and `[local-host]` replaces the XML computer name. The four XML copies were parsed against the originals. Their result counts and method entries matched. The XML time fields matched too.

The run’s passing images show individual page states, while the focused log and XML show the seven method outcomes and command result. [Milestone-F evidence](../Milestone-F/README.md) already includes checkpoint images for these workflows, so another set was not copied into Milestone-H. No Milestone-H test failed, so no failure image was produced.

On 9 October at about 11:50 a.m., the [SauceDemo sign-in page](https://www.saucedemo.com/) loaded with the *Swag Labs* title and Login button. No credentials were entered in that brief check. The public GitHub check opened the baseline and comparison links from the root README. The licence opened as well. The README and PDF file pages for Milestone-A through Milestone-G opened, along with representative evidence pages from Milestone-E, Milestone-F and Milestone-G. The PDF pages offered a raw download. This check did not test every inline preview.

## Scope and conclusion

Milestone-H checked the published command on one Windows machine in Chrome. It did not repeat Milestone-E's 19 manual variations. The sign-in observation describes one point in time. The public link check covers only the pages named above on 9 October. The seven first-attempt passes support the reported result under these conditions. They do not establish cross-machine portability or release readiness.
