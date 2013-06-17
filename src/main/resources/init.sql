insert into users (
   id
  ,blocked
  ,email
  ,first_name
  ,last_name
  ,login
  ,password
) VALUES (
   1   -- id - IN bigint(20)
  ,0   -- blocked - IN bit(1)
  ,'admin@admin.ru'  -- email - IN varchar(255)
  ,'Admin'  -- first_name - IN varchar(255)
  ,'Admin'  -- last_name - IN varchar(255)
  ,'admin'  -- login - IN varchar(255)
  ,'admin'  -- password - IN varchar(255)
);

insert into user_roles (
   User_id
  ,roles
) VALUES (
   1   -- User_id - IN bigint(20)
  ,'Admin'  -- roles - IN varchar(255)
);
