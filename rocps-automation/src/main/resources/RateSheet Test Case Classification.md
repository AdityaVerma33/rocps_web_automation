
# RateSheet Test Case Classification — Client Side vs Server Side

## Classification Rules Applied
| Category | Includes |
|---|---|
| **Client Side** | Ratesheet Template config, Column/screen validation, Tariff creation, Tariff Class creation, Element Set creation/editing, Voice Stream editing, any other UI configuration setup |
| **Server Side** | RS Import Requests, Task status verification, Tariff Band validation (post-import), Authorize Import, Generate/Download Report, View Statistics/Errors/Template, Reject Import, Task Controller capability setup |

---

## FILE 1 — `TCRateSheetTemplate.java` (10 test methods)

| # | Priority | Method Name | Classification | Reason |
|---|---|---|---|---|
| 1 | 1 | `editVoiceStream` | **Client Side** | Edits Voice Stream configuration |
| 2 | 2 | `createTariffTransit` | **Client Side** | Creates Tariff Class, Tariff, Element Set — all UI config |
| 3 | 3 | `ratesheetTemplateConfigDestination` | **Client Side** | Configures RateSheet Template (Destination) |
| 4 | 4 | `ratesheetTemplateConfigOrigin` | **Client Side** | Configures RateSheet Template (Origin) |
| 5 | 5 | `rsTemplateConfigDestAllFields` | **Client Side** | Configures RateSheet Template (Destination – All Fields) |
| 6 | 6 | `rsTemplateConfigOriginAllFields` | **Client Side** | Configures RateSheet Template (Origin – All Fields) |
| 7 | 7 | `rsTemplateColVal` | **Client Side** | Search screen column validation on RateSheet Template |
| 8 | 8 | `rsTemplateExpirationStrategy` | **Client Side** | Configures RateSheet Template (Expiration Strategy) |
| 9 | 9 | `rsTemplateExpirationStrategyMin` | **Client Side** | Configures RateSheet Template (Expiration Strategy – Min) |
| 10 | 10 | `rsTemplateExpirationStrategyMax` | **Client Side** | Configures RateSheet Template (Expiration Strategy – Max) |

**TCRateSheetTemplate → Client Side: 10 | Server Side: 0**

---

## FILE 2 — `TCRateSheetImportRequest.java` (11 active test methods, 1 commented out)

| # | Priority | Method Name | Classification | Reason |
|---|---|---|---|---|
| 1 | 1 | `rateSheetImportRequest` | **Server Side** | RS import request + result validation |
| 2 | 2 | `rateSheetImportRequestOrigin` | **Server Side** | RS import request (Origin) + result validation |
| 3 | 3 | `rateSheetImportRequestTemplate` | **Server Side** | RS import request (Template) + result validation |
| 4 | 4 | `rateSheetImportRequestColVal` | **Client Side** | Search screen column validation on Import Request screen |
| 5 | 5 | `rateSheetImportRequestViewTemplate` | **Client Side** | View Template action on Import Request — UI-only inspection |
| 6 | 6 | `rateSheetImportRequestViewStatistics` | **Client Side** | View Statistics action — UI-only inspection |
| 7 | 7 | `rateSheetImportRequestViewErrors` | **Client Side** | Task Controller capability setup + View Errors — UI-only inspection |
| 8 | 8 | `rateSheetGenerateReport` | **Server Side** | Generate Report (server-driven background task) |
| 9 | 9 | `rateSheetDownloadReport` | **Server Side** | Download Report (result of server processing) |
| 10 | 10 | `rateSheetImportRequestCreation2` | **Server Side** | RS import request + result validation |
| 11 | 11 | `rateSheetImportRequestRejectImport` | **Server Side** | Reject Import action (server-side workflow action) |

**TCRateSheetImportRequest → Client Side: 4 | Server Side: 7**

---

## FILE 3 — `TCRatesheetServerCases.java` (37 test methods)

