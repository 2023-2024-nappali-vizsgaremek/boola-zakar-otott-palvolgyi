create table profile
(
    id            uuid    not null
        constraint profile_pk
            primary key,
    name          varchar,
    isbusiness    boolean not null,
    expenselistid uuid
        constraint profile_expenselist_fk
            references expenselist
            on update cascade on delete set null,
    languagecode  varchar not null
        constraint profile_language_fk
            references language
            on update cascade on delete set default,
    accountemail  varchar
        constraint profile_account_fk
            references account
            on update cascade
);

alter table profile
    owner to fkpdquwvcbffxp;

create index profile_name_idx
    on profile (name);

INSERT INTO public.profile (id, name, isbusiness, expenselistid, languagecode, accountemail) VALUES ('1186c620-7b0d-43d8-b120-319084b634c1', 'Patrik', true, '24166bce-d064-4719-9f1b-18642c0ad5af', 'hu-HU', 'patrik67895@gmail.com');
INSERT INTO public.profile (id, name, isbusiness, expenselistid, languagecode, accountemail) VALUES ('92af291c-e9c4-4a73-95ec-a6ef5419f794', 'dqwd', false, '717727ae-0084-4b2f-925e-f7095a2fc310', 'hu-HU', 'szt2zakbal@vasvari.org');
INSERT INTO public.profile (id, name, isbusiness, expenselistid, languagecode, accountemail) VALUES ('30342d0a-edcb-4d27-9866-9b1c3d80d5f2', 'ycyxc', false, '8107d774-425d-4357-a840-3d999df0b04c', 'hu-HU', 'szt2zakbal@vasvari.org');
INSERT INTO public.profile (id, name, isbusiness, expenselistid, languagecode, accountemail) VALUES ('1cffda14-0f4f-4025-8f5e-b8593a1bd5c0', 'íyxíyx', false, 'e50cb980-56cc-4a2a-853f-3b3b0cddf4ac', 'hu-HU', 'szt2zakbal@vasvari.org');
INSERT INTO public.profile (id, name, isbusiness, expenselistid, languagecode, accountemail) VALUES ('d0ffb920-0ea1-4267-98c9-ce0b1079ba58', 'egy', true, '273d2a1e-5d1a-4f42-9a9b-d8081d271db0', 'jp-JP', 'otottkovi@hotmail.com');
INSERT INTO public.profile (id, name, isbusiness, expenselistid, languagecode, accountemail) VALUES ('3d079a64-d5fb-4b2c-b47e-8f78a7946544', 'Patrik', false, '3c42a122-7db3-46e2-91fc-5edeadba7a3d', 'hu-HU', 'admin@gmail.com');
INSERT INTO public.profile (id, name, isbusiness, expenselistid, languagecode, accountemail) VALUES ('d5c0a6d6-5e7d-4b14-ba20-72f8af8c7b14', 'Patrik', true, 'd43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b', 'hu-HU', 'admin@gmail.com');
INSERT INTO public.profile (id, name, isbusiness, expenselistid, languagecode, accountemail) VALUES ('923c15bc-d092-4701-8528-a302766f4881', 'brou', false, '7f24e20d-3df5-4977-97e9-9a2953962a9b', 'hu-HU', 'admin@gmail.com');
INSERT INTO public.profile (id, name, isbusiness, expenselistid, languagecode, accountemail) VALUES ('e6e8e22f-e4cb-4bbe-a0c1-796a662ac082', 'Japán', false, 'fe8ee88e-36cf-4544-9c68-6d7de5f90e2f', 'hu-HU', 'admin@gmail.com');
INSERT INTO public.profile (id, name, isbusiness, expenselistid, languagecode, accountemail) VALUES ('ff81aa36-4485-469f-996d-9b2767f3c809', 'asd', true, '26405b21-477a-44d1-9122-271626663704', 'hu-HU', 'asd@gmail.com');
INSERT INTO public.profile (id, name, isbusiness, expenselistid, languagecode, accountemail) VALUES ('12975a64-8ab9-4637-a0bb-38fd90f130f7', 'Patrik', false, '762ea073-28bd-4064-93e4-cdd1e9d12bd6', 'hu-HU', 'admin@gmail.com');
INSERT INTO public.profile (id, name, isbusiness, expenselistid, languagecode, accountemail) VALUES ('a78bd733-c169-4cb5-bd5c-d220f935f518', 'EUR4', true, 'e89afafc-4360-4d95-990b-40dc1b34f736', 'en-US', 'otottkovi@hotmail.com');
