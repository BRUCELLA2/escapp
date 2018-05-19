
CREATE SEQUENCE public.site_id_seq;

CREATE TABLE public.site (
                id INTEGER NOT NULL DEFAULT nextval('public.site_id_seq'),
                name VARCHAR(100) NOT NULL,
                department VARCHAR(3) NOT NULL,
                municipality VARCHAR(100) NOT NULL,
                description VARCHAR(10000),
                CONSTRAINT site_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.site_id_seq OWNED BY public.site.id;

CREATE SEQUENCE public.sector_id_seq;

CREATE TABLE public.sector (
                id INTEGER NOT NULL DEFAULT nextval('public.sector_id_seq'),
                name VARCHAR(100) NOT NULL,
                description VARCHAR(10000),
                site_id INTEGER NOT NULL,
                CONSTRAINT sector_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.sector_id_seq OWNED BY public.sector.id;

CREATE SEQUENCE public.route_id_seq;

CREATE TABLE public.route (
                id INTEGER NOT NULL DEFAULT nextval('public.route_id_seq'),
                sector_id INTEGER NOT NULL,
                name VARCHAR(100) NOT NULL,
                grade VARCHAR(2) NOT NULL,
                points_nb INTEGER NOT NULL,
                description VARCHAR(10000),
                CONSTRAINT route_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.route_id_seq OWNED BY public.route.id;

CREATE SEQUENCE public.length_id_seq;

CREATE TABLE public.length (
                id INTEGER NOT NULL DEFAULT nextval('public.length_id_seq'),
                length INTEGER NOT NULL,
                grade VARCHAR(2) NOT NULL,
                points_nb INTEGER NOT NULL,
                description VARCHAR(10000),
                route_id INTEGER NOT NULL,
                CONSTRAINT length_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.length_id_seq OWNED BY public.length.id;

CREATE SEQUENCE public.escapp_user_id_seq;

CREATE TABLE public.escapp_user (
                id INTEGER NOT NULL DEFAULT nextval('public.escapp_user_id_seq'),
                login VARCHAR(30) NOT NULL,
                email VARCHAR(100) NOT NULL,
                password VARCHAR(60) NOT NULL,
                CONSTRAINT escapp_user_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.escapp_user_id_seq OWNED BY public.escapp_user.id;

CREATE TABLE public.role_user (
                role VARCHAR NOT NULL,
                escapp_user INTEGER NOT NULL,
                CONSTRAINT role_user_pk PRIMARY KEY (role, escapp_user)
);

CREATE SEQUENCE public.comment_id_seq;

CREATE TABLE public.comment (
                id INTEGER NOT NULL DEFAULT nextval('public.comment_id_seq'),
                text VARCHAR(1000) NOT NULL,
                target_type VARCHAR NOT NULL,
                id_comment_target INTEGER NOT NULL,
                escapp_user INTEGER NOT NULL,
                CONSTRAINT comment_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;

CREATE SEQUENCE public.topo_id_seq;

CREATE TABLE public.topo (
                id INTEGER NOT NULL DEFAULT nextval('public.topo_id_seq'),
                name VARCHAR(100) NOT NULL,
                department VARCHAR(3) NOT NULL,
                is_borrowable BOOLEAN NOT NULL,
                pdf_file_name VARCHAR(200) NOT NULL,
                municipality VARCHAR(100),
                end_date_borrow TIMESTAMP,
                borrower_id INTEGER,
                description VARCHAR(10000),
                owner_id INTEGER NOT NULL,
                CONSTRAINT topo_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.topo_id_seq OWNED BY public.topo.id;

ALTER TABLE public.sector ADD CONSTRAINT site_sector_fk
FOREIGN KEY (site_id)
REFERENCES public.site (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.route ADD CONSTRAINT sector_route_fk
FOREIGN KEY (sector_id)
REFERENCES public.sector (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.length ADD CONSTRAINT route_length_fk
FOREIGN KEY (route_id)
REFERENCES public.route (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT escapp_user_topo_fk
FOREIGN KEY (owner_id)
REFERENCES public.escapp_user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT escapp_user_topo_fk1
FOREIGN KEY (borrower_id)
REFERENCES public.escapp_user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.comment ADD CONSTRAINT escapp_user_commentaire_fk
FOREIGN KEY (escapp_user)
REFERENCES public.escapp_user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.role_user ADD CONSTRAINT escapp_user_role_user_fk
FOREIGN KEY (escapp_user)
REFERENCES public.escapp_user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;