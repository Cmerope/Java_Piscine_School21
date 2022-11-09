insert into chat.users (login, password) VALUES ('esobchak', 'T3SWUi');
insert into chat.users (login, password) VALUES ('mstyr', 'SMnMyY');
insert into chat.users (login, password) VALUES ('amuriel', '71bkeb');
insert into chat.users (login, password) VALUES ('cmerope', 'qLacoa');
insert into chat.users (login, password) VALUES ('dgallard', '7g9Exr');
insert into chat.users (login, password) VALUES ('eshakita', 'nPjCNq');

insert into chat.room (chat_owner, chat_name) VALUES (1, 'chatroom1');
insert into chat.room (chat_owner, chat_name) VALUES (2, 'chatroom2');
insert into chat.room (chat_owner, chat_name) VALUES (3, 'chatroom3');
insert into chat.room (chat_owner, chat_name) VALUES (4, 'chatroom4');
insert into chat.room (chat_owner, chat_name) VALUES (5, 'chatroom5');

set timezone = 'Europe/Moscow';

insert into chat.message (room_id, sender, message, time) VALUES (1, 2, 'Baby, I am dancing in the dark', to_timestamp(extract(epoch FROM now())));
insert into chat.message (room_id, sender, message, time) VALUES (3, 1, 'with you between my arms', to_timestamp(extract(epoch FROM now())));
insert into chat.message (room_id, sender, message, time) VALUES (4, 5, 'Barefoot on the grass', to_timestamp(extract(epoch FROM now())));
insert into chat.message (room_id, sender, message, time) VALUES (1, 2, 'listening to our favorite song', to_timestamp(extract(epoch FROM now())));
insert into chat.message (room_id, sender, message, time) VALUES (1, 1, 'When I saw you in that dress', to_timestamp(extract(epoch FROM now())));
insert into chat.message (room_id, sender, message, time) VALUES (2, 3, 'looking so beautiful', to_timestamp(extract(epoch FROM now())));

