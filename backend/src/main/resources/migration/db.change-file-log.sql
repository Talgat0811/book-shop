-- Добавление ролей
insert into auth_roles(id, description, name)
values (nextval('auth_roles_seq'), '', 'ADMIN');
insert into auth_roles(id, description, name)
values (nextval('auth_roles_seq'), '', 'USER');
--------------------------------------------------------------------------------------------------------------------------------
-- Добавление открытых роутов
insert into auth_routes(id, description, http_method, is_active, is_public, uri)
VALUES (nextval('auth_routes_seq'), 'Роут для регистрации пользователя', 'POST', true, true, '/api/v1/users');
insert into auth_routes(id, description, http_method, is_active, is_public, uri)
VALUES (nextval('auth_routes_seq'), 'Роут для аутентификации', 'POST', true, true, '/api/v1/auth/login');
insert into auth_routes(id, description, http_method, is_active, is_public, uri)
VALUES (nextval('auth_routes_seq'), 'Роут для получение книги по ID', 'GET', true, true, '/api/books/get-book-by-id/*');

--------------------------------------------------------------------------------------------------------------------------------
-- Добавление закрытых роутов
insert into auth_routes(id, description, http_method, is_active, is_public, uri)
VALUES (nextval('auth_routes_seq'), 'Роут для удаления книг по ID', 'DELETE', true, false, '/api/books/delete-book/*');

-- Настройка роутов
insert into auth_routes_permissions(auth_route_id, auth_permission_id)
values (
        (select id from auth_routes where uri='/api/books/delete-book/*' and http_method = 'DELETE'),
        (select id from auth_permissions where name='BOOK_DELETE')
       )
--------------------------------------------------------------------------------------------------------------------------------
