create table expense
(
    id          uuid    not null
        constraint expense_pk
            primary key,
    title       varchar not null,
    category    integer
        constraint expense_category_fk
            references category
            on update cascade,
    exceptstats boolean not null,
    tags        varchar,
    notes       varchar,
    status      boolean,
    date        varchar,
    payeeid     integer
        constraint expense_partner_fk
            references partner
            on update cascade on delete set null,
    amount      double precision,
    listid      uuid
        constraint expense_expenselist_fk
            references expenselist
            on update cascade on delete cascade
);

alter table expense
    owner to fkpdquwvcbffxp;

INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('0c6764e4-a258-4d36-8f31-7269189a1da6', 'pls', 4, false, 'no', 'y', true, '2024-04-02 +00', 1, 10, '273d2a1e-5d1a-4f42-9a9b-d8081d271db0');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('5ac0bf22-25a3-4766-b1b2-1b7dbd8522ae', 'másik', 5, true, 'test', 'o', true, '2024-04-17 +00', 40, 210, '273d2a1e-5d1a-4f42-9a9b-d8081d271db0');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('5f6310ec-89a5-4d5a-93cc-d491ebea3eb4', 'műfasz', 1, true, 'nyaminyami', 'szeretek műfaszokta enni hami hami', true, '2024-04-29 +00', 49, 9999999999, '7f24e20d-3df5-4977-97e9-9a2953962a9b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('c61b3955-ae46-47c0-8637-87a500150051', 'anyád', 4, false, 'dum dum dum', 'anyadat is meg makkolom dzsá dzsá csibidi bum bum pám pám', false, '2024-04-29 +00', 50, 10, '7f24e20d-3df5-4977-97e9-9a2953962a9b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('50e8351d-3f31-4a38-b159-af83d4647338', 'anyád', 4, false, 'dum dum dum', 'anyadat is meg makkolom dzsá dzsá csibidi bum bum pám pám', false, '2024-04-29 +00', 50, 10, '7f24e20d-3df5-4977-97e9-9a2953962a9b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('5008c097-c07a-4705-aa92-4d06afbc0270', 'anyád', 4, false, 'dum dum dum', 'anyadat is meg makkolom dzsá dzsá csibidi bum bum pám pám', true, '2024-04-29 +00', 50, 10, '7f24e20d-3df5-4977-97e9-9a2953962a9b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('dae216f2-63f8-43ac-b0d3-920d93a97041', 'anyád', 4, false, 'dum dum dum', 'anyadat is meg makkolom dzsá dzsá csibidi bum bum pám pám', true, '2024-04-29 +00', 50, 10, '7f24e20d-3df5-4977-97e9-9a2953962a9b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('3bb571fb-c030-4bed-a834-65708bae76d5', 'anyád', 4, false, 'dum dum dum', 'anyadat is meg makkolom dzsá dzsá csibidi bum bum pám pám', true, '2024-04-29 +00', 50, 10, '7f24e20d-3df5-4977-97e9-9a2953962a9b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('418323bf-ba3a-483c-9f86-bf325148d63d', 'asdadad', 3, false, '20202', 'sadasd', false, '2024-04-29 +00', 35, 1000, '3c42a122-7db3-46e2-91fc-5edeadba7a3d');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('64e59a9e-98d8-4e2f-90d6-57007f9a38e0', 'asdadad', 3, false, '20202', 'sadasd', false, '2024-04-29 +00', 35, 1000, '3c42a122-7db3-46e2-91fc-5edeadba7a3d');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('7bd60520-882d-4630-980b-9ad14fdc3a26', 'asdadad', 3, false, '20202', 'sadasd', false, '2024-04-29 +00', 35, 1000, '3c42a122-7db3-46e2-91fc-5edeadba7a3d');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('ea942503-fbf9-4882-8d96-8f632b74f312', 'asdadad', 3, false, '20202', 'sadasd', false, '2024-04-29 +00', 35, 1000, '3c42a122-7db3-46e2-91fc-5edeadba7a3d');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('b6341d4f-9462-41bd-97a9-3541bb91eea4', 'asdadad', 3, false, '20202', 'sadasd', false, '2024-04-29 +00', 35, 1000, '3c42a122-7db3-46e2-91fc-5edeadba7a3d');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('555c5ae1-33ee-4fa8-af20-128f2cc8007f', 'asdasdad', 6, false, 'sadasda', 'asdadsads', false, '2024-05-01 +00', 52, 10000, 'd43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('7b7a7c2d-373d-4ffb-b4d7-7ef3f41f4253', 'asdasdad', 6, false, 'sadasda', 'asdadsads', false, '2024-05-01 +00', 52, 10000, 'd43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('95fd0373-79a2-487f-b998-d698af4d6025', 'asdadad', 1, false, 'sdsadad', 'asdasda', false, '2024-04-25 +00', 53, 1000, 'd43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('95c0b876-b7ce-4364-892f-4afb584bf05c', 'asdadad', 1, false, 'sdsadad', 'asdasda', false, '2024-04-25 +00', 53, 1000, 'd43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('3f5a2333-3155-42dc-b906-3034caa913e9', 'asdadad', 1, false, 'sdsadad', 'asdasda', false, '2024-04-25 +00', 53, 1000, 'd43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('5913a8ee-2ff3-4c74-ab5b-247db38bcc17', 'asdadad', 1, false, 'sdsadad', 'asdasda', false, '2024-04-25 +00', 53, 1000, 'd43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('70f8b5a9-5f08-4cc1-9766-eba67f85f484', 'asdadad', 1, false, 'sdsadad', 'asdasda', false, '2024-04-25 +00', 53, 1000, 'd43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('9886b99c-c187-484b-a88a-7fee472c43e0', 'asdadad', 1, false, 'sdsadad', 'asdasda', false, '2024-04-25 +00', 53, 1000, 'd43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('b3c9cee3-a4e3-4587-928a-ba529936e187', 'asdadad', 1, false, 'sdsadad', 'asdasda', false, '2024-04-25 +00', 53, 1000, 'd43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('6c433086-8d04-4739-a29a-f88f01b08211', 'asdadad', 1, false, 'sdsadad', 'asdasda', false, '2024-04-25 +00', 53, 1000, 'd43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b');
INSERT INTO public.expense (id, title, category, exceptstats, tags, notes, status, date, payeeid, amount, listid) VALUES ('c81319e6-b9e1-45f5-a876-d15397df228e', 'asdadad', 1, false, 'sdsadad', 'asdasda', false, '2024-04-25 +00', 53, 1000, 'd43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b');
