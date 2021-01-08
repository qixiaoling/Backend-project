


INSERT INTO role(role_id, role_name) VALUES (1, 'USER_BAC');
INSERT INTO role(role_id, role_name) VALUES (2, 'USER_FRO');
INSERT INTO role(role_id, role_name) VALUES (3, 'USER_TRE');
INSERT INTO role(role_id, role_name) VALUES (4, 'USER_TEC');
INSERT INTO role(role_id, role_name) VALUES (5, 'ADMIN');

INSERT INTO app_user(user_id, password, user_name)
VALUES(1, crypt('password', gen_salt('bf')), 'Elsa');
INSERT INTO app_user(user_id, password, user_name)
VALUES(2, crypt('password', gen_salt('bf')), 'Anna');
INSERT INTO app_user(user_id, password, user_name)
VALUES(3, crypt('password', gen_salt('bf')), 'Hans');
INSERT INTO app_user(user_id, password, user_name)
VALUES(4, crypt('password', gen_salt('bf')), 'Kristoff');
INSERT INTO app_user(user_id, password, user_name)
VALUES(5, crypt('password', gen_salt('bf')), 'Olaf');



INSERT INTO app_user_roles(app_users_user_id, roles_role_id) VALUES(1, 2);
INSERT INTO app_user_roles(app_users_user_id, roles_role_id) VALUES(2, 1);
INSERT INTO app_user_roles(app_users_user_id, roles_role_id) VALUES(3, 3);
INSERT INTO app_user_roles(app_users_user_id, roles_role_id) VALUES(4, 4);
INSERT INTO app_user_roles(app_users_user_id, roles_role_id) VALUES(5, 5);
INSERT INTO app_user_roles(app_users_user_id, roles_role_id) VALUES(5, 1);
INSERT INTO app_user_roles(app_users_user_id, roles_role_id) VALUES(5, 2);
INSERT INTO app_user_roles(app_users_user_id, roles_role_id) VALUES(5, 3);
INSERT INTO app_user_roles(app_users_user_id, roles_role_id) VALUES(5, 4);






/*INSERT INTO car(number_plate, make, model ) VALUES('64-TP-TF', 'BMW', '1Serie');

INSERT INTO inspection(inspection_number, agree_to_repair, inspection_complete, inspection_date, inspection_fee, inspection_result, quantities, repair_complete, repair_date, car_number_plate, invoice_customer_id, invoice_inspection_number)
VALUES(1, true, true, '2020-12-11', 45, false, 2, true, '2020-12-22', null, null, null );

UPDATE inspection SET car_number_plate='64-TP-TF' WHERE inspection_number=1;*/

