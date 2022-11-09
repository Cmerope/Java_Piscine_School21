create schema if not exists chat;

drop table if exists chat.message, chat.room, chat.users;

create table if not exists chat.users (
      id          serial primary key ,
      login       text not null ,
      password    text
);

create table if not exists chat.room (
     id          serial primary key ,
     chat_owner  int not null references chat.users(id) ,
     chat_name   text not null
);

create table if not exists chat.message (
    id          serial primary key,
    room_id     int not null references chat.room(id),
    sender      int not null references chat.users(id),
    message     text not null ,
    time        timestamp
);