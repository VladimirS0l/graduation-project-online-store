insert into users (name, username, password) values
      ('John Doe', 'johnjohn@mail.ru', '$2y$10$38dT4yDEcX2ykrlsmQd.IuK8A1IvqHqisO5eB0nPCnAEHuPoMgnNS'),
      ('Alice Burns', 'aliceburns@mail.ru', '$2y$10$RMujq3T3GlxlM4r73pyq4eiiMHqrW5rFfILq9LvyYFO6ZVJj.vPry');

insert into users_roles (user_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');

insert into products (title, price, description, pathname) values
        ('TV', 23123.11, 'lorems dasdasd asd asd as', '/ewqewq/qweqwe/wqe'),
        ('Samsung', 23123.11, 'lorems dasdasd asd asd as', '/ewqewq/qweqwe/wqe'),
        ('Iphone', 31231.99, 'lorems dasdasd asd asd as', '/ewqewq/qweqwe/wqe'),
        ('Siemens', 32439.66, 'lorems dasdasd asd asd as', '/ewqewq/qweqwe/wqe'),
        ('Huawei', 64343.44, 'lorems dasdasd asd asd as', '/ewqewq/qweqwe/wqe');