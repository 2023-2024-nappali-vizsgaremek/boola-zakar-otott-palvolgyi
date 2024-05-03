create table category
(
    id   integer not null
        constraint category_pk
            primary key,
    name varchar
);

alter table category
    owner to fkpdquwvcbffxp;

INSERT INTO public.category (id, name) VALUES (1, 'Étkezés');
INSERT INTO public.category (id, name) VALUES (2, 'Utazás');
INSERT INTO public.category (id, name) VALUES (3, 'Közlekedés');
INSERT INTO public.category (id, name) VALUES (4, 'Szórakozás');
INSERT INTO public.category (id, name) VALUES (5, 'Egészség');
INSERT INTO public.category (id, name) VALUES (6, 'Vásárlás');
INSERT INTO public.category (id, name) VALUES (7, 'Szolgáltatások');
INSERT INTO public.category (id, name) VALUES (8, 'Számlák');
INSERT INTO public.category (id, name) VALUES (9, 'Élelmiszer');
INSERT INTO public.category (id, name) VALUES (10, 'Pénzügyek');
INSERT INTO public.category (id, name) VALUES (11, 'Egyéb');
