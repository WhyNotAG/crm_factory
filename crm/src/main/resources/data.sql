INSERT INTO roles (id, name) values (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) values (2, 'ROLE_MANAGER');
INSERT INTO roles (id, name) values (3, 'ROLE_WORKSHOP');



INSERT INTO users (id, username, password) values (1, 'test', '$2a$10$MGzsOEq6zHF1r7NcDVBjmumn7HfVFBXsrVMSONuWuuUXCYX71ha');

INSERT INTO user_roles(user_id, role_id) values (1,1);