# Automation-ready Test Scenarios

| ID | Scenario | Expected |
|---|---|---|
| ACC-01 | GET accounts | 200, three initial demo accounts |
| ACC-02 | GET unknown account UUID | 404 |
| TRF-01 | Transfer 25.50 between two USD accounts | Transfer saved, balances adjusted |
| TRF-02 | Transfer exceeding balance | 400, unchanged balances |
| TRF-03 | Transfer to same account | 400 |
| TRF-04 | Transfer amount 0 or negative | 400 |
| TRF-05 | Transfer amount with more than 2 decimal places | 400 |
| TRF-06 | Concurrent opposite-direction transfers | No lost updates or deadlock under supported load |
| TRF-07 | GET transfer history | Sorted by createdAt descending |
| UI-01 | Load dashboard with API | Accounts/cards appear |
| UI-02 | Submit valid transfer | Success notification and refreshed balances |
| UI-03 | API unavailable | Helpful error displayed |

TRF-06, TRF-07 and browser cases are **planned**, not implemented tests in this MVP.
