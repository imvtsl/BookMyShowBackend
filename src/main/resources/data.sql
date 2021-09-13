create table events(id number(4) primary key AUTO_INCREMENT, name varchar(255) not null, duration number(5) not null);

create table movies(id number(4) primary key AUTO_INCREMENT, name varchar(255) unique not null, duration number(5) not null,
director varchar(255) not null, actors varchar(255) not null, summary varchar(255),
lang varchar(255), genre varchar(255), certificate varchar(12),
check(lang = 'english' or lang = 'hindi'),
check(genre = 'drama' or genre = 'action' or genre = 'romance' or genre = 'comedy' or genre = 'documentary'),
check(certificate = 'U' or certificate = 'UA' or certificate = 'A')
);

create table venue(id number(4) primary key AUTO_INCREMENT, name varchar(255) not null, location varchar(255) not null,
type varchar(255) not null,
check(type = 'cinema' or type = 'stadium' or type = 'theatre'));

create table venue_seat(id number(12) primary key AUTO_INCREMENT, venue_id number(4) not null, seat_number varchar(12) not null,
type varchar(255) not null,
unique(venue_id, seat_number, type),
foreign key (venue_id) references venue(id) on delete cascade
);

create table show(id number(6) primary key AUTO_INCREMENT, venue_id number(4) not null, start_timestamp timestamp not null,
unique(venue_id, start_timestamp),
foreign key (venue_id) references venue(id) on delete cascade
);

create table show_movie(show_id number(6), movie_id number(4),
foreign key (show_id) references show(id) on delete cascade,
foreign key (movie_id) references movies(id) on delete cascade
);

create table show_event(show_id number(6), event_id number(4),
primary key(show_id, event_id),
foreign key (show_id) references show(id) on delete cascade,
foreign key (event_id) references events(id) on delete cascade
);

create table show_seat(id number(14) primary key AUTO_INCREMENT, show_id number(6) not null, price number(6) not null,
status varchar(255) not null, venue_seat_id number(12) not null,
check(status = 'available' or status = 'reserved' or status = 'unavailable'),
foreign key (show_id) references show(id) on delete cascade,
foreign key (venue_seat_id) references venue_seat(id) on delete cascade
);

create table booking (id varchar(40) primary key, booking_timestamp timestamp not null, status varchar(255) not null,
amount number(6) not null,
check(status = 'confirmed' or status = 'cancelled' or status = 'pending')
);

create table booking_seat (id number(20) primary key AUTO_INCREMENT, booking_id varchar(40) not null, show_seat_id number(14) not null,
unique (booking_id, show_seat_id),
foreign key (booking_id) references booking(id),
foreign key (show_seat_id) references show_seat(id)
);

create table payment ( payment_id number(128) primary key AUTO_INCREMENT, booking_id varchar(40) not null, method varchar(255) not null,
status varchar(255) not null,
check(status = 'success' or status = 'failed'),
check(method = 'credit card' or method = 'debit card' or method = 'paytm' or method = 'upi'),
foreign key (booking_id) references booking(id)
);

create view movie_show as select s.id as show_id, s.venue_id, s.start_timestamp, sm.movie_id from show s join show_movie sm on (s.id = sm.show_id);


insert into events values (1, 'DC vs MI', 10800);
insert into events values (2, 'DC vs CSK', 10800);

insert into movies values (1, 'Shershaah', 8100, 'Vishnuvardhan', 'Sidharth Malhotra, Kiara Advani', 'The life of Indian army captain Vikram Batra, awarded with the Param Vir Chakra, Indias highest award for valour for his actions during the 1999 Kargil War.', 'hindi', 'action', 'UA');
insert into movies values (2, 'Bhuj', '6780', 'Abhishek Dudhaiya', 'Ajay Devgan, Nora Fatehi', 'Bhuj: The Pride of India is a 2021 Indian Hindi-language war film directed by Abhishek Dudhaiya. Set during the Indo-Pakistani War of 1971,', 'hindi', 'action', 'UA');

insert into venue values (1, 'PVR Cinemas', 'Elante Mall', 'cinema');
insert into venue values (2, 'Picadilly Cinemas', 'Sector-34', 'cinema');
insert into venue values (3, 'Fun Cinemas', 'Mani Majra', 'cinema');
insert into venue values (4, 'Wave Cinemas', 'Industrial Area', 'cinema');
insert into venue values (5, 'PCA Stadium', 'Mohali', 'stadium');

insert into venue_seat values(1, 1, 'A1', 'Silver');
insert into venue_seat values(2, 1, 'A2', 'Silver');
insert into venue_seat values(3, 1, 'A3', 'Silver');
insert into venue_seat values(4, 1, 'A4', 'Silver');
insert into venue_seat values(5, 1, 'A5', 'Silver');
insert into venue_seat values(6, 1, 'A6', 'Silver');

insert into venue_seat values(7, 1, 'G1', 'Diamond');
insert into venue_seat values(8, 1, 'G2', 'Diamond');
insert into venue_seat values(9, 1, 'G3', 'Diamond');
insert into venue_seat values(10, 1, 'G4', 'Diamond');
insert into venue_seat values(11, 1, 'G5', 'Diamond');
insert into venue_seat values(12, 1, 'G6', 'Diamond');

