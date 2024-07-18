INSERT INTO roles VALUES (1, 'USER'), (2, 'ADMIN');

INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`, `uuid`)
VALUES (1,'lachezar@example.com','Lachezar','Balev','04141fc05c9b39290915f9f951889c9f2c478c9e54b11a9bc1df1a5f02d53a9bbb92aba98149ae9b2fa99301066fd9a3','44a090f5-66cc-40d6-b827-cfd1fac78b3c');

INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES
    (1, 1),
    (1, 2);