| # | Priority | Method Name | Classification | Reason |
|---|---|---|---|---|
| 1 | 1 | `taskControllerCapabilities` | **Server Side** | Sets Task Controller capability — server-side pre-config |
| 2 | 2 | `createTariffTransit` | **Client Side** | Creates Element Set, Tariff Class, Tariff — UI config |
| 3 | 3 | `rateSheetTemplateConfigDest` | **Client Side** | Configures RateSheet Template (Destination) |
| 4 | 4 | `rateSheetTemplateConfigOrigin` | **Client Side** | Configures RateSheet Template (Origin) |
| 5 | 5 | `rsRequest1_destinationBand` | **Server Side** | RS import request + task status verification |
| 6 | 6 | `rsAuthorize1_destinationBand` | **Server Side** | Authorize import + task status verification |
| 7 | 7 | `rsResultAndTariffValidation_destinationBand` | **Server Side** | Result validation + tariff band validation |
| 8 | 8 | `rsRequest2_DestinationBased` | **Server Side** | RS import request + task status verification |
| 9 | 9 | `rsesult2AndTariffValidation_destinationBased` | **Server Side** | Result validation + tariff band validation |
| 10 | 10 | `rsRequest3_DestinationBased` | **Server Side** | RS import request + task status verification |
| 11 | 11 | `rs3ResultAndTariffValidation_DestinationBased` | **Server Side** | Result validation + tariff band validation |
| 12 | 12 | `rsRequest4_OriginBased` | **Server Side** | RS import request (Origin) + task status verification |
| 13 | 13 | `rsResult4AndTariffValidation_OriginBased` | **Server Side** | Result validation + tariff band validation |
| 14 | 14 | `rsRequest5_OriginBased` | **Server Side** | RS import request (Origin) + task status verification |
| 15 | 15 | `rsResult5AndTariffValidation_OriginBased` | **Server Side** | Result validation + tariff band validation |
| 16 | 16 | `rsRequest6OriginBased` | **Server Side** | RS import request (Origin) + task status verification |
| 17 | 17 | `rsResult6AndTariffValidation_OriginBased` | **Server Side** | Result validation + tariff band validation |
| 18 | 18 | `rsReq7AndResult_DestBased_updateFuturePeriod` | **Server Side** | RS import request + task status + result validation |
| 19 | 19 | `rsReq8AndResult_DestBased_updateFuturePeriod` | **Server Side** | RS import request (expire elements) + task status + result validation |
| 20 | 20 | `rsReq9AndResult_DestBased_updateFuturePeriod` | **Server Side** | RS import request (update future) + task status + result validation |
| 21 | 21 | `rsTariffValidation_DestBased_updateFuturePeriod` | **Server Side** | Tariff band validation (Jan/Feb/Mar) — post-import verification |
| 22 | 22 | `createTariffTransit_Expiry` | **Client Side** | Creates Tariff Class + Tariff (Expiry) — UI config |
| 23 | 23 | `rsTemplateExpirationStrategy` | **Client Side** | Configures RateSheet Template (Expiration Strategy) |
| 24 | 24 | `rsRequestExpriry1Validation` | **Server Side** | RS import request + task status verification |
| 25 | 25 | `rsRequestExpriry1TariffValidation` | **Server Side** | Tariff band validation |
| 26 | 26 | `rsRequestExpriry2Validation` | **Server Side** | RS import request + task status verification |
| 27 | 27 | `rsRequestExpriry2TariffValidation` | **Server Side** | Tariff band validation |
| 28 | 28 | `rsTemplateExpirationStrategyMin` | **Client Side** | Configures RateSheet Template (Expiration – Min) |
| 29 | 29 | `rsRequestExpriryValidationMin1` | **Server Side** | RS import request + task status verification |
| 30 | 30 | `rsRequestExpriryMin1_tariffBandValidation` | **Server Side** | Tariff band validation |
| 31 | 31 | `rsRequestExpriryValidationMin2` | **Server Side** | RS import request + task status verification |
| 32 | 32 | `rsRequestExpriryMin2_tariffBandValidation` | **Server Side** | Tariff band validation |
| 33 | 33 | `rsTemplateExpirationStrategyMax` | **Client Side** | Configures RateSheet Template (Expiration – Max) |
| 34 | 34 | `rsRequestExpriryValidationMax1` | **Server Side** | RS import request + task status verification |
| 35 | 35 | `rsRequestExpriryMax1_tariffBandValidation` | **Server Side** | Tariff band validation |
| 36 | 36 | `rsRequestExpriryValidationMax2` | **Server Side** | RS import request + task status verification |
| 37 | 37 | `rsRequestExprirytariffBandValidationMax2` | **Server Side** | Tariff band validation |

**TCRatesheetServerCases → Client Side: 7 | Server Side: 30**

---

## Grand Total Summary

| File | Total Test Cases | Client Side | Server Side |
|---|:---:|:---:|:---:|
| `TCRateSheetTemplate.java` | 10 | **10** | 0 |
| `TCRateSheetImportRequest.java` | 11 | **4** | **7** |
| `TCRatesheetServerCases.java` | 37 | **7** | **30** |
| **GRAND TOTAL** | **58** | **21** | **37** |
