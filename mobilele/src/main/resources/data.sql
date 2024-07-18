INSERT INTO roles VALUES (1, 'USER'), (2, 'ADMIN');

INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`, `uuid`)
VALUES
    (1,'lachezar@example.com','Lachezar','Balev','8dded4dc9b97fe3124a0b5397e3ab5251046e6ffaf0fcf6b32c6eb9fea7b1337bfa48a886c8c064adebeb5ff05b84300','1cc6e990-3699-4633-8c68-f148bec2748a');

INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES
    (1, 1),
    (1, 2);