create table language
(
    code varchar not null
        constraint language_pk
            primary key,
    name varchar
);

alter table language
    owner to fkpdquwvcbffxp;

INSERT INTO public.language (code, name) VALUES ('en-GB', 'English (United Kingdom)');
INSERT INTO public.language (code, name) VALUES ('en-US', 'English (United States)');
INSERT INTO public.language (code, name) VALUES ('jp-JP', '日本語 (Japan)');
INSERT INTO public.language (code, name) VALUES ('fr-FR', 'Français (France)');
INSERT INTO public.language (code, name) VALUES ('hu-HU', 'Magyar');
