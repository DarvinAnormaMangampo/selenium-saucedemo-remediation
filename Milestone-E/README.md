# Milestone-E Manual Testing

- Prepared by: Darvin Anorma Mangampo
- GitHub: [DarvinAnormaMangampo](https://github.com/DarvinAnormaMangampo)
- Assessment period: 17–23 September 2026 (UTC+08:00)

## Purpose

Milestone-E records the preparation, execution and reconciliation of documented manual checks against the public SauceDemo application. It follows the browser-readiness result established in Milestone-D. The manual cases and data were prepared specifically for SauceDemo rather than taken from the inherited Automation Practice tests.

## Recorded outcome

The completed scope contains nine case groups, TC01 to TC09, with 19 variations. All 19 variations were executed and met their supported expected results on `attempt1`. The reconciled record contains 19 Pass results and no Fail, Blocked, Not Run or excluded entries. No material observation or repeat run was required.

Results RES-001 to RES-019 contain 33 evidence references that resolve to 32 unique files: 31 PNG screenshots and one generated PDF receipt. The same TC08 Overview image is referenced by RES-016 and RES-017.

## Publication package

- [Milestone-E Manual Testing](./Milestone-E%20Manual%20Testing.pdf) – consolidated manual-testing publication.
- [Milestone-E Test Cases and Data](./Test%20Cycle%20Artefacts/01%20Test%20Preparation/Milestone-E%20Test%20Cases%20and%20Data.xlsx) – application information, test data and 19 case rows.
- [Milestone-E Test Execution](./Test%20Cycle%20Artefacts/02%20Test%20Execution/Milestone-E%20Test%20Execution.xlsx) – ENV-01, RES-001 to RES-019, execution outcomes and the evidence index.
- [Milestone-E Test Summary](./Test%20Cycle%20Artefacts/03%20Test%20Summary/Milestone-E%20Test%20Summary.docx) – reconciled scope, outcomes and limitations.
- `README.md` – this package overview.

The [Evidence folder](./Test%20Cycle%20Artefacts/02%20Test%20Execution/Evidence/) accompanies the execution workbook. It contains the screenshots and receipt referenced by the Results worksheet and is not counted as a sixth primary document.

## Scope and limits

The results apply to the 19 documented variations executed on Windows with Firefox 156.0 in Private Browsing on 22 September 2026. The documented functional workflows used `standard_user`, and the access-rejection check used `locked_out_user`. Expected results came from public SauceDemo information and visible interface responses. They were also supported by comparisons between related pages and calculations based on displayed values. No client-approved requirements applied to this work.

The completed testing does not establish complete SauceDemo coverage, business acceptance, production suitability or defect-free operation. Other demo accounts and browser combinations were not evaluated. Security, performance, accessibility, API, database, enterprise-integration, real-payment, fulfilment and release-approval work remained outside Milestone-E.

## References

- [SauceDemo](https://www.saucedemo.com)
- [selenium-saucedemo-remediation repository](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation)
- [Milestone-D Automation Readiness](https://github.com/DarvinAnormaMangampo/selenium-saucedemo-remediation/blob/master/Milestone-D/Milestone-D%20Automation%20Readiness.pdf)