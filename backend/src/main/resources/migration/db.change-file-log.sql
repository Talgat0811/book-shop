-- Добавление ролей
insert into auth_roles(id, description, name)
values (nextval('auth_roles_seq'), '', 'ADMIN');
insert into auth_roles(id, description, name)
values (nextval('auth_roles_seq'), '', 'USER');

-- Добавление открытых роутов
insert into auth_routes(id, description, http_method, is_active, is_public, uri)
VALUES (nextval('auth_routes_seq'), 'Роут для регистрации пользователя', 'POST', true, true, '/api/v1/users');
insert into auth_routes(id, description, http_method, is_active, is_public, uri)
VALUES (nextval('auth_routes_seq'), 'Роут для аутентификации', 'POST', true, true, '/api/v1/auth/login');

