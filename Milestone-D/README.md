# Milestone-D: Automation Readiness

Prepared by: Darvin Anorma Mangampo

GitHub: [DarvinAnormaMangampo](https://github.com/DarvinAnormaMangampo)

Assessment period: 15-16 September 2026 (UTC+08:00)

## Purpose

Milestone-D began from the published Milestone-C reference, `bc95cd1a88defa160f5eb7d42d708f35a07a3667`. The separate maintained test lane could already compile with Gradle 8.14.5 and Eclipse Temurin JDK 21. Previous tests showed that the Gradle process returned exit code 0 when the integrity probe passed. It returned exit code 1 during a deliberate failure check. The inherited test source was neither compiled nor run during those tests. The next step was to confirm browser readiness on SauceDemo. The check also had to report its result correctly and close the created session.

I used Selenium 4.49.0 as a pinned dependency in the separate maintained test lane. The SauceDemo readiness check did not enter credentials or submit a login. I added Selenium only to maintainedTestImplementation and left the inherited dependency buckets unchanged. The original forced uncached acceptance run passed once.

This is a browser-readiness result. It does not establish login behaviour or regression coverage. The inherited suite remains outside this result.

## Authorship and two-file boundary

The project began with an inherited Java Selenium repository. Milestone-A preserved its starting baseline and recorded the problems found during assessment. A separate maintained TestNG path was added in Milestone-B. Milestone-C updated the toolchain and resolved the build-configuration blocker affecting that path.

Milestone-D extended this setup with Selenium 4.49.0 and one no-login SauceDemo readiness check. The technical change contained only `build.gradle` and the new readiness class. The original Automation Practice test source remained unchanged and was not compiled or run by the readiness command.

SauceDemo provides a public application for the planned testing work. Its functionality differs from Automation Practice, so its test coverage will be designed for that application rather than treated as a direct conversion of the original tests. The goal is to confirm browser readiness before manual testing and functional automation begin.

The technical paths are:

- `build.gradle`

- `src/maintainedTest/java/readiness/SauceDemoReadinessSmokeTest.java`

The change is recorded in signed technical commit [`a27743b2e31bb63406005b7087fc5b5ba534646b`](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/commit/a27743b2e31bb63406005b7087fc5b5ba534646b). The commit page opened while signed out. Local `master` and `origin/master` pointed to the same commit.

## Package contents

- [Milestone-D Automation Readiness](./Milestone-D%20Automation%20Readiness.pdf) - the consolidated report with the two figures.

- `README.md` - this overview and the repository links used to trace the result.

The two PNG files are embedded in the PDF. They are not separate publication files.

## Work completed

Activity A1 established the starting state. Readiness checks passed. Gradle 8.14.5, JDK 21.0.12.1 and Selenium 4.49.0 were confirmed for the implementation. The repository’s starting commit was confirmed before implementation. The public SauceDemo login page was reachable, with stable `data-test` anchors for its login controls. The checkpoint recorded supported Chrome and the two workbook hashes. No unmanaged ChromeDriver was found, and the technical allow-list contained only the two paths.

Activity A2 added Selenium Java 4.49.0 only to `maintainedTestImplementation`. The new `automationReadiness` task uses the maintained output and runtime, TestNG 7.12.0, Java 21, one fork, strict failure handling, and an exact class filter. The existing `maintainedTest` task and `ExecutionIntegrityProbe.java` were not changed. The dry-run graph excluded compileTestJava and test along with cucumber and the legacy runner path. It showed the maintained compilation and readiness tasks instead. All listed tasks were marked SKIPPED because those tasks were not executed during the dry run.

The readiness class opens `https://www.saucedemo.com/` through `new ChromeDriver(options)`. Selenium Manager resolves the driver because the code supplies no executable path. The explicit waits check the exact address and the Swag Labs title. Each wait has a ten-second timeout. It also checks visible blank username and password fields plus a visible, enabled Login control. The class does not call `sendKeys`, `click`, or `Thread.sleep`. Its always-run teardown quits the created browser when the driver is present.

Activity A3 used this original acceptance command:

```powershell

.\gradlew.bat automationReadiness --rerun-tasks --no-build-cache --no-daemon

```

The original acceptance command compiled the maintained source and selected one readiness test. That test executed once and passed. Gradle reported `BUILD SUCCESSFUL`, two executed tasks, and process exit code 0. The result was neither cached nor up to date. The test reported Chrome `152.0.7977.83` and ChromeDriver `152.0.7977.82`.

The page remained at the public login surface. Both fields were blank. No credentials were entered, the Login control was not submitted, and the browser session closed through the test teardown.

Activity A4 reviewed the complete diff against the two-file boundary. The applicant staged, signed, and pushed the technical commit. Technical commit a27743b2 contains the two files. Local and remote commit references matched. Generated Gradle output and driver binaries were not included.

## Evidence explanation

The report contains two figures. Figure 1 in Section 8 of the report shows an extract from the saved output of the original acceptance run. It shows the command and one passing test with exit code 0. Browser and driver versions are also visible. Figure 2 was captured during a later successful run of the unchanged readiness test because no browser image was captured during the original run.

The browser image shows the SauceDemo login page with blank fields and the visible Login control. The Chrome automation banner remains visible. No credentials were entered and no login was submitted. This figure supports the browser-readiness result. Functional testing is reserved for the later activities.

Setup diagnostics, dependency extracts, full hashes, failed evidence-capture attempts, and local filesystem views remain private. They help with audit and troubleshooting, but they add machine-specific detail that an assessor does not need to judge the public result.

## Technical closure

The two protected workbook hashes remained unchanged:

- `testdata.xlsx`: `9ECECF817507E4762A560DD1BFDA6C563B79648114B4121C1C54906CFDA07B9C`

- `backup-testdata.xlsx`: `A09FB992C49034F0465F5ECEA42628F1EA9BB6C7668F1523789B2FBD0F5366A2`

The Milestone-B integrity probe also kept its recorded hash. Post-run checks found the exact two-file technical scope, ignored `.gradle` and `build` output, and no whitespace error in the tracked diff. The Milestone-B failure probe was not rerun because Milestone-D did not alter its task or execution semantics.

## Limits

- The original acceptance run executed one readiness test once. Milestone-D did not measure stability.

- It is not a login test or a regression test. No credentials were entered.

- The inherited `src/test` framework was not compiled or executed by the Milestone-D readiness command.

- The legacy Selenium 3 framework was not migrated.

- Readiness depends on the public SauceDemo service and a supported Chrome installation.

- The result does not claim that the inherited suite is repaired or regression-ready.

## Conclusion

Milestone-D added Selenium 4.49.0 to the maintained TestNG path developed through Milestones B and C. The original forced uncached run compiled the maintained source and passed one no-login SauceDemo readiness check with exit code 0. Chrome closed afterwards. The protected workbooks and integrity probe remained unchanged. The signed technical commit contained only the two files.

This work continued the maintenance of the inherited repository's build and execution setup, using the baseline preserved in Milestone-A. It established browser readiness on a public application so the wider testing work could proceed. Manual testing will record the test cases and their actual results. Functional automation will follow those executed cases. Regression runs will provide results for analysis. The final portfolio will bring together the project evidence.

Milestone-D therefore provides the technical starting point for the remaining testing stages. Functional coverage and regression conclusions remain outside this readiness result.

## Repository links

- [Milestone-D technical commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/commit/a27743b2e31bb63406005b7087fc5b5ba534646b)

- [build.gradle at the Milestone-D commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/blob/a27743b2e31bb63406005b7087fc5b5ba534646b/build.gradle)

- [SauceDemoReadinessSmokeTest.java at the Milestone-D commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/blob/a27743b2e31bb63406005b7087fc5b5ba534646b/src/maintainedTest/java/readiness/SauceDemoReadinessSmokeTest.java)

- [Published Milestone-C starting reference](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/commit/bc95cd1a88defa160f5eb7d42d708f35a07a3667)

- [Selenium 4.49 release record](https://www.selenium.dev/blog/2026/selenium-4-49-released/)

- [Selenium Manager documentation](https://www.selenium.dev/documentation/selenium\_manager/)
