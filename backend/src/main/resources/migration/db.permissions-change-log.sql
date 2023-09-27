-- для книг (чтение книг всегда ОТКРЫТО для всех (своих пользователей и анонимных пользователей)
insert into auth_permissions(id, description, name)
values (nextval('auth_permissions_seq'), 'Права на изменение/добавление книг', 'BOOK_WRITE');
insert into auth_permissions(id, description, name)
values (nextval('auth_permissions_seq'), 'Права на удаление книг', 'BOOK_DELETE');

-- Настройка пермишонов для ролей
-- У админа есть все доступы
insert into auth_roles_permissions(auth_role_id, auth_permission_id)
values (
           (select id from auth_roles where name='ADMIN'),
           (select id from auth_permissions where name='BOOK_WRITE')
       );
insert into auth_roles_permissions(auth_role_id, auth_permission_id)
values (
           (select id from auth_roles where name='ADMIN'),
           (select id from auth_permissions where name='BOOK_DELETE')
       );

-- У пользователя нет доступов к удалению или добавлению книг (а чтение книг ОТКРЫТО для всех)
-------------------------------------------------------------------------------------------------------
-- Добавление общих пермишонов
insert into auth_permissions(id, description, name)
values (nextval('auth_permissions_seq'), 'Чтение данных', 'READ');
insert into auth_permissions(id, description, name)
values (nextval('auth_permissions_seq'), 'Удаление данных', 'DELETE');
insert into auth_permissions(id, description, name)
values (nextval('auth_permissions_seq'), 'Запись данных', 'WRITE');

-- Настройка пермишонов
-- У админа имеется доступ ко всему
insert into auth_roles_permissions(auth_role_id, auth_permission_id)
values (
        (select id from auth_roles where name='ADMIN'),
        (select id from auth_permissions where name='READ')
       );
insert into auth_roles_permissions(auth_role_id, auth_permission_id)
values (
           (select id from auth_roles where name='ADMIN'),
           (select id from auth_permissions where name='DELETE')
       );
insert into auth_roles_permissions(auth_role_id, auth_permission_id)
values (
           (select id from auth_roles where name='ADMIN'),
           (select id from auth_permissions where name='WRITE')
       );
