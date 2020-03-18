drop table question_tags if exists;
drop table question if exists;
create table question (id bigint not null, answer_count integer, answered boolean, creation_date timestamp, user_id bigint, view_count integer, primary key (id));
create table question_tags (question_id bigint not null, tags varchar(255));
create index IDXqmar1tb87ooialj3eiox6gxig on question_tags (tags);
alter table question_tags add constraint FKf76giw3qwi7ooxeims83jp29k foreign key (question_id) references question;