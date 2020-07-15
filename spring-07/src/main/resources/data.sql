insert into author (`id`, `name`) values (1, 'Роджер Желязны'),(2, 'Роберт Хайнлайн');
insert into genre (`id`, `name`) values (1, 'Зарубежная фантастика'),(2, 'Фентези'),(3,'Научная фантастика');

insert into `comments` (`id`, `book`, `content`)
            values (1, 1,'Прикольная книга')
                 ,(2, 2,'На подумать')
                 ,(3, 2,'Много букаф. Неасилил')
                 ,(4, 3,'На разок');

insert into book (`id`,`author`, `genre`,`name`,`year`,`description`)
          values (1, 1, 2, 'Девять принцев Амбера','1970','Первая книга из первой пенталогии цикла романов «Хроники Амбера»')
                ,(2, 1, 1, 'Князь Света','1967','После гибели Земли экипаж космического корабля «Звезда Индии» основывает колонию на одной из планет в другой звёздной системе. Победив почти всех исконных обитателей планеты, экипаж корабля строит для себя город на одном из полюсов планеты.')
                ,(3, 2, 3, 'Звёздный десант','1960','Действие романа происходит в будущем, примерно через 700 лет от сегодняшнего дня, когда земляне уже начали осваивать космические пространства')
                ,(4, 2, 1, 'Чужак в чужой стране','1987','Валентайн Майкл Смит родился на Марсе в команде злополучного корабля «Посланец» примерно через восемь лет после основания поселения на Луне. Не выдержав 480-дневного ожидания старта на Марсе, члены его команды умерли или покончили жизнь самоубийством, но новорождённый Майк был подобран марсианами.');

