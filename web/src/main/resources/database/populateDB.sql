INSERT
INTO public."t_role"
    ("id", "name")
VALUES
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER')
ON CONFLICT DO NOTHING
;

INSERT
INTO public."t_user"
    ("id", "username", "password")
VALUES
    (1, 'admin', '$2a$10$7uWaEoOdIUAaiDORjq1of.MsF44JIbi4g9i8OLOCDt99i1.u.4jHG'),
    (2, 'user',  '$2a$10$7uWaEoOdIUAaiDORjq1of.MsF44JIbi4g9i8OLOCDt99i1.u.4jHG')
ON CONFLICT DO NOTHING
;

INSERT
INTO public."t_user_roles"
    ("user_id", "roles_id")
VALUES
    (1, 1),
    (2, 2)
ON CONFLICT DO NOTHING
;
