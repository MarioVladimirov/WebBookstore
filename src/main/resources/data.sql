INSERT INTO roles (id, role)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, role)
VALUES (2, 'USER');

INSERT INTO users (id, first_name, last_name, username, password)
VALUES (1, 'Admin', 'Adminov', 'admin', '713ced98f52887220162f4a73fc4109ac9a76bb919a888ffb41fed4f922148b158f84bdef58778a3');
INSERT INTO users (id, first_name, last_name, username, password)
VALUES (2, 'User', 'Userov', 'user', '713ced98f52887220162f4a73fc4109ac9a76bb919a888ffb41fed4f922148b158f84bdef58778a3');

INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (1, 1);
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (1, 2);
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (2, 2);


INSERT INTO categories (id, category)
VALUES (1, 'ФЕНТЪЗИ');
INSERT INTO categories (id, category)
VALUES (2, 'ИСТОРИЯ');
INSERT INTO categories (id, category)
VALUES (3, 'ИЗКУСТВО');
INSERT INTO categories (id, category)
VALUES (4, 'ХОРЪР');
INSERT INTO categories (id, category)
VALUES (5, 'ПОЕЗИЯ');
INSERT INTO categories (id, category)
VALUES (6, 'ХУДОЖЕСТВЕНА_ЛИТЕРАТУРА');
INSERT INTO categories (id, category)
VALUES (7, 'КРИМИНАЛНИ_И_ТРИЛЪРИ');
INSERT INTO categories (id, category)
VALUES (8, 'РОМАНТИЧНИ');
INSERT INTO categories (id, category)
VALUES (9, 'НАУЧНА_ФАНТАТИКА');
INSERT INTO categories (id, category)
VALUES (10, 'НАУКА');
INSERT INTO categories (id, category)
VALUES (11, 'КЛАСИЧЕСКИ_РОМАНИ');
INSERT INTO categories (id, category)
VALUES (12, 'ХУМОР');
INSERT INTO categories (id, category)
VALUES (13, 'ДЕТСКА_ЛИТЕРАТУРА');


INSERT INTO authors (id, first_name, last_name, description, image_url)
VALUES (1, 'Джон Р. Р.', 'Толкин',
        'Джон Роналд Руел Толкин, CBE (на английски: John Ronald Reuel Tolkien) е британски писател, смятан за основател на жанра фентъзи с романа си „Хобитът“ (The Hobbit) и неговото продължение трилогията „Властелинът на пръстените“ (The Lord of the Rings).',
        'D:\Programming\SoftUni\WebBookstore\src\main\resources\static\images\author-pics\Tolkien.jpg');
