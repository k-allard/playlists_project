INSERT
INTO public."t_role"
    ("name")
VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER')
ON CONFLICT DO NOTHING
;

INSERT
INTO public."t_user"
    ("username", "password")
VALUES
    ('admin', '$2a$10$7uWaEoOdIUAaiDORjq1of.MsF44JIbi4g9i8OLOCDt99i1.u.4jHG'),
    ('user',  '$2a$10$7uWaEoOdIUAaiDORjq1of.MsF44JIbi4g9i8OLOCDt99i1.u.4jHG')
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
