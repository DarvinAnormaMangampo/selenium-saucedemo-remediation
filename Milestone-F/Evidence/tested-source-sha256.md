# Tested source checksums

The verified passing run `20260930-210141-857` used local base commit
`0570842b8da0c0f4000b21e590f70a4618c97f71` plus the eleven F files below.
The pre-run and post-run source checksums matched. These values identify the tested
working-tree state before its technical commit. They do not claim a published revision.

| File | SHA-256 |
| --- | --- |
| `build.gradle` | `2E44A86818638DFA1ED1FB7CBE6B12C6A15393D54B0FFEA9F61CF00302E7FEF3` |
| `gradle/timestamped-build.init.gradle` | `4438FA9AA979C917512AC39831468AB554306ABBEBF43FBF77C64A22FECC9DCA` |
| `src/maintainedTest/java/saucedemo/pages/CartPage.java` | `34A0230800F3E7FA6A553D6E13C2A23D23079195F063AF8F99D946A242F29ED1` |
| `src/maintainedTest/java/saucedemo/pages/CheckoutPage.java` | `72E8CE74FFE469E7D0ACED4BD58378004FF534330997A4F6D7E981761C862E59` |
| `src/maintainedTest/java/saucedemo/pages/InventoryPage.java` | `A5373E0E77524BD16FCE779DB0509BB2517A886E76FF8AEE4A9E76BD801245F7` |
| `src/maintainedTest/java/saucedemo/pages/LoginPage.java` | `51CE3A1DBDE846938EF979761A28831B3325D976D536DCA16C3D4C474D6D4297` |
| `src/maintainedTest/java/saucedemo/support/BaseUiTest.java` | `CD110A6D9F95CD29B767C6E6A345749996DF63D05010027D5B67212DD5548003` |
| `src/maintainedTest/java/saucedemo/support/TestData.java` | `D4C7C0F0566356691AC51F3D9C393AFB300F9B9B2F2FE1651A2ED1509A042B22` |
| `src/maintainedTest/java/saucedemo/tests/AuthenticationTests.java` | `C996BCE31CE9A00B72FDBAE0978B85C71897DA14C9A42BB5134C7CF004AD1465` |
| `src/maintainedTest/java/saucedemo/tests/CheckoutTests.java` | `32E7BE0451926F821F7B53993417A280C3283C200C435D2DDAF6AC6518AFA96E` |
| `src/maintainedTest/java/saucedemo/tests/InventoryAndCartTests.java` | `9A2EDDC37338CA06E497823F51ED2ED43EA4BBA3C173176D8E2AB6A6CD20BFD8` |

The two protected repository workbooks also matched the A1 baseline:

- `src/test/resources/files/testdata.xlsx`: `9ECECF817507E4762A560DD1BFDA6C563B79648114B4121C1C54906CFDA07B9C`
- `src/test/resources/files/backup-testdata.xlsx`: `A09FB992C49034F0465F5ECEA42628F1EA9BB6C7668F1523789B2FBD0F5366A2`
