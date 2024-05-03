create table currency
(
    code varchar not null
        constraint currency_pk
            primary key,
    name varchar
);

alter table currency
    owner to fkpdquwvcbffxp;

INSERT INTO public.currency (code, name) VALUES ('AUD', 'Ausztrál dollár');
INSERT INTO public.currency (code, name) VALUES ('CAD', 'Kanadai dollár');
INSERT INTO public.currency (code, name) VALUES ('CHF', 'Svájci frank');
INSERT INTO public.currency (code, name) VALUES ('CNY', 'Kínai jüan');
INSERT INTO public.currency (code, name) VALUES ('DKK', 'Dáni frank');
INSERT INTO public.currency (code, name) VALUES ('GBP', 'Brit font');
INSERT INTO public.currency (code, name) VALUES ('HKD', 'Hongkongi dollár');
INSERT INTO public.currency (code, name) VALUES ('HUF', 'Magyar forint');
INSERT INTO public.currency (code, name) VALUES ('JPY', 'Japán jen');
INSERT INTO public.currency (code, name) VALUES ('NZD', 'Új-zélandi dollár');
INSERT INTO public.currency (code, name) VALUES ('USD', 'Amerikai dollár');
INSERT INTO public.currency (code, name) VALUES ('EUR', 'Euró');
INSERT INTO public.currency (code, name) VALUES ('ZAR', 'Dél-afrikai rand');