insert into venue_seat values(13, 1, 'P1', 'Platinum');
insert into venue_seat values(14, 1, 'P2', 'Platinum');
insert into venue_seat values(15, 1, 'P3', 'Platinum');
insert into venue_seat values(16, 1, 'P4', 'Platinum');
insert into venue_seat values(17, 1, 'P5', 'Platinum');
insert into venue_seat values(18, 1, 'P6', 'Platinum');


insert into venue_seat values(19, 3, 'A1', 'Silver');
insert into venue_seat values(20, 3, 'A2', 'Silver');
insert into venue_seat values(21, 3, 'A3', 'Silver');
insert into venue_seat values(22, 3, 'A4', 'Silver');
insert into venue_seat values(23, 3, 'A5', 'Silver');
insert into venue_seat values(24, 3, 'A6', 'Silver');

insert into venue_seat values(25, 3, 'K1', 'Gold');
insert into venue_seat values(26, 3, 'K2', 'Gold');
insert into venue_seat values(27, 3, 'K3', 'Gold');
insert into venue_seat values(28, 3, 'K4', 'Gold');
insert into venue_seat values(29, 3, 'K5', 'Gold');
insert into venue_seat values(30, 3, 'K6', 'Gold');


insert into venue_seat values(31, 5, 'A1', 'General');
insert into venue_seat values(32, 5, 'A2', 'General');
insert into venue_seat values(33, 5, 'A3', 'General');
insert into venue_seat values(34, 5, 'A4', 'General');
insert into venue_seat values(35, 5, 'A5', 'General');
insert into venue_seat values(36, 5, 'A6', 'General');

insert into venue_seat values(37, 5, 'G1', 'Balcony');
insert into venue_seat values(38, 5, 'G2', 'Balcony');
insert into venue_seat values(39, 5, 'G3', 'Balcony');
insert into venue_seat values(40, 5, 'G4', 'Balcony');
insert into venue_seat values(41, 5, 'G5', 'Balcony');
insert into venue_seat values(42, 5, 'G6', 'Balcony');

insert into venue_seat values(43, 5, 'P1', 'VIP');
insert into venue_seat values(44, 5, 'P2', 'VIP');
insert into venue_seat values(45, 5, 'P3', 'VIP');
insert into venue_seat values(46, 5, 'P4', 'VIP');
insert into venue_seat values(47, 5, 'P5', 'VIP');
insert into venue_seat values(48, 5, 'P6', 'VIP');

insert into show values (1, 1, to_timestamp('06-09-21 09:00:00', 'dd-mm-rr hh24:mi:ss'));
insert into show values (2, 1, to_timestamp('06-09-21 18:00:00', 'dd-mm-rr hh24:mi:ss'));
insert into show values (3, 1, to_timestamp('06-09-21 12:00:00', 'dd-mm-rr hh24:mi:ss'));
insert into show values (4, 1, to_timestamp('06-09-21 15:00:00', 'dd-mm-rr hh24:mi:ss'));
insert into show values (5, 3, to_timestamp('06-09-21 12:00:00', 'dd-mm-rr hh24:mi:ss'));
insert into show values (6, 3, to_timestamp('06-09-21 15:00:00', 'dd-mm-rr hh24:mi:ss'));
insert into show values (7, 3, to_timestamp('06-09-21 09:00:00', 'dd-mm-rr hh24:mi:ss'));
insert into show values (8, 3, to_timestamp('06-09-21 18:00:00', 'dd-mm-rr hh24:mi:ss'));

insert into show values (9, 5, to_timestamp('07-09-21 19:30:00', 'dd-mm-rr hh24:mi:ss'));
insert into show values (10, 5, to_timestamp('20-09-21 16:00:00', 'dd-mm-rr hh24:mi:ss'));

insert into show_movie values(1, 1);
insert into show_movie values(2, 1);
insert into show_movie values(3, 2);
insert into show_movie values(4, 2);
insert into show_movie values(5, 1);
insert into show_movie values(6, 1);
insert into show_movie values(7, 2);
insert into show_movie values(8, 2);

insert into show_event values(9, 1);
insert into show_event values(10, 2);


insert into show_seat values(1, 1, 150, 'available', 1);
insert into show_seat values(2, 1, 150, 'available', 2);
insert into show_seat values(3, 1, 150, 'available', 3);
insert into show_seat values(4, 1, 150, 'available', 4);
insert into show_seat values(5, 1, 150, 'available', 5);
insert into show_seat values(6, 1, 150, 'available', 6);
insert into show_seat values(7, 1, 180, 'available', 7);
insert into show_seat values(8, 1, 180, 'available', 8);
insert into show_seat values(9, 1, 180, 'available', 9);
insert into show_seat values(10, 1, 180, 'available', 10);
insert into show_seat values(11, 1, 180, 'available', 11);
insert into show_seat values(12, 1, 180, 'available', 12);
insert into show_seat values(13, 1, 200, 'available', 13);
insert into show_seat values(14, 1, 200, 'available', 14);
insert into show_seat values(15, 1, 200, 'available', 15);
insert into show_seat values(16, 1, 200, 'available', 16);
insert into show_seat values(17, 1, 200, 'available', 17);
insert into show_seat values(18, 1, 200, 'available', 18);





