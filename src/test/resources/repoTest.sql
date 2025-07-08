/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  aar1069
 * Created: Jul 8, 2025
 */

insert into Transaction (TransId, TransEffDte, TransAmt) values (1, '2025-06-01', 51.50);
insert into Transaction (TransId, TransEffDte, TransAmt) values (2, '2025-07-15', 52);

insert into TransDtl (TransDtlId, TransId, DtlTypCde) values (1, 1, 1);
insert into TransDtl (TransDtlId, TransId, DtlTypCde) values (2, 2, 2);

insert into TransDtlTyp1 (TransDtlId, GenAmt, ClntNam) values (1, 10, 'Henrietta Schmoop');
insert into TransDtlTyp2 (TransDtlId, Rate, RateTypCde) values (2, 1, 'flat');