INSERT INTO authors (id, first_name, last_name, description)
VALUES (2, 'Роджър', 'Зелазни',
        'Роджър Джоузеф Зелазни (на английски: Roger Joseph Zelazny) е американски автор на научна фантастика, фентъзи и стихове[1] роден на 13 май 1937 г. Юклид, Охайо, и починал 14 юни 1995 г. в Санта Фе, Ню Мексико.
Зелазни е една от най-значимите фигури в съвременния фентъзи жанр и научна фантастика.[2] В началото на 60-те години на XX век печели бърза слава на един от най-добрите представители на Новата вълна на фантастиката.[1] Носител е на наградите Небюла три пъти и Хюго шест пъти, включително за романите си Господарят на светлината (1968) и ...And Call Me Conrad (1966) (по-късно публикуван като This Immortal).
Писателят има изключителен талант в измислянето и изобразяването на светове с правдоподобни магически системи, сили и свръхестествени същества. Впечатляващите описания на детайли от приложната магия в измислените от него светове го отличават от останалите автори в областта. Научната фантастика на Зелазни е силно повлияна от митология и поезия, включително френската, британската и американската класика от късния 19 век и ранния 20 век. Честа тема са богове или хора, превърнали се в богове. Романите и късите разкази на Зелазни обичайно включват образи от класическата митология, вписани в модерния свят.');
INSERT INTO authors (id, first_name, last_name, description, image_url)
VALUES (3, 'Фьодор', 'Достоевски',
        'Фьодор Миха̀йлович Достоѐвски (на руски: Фёдор Миха́йлович Достое́вский, Loudspeaker.svg[ˈfʲodər mʲɪˈxajləvʲɪtɕ dəstɐˈjɛfskʲɪj]) е руски писател и публицист, най-известен със своите романи „Престъпление и наказание“, „Братя Карамазови“, „Идиот“ и „Бесове“.
Литературното творчество на Достоевски изследва човешката психика в тревожните политически, обществени и духовни условия на руското общество през XIX век. Като изявен славянофил, националист и монархист, в творбите си той критикува буржоазията, Запада в навечерието на епохата на материализма и нихилизма. Смятан от мнозина за основоположник или пряк предшественик на европейския екзистенциализъм, неговите „Записки от подземието“ са определяни като „най-доброто въведение в екзистенциализма, писано някога".[5] Достоевски започва да пише от средата на 40-те години на XIX век, като първите му произведения са повлияни от реалисти и романтици – Дикенс, Гогол, Балзак и др. Въпреки това най-известни са творбите от последните му години – „Престъпление и наказание“, „Идиот“ и „Братя Карамазови“. Достоевски често е определян и като един от най-значимите психолози в историята на световната литература.[6] Написва общо 11 романа, 3 новели, 17 разказа и 3 есета.',
        'D:\Programming\SoftUni\WebBookstore\src\main\resources\static\images\author-pics\Dostoevsky.jpg');
INSERT INTO authors (id, first_name, last_name, description, image_url)
VALUES (4, 'Айзък', 'Азимов',
        'Айзък Азимов (на английски: Isaac Asimov, рождено име – Исаак Юдович Азимов) е американски писател на фантастични и научно-популярни творби и биохимик от руско-еврейски произход.
Азимов написва или редактира над 500 тома, а общият брой на написаните от него писма и пощенски картички е оценен на около 90 000. Негови творби има в девет от десетте категории в Десетичната система на Дюи – всички без „Философия“.[1] Азимов е смятан за един от майсторите в жанра научна фантастика и заедно с Робърт Хайнлайн и Артър Кларк е считан за един от „големите трима“ писатели-фантасти на времето си.[2]
Вероятно най-известната творба на Азимов е поредицата за „Фондацията“; неговите други главни поредици са тези за Галактическата империя и за роботите, които по-късно също обвързва с Фондацията. Неговото творчество е удостоено с пет награди „Хюго“ (1963, 1966, 1973, 1977, 1983 г.), две награди „Небюла“ (1972, 1976 г.) и други.
Азимов е дългогодишен, но неохотен член на Менса; описва ги като „интелектуално войнствени“. Повече удоволствие му доставя да бъде президент на Американската асоциация на хуманистите.
На негово име е наречен астероидът 5020 Азимов, две различни награди „Айзък Азимов“ и списанието „Asimov''s Science Fiction“.',
        'D:\Programming\SoftUni\WebBookstore\src\main\resources\static\images\author-pics\Asimov.jpg');
INSERT INTO authors (id, first_name, last_name, description)
VALUES (5, 'Фредрик', 'Бакман',
        'Фредрик Бакман (на шведски: Fredrik Backman) е шведски журналист, блогър и писател, автор на бестселъри в жанровете съвременен роман и сатира.');



INSERT INTO books (id, isbn, title, description, image_url, pages_count, copies, release_year, price, language,
                   publishing_house,
                   author_id, creator_id)
VALUES (1, '9547394169', 'Братя Карамазови',
        'Шедьоврите на световната литература са кардиналите, които крепят огромното и невидимо мироздание на човешкия дух. Във всяко време, когато са поставени на изпитание нравствените устои на отделната личност или цели нации, великата творба е като спасителна слънчева стълба в бездната на отчаянието, хаоса и неверието. Романът "Братя Карамазови" е един от най-ярките в творчеството на Фьодор Достоевски и заема своето достойно място в така нареченото Петокнижие на автора, редом с "Идиот", "Престъпление и наказание", "Бесове"... В него характерната за Достоевски тема за страданието и просветлението е особено мащабно застъпена, а образите, които великият майстор е изваял с перото си, са се превърнали в емблематични за всяко поколение.',
        'D:\Programming\SoftUni\WebBookstore\src\main\resources\static\images\book-cover-pics\default-book-cover.jpg',
        934, 3, 2003, 30.00, 'БЪЛГАРСКИ', 'ЗАХАРИЙ_СТОЯНОВ', 3, 1);
INSERT INTO books (id, isbn, title, description, image_url, pages_count, copies, release_year, price, language,
                   publishing_house,
                   author_id, creator_id)
VALUES (2, '9789545841958', 'Хрониките на Амбър 1',
        '“Хрониките на Амбър” са си спечелили заслужено място като класика на всички времена сред шедьоврите на творческото въображение.
Амбър е единственият истински свят, който се отразява в безброй огледални светове Сенки, подвластни на Принцовете на Амбър. Кралското семейство е разединено от завист и подозрения. Изчезването на родоначалника Оберон е засилило вътрешните конфликти и тронът е уязвим за узурпатори. Станете свидетели на титаничната битка за надмощие на Земята и в Царството на Хаоса, където силите на Амбър и Хаоса водят непрестанна борба за власт посредством заговори и смели ходове.',
        'D:\Programming\SoftUni\WebBookstore\src\main\resources\static\images\book-cover-pics\hronikite-na-ambar-1.jpg',
        576, 5, 2014, 29.99, 'БЪЛГАРСКИ', 'БАРД', 2, 1);
INSERT INTO books (id, isbn, title, image_url, pages_count, copies, release_year, price, language,
                   publishing_house,
                   author_id, creator_id)
VALUES (3, '9549513971', 'Събрани разкази; т.5',
        'D:\Programming\SoftUni\WebBookstore\src\main\resources\static\images\book-cover-pics\sybrani-razkazi-5.jpg',
        288, 2, 2001, 7.00, 'БЪЛГАРСКИ', 'АБАГАР_ХОЛДИНГ', 4, 1);
INSERT INTO books (id, isbn, title, description, image_url, pages_count, copies, release_year, price, language,
                   publishing_house,
                   author_id, creator_id)
VALUES (4, '9789545841705', 'Властелинът на Пръстените',
        'Не е възможно да се предадат на посягащия за пръв път към тази книга всичките й достойнства, нейния мащаб и великолепие.
Криволичещ от епичното до комичното, от пасторалното до диаболичното, сюжетът пресъздава по възхитителен начин герои и сцени в един изцяло измислен, но напълно правдоподобен свят на джуджета, елфи и хора.
Пред вас е пълното издание на един невероятен роман, който не ще ви даде миг покой до последната страница… и дълги години след това.',
        'D:\Programming\SoftUni\WebBookstore\src\main\resources\static\images\book-cover-pics\vlastelinyt.jpg',
        1088, 4, 2001, 46.99, 'БЪЛГАРСКИ', 'БАРД', 1, 1);
INSERT INTO books (id, isbn, title, description, image_url, pages_count, copies, release_year, price, language,
                   publishing_house,
                   author_id, creator_id)
VALUES (5, '978954091017Х', 'Престъпление и наказание',
        'Шедьоврите на световната литература са кариатидите, които крепят огромното и невидимото мироздание на човешкия дух. Във всяко време, когато са поставени на изпитание нравствените устои на отделната личност или цели нации, великата творба е като спасителна слънчева стълба в бездната на отчаянието, хаоса и неверието.
Поредицата „Шедьовър" представя на българския читател образци на световната литература. В този том, драги читателю, ти предстои среща с един от великите романи на Достоевски - „Престъпление и наказание". Всичко ли е позволено на човека, мъртъв ли е неговият вътрешен бог? Всяко престъпление е човешко, а наказанието следва невидимите проявления на божествената воля, която спасява човешкото у човека.',
        'D:\Programming\SoftUni\WebBookstore\src\main\resources\static\images\book-cover-pics\default-book-cover.jpg',
        512, 1, 2011, 30.00, 'БЪЛГАРСКИ', 'ЗАХАРИЙ_СТОЯНОВ', 3, 1);
INSERT INTO books (id, isbn, title, description, image_url, pages_count, copies, release_year, price, language,
                   publishing_house,
                   author_id, creator_id)
VALUES (6, '9789542815075', 'Човек на име Уве',
        'Уве е на 59, заклет почитател и притежател на сааб, вдовец. Тъгата по любимата му Соня го кара всеки ден да пожелава смъртта си.
Вечно недоволен и гневен, наричат го изпълнения с горчивина кошмарен съсед, лесноизбухлив. Понякога Уве се пита защо наричат даден човек кисел, след като не обикаля улиците с фалшива усмивка. Той е темерут – от хората, които сочат с пръст онези, които не одобряват, и се отнасят към тях като към крадци, хванати пред прозореца. Но за него да спаси човешки живот е дреболия. Има железни принципи и е постигнал непоклатима рутина в действията си. Защо ли започва да ги нарушава?

Една сутрин бъбрива млада двойка с две весели дъщерички се нанасят в съседна къща и случайно смачкват пощенската му кутия. Случката води до комичния и трогателен разказ за безпризорна котка, неочаквано приятелство и древното изкуство да дадеш на заден с ремарке. Всичко, което се случва, ще промени киселия старец и ще разтърси до основи кварталното сдружение на обитателите.',
        'D:\Programming\SoftUni\WebBookstore\src\main\resources\static\images\book-cover-pics\uwe.jpg',
        294, 5, 2014, 14.00, 'БЪЛГАРСКИ', 'СИЕЛА', 5, 1);


INSERT INTO books_categories (book_entity_id, categories_id)
VALUES (1, 6);
INSERT INTO books_categories (book_entity_id, categories_id)
VALUES (1, 11);
INSERT INTO books_categories (book_entity_id, categories_id)
VALUES (2, 2);
INSERT INTO books_categories (book_entity_id, categories_id)
VALUES (2, 6);
INSERT INTO books_categories (book_entity_id, categories_id)
VALUES (3, 6);
INSERT INTO books_categories (book_entity_id, categories_id)
VALUES (3, 9);
INSERT INTO books_categories (book_entity_id, categories_id)
VALUES (4, 2);
INSERT INTO books_categories (book_entity_id, categories_id)
VALUES (4, 6);
INSERT INTO books_categories (book_entity_id, categories_id)
VALUES (5, 6);
INSERT INTO books_categories (book_entity_id, categories_id)
VALUES (5, 11);
INSERT INTO books_categories (book_entity_id, categories_id)
VALUES (6, 3);
INSERT INTO books_categories (book_entity_id, categories_id)
VALUES (6, 6);