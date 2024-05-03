create table account
(
    email        varchar not null
        constraint account_pk
            primary key,
    passwordhash varchar not null,
    name         varchar,
    salt         varchar
);

alter table account
    owner to fkpdquwvcbffxp;

create index account_name_idx
    on account (name);

INSERT INTO public.account (email, passwordhash, name, salt) VALUES ('szt2zakbal@vasvari.org', '$2a$06$bkeZWRPDHiTsKQnTHBflN.rT3Ssnfvx5TuIz3eOeHD9xeVpFeyP8q', 'asd', 'vha4E&En1*U$8g<');
INSERT INTO public.account (email, passwordhash, name, salt) VALUES ('patrik67895@gmail.com', '$2a$06$bEvzMz3RST6CCFqBE//RKuYU3c7FXgB29fWQQjs3Ku/PJnx5eZxXq', 'Patrik', 'tlu;^SQ_{S3');
INSERT INTO public.account (email, passwordhash, name, salt) VALUES ('otottkovi@hotmail.com', '$2a$06$PTj0K1H6EUiYDArVIEHoDuO6//f/woFIBSf6w7tJHz3MEYLVH6WoO', 'Imot', 'EYv3r|i+W(bj');
INSERT INTO public.account (email, passwordhash, name, salt) VALUES ('patrik6666@gmail.com', '$2a$06$NeCBDF3UB0OEQ1TcKAmRdOU9QO2YCWhZbtyFmS8s6bVqqysxeLoY6', 'Patrik', '>~VdKu^0*}');
INSERT INTO public.account (email, passwordhash, name, salt) VALUES ('admin@gmail.com', '$2a$06$ciOrPEL2ZiuaB128LBKKSeC793zfJDey1KShlndX7TwA1jPh0USHu', 'Patrik', 'zD-DcxnL~>43R');
INSERT INTO public.account (email, passwordhash, name, salt) VALUES ('asd@gmail.com', '$2a$06$ZDTMSiedUPui.kC5Gvnb.eHcWt7dehDwyO4pMiwrsLl2GtU5AlTTq', 'asd', 'lUNRHY$a;#]');
