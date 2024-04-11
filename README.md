# java-filmorate
Template repository for Filmorate project.

БД состоит из нескольких таблиц, связанных между собой:
1. таблица хранения пользователей (users)
2. таблица хранения фильмов (films)
3. таблица друзей (user_friends)
4. таблица лайков к к фильмам (film_liked)
5. таблица хранения статусов пользователя (status)
6. таблица хранения жанров (genre)

Запросы к БД:

1. Получить все фильмы:

SELECT *
FROM films;

2. Получить фильм по id:

SELECT *
FROM films
WHERE film_id = id;

3. Получить определенное количество фильмов n:

SELECT *
FROM films
LIMIT n

4. Получить всех пользователей:

SELECT *
FROM users;

5. Получить пользователя по id:

SELECT *
FROM users
WHERE user_id = id;

6. Получить друзей определенного user-а по id:

SELECT *
FROM user_friends
WHERE user_id = id

7. Получить общих друзей пользователя user1 (id1) с user2 по id (id2):

SELECT uf.user_id,
       u.login
FROM user_friends AS uf
INNER JOIN users AS u ON u.user_id = uf.user_id
WHERE (uf.user_id) = id1 AND 
(uf.user_id) = id2 AND 
COUNT (user_id) > 1
GROUP BY uf.user_id

![прикладываю ER - диаграмму с таблицами БД](https://github.com/mitya5247/java-filmorate/blob/add-friends-likes/pictures/filmorate_diagram.png)
