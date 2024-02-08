create table league (id bigint not null auto_increment, logo_url varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table `match` (away_team_id bigint, home_team_id bigint, id bigint not null auto_increment, matchday_id bigint, result_id bigint, primary key (id));
create table matchday (date date, id bigint not null auto_increment, league_id bigint, name varchar(255), primary key (id)) engine=InnoDB;
create table match_result (away_team_goals integer not null, home_team_goals integer not null, penalties bit not null, id bigint not null auto_increment, prediction_id bigint, winner_id bigint, primary key (id)) engine=InnoDB;
create table prediction (id bigint not null auto_increment, matchday_id bigint, predicter varchar(255), primary key (id)) engine=InnoDB;
create table team (id bigint not null auto_increment, league_id bigint, logo_url varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
alter table `match` add constraint UK_66hid319jt8r8mtlk8o89oehk unique (result_id);
alter table `match` add constraint FKsyjor2anx7bkbst7ebyw13jcs foreign key (away_team_id) references team (id);
alter table `match` add constraint FK6ihefb9r7f0fcm0xuves72b2l foreign key (home_team_id) references team (id);
alter table `match` add constraint FKibs28mborsm5k4dri42ia9j7q foreign key (result_id) references match_result (id);
alter table `match` add constraint FK4vlrwnata2ykj9k88nrn0vl1s foreign key (matchday_id) references matchday (id);
alter table matchday add constraint FKom0ftliixpgkvp27n3liuc4n2 foreign key (league_id) references league (id);
alter table match_result add constraint FKr0csdvtcx3yujht43b42gost2 foreign key (winner_id) references team (id);
alter table match_result add constraint FKgtiq3566kh8bw8g5qi3f2jwdv foreign key (prediction_id) references prediction (id);
alter table prediction add constraint FKmqut08xymyd8khoua7rkoc6qo foreign key (matchday_id) references matchday (id);
alter table team add constraint FK9rk8716asfr76xkn99aa3uvp foreign key (league_id) references league (id);

