create table expenselist
(
    id           uuid             not null
        constraint expenselist_pk
            primary key,
    balance      double precision not null,
    currencycode varchar          not null
        constraint expenselist_currency_fk
            references currency
            on update cascade on delete restrict
);

alter table expenselist
    owner to fkpdquwvcbffxp;

create index expenselist_balance_idx
    on expenselist (balance);

INSERT INTO public.expenselist (id, balance, currencycode) VALUES ('3c42a122-7db3-46e2-91fc-5edeadba7a3d', 93000, 'HUF');
INSERT INTO public.expenselist (id, balance, currencycode) VALUES ('24166bce-d064-4719-9f1b-18642c0ad5af', 0, 'HUF');
INSERT INTO public.expenselist (id, balance, currencycode) VALUES ('717727ae-0084-4b2f-925e-f7095a2fc310', 0, 'HUF');
INSERT INTO public.expenselist (id, balance, currencycode) VALUES ('8107d774-425d-4357-a840-3d999df0b04c', 0, 'HUF');
INSERT INTO public.expenselist (id, balance, currencycode) VALUES ('e50cb980-56cc-4a2a-853f-3b3b0cddf4ac', 0, 'HUF');
INSERT INTO public.expenselist (id, balance, currencycode) VALUES ('d43b2f2b-bd1d-40b8-a86a-cb4f80c8d47b', 860000, 'HUF');
INSERT INTO public.expenselist (id, balance, currencycode) VALUES ('26405b21-477a-44d1-9122-271626663704', 0, 'HUF');
INSERT INTO public.expenselist (id, balance, currencycode) VALUES ('762ea073-28bd-4064-93e4-cdd1e9d12bd6', 0, 'HUF');
INSERT INTO public.expenselist (id, balance, currencycode) VALUES ('e89afafc-4360-4d95-990b-40dc1b34f736', 0, 'HUF');
INSERT INTO public.expenselist (id, balance, currencycode) VALUES ('273d2a1e-5d1a-4f42-9a9b-d8081d271db0', 9640, 'HUF');
INSERT INTO public.expenselist (id, balance, currencycode) VALUES ('fe8ee88e-36cf-4544-9c68-6d7de5f90e2f', 0, 'HUF');
INSERT INTO public.expenselist (id, balance, currencycode) VALUES ('7f24e20d-3df5-4977-97e9-9a2953962a9b', 999999990010000000, 'HUF');
