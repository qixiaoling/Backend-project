INSERT INTO role(role_id, role_name) VALUES (1, 'USER_BAC');
INSERT INTO role(role_id, role_name) VALUES (2, 'USER_FRO');
INSERT INTO role(role_id, role_name) VALUES (3, 'USER_TRE');
INSERT INTO role(role_id, role_name) VALUES (4, 'USER_TEC');
INSERT INTO role(role_id, role_name) VALUES (5, 'ADMIN');

INSERT INTO app_user(user_id, password, user_name)
VALUES(1, 'password', 'Elsa');
INSERT INTO app_user(user_id, password, user_name)
VALUES(2, 'password', 'Anna');
INSERT INTO app_user(user_id, password, user_name)
VALUES(3, 'password', 'Hans');
INSERT INTO app_user(user_id, password, user_name)
VALUES(4, 'password', 'Kristoff');
INSERT INTO app_user(user_id, password, user_name)
VALUES(5,'password', 'Olaf');

UPDATE app_user_roles SET app_user_user_id=1 WHERE roles_role_id=2;
UPDATE app_user_roles SET app_user_user_id=2 WHERE roles_role_id=1;
UPDATE app_user_roles SET app_user_user_id=3 WHERE roles_role_id=3;
UPDATE app_user_roles SET app_user_user_id=4 WHERE roles_role_id=4;
UPDATE app_user_roles SET app_user_user_id=5 WHERE roles_role_id=5;







/*INSERT INTO car(number_plate, make, model ) VALUES('64-TP-TF', 'BMW', '1Serie');

INSERT INTO inspection(inspection_number, agree_to_repair, inspection_complete, inspection_date, inspection_fee, inspection_result, quantities, repair_complete, repair_date, car_number_plate, invoice_customer_id, invoice_inspection_number)
VALUES(1, true, true, '2020-12-11', 45, false, 2, true, '2020-12-22', null, null, null );

UPDATE inspection SET car_number_plate='64-TP-TF' WHERE inspection_number=1;*/
