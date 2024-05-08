
drop table if exists user_friend, users, films, genres, genre_films, mpa, film_liked, mpa_films cascade;

create table if not exists users(
    user_id long GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email varchar(64) not null,
    login varchar(30) not null,
    name varchar(60) not null,
    birthday date
);

create table if not exists mpa(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(30) not null unique
);

create table if not exists films(
    film_id long GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(30),
    description varchar(200),
    release_date date,
    duration int,
    mpa int references mpa(id)
);

create table if not exists film_liked(
    films_id long references films (film_id),
    users_id long references users (user_id)
);

create table if not exists user_friend(
    user_id long references users (user_id),
    friend_id long references users (user_id)
);


create table if not exists genres(
    genre_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY references genres(genre_id),
    name varchar(30)
);

create table if not exists genre_films(
    film_id int references films(film_id),
    genre_id int
);

create table if not exists mpa_films(
    film_id int references films(film_id),
    mpa_id int
);

