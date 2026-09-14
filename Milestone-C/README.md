# Milestone-C: Build Restoration

Prepared by: Darvin Anorma Mangampo

GitHub: [DarvinAnormaMangampo](https://github.com/DarvinAnormaMangampo)

Assessment period: 13–14 September 2026 (UTC+08:00)

## Purpose

Milestone-C moved the separate `maintainedTest` lane from Gradle 6.6.1 to 8.14.5 under Eclipse Temurin JDK 21. The lane's own TestNG dependency changed from 7.3.0 to 7.12.0.

The aim was to prove that this source could compile repeatedly and still return a failure or pass through Gradle. The inherited `src/test` tree and browser framework were kept outside the run.

## Authorship and repository boundary

The inherited application source and legacy test framework are not my work. My Milestone-C change is limited to `build.gradle` and the four Gradle Wrapper files. The `ExecutionIntegrityProbe` source added in Milestone-B was not changed.

Work began from published commit `c5a969652ecd93546318bb95b9d389ed16a05de3`. The build restoration is recorded in signed technical commit `a4e5f9829ed5f6047ca5672c8bb425ccc6580be1`.

## Package contents

- [Milestone-C Build Restoration](./Milestone-C%20Build%20Restoration.pdf) - the consolidated report and selected evidence.
- `README.md` - this overview and the links used to trace the result.

## Recorded outcome

The Gradle 8.14.5 wrapper ran under JDK 21 with the recorded distribution checksum, and its generated wrapper JAR matched Gradle's published SHA-256 value. Maven Central remained active while JCenter was removed.

The first Gradle 8 configuration attempt stopped at the inherited Allure plugin. I removed only its plugin declaration and configuration block after the error identified it as the root-level blocker. The inherited `allure-testng:2.13.5` dependency stayed in the legacy dependency block and did not enter the maintained runtime classpath.

The Gradle `--dry-run` task-graph check listed four tasks, ending at `maintainedTest`, without executing them. It did not include `compileTestJava`, the inherited test task or a browser runner. The maintained runtime classpath resolved TestNG 7.12.0 without Selenium, Cucumber, Allure or Rest Assured.

Two forced compile-only runs returned exit code 0. Both executed `compileMaintainedTestJava` from unchanged source and produced this class SHA-256 value:

`D888EAE826670CDCE11509C9A47B91A3E8BE5B392F464BC7AB75DAABE9248790`

The existing probe then ran once with `integrityProbe=fail` and once with `integrityProbe=pass`. The failure run returned exit code 1 with one failed invocation. The passing run returned exit code 0 with one passing invocation. Neither result came from the cache, and no retry was observed.

## Technical closure

The signed commit contains these five paths:

- `build.gradle`
- `gradle/wrapper/gradle-wrapper.jar`
- `gradle/wrapper/gradle-wrapper.properties`
- `gradlew`
- `gradlew.bat`

At the recorded closure, local HEAD and remote `master` both resolved to `a4e5f9829ed5f6047ca5672c8bb425ccc6580be1`. The worktree was clean. Generated output and publication files were absent from the technical commit.

The tracked workbook kept this SHA-256 value:

`9ECECF817507E4762A560DD1BFDA6C563B79648114B4121C1C54906CFDA07B9C`

## Limits

These results apply only to the maintained non-browser TestNG lane. The inherited test source was not compiled. No Selenium session or application function ran. The failed result came from ExecutionIntegrityProbe using integrityProbe=fail, so it is not an application defect.

The Allure change does not prove that inherited reporting works. Other legacy integrations were not upgraded. This milestone does not claim that the whole repository is restored or ready for functional regression testing.

## Conclusion

Milestone-C restored the build path for the isolated `maintainedTest` lane without bringing the inherited browser framework into this work. The lane now uses Gradle 8.14.5 under JDK 21. Its own TestNG dependency is 7.12.0 and resolves from Maven Central.

Both forced compiles completed from unchanged source and produced the same class hash. When the existing probe ran with integrityProbe=fail, Gradle returned a failed process. The same probe later passed once with integrityProbe=pass. Each retained XML result contained one invocation, and no retry was observed.

The final commit remained within the five approved build and wrapper paths. The workbook and probe source maintained their recorded hashes. This closes the build-restoration work for the maintained non-browser lane. Selenium execution and application testing were outside this result. They belong to later approved milestones.

## Repository links

- [Milestone-C technical commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/commit/a4e5f9829ed5f6047ca5672c8bb425ccc6580be1)
- [build.gradle at the Milestone-C commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/blob/a4e5f9829ed5f6047ca5672c8bb425ccc6580be1/build.gradle)
- [Gradle Wrapper properties at the Milestone-C commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/blob/a4e5f9829ed5f6047ca5672c8bb425ccc6580be1/gradle/wrapper/gradle-wrapper.properties)
- [ExecutionIntegrityProbe source at the Milestone-C commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/blob/a4e5f9829ed5f6047ca5672c8bb425ccc6580be1/src/maintainedTest/java/integrity/ExecutionIntegrityProbe.java)
- [Published Milestone-B starting commit](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/commit/c5a969652ecd93546318bb95b9d389ed16a05de3)
- [Inherited baseline tag](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/releases/tag/inherited-baseline-2026-09-05)
