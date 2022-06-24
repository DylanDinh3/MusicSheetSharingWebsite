create table composers (composer_id integer not null, name varchar(255), primary key (composer_id)) engine=InnoDB;
create table composers_sheets (composer_id integer not null, sheet_id integer not null, primary key (composer_id, sheet_id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table instruments (instrument_id integer not null, name varchar(255), primary key (instrument_id)) engine=InnoDB;
create table instruments_sheets (instrument_id integer not null, sheet_id integer not null, primary key (instrument_id, sheet_id)) engine=InnoDB;
create table role (role_id integer not null, name varchar(255), primary key (role_id)) engine=InnoDB;
create table sheets (sheet_id integer not null, sheets_image longblob, title varchar(255), user_sheets integer not null, primary key (sheet_id)) engine=InnoDB;
create table users (user_id integer not null, email varchar(255), password varchar(255), username varchar(255), primary key (user_id)) engine=InnoDB;
create table users_roles (user_id integer not null, role_id integer not null) engine=InnoDB;
alter table composers add constraint UK_1lj47g06jorc0c0yxj52pe1nm unique (name);
alter table instruments add constraint UK_kdoje2ju81w7bdi8oy9wrkffo unique (name);
alter table role add constraint UK_8sewwnpamngi6b1dwaa88askk unique (name);
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table users add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);
alter table composers_sheets add constraint FKqtnlvep1utk5702nik8c3fgt foreign key (sheet_id) references composers (composer_id);
alter table composers_sheets add constraint FK601eah6botqq143qmbk3llcg5 foreign key (composer_id) references sheets (sheet_id);
alter table instruments_sheets add constraint FKaq52ns3efdv93q96vkootg7cb foreign key (sheet_id) references instruments (instrument_id);
alter table instruments_sheets add constraint FKairpro7cku7qfwc8m5bnfp4i foreign key (instrument_id) references sheets (sheet_id);
alter table sheets add constraint FK89hvivva8fx2lm7q8gewvjhnf foreign key (user_sheets) references users (user_id);
alter table users_roles add constraint FKt4v0rrweyk393bdgt107vdx0x foreign key (role_id) references role (role_id);
alter table users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users (user_id);